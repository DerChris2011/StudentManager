package com.project.cvd.studentmanager.Models;

import com.orm.SugarRecord;

/**
 * Created by Heero on 04.07.2018.
 */

public class Student extends SugarRecord<Student> {
    String Firstname;
    String Lastname;
    String Email;
    String Address;
    String Phone;

    public Student(){

    }

    public Student(String firstname, String lastname, String email, String address, String phone){
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Email = email;
        this.Address = address;
        this.Phone = phone;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
