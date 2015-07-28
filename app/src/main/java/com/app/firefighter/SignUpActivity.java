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


public class SignUpActivity extends FragmentActivity {
    Fragment fr;
    FragmentManager fm;
    FragmentTransaction ft;
    Button student,startup;
    TextView existUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        student = (Button)findViewById(R.id.student);
        startup = (Button)findViewById(R.id.startup);
        existUser = (TextView)findViewById(R.id.existUser);

        // Call fragment
        fr = new StudentSignUpFragment();
        fm =  getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.fragment_student1,fr);
        ft.commit();

        //Button click event fragment replace to another fragment
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new StudentSignUpFragment();
                navigationFragment1();
            }
        });
        startup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr = new StartupSIgnUpFragment();
                navigationFragment1();
            }
        });

        //Redirect to another activity on text click event
        existUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    // Asynchronous navigation fragment for remove previous fragment and replace with new fragment
    public void navigationFragment1() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.remove(fr);
        ft.replace(R.id.fragment_student1, fr);
        ft.commit();
    }
}
