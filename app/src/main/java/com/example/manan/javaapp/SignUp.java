package com.example.manan.javaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private static final String TAG = SignUp.class.getName();

    private DatabaseReference db;
    private FirebaseAuth mAuth;


    private Button signUpBtn;

    private EditText emailET;
    private EditText pwET;
    private TextView messageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        signUpBtn = findViewById(R.id.signUpBtn);

        emailET = findViewById(R.id.emailET);
        pwET = findViewById(R.id.pwET);
        messageTV = findViewById(R.id.messageTV);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String pw = pwET.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, pw)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign up success, navigate back to sign in
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser fbUser = mAuth.getCurrentUser();
                                    User user = new User(fbUser.getUid(),1);
                                    db.child("user").child(user.getUid()).setValue(user);
                                    Intent intent = new Intent(SignUp.this,MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    messageTV.setText("Sorry, could not create an account with those credentials. Please try again.");
                                }
                            }
                        });
            }
        });

    }
}
