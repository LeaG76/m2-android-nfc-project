package com.example.nfc_scanner_m2iwocs;

public class Person {
    // class parameters
    private String card_id;
    private String surname;
    private String lastname;
    // constructor
    public Person(String card_id,String surname,String lastname){
        this.surname=surname;
        this.card_id=card_id;
        this.lastname=lastname;
    }
    // getters
    public String getCard_id() {
        return card_id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSurname() {
        return surname;
    }
    public String personName(){ return surname+" "+lastname;}
    @Override
    public String toString() {
        return "Person{" +
                "card_id='" + card_id + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
