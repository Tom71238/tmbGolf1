package com.tbrey1gmail.tmbgolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tbrey1gmail.tmbgolf.View.SelectGolfCourse;

public class registerNewUser extends AppCompatActivity {

    private FirebaseAuth mFBAuth;   //For FB auth.
    //Constantly checks this user auth statr (ex. if logged in)
    private FirebaseAuth.AuthStateListener mFBauthStateListener;
    FirebaseDatabase mFBdatabase;   //For DB access
    DatabaseReference mFBref;
    //screen var handling
    private EditText eMailAddr;
    private EditText password;
    private EditText longName;
    private EditText shortName;
    final String TAG = "registerNewUser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        //get adapter to screen fields
        eMailAddr = (EditText) findViewById(R.id.etEmailID);
        password = (EditText) findViewById(R.id.etPasswordID);
        longName = (EditText) findViewById(R.id.etLongNameID);
        shortName = (EditText) findViewById(R.id.etShortNameID);
        mFBAuth = FirebaseAuth.getInstance();

    }

    public void RegisterOnClick(View v) {
        String email = eMailAddr.getText().toString();
        String pw = password.getText().toString();
        String lName = longName.getText().toString();
        String sName = shortName.getText().toString();
        Boolean canRegister = true;

        int findasteric = TextUtils.indexOf(email,"@");

        if (TextUtils.isEmpty(email) || (findasteric==0) )  {
            Toast.makeText(this, "Not a vaild email address",Toast.LENGTH_LONG).show();
            canRegister = false;
        }
        if (TextUtils.isEmpty(pw) || TextUtils.isEmpty(lName) || TextUtils.isEmpty(sName))  {
            Toast.makeText(this, "Password and name fields must be entered",Toast.LENGTH_LONG).show();
            canRegister = false;
        }

        if (canRegister == true) {
            mFBAuth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mFBAuth.getCurrentUser();
                                // Write long and short name into database
                                // Update settings base upon click
                                //updateUI(user);
                                Intent mIntent = new Intent(registerNewUser.this, SelectGolfCourse.class);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(registerNewUser.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            //ToDo: write setting that indicate that score is to be sent to email at end of round
        } else {
            //ToDo: write setting that indicate that score is NOT to be sent to email at end of round
        }
    }
}
