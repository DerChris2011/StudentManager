package com.project.cvd.studentmanager.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Heero on 04.07.2018.
 */

public class Student implements Parcelable {


    int Id;
    String Firstname;
    String Lastname;
    String Email;
    String Address;
    String Phone;

    public Student(String firstname, String lastname, String email, String address, String phone){
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Email = email;
        this.Address = address;
        this.Phone = phone;
    }

    /**
     * Use when reconstructing User object from parcel
     * This will be used only by the 'CREATOR'
     *
     * @param in a parcel to read this object
     */
    public Student(Parcel in) {
        this.Id = in.readInt();
        this.Firstname = in.readString();
        this.Lastname = in.readString();
        this.Email = in.readString();
        this.Address = in.readString();
        this.Phone = in.readString();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String GetNames(){
        return Firstname + " " + Lastname;
    }

    public int compareTo(Student student) {
        return Firstname.compareTo(student.Firstname);
    }

    /**
     * Define the kind of object that you gonna parcel,
     * You can use hashCode() here
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Actual object serialization happens here, Write object content
     * to parcel, reading should be done according to this write order
     * param dest - parcel
     * param flags - Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Firstname);
        dest.writeString(Lastname);
        dest.writeString(Email);
        dest.writeString(Address);
        dest.writeString(Phone);
    }

    /**
     * This field is needed for Android to be able to
     * create new objects, individually or as arrays
     * <p>
     * If you don’t do that, Android framework will raises an exception
     * Parcelable protocol requires a Parcelable.Creator object
     * called CREATOR
     */
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {

        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
