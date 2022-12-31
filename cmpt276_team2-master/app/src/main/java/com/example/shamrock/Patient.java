package com.example.shamrock;
//importing all the required libraries
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
//this class helps to add patient information in the database
public class Patient {
    //initializing
    private String username;
    private String age;
    private String sex;
    private String documentId;
    //array list for storing patients
    private ArrayList<Schedule> sList;
    private int imageId;
    private String list_patient_id;
    private String phoneNo;
    private String country;
    public Patient(){
        //no-arg constructor

    }
    //constructor
//    public Patient(String username, String age, String sex){
//        this.username = username;
//        this.age = age;
//        this.sex = sex;
//    }
    public Patient(String username, String age, String sex, String list_patient_id, String phoneNo, String country, int imageId){
        this.username = username;
        this.age = age;
        this.sex = sex;
        this.list_patient_id = list_patient_id;
        this.phoneNo = phoneNo;
        this.country = country;
        this.imageId = imageId;
    }

    @Exclude
    //getters and setters
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUsername(){
        return username;
    }

    public String getAge(){
        return age;
    }

    public String getSex(){
        return sex;
    }

    public int getImageId(){
        return imageId;
    }

    public String getList_patient_id(){
        return list_patient_id;
    }

    public String getPhoneNo(){
        return phoneNo;
    }

    public String getCountry(){
        return country;
    }




    public void setUsername(String username){
        this.username = username;
    }

    public void setAge(String age){
        this.age = age;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public void setImageId(String imageId){
        this.sex = imageId;
    }

    public void setList_patient_id(String list_patient_id){
        this.list_patient_id = list_patient_id;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }

    public void setCountry(){
        this.phoneNo = phoneNo;
    }

}