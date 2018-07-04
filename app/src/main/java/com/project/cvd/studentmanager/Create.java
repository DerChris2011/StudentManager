package com.project.cvd.studentmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);
    }

    private void SaveStudent(){
        Student stud = new Student();
        stud.setFirstname("");
        stud.setLastname("");
        stud.setEmail("");
        stud.setAddress("");
        stud.setPhone("");
    }

}
