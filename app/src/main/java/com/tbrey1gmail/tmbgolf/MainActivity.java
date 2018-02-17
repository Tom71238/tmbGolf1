package com.tbrey1gmail.tmbgolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tbrey1gmail.tmbgolf.View.SelectGolfCourse;

//*********************************************************
//tmb reference https://www.youtube.com/watch?v=P9h6ukPQU_4
//*********************************************************

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFBAuth;   //For FB auth.
                                    //Constantly checks this user auth statr (ex. if logged in)
    private FirebaseAuth.AuthStateListener mFBauthStateListener;
    private FirebaseDatabase mFBdatabase;   //For DB access
    DatabaseReference mFBref;


    //screen var handling
    private EditText eMailAddr;
    private EditText password;
    private ProgressBar pBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get adapter to screen fields
        eMailAddr = (EditText) findViewById(R.id.etLongNameID);
        password = (EditText) findViewById(R.id.etPasswordID);
        pBarLogin = (ProgressBar) findViewById(R.id.pBarLoginID);

        mFBAuth = FirebaseAuth.getInstance();

        //mFBAuth.signOut(); //Put this here for testing so it forces me thru the signon/register page

        // Someone has successfully logged in (???or out???)
        mFBauthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Check if I am logged in
                if (firebaseAuth.getCurrentUser() !=null) {
                    Intent mIntent = new Intent(MainActivity.this, SelectGolfCourse.class);
                    startActivity(mIntent);
                }
            }
        };

//        // Keep Copy on local device
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        // Write a string to node "disconnectmessage" when this client loses connection
//        FirebaseDatabase.getInstance().getReference("disconnectmessage").onDisconnect().setValue("I disconnected!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFBAuth.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Keep the listener up when restarting
        mFBAuth.addAuthStateListener(mFBauthStateListener);
    }

    //Login button clicked
    public void LoginOnClick(View v) {
        String email = eMailAddr.getText().toString();
        String pw = password.getText().toString();

        //ToDo: Remove id/pw pump
        email = "tbrey1@gmail.com";
        pw = "tbrey1";


        pBarLogin.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pw))  {
            Toast.makeText(this, "Both email and password must be entered",Toast.LENGTH_LONG).show();
        } else {
            // Show the progress bar while waiting for the login

            //The following kicks off an attempt to login with an onComplete listener response
            mFBAuth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    pBarLogin.setVisibility(View.INVISIBLE);
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Login failed: Incorrect email or password",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login successful",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }


    //Login button clicked
    public void RegisterOnClick(View v) {
        String email = eMailAddr.getText().toString();
        String pw = password.getText().toString();

        Intent mIntent = new Intent(MainActivity.this, registerNewUser.class);
        startActivity(mIntent);

    }

}
