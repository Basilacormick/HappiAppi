package com.example.MScSoftwareProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    29/09/2014.
 * This programme delivers the SQLite login database along with the methods to manipulate and search this database for user
 * details such as user name and password
 */
public class LoginHandler {
    //columns of the table
    public static final String USERID = "userid";
    public static final String FIRSTNAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    //table name
    public static final String USERTABLE = "usertable";
    //database name
    public static final String USERDATABASE = "userdatabase";
    //database version
    public static final int USERVERSION = 5;
    //SQL statement to create table
    public static final String FDCREATEUSER = "CREATE TABLE " + USERTABLE + "(" + USERID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FIRSTNAME + " TEXT," + LASTNAME + " TEXT," + USERNAME + " TEXT," + PASSWORD + " TEXT" + ")";

    LoginDatabaseHelper loginhelper;
    Context logincontext;
    SQLiteDatabase logindatabase;
    //database constructor
    public LoginHandler(Context context) {
        this.logincontext = context;
        loginhelper = new LoginDatabaseHelper(context);
    }
    //login database helper class
    private static class LoginDatabaseHelper extends SQLiteOpenHelper {

        public LoginDatabaseHelper(Context context) {
            super(context, USERDATABASE, null, USERVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(FDCREATEUSER);//table created here
            //some dummy users added here
            ContentValues uservalues = new ContentValues();
            uservalues.put(FIRSTNAME, "Johnny");
            uservalues.put(LASTNAME, "McCartney");
            uservalues.put(USERNAME, "Ems");
            uservalues.put(PASSWORD, "Go");
            db.insert(USERTABLE, null, uservalues);

            uservalues.put(FIRSTNAME, "Paul");
            uservalues.put(LASTNAME, "McGuire");
            uservalues.put(USERNAME, "yes");
            uservalues.put(PASSWORD, "no");
            db.insert(USERTABLE, null, uservalues);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + USERTABLE);//drop table if it exists
            onCreate(db);
        }
    }
    //method to open database
    public LoginHandler open() {
        logindatabase = loginhelper.getWritableDatabase();
        return this;
    }
    //method to close database
    public void close() {
        loginhelper.close();
    }
    //method to add the user to the database when they register account
    public long addUser(String pfirstname, String plastname, String pusername, String ppassword){

        ContentValues userinfo = new ContentValues();
        userinfo.put(FIRSTNAME, pfirstname);
        userinfo.put(LASTNAME, plastname);
        userinfo.put(USERNAME, pusername);
        userinfo.put(PASSWORD, ppassword);

        return logindatabase.insert(USERTABLE, null, userinfo);
    }
    //method to return if username and password is found, if it is found finder is returned as 1 if not it is 0
    public int returnResult(String pusername, String ppassword) {

        int finder;
        Cursor usercursor;

        logindatabase = loginhelper.getReadableDatabase();
        //raw query to search database for user name and password
        usercursor = logindatabase.rawQuery("SELECT * FROM usertable WHERE username=? AND password=?", new String[]{pusername, ppassword});

        usercursor.moveToNext();
        finder = usercursor.getCount();//cursor found is counted and returned as 1 or 0
        usercursor.close();

        return finder;
    }
}


