package com.example.shamrock;

//import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firestore.v1.WriteResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
    //Declarations
    public Button button;
    private static final String TAG = "MainActivity";

    FirebaseAuth mAuth;

    //fields
    private static final String KEY_NAME = "Name";
    private static final String KEY_TITLE = "Username";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_DESCRIPTION = "password";

    //from xml from user
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewData;

    private Button register_button;
    private Button login_button;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference dRef = db.collection("Caregiver").document("My First Note");
    private CollectionReference cRef = db.collection("Caregiver");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        textViewData = findViewById(R.id.text_view_data);

        register_button = findViewById(R.id.register_button);
        login_button = findViewById(R.id.login_button);

        mAuth = FirebaseAuth.getInstance();

        //when login button clicked, call this method
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn(view);
            }
        });

        //lead to registration page
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(view);            }
        });
    }

    public void logIn(View v){
        String user = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        //error checking user input
        if (TextUtils.isEmpty(user)){
            editTextUsername.setError("Username cannot be empty");
            editTextUsername.requestFocus();
        }
        else if (TextUtils.isEmpty(email)){
            editTextEmail.setError("Email cannot be empty");
            editTextEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            editTextPassword.setError("Password cannot be empty");
            editTextPassword.requestFocus();
        }
        else{
            //adding user to firebase authentication
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity2.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                        i.putExtra("documentId",mAuth.getCurrentUser().getUid());
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MainActivity2.this, "Login Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void createAccount(View v) {
        //gets user input
        String user = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(user)) {
            editTextUsername.setError("Username cannot be empty");
            editTextUsername.requestFocus();
        } else if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email cannot be empty");
            editTextEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Password cannot be empty");
            editTextPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity2.this, "Account created", Toast.LENGTH_SHORT).show();

                        //new create account
                        Caregiver caregiver = new Caregiver(user, email, password);

                        //add document to firebase
                        cRef.document(mAuth.getCurrentUser().getUid()).set(caregiver);
                        Intent i = new Intent(MainActivity2.this, MainActivity6.class);
                        i.putExtra("documentId",mAuth.getCurrentUser().getUid());
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity2.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

}