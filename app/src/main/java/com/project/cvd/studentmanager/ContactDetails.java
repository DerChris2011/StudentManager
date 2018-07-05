package com.project.cvd.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.project.cvd.studentmanager.Models.Student;

public class ContactDetails extends AppCompatActivity {

    Student selectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Student extraObject = getIntent().getExtras().getParcelable("SelectedStudent");

        TextView Name = findViewById(R.id.accVornameNachname);
        TextView Email = findViewById(R.id.accEmail);
        TextView Address = findViewById(R.id.accAdresse);
        TextView Phone = findViewById(R.id.accTelefon);

        if (extraObject != null) {
            selectedStudent = extraObject;

            Name.setText(selectedStudent.getFirstname() + "-" + selectedStudent.getLastname());
            Email.setText(selectedStudent.getEmail());
            Address.setText(selectedStudent.getAddress());
            Phone.setText(selectedStudent.getPhone());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        selectedStudent = null;
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
