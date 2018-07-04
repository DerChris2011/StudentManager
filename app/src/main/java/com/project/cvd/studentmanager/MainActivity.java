package com.project.cvd.studentmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;

import com.project.cvd.studentmanager.DatabaseRepos.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Student> StudentsList = new ArrayList<>();

    UsersAdapter adapter;

    ListView lvlStudents;

    ImageButton delButton;

    Switch swSorting;

    Boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lvlStudents = findViewById(R.id.lvlStudents);
        swSorting = findViewById(R.id.swSorting);
        swSorting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swSorting.isChecked()) {
                    switchState = true;
                    swSorting.setText("Asc");
                }
                else {
                    switchState = false;
                    swSorting.setText("Dsc");
                }

                LoadList();
            }
        });

        swSorting.setChecked(true);
        swSorting.setText("Asc");
        switchState=true;

        //Liste mit den Usern laden.
        LoadList();


    }

    @Override
    protected void onResume() {
        super.onResume();

        //Liste mit den Usern nach Create neu laden.
        LoadList();
    }

    private void DeleteItem(Student user){
        DBHelper db = new DBHelper(this);
        db.deleteContact(user.Id);
        LoadList();
    }

    public void LoadList(){
        //Liste einmal leeren
        StudentsList.clear();

        //Liste vcn der SugarORm Datenbank laden.
        DBHelper db = new DBHelper(this);
        StudentsList = db.getAllContacts();
        db.close();

        if(switchState){
            BubbleSortAscending();
        }else{
            BubbleSortDescending();
        }

        adapter = new UsersAdapter(this, (ArrayList<Student>) StudentsList);
        lvlStudents.setAdapter(adapter);
    }

    public void deleteUser(Student user){
        DBHelper db = new DBHelper(this);
        db.deleteContact(user.Id);
    }

    public void CreateUser(View view) {
        //Verweis auf die Create Activity und anschließend öffnen.
        Intent i = new Intent(this, Create.class);
        startActivity(i);
    }

    public void Home(View view) {
        Intent i = new Intent(this, Create.class);
        startActivity(i);
    }

    private void BubbleSortAscending(){
        List<Student> BubbleList = new ArrayList<>(StudentsList);

        Student student;
        if (BubbleList.size()>1) // check if the number of orders is larger than 1
        {
            for (int x=0; x<BubbleList.size(); x++) // bubble sort outer loop
            {
                for (int i=0; i < BubbleList.size()- x -i; i++) {
                    if (BubbleList.get(i).compareTo(BubbleList.get(i+1)) > 0)
                    {
                        student = BubbleList.get(i);
                        BubbleList.set(i,BubbleList.get(i+1) );
                        BubbleList.set(i+1, student);
                    }
                }
            }
        }

        StudentsList.clear();
        StudentsList = BubbleList;

    }

    private void BubbleSortDescending(){
        List<Student> BubbleList = new ArrayList<>(StudentsList);

        Student student;
        if (BubbleList.size()>1) // check if the number of orders is larger than 1
        {
            for (int x=0; x<BubbleList.size(); x++) // bubble sort outer loop
            {
                for (int i=0; i < BubbleList.size()- x -i; i++) {
                    if (BubbleList.get(i).compareTo(BubbleList.get(i+1)) < 1)
                    {
                        student = BubbleList.get(i);
                        BubbleList.set(i,BubbleList.get(i+1) );
                        BubbleList.set(i+1, student);
                    }
                }
            }
        }

        StudentsList.clear();
        StudentsList = BubbleList;

    }
}

