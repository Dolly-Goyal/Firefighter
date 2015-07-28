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
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button student,startup;
    TextView newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = (Button)findViewById(R.id.student);
        startup = (Button)findViewById(R.id.startup);
        newUser = (TextView)findViewById(R.id.newUser);

        // Call fragment
        fragment = new StudentLoginFragment();
        fragmentManager =  getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_student, fragment);
        fragmentTransaction.commit();

        //Button click event fragment replace to another fragment
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new StudentLoginFragment();
                navigationFragment();
            }
        });
        startup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new StartupLoginFragment();
                navigationFragment();
            }
        });

        //Redirect to another activity on text click event
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        fragmentTransaction.remove(fragment);
        fragmentTransaction.replace(R.id.fragment_student, fragment);
        fragmentTransaction.commit();
    }
}
