package com.app.firefighter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class JobPostActivity extends Activity {
    EditText Name,Mission,Role,Skill,Location,Vacancy;
    Spinner Validity;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        Name = (EditText)findViewById(R.id.name);
        Mission = (EditText)findViewById(R.id.mission);
        Role = (EditText)findViewById(R.id.role);
        Skill = (EditText)findViewById(R.id.skill);
        Location = (EditText)findViewById(R.id.location);
        Vacancy = (EditText)findViewById(R.id.vacancy);

        Validity = (Spinner)findViewById(R.id.spinner1);

        Submit = (Button)findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject parseObject = new ParseObject("JobPost");

                parseObject.put(Constant.KEY_NAME,Name.getText().toString());
                parseObject.put(Constant.KEY_ROLE,Role.getText().toString());
                parseObject.put(Constant.KEY_MISSION,Mission.getText().toString());
                parseObject.put(Constant.KEY_SKILL,Skill.getText().toString());
                parseObject.put(Constant.KEY_LOCATION,Location.getText().toString());
                parseObject.put(Constant.KEY_VACANCY,Vacancy.getText().toString());
                parseObject.put(Constant.KEY_USER_NAME, ParseUser.getCurrentUser().getUsername());

                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                    }
                });

                Intent intent = new Intent(JobPostActivity.this, StudentHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_job_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Home) {
            Intent intent = new Intent(JobPostActivity.this,StartupHomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
