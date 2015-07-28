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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentSignUpFragment extends Fragment {
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

        name = (EditText) rootView.findViewById(R.id.nameStudent);
        college = (EditText) rootView.findViewById(R.id.college);
        email = (EditText) rootView.findViewById(R.id.email);
        password = (EditText) rootView.findViewById(R.id.password);
        conformPassword = (EditText) rootView.findViewById(R.id.conform);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_search);
        location = (Spinner) rootView.findViewById(R.id.spinner);
        studentPic = (ImageView) rootView.findViewById(R.id.studentPic);

        signIn = (Button) rootView.findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString().trim();
                String College = college.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Conform = conformPassword.getText().toString().trim();

                if (Name.isEmpty() || Email.isEmpty() || College.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Oops!!!").
                            setMessage("Name,College Name,Email must be required")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    ParseUser User = new ParseUser();
                    User.setUsername(Name);
                    User.setEmail(Email);
                    User.setPassword(Password);

                    if (!Password.equals(Conform)) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("Sorry!!").
                                setMessage("Password is not match").
                                setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder1.create();
                        dialog.show();
                    }

                    User.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            if (e == null) {
                                Intent intent = new Intent(getActivity(), StudentHomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
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
