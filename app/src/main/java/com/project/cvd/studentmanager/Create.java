package com.project.cvd.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.cvd.studentmanager.Database.DBHelper;

public class Create extends AppCompatActivity {

    EditText Firstname;
    EditText Lastname;
    EditText Email;
    EditText Address;
    EditText Phone;

    Button GoBack;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create);

        Firstname = findViewById(R.id.edVorname);
        Lastname = findViewById(R.id.edNachname);
        Email = findViewById(R.id.edEmail);
        Address = findViewById(R.id.edAdresse);
        Phone = findViewById(R.id.edTelefon);

        GoBack = findViewById(R.id.btnAbbrechen);
        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateToMain();
            }
        });

        Save = findViewById(R.id.btnSpeichern);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveStudent();
            }
        });
    }

    private void NavigateToMain(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void SaveStudent(){

        DBHelper helper = new DBHelper(Create.this);
        helper.insertContact(Firstname.getText().toString(), Lastname.getText().toString(), Email.getText().toString(), Address.getText().toString(), Phone.getText().toString());
        NavigateToMain();
    }

}
