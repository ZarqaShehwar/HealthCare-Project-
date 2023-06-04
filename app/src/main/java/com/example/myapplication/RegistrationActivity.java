package com.example.myapplication;

import static com.example.myapplication.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    EditText edusername,email,edPassword,edConfirm;
    Button btn;
    TextView tv;
FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_registration);

        edusername = findViewById(R.id.editTextRegUsername);
        email = findViewById(R.id.editTextEmail);
        edPassword = findViewById(R.id.editTextPassword);
        edConfirm = findViewById(R.id.editTextConfirmPassword);
        btn = findViewById(R.id.Signup1);
        tv = findViewById(R.id.Login);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, loginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = edusername.getText().toString();
                String email1 = email.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirm)==1 ) {
                        Toast.makeText(getApplicationContext(), "Confirm password not matched", Toast.LENGTH_SHORT).show();
                    } else {
                        if (isValid(password)) {
                            firebaseAuth.createUserWithEmailAndPassword(email1, password).addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Map<String,Object> user = new HashMap<>();
                                                user.put("Firstname",username);
                                                user.put("Email",email1);
                                                user.put("Password",password);
                                                user.put("Confirm password",confirm);

                                                firebaseFirestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(RegistrationActivity.this,HomeActivity.class));

                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                Toast.makeText(getApplicationContext(), "Sign In successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Authenticaton Failed", Toast.LENGTH_SHORT).show();
 }
                                        }
                                    });
} else {
                            Toast.makeText(getApplicationContext(), "password must  be atleast 8 digit or more", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

 }
          });


        }
    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
//            for ( int s1 = 0; s1 < passwordhere.length(); s1++) {
//                char s = passwordhere.charAt(s1);
//                if (s > 33 & s < 44 || s == 64) {
//                    f3 = 1;
//                }
        }


        if(f1==1||f2==1) {
            return true;
        }
        return false;


    }
        }
