package com.project.cvd.studentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<Student> {

    ArrayList<Student> list;

    public UsersAdapter(Context context, ArrayList<Student> users) {
        super(context, 0, users);
        list=users;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Student user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_items, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvFirstname);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvLastname);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
        // Populate the data into the template view using the data object
        tvName.setText(user.getFirstname());
        tvHome.setText(user.getLastname());
        tvPhone.setText(user.getPhone());
        tvEmail.setText(user.getEmail());
        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = convertView.findViewById(R.id.btDelete);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                Context c = getContext();
                DBHelper db = new DBHelper(c);
                db.DeleteAll();

                for (Student studs:list)
                {
                    db.insertContact(studs.Firstname, studs.Lastname, studs.Email, studs.Address, studs.Phone);
                }
                db.close();
                notifyDataSetChanged();
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }




}
