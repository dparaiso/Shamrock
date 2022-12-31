package com.example.shamrock;
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.Calendar;

public class Schedule {
    private Calendar calendar;
    private ArrayList<Task> taskArrayList;
    private String documentID;
    private Integer Day;
    private Integer Month;
    private Integer Year;

    //constructors
    public Schedule(Calendar calendar, ArrayList<Task> taskArrayList) {
        this.calendar = calendar;
        this.taskArrayList = taskArrayList;
    }
    public Schedule(Calendar calendar, String documentID) {
        this.calendar = calendar;
        this.documentID = documentID;
    }
    public Schedule(String documentID) {
        this.documentID = documentID;
    }

    public Schedule() {

    }

    //getters and setters
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    @Exclude
    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }


    public void setTaskArrayList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }


}
