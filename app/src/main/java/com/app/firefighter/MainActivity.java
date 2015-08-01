package com.app.firefighter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

    //Declare fragment
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //Declare variable
    Button student,startup;
    TextView newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get the view from main.xml
        setContentView(R.layout.activity_main);

        //Locate button in Main.xml
        student = (Button)findViewById(R.id.student);
        startup = (Button)findViewById(R.id.startup);

        //Locate TextView in main.xml
        newUser = (TextView)findViewById(R.id.newUser);

        // Call default fragment
        fragment = new StudentLoginFragment();
        fragmentManager =  getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //add default fragment
        fragmentTransaction.add(R.id.fragment_student, fragment);
        fragmentTransaction.commit();

        //student button click Listner
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define fragment
                fragment = new StudentLoginFragment();
                //call navigationFragment method
                navigationFragment();
            }
        });

        //startup button click listner
        startup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define navigationFragment
                fragment = new StartupLoginFragment();
                //call navigationFragment method
                navigationFragment();
            }
        });

        //newUser TextView click listner
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send signUpActivity for new user
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Asynchronous navigation fragment for remove previous fragment and replace with new fragment
    public void navigationFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //remove previous fragment
        fragmentTransaction.remove(fragment);
        //replace with new fragment
        fragmentTransaction.replace(R.id.fragment_student, fragment);
        fragmentTransaction.commit();
    }
}
