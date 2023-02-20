package com.example.nfc_scanner_m2iwocs;

import static android.app.PendingIntent.FLAG_MUTABLE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.FormatException;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.nfc_scanner_m2iwocs.databinding.ActivityMainBinding;

import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.NdefMessage;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.EditText;

import android.app.PendingIntent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private PendingIntent pendingIntent;
    private DBOperator DBoperator;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        textView = findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        if (Build.VERSION.SDK_INT >= 5) {
            pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), FLAG_MUTABLE);
        } else {
            pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        }
        //pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), FLAG_MUTABLE);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        DBoperator= new DBOperator(this.getBaseContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    public boolean contains(String[] values, String value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String id = bytesToHex(tag.getId());
            // person doesn't exist in the database create a new user by asking them to enter name and surname
            Person user_card=DBoperator.getPersonById(id);
            if(user_card==null){
                final EditText nameEditText = new EditText(MainActivity.this);
                nameEditText.setHint("Enter your name");
                final EditText surnameEditText = new EditText(MainActivity.this);
                surnameEditText.setHint("Enter your surname");

                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(nameEditText);
                layout.addView(surnameEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Welcome")
                        .setView(layout)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String name = nameEditText.getText().toString();
                                String surname = surnameEditText.getText().toString();
                                if(name.isEmpty() || surname.isEmpty()){
                                    Toast.makeText(MainActivity.this, "Please enter your name and surname", Toast.LENGTH_SHORT).show();
                                }else{
                                    DBoperator.addNewPerson(surname,name,id);
                                }
                            }
                        }).show();
            }else{
                // user exist , just display their name
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("User found in the database")
                        .setMessage("User is :"+user_card.personName())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing as we just print name
                            }
                        }).show();
            }
        }
    }
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}

