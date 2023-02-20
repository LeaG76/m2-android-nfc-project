package com.example.nfc_scanner_m2iwocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOperator extends SQLiteOpenHelper{
    // constants
    private static final String DATABASE_NAME="person.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "person";
    private static final String ID_COL = "id";
    private static final String CARD_ID="card_id";
    private static final String SURNAME_COL = "surname";
    private static final String LAST_NAME_COL = "last_name";
    // constructor
    public DBOperator(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }
    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE  IF NOT EXISTS "+TABLE_NAME+" ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CARD_ID + " TEXT UNIQUE,"
                + SURNAME_COL + " TEXT,"
                + LAST_NAME_COL + " ,TEXT )";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }
    public Person getPersonById(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        String[] columns={CARD_ID,SURNAME_COL,LAST_NAME_COL};
        // where clause to get the card id
        String whereClause=CARD_ID+"= ?";
        String[] WhereArgsClause={(id)};
        Cursor cursor = db.query(TABLE_NAME,columns,whereClause,WhereArgsClause,null,null,null);
        // we found one
        if(cursor.getCount()>=1){
            for (int i = 0; i <cursor.getColumnNames().length ; i++) {
                Log.v("getPersonById:", cursor.getColumnNames()[i]);
            }
            int index_surname=cursor.getColumnIndex(SURNAME_COL);
            int index_lastname=cursor.getColumnIndex(LAST_NAME_COL);
            String surname="",lastname="";
            while (cursor.moveToNext()){
                surname=cursor.getString(index_surname);
                lastname= cursor.getString(index_lastname);
            }
            return new Person(id,surname,lastname);
        } else {
            return null;
        }
    }
    // this method is use to add new person to our sqlite database.
    public void addNewPerson(String surname, String lastname,String card_id) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(SURNAME_COL, surname);
        values.put(LAST_NAME_COL, lastname);
        values.put(CARD_ID,card_id);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public void emptyDatabase(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //onCreate(db);
    }
}
