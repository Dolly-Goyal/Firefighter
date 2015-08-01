package com.app.firefighter;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentSignUpFragment extends Fragment {
    //Declare variable
    protected EditText name, college, email, password, conformPassword;
    Button signIn;
    Spinner location;
    ImageView studentPic;
    private ProgressBar progressBar;

    public StudentSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_sign_up, container, false);

        //locate Edit text in studentSignUpFragment.xml
        name = (EditText) rootView.findViewById(R.id.nameStudent);
        college = (EditText) rootView.findViewById(R.id.college);
        email = (EditText) rootView.findViewById(R.id.email);
        password = (EditText) rootView.findViewById(R.id.password);
        conformPassword = (EditText) rootView.findViewById(R.id.conform);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_search);

        location = (Spinner) rootView.findViewById(R.id.spinner);

        studentPic = (ImageView) rootView.findViewById(R.id.studentPic);

        signIn = (Button) rootView.findViewById(R.id.signIn);

        //signIn button click listener
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieved the text entered from the edit text
                String Name = name.getText().toString().trim();
                String College = college.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Conform = conformPassword.getText().toString().trim();

                //Force user to fill up the signUp form
                if (Name.isEmpty() || Email.isEmpty() || College.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Oops!!!").
                            setMessage("Name,College Name,Email must be required")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    // Save new user data into Parse.com Data Storage
                    ParseUser User = new ParseUser();
                    User.setUsername(Name);
                    User.setEmail(Email);
                    User.setPassword(Password);

                    //Force user to fill the same password
                    if (!Password.equals(Conform)) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("Sorry!!").
                                setMessage("Password is not match").
                                setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder1.create();
                        dialog.show();
                    }

                    // Save new user data into Parse.com Data Storage
                    User.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            if (e == null) {
                                //Successfully signUp
                                //send signUp user to StudentHomeActivity.class
                                Intent intent = new Intent(getActivity(), StudentHomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                //SignUp error
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
        return rootView;
    }
}
