package com.project.cvd.studentmanager.DatabaseRepos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.cvd.studentmanager.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Heero on 04.07.2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "students";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_FIRSTNAME = "firstname";
    public static final String CONTACTS_COLUMN_LASTTNAME = "lastname";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "address";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table students " +
                        "(id integer primary key, lastname text, firstname text,email text, address text, phone text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }

    public boolean insertContact (String firstname, String lastname,  String email, String address ,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("address", address);
        db.insert("students", null, contentValues);
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from students", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public void DeleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("students", null, null);
    }


    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("students",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Student> getAllContacts() {
        ArrayList<Student> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from students", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            int id = res.getInt(res.getColumnIndex("id"));
            String first = res.getString(res.getColumnIndex("firstname"));
            String last = res.getString(res.getColumnIndex("lastname"));
            String phone = res.getString(res.getColumnIndex("phone"));
            String email = res.getString(res.getColumnIndex("email"));
            String address = res.getString(res.getColumnIndex("address"));
            Student stud = new Student(first, last, email,address,phone);
            stud.setId(id);
            array_list.add(stud);
            res.moveToNext();
        }
        return array_list;
    }
}
