package com.app.firefighter;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentLoginFragment extends Fragment {
    //Declare variable
    protected EditText Email,Password;
    Button logIn;


    public StudentLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_login, container, false);

        //Locate Edit text in studentLoginFragment.xml
        Email = (EditText)view.findViewById(R.id.studentEmail);
        Password = (EditText)view.findViewById(R.id.studentPassword);

        //Locate button in studentLoginFragment.xml
        logIn = (Button)view.findViewById(R.id.studentLogin);

        //login button click listener
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the data as a string form from parse
                final String sEmail = Email.getText().toString().trim();
                String sPassword = Password.getText().toString().trim();

                //force to fill the login form
                if (sEmail.isEmpty() || sPassword.isEmpty()) {
                    //Display the alert box
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Oops!!!")
                            .setMessage("Email Id and Password is not match")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    //check email and password for login
                    ParseUser.logInInBackground(sEmail, sPassword, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if (e == null) {
                                //send login user in StudentHomeActivity.class
                                Intent intent = new Intent(getActivity(), StudentHomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                getActivity().finish();
                            } else {
                                //error message generate
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Oops!").setMessage(e.getMessage())
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
        return view;
    }


}
