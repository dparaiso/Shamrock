package com.example.shamrock;
//importing all the required libraries
import android.widget.Toast;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
//creating  a caregiver class
//this class will help to add caregiver information
//to the database or to retrieve the pre-fed information
//from the database
public class Caregiver {
    //initializing
    private String documentId;
    private String name;
    private String username;
    private String email;
    private String password;
    //will be used to store different patients
    private ArrayList<String> pList;

    public Caregiver() {
        //public no-arg constructor needed
    }

    @Exclude
    //unique document id will be used
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    //creating a constructor
    public Caregiver(ArrayList<String> list, String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.pList = list;
    }

    //creating another constructor where we don't have any patient added
    //this means its the first time the caregiver is creating a new account
    public Caregiver(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.pList = null;
    }
    //making getter and setter for returning different values according to the need
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getpList() {
        return pList;
    }

    public void setpList(ArrayList<String> pList) {
        this.pList = pList;
    }


}