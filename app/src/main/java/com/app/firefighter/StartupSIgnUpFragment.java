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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartupSIgnUpFragment extends Fragment {
    //Declare variable
    protected EditText company,cEmail,cPassword,cConformPassword;
    Button cSignIn;
    ImageView logoImage;

    public StartupSIgnUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_startup_sign_up, container, false);

        //Locate Edit text in startupSignUpFragment.xml
        company = (EditText)v.findViewById(R.id.company);
        cEmail = (EditText)v.findViewById(R.id.companyEmail);
        cPassword = (EditText)v.findViewById(R.id.companyPassword);
        cConformPassword = (EditText)v.findViewById(R.id.conformCompany);

        //Locate Button in startupSignUpFragment.xml
        cSignIn = (Button)v.findViewById(R.id.companySignIn);

        logoImage = (ImageView)v.findViewById(R.id.imgLogo);

        //cSignIn button click listener
        cSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieved the text entered from the edit text
                String Company = company.getText().toString().trim();
                String CEmail = cEmail.getText().toString().trim();
                String CPassword = cPassword.getText().toString().trim();
                String CConformPassword = cConformPassword.getText().toString().trim();

                //Force to fill signup form
                if (Company.isEmpty() || CEmail.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Oops!!!").
                            setMessage("Company Name,Email must be required")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(Company);
                    user.setEmail(CEmail);
                    user.setPassword(CPassword);

                    //Force user to fill the same password
                    if (!CPassword.equals(CConformPassword)) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("Sorry!!").
                                setMessage("Password is not match").
                                setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder1.create();
                        dialog.show();
                    }

                    // Save new user data into Parse.com Data Storage
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                //Successfully signUp
                                //send signUp user to StartupHomeActivity.class
                                Intent intent = new Intent(getActivity(), StartupHomeActivity.class);
                                //setFlags for new task
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                //setFlags for clear task
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
        return v;
    }
}
