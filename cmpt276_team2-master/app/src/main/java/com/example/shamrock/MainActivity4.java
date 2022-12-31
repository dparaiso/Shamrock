package com.example.shamrock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

/*
   *Page is still in progress
 */
public class MainActivity4 extends AppCompatActivity {
    private Button newevent;
    private Button changePatientInfo_button;
    private Button newDate;
    DatePickerDialog.OnDateSetListener setListener;
    TextView tvDate;
    TextView etDate;
    TextView patientName;
    private Integer count = 0;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference sRef = db.collection("Schedule");

    public Patient temp_patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //for set the patient name in patient's task page(Activity4)

//        patientName = (TextView) findViewById(R.id.patient_name);
//        if (temp_patient.getUsername() == null){
//            patientName.setText("Defalut Patient");
//        }
//        else {
//            patientName.setText(temp_patient.getUsername());
//        }


        tvDate = findViewById(R.id.tv_date);
        etDate = findViewById(R.id.et_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });
//

        newevent = (Button) findViewById(R.id.newevent);
        newevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity5();
            }
        });

        changePatientInfo_button = (Button) findViewById(R.id.changePatientInfo_button);
        changePatientInfo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity6();
            }
        });

    }
    public void openActivity5(){
        if (count < 1){
            Toast.makeText(this, "Must Select Date first", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this,MainActivity5.class);
        startActivity(intent);
    }

    public void openActivity6(){
        Intent intent = new Intent(this,MainActivity6.class);
        startActivity(intent);
    }

    public void selectDate(){

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        //tvDate
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                MainActivity4.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                setListener, year, month, day);
//        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        datePickerDialog.show();

//        setListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int day) {
//                month = month +1;
//                String date = day + "/" + month + "/" + year;
//                tvDate.setText(date);
//            }
//        };


        //etDate
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity4.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month +1;
                String date = day + "/" + month + "/" + year;
                etDate.setText(date);
                count++;
            }
        },year,month,day);
        datePickerDialog.show();


        //pre set time for date
        //query database for existing date
        //if exists grab schedule task for that day and add
        //if doesn't exist create new schedule
        //add calendar to intent

        DocumentReference addedDocRef = sRef.document();
        Schedule schedule = new Schedule(addedDocRef.getId());
        schedule.setCalendar(calendar);
        addedDocRef.set(schedule);

    }

}
