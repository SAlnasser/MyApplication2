package com.example.siba.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by siba on 7/18/17.
 */

public class SignIn extends AppCompatActivity{
    Button signout,upload_bttn,showData,notification,chat;
    private FirebaseAuth mAuth;
    TextView username;

    static String LoggedIn_User_Email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        mAuth = FirebaseAuth.getInstance(); // important Call
        signout = (Button)findViewById(R.id.signout);
        username = (TextView) findViewById(R.id.tvName);
        chat = (Button)findViewById(R.id.chat_button);

        if (MainActivity.mDatabase == null) {
            MainActivity.mDatabase = FirebaseDatabase.getInstance();
            //mDatabase.setPersistenceEnabled(true);
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        //Again check if the user is Already Logged in or Not

        if(mAuth.getCurrentUser() == null)
        {
            //User NOT logged In
            this.finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        //Fetch the Display name of current User
        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("LOGGED", "FirebaseUser: " + user);
        if (user != null) {
            username.setText("Welcome, " + user.getDisplayName());
            LoggedIn_User_Email =user.getEmail();
        }
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChatActivity.class));
            }
        });


    }//end oncreat






    }

