package com.example.MScSoftwareProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    08/10/2014
 * This programme delivers the SQLite activity database along with the methods to manipulate this database for the users calorie
 * activity
 */

public class ActivityHandler {
    //Columns for table are created here
    public static final String ACTIVITYID = "activityid";
    public static final String ACTIVITYNAME = "activityname";
    public static final String CALORIES = "activitycals";
    public static final String TIME = "activitytime";

    //table name
    public static final String ACTIVITYTABLE = "activitytable";
    //database name
    public static final String ACTIVITYDATABASE = "activitydatabase";
    //database version
    public static final int ACTIVITYVERSION = 4;
    //SQL statement to create ACTIVITYTABLE
    public static final String CREATEACTIVITYTAB = "CREATE TABLE " + ACTIVITYTABLE + "(" + ACTIVITYID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ACTIVITYNAME + " TEXT," + CALORIES + " INTEGER," + TIME + " TEXT" + ")";

    ActivityDatabaseHelper activityhelper;//ActivityDatabaseHelper created here
    Context activitycontext;
    SQLiteDatabase activitydatabase;//create SQLiteDatabase

    //Constructor for database
    public ActivityHandler(Context context) {
        this.activitycontext = context;
        activityhelper = new ActivityDatabaseHelper(context);
    }

    //Activity database helper class
    private static class ActivityDatabaseHelper extends SQLiteOpenHelper {

        public ActivityDatabaseHelper(Context context) {
            super(context, ACTIVITYDATABASE, null, ACTIVITYVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATEACTIVITYTAB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ACTIVITYTABLE);//if table exists it is dropped
            onCreate(db);
        }
    }
    //method to open database
    public ActivityHandler open(){
        activitydatabase = activityhelper.getWritableDatabase();
        return this;
    }
    //method to close database
    public void close(){
        activityhelper.close();
    }
    //method to add an activity
    public long addActivity(String pactivityname, Integer pcalories, String ptime){

        ContentValues activity = new ContentValues();
        activity.put(ACTIVITYNAME, pactivityname);
        activity.put(CALORIES, pcalories);
        activity.put(TIME, ptime);
        return activitydatabase.insert(ACTIVITYTABLE, null, activity);
    }
    //method to query database and return the activities logged, called in ViewActivity
    public String returnActivities(){
        activitydatabase = activityhelper.getWritableDatabase();
        String[]columns = {ActivityHandler.ACTIVITYID,ActivityHandler.ACTIVITYNAME,ActivityHandler.CALORIES,ActivityHandler.TIME};//columns
        Cursor cursor = activitydatabase.query(ActivityHandler.ACTIVITYTABLE,columns,null,null,null,null,null);//query rather than raw query used
        StringBuffer buffer = new StringBuffer();//buffer to hold results
        //cursor is returned as address space
        while(cursor.moveToNext()){
            //columns are returned at relevant index
            int index1 = cursor.getColumnIndex(ActivityHandler.ACTIVITYID);
            int index2 = cursor.getColumnIndex(ActivityHandler.ACTIVITYNAME);
            int index3 = cursor.getColumnIndex(ActivityHandler.CALORIES);
            int index4 = cursor.getColumnIndex(ActivityHandler.TIME);
            //stored in strings
            String activityid = cursor.getString(index1);
            String activityname = cursor.getString(index2);
            String activitycals= cursor.getString(index3);
            String activitytime= cursor.getString(index4);
            //appended to a buffer and returned to be assigned to android text view
            buffer.append("Activity: " +activityname + "\nCalories: " + activitycals +"\n"+"Time: "+ activitytime +"\n\n");
        }
        return buffer.toString();
    }
}