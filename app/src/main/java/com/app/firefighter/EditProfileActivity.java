package com.app.firefighter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.ParseObject;


public class EditProfileActivity extends ActionBarActivity {
    EditText edit_name,edit_course,edit_college,edit_number,edit_email,edit_yourself;
    Spinner edit_location;
    Button btn_save_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_course = (EditText)findViewById(R.id.edit_course);
        edit_college = (EditText)findViewById(R.id.edit_college);
        edit_number = (EditText)findViewById(R.id.edit_number);
        edit_email = (EditText)findViewById(R.id.edit_email);
        edit_yourself = (EditText)findViewById(R.id.edit_yourself);

        edit_location = (Spinner)findViewById(R.id.spinner);

        btn_save_submit = (Button)findViewById(R.id.edit_save_submit);

        btn_save_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject studentData = new ParseObject("Student");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
