package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    EditText editemail,password;
    Button btn,mAP;
    TextView tv;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editemail = findViewById(R.id.editTextLoginEmail);
        password=findViewById(R.id.editTextLoginPassword);
        btn=findViewById(R.id.Login);
        mAP=findViewById(R.id.map1);
        tv= findViewById(R.id.Signup);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = editemail.getText().toString();
                String userpassword = password.getText().toString();
                if(email.length()==0||userpassword.length()==0) {


                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(email,userpassword).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(loginActivity.this,HomeActivity.class));

                           }
                           else{
                               Toast.makeText(getApplicationContext(),"Authentication failed",Toast.LENGTH_SHORT).show();

                           }

                                                      }
                                                  }

                            );

                }
                }


        });
       tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(loginActivity.this,RegistrationActivity.class));

            }


        });
       mAP.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(loginActivity.this,MapsActivity2.class));
           }
       });

    }


}