package com.app.firefighter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutCompanyFragment extends Fragment {
    TextView company_name,company_mission,company_role,compny_skill,company_location;
    Button btn_apply,btn_reject;
    ImageView company_logo;


    public AboutCompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.fragment_about_company, container, false);

        company_name = (TextView)r.findViewById(R.id.company_name);
        company_mission = (TextView)r.findViewById(R.id.mission_about);
        company_role = (TextView)r.findViewById(R.id.role_about);
        compny_skill = (TextView)r.findViewById(R.id.skill_about);
        company_location = (TextView)r.findViewById(R.id.location_about);

        btn_apply = (Button)r.findViewById(R.id.btn_apply);
        btn_reject = (Button)r.findViewById(R.id.btn_reject);

        company_logo = (ImageView)r.findViewById(R.id.logo_img_about);

        // Call from F_DataListFragment
        final String companyName = getArguments().getString("User_Name");
        final String companyRole = getArguments().getString("Role");
        final String companySkill = getArguments().getString("Skill");
        final String companyLocation = getArguments().getString("Location");
        final String CompanyMission = getArguments().getString("Mission");

        // fetch data from parse and set in xml file
        company_name.setText(companyName);
        company_role.setText(companyRole);
        compny_skill.setText(companySkill);
        company_location.setText(companyLocation);
        company_mission.setText(CompanyMission);

        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StudentHomeActivity.class);
                startActivity(i);
            }
        });

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);
            }
        });
        return r;
    }


}
