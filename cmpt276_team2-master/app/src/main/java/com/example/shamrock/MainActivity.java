package com.example.shamrock;
//importing all the required libraries
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //initializing all the buttons used
    private Button button;
    private Button pButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //linking the buttons with the id set in xml file
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.caregiver);
        pButton = (Button) findViewById(R.id.patient);

        //using on click feature
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
        pButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openPatientLogin();
            }
        });
    }
    //lead to Caregiver page
    public void openActivity2(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    //lead to Patient page
    public void openPatientLogin(){
        Intent intent = new Intent(this,MainActivity7.class);
        startActivity(intent);
    }
}