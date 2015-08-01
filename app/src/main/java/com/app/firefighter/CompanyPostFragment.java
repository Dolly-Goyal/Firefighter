package com.app.firefighter;


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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyPostFragment extends Fragment {
    protected GridView postList;
    List<String> postRole,postCompany;
    protected List<ParseObject> jobData;

    public CompanyPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_company_post, container, false);
        postList = (GridView)root.findViewById(R.id.company_list);
        postRole = new ArrayList<String>();
        postCompany = new ArrayList<String>();
        jobData = new ArrayList<ParseObject>();

        // Query from jobPost class
        ParseQuery<ParseObject> postQuery = ParseQuery.getQuery("JobPost");
        postQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    jobData = parseObjects;
                    int i = 0;
                    for (ParseObject pData : jobData) {

                        // add Company Name and Role in List View
                        postRole.add(pData.getString(Constant.KEY_ROLE));
                        postCompany.add(pData.getString(Constant.KEY_USER_NAME));
                        System.out.print(postRole);
                        Log.d("Parse data fetch", postRole.toString());
                        i++;
                    }

                    //Call Custom Adapter
                    JobListAdapter adapter = new JobListAdapter(getActivity(), postRole, postCompany);
                    postList.setAdapter(adapter);
                }
            }
        });

        //post list item click listener
        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putString("User_Name", jobData.get(position).getString(Constant.KEY_USER_NAME));
                bundle.putString("Role", jobData.get(position).getString(Constant.KEY_ROLE));
                bundle.putString("Skill", jobData.get(position).getString(Constant.KEY_SKILL));
                bundle.putString("Location", jobData.get(position).getString(Constant.KEY_LOCATION));
                bundle.putString("Mission", jobData.get(position).getString(Constant.KEY_MISSION));

                //replace with another fragment
                AboutCompanyFragment frag = new AboutCompanyFragment();
                frag.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.company_post, frag, "MY_FRAG");
                fragmentTransaction.commit();
            }
        });
        return root;
    }

    //JobListAdapter define
    class JobListAdapter extends ArrayAdapter<String> {

        Context mContext;
        private List<String> rolePost,companyPost;

        public JobListAdapter(Context context, List<String> postRole, List<String> postCompany) {
            super(context, R.layout.company_post_data, postRole);
            mContext = context;
            rolePost = postRole;
            companyPost = postCompany;
            System.out.println(rolePost);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.company_post_data, null);
                holder = new ViewHolder();

                holder.postRole = (TextView) convertView.findViewById(R.id.role_post);
                holder.postCompany = (TextView) convertView.findViewById(R.id.company_post);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.postRole.setText(rolePost.get(position));
            holder.postCompany.setText(companyPost.get(position));
            return convertView;
        }

        public class ViewHolder {
            TextView postRole;
            TextView postCompany;
        }
    }
}
