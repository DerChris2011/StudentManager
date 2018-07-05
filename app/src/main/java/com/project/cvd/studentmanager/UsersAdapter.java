package com.project.cvd.studentmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.cvd.studentmanager.Database.DBHelper;
import com.project.cvd.studentmanager.Models.Student;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<Student> {

    ArrayList<Student> list;

    MainActivity _mainActivity;

    public MainActivity get_mainActivity() {
        return _mainActivity;
    }

    public void set_mainActivity(MainActivity _mainActivity) {
        this._mainActivity = _mainActivity;
    }

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
        TextView tvName = convertView.findViewById(R.id.tvFirstname);
        TextView tvHome = convertView.findViewById(R.id.tvLastname);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);
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
                    db.insertContact(studs.getFirstname(), studs.getLastname(), studs.getEmail(), studs.getAddress(), studs.getPhone());
                }
                db.close();
                notifyDataSetChanged();
            }
        });

        ImageButton openDetails = convertView.findViewById(R.id.btOpenDetails);
        openDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student selectedStudent = list.get(position);

                Intent i = new Intent(_mainActivity, ContactDetails.class);
                i.putExtra("SelectedStudent", selectedStudent);

                StartActivity(i);
            }
        });



        // Return the completed view to render on screen
        return convertView;
    }

    private void StartActivity(Intent _i) {
        _mainActivity.startActivity(_i);
    }




}
