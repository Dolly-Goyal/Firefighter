package com.app.firefighter;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ShowJobFragment extends Fragment {
    GridView mGridView;
    List<String> Role,ApplicantNumber;
    protected List<ParseObject> jobPost;

    public ShowJobFragment(){
        //required empty fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.show_job_fragment, container, false);

        mGridView = (GridView)v.findViewById(R.id.grid);
        Role = new ArrayList<String>();
        ApplicantNumber = new ArrayList<String>();
        jobPost = new ArrayList<ParseObject>();

        ParseQuery<ParseObject> jobQuery = ParseQuery.getQuery("JobPost");
        jobQuery.whereEqualTo(Constant.KEY_USER_NAME, ParseUser.getCurrentUser().getUsername().toString());

        jobQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    jobPost = list;
                    int i = 0;
                    for (ParseObject jData : jobPost) {
                        Role.add(jData.getString(Constant.KEY_ROLE));
                        Log.d("Parse data fetch", Role.toString());
                        i++;
                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                            R.layout.job_role_list,R.id.role1,Role);
//                    mGridView.setAdapter(adapter);

                    ListAdapter adapter = new ListAdapter(getActivity(),Role,ApplicantNumber);
                    mGridView.setAdapter(adapter);
                }
            }
        });

        //Grid view item click listener
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //on click event item replace toanother fragment
                ApllicantDataFragment frag = new ApllicantDataFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_post, frag, "MY_FRAG");
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    //Define ListAdapter
    class ListAdapter extends ArrayAdapter<String> {
        Context mContext;
        private List<String> role,applicant;

        public ListAdapter(Context context,List<String> job, List<String> applicance) {
            super(context,R.layout.job_role_list,job);
            role = job;
            mContext = context;
            System.out.println(job);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.job_role_list, null);
                holder = new ViewHolder();

                holder.job = (TextView) convertView.findViewById(R.id.role1);
                holder.applicance = (TextView) convertView.findViewById(R.id.applicant);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.job.setText(role.get(position));
            return convertView;
        }
        public class ViewHolder {
            TextView job;
            TextView applicance;
        }

    }

}
