package com.example.MScSoftwareProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    21/09/2014
 * This programme delivers the SQLite food database along with the methods to manipulate and search this database for food and
 * drink items
 */
public class FoodDatabaseHandler {
    //Columns for database
    public static final String ITEMID = "itemid";
    public static final String ITEMNAME = "itemname";
    public static final String CARBS = "carbs";
    public static final String PROTEIN = "protein";
    public static final String SUGARS = "sugar";
    public static final String FATS = "fat";
    public static final String SATURATES = "saturates";
    public static final String FIBER = "fiber";
    public static final String SODIUM = "sodium";
    public static final String CHOLESTEROL = "cholesterol";
    public static final String TOTALCALORIES = "totalccalories";
    public static final String FGROUPID = "fgroupid";

    //fooditem name
    public static final String NUTRIENTSTABLE = "nutrientstable";
    //database name
    public static final String FOODDATABASE = "fooddatbase";
    //database version
    public static final int FDVERSION = 63;
    //SQL statement
    public static final String FDCREATENUTRIENTSTABLE = "CREATE TABLE " + NUTRIENTSTABLE + "(" + ITEMID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + ITEMNAME + " TEXT," + CARBS + " INTEGER," + PROTEIN + " INTEGER," + SUGARS + " INTEGER," + FATS + " INTEGER," + SATURATES + " INTEGER," + FIBER + " INTEGER," + SODIUM + " INTEGER," + CHOLESTEROL + " INTEGER," + TOTALCALORIES + " INTEGER," + FGROUPID + " INTEGER" + ")";

    //food group table
    public static final String FOODGROUPID = "foodgroupid";
    public static final String FOODNAME = "foodgroupname";

    public static final String FOODGROUPTABLE = "foodgrouptable";

    public static final String FDCREATEFOODGROUPTABLE = "CREATE TABLE " + FOODGROUPTABLE + "(" + FOODGROUPID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + FOODNAME + " TEXT" + ")";

    //vitamin table
    public static final String VITID = "vitaminid";
    public static final String VITA = "vitaminA";
    public static final String VITB = "vitaminB";
    public static final String VITC = "vitaminC";
    public static final String VITD = "vitaminD";
    public static final String VITE = "vitaminE";
    //vitamin table name
    public static final String VITTABLE = "vitamintable";
    //SQL statement to create vitamin table
    public static final String FDCREATEVITTABLE = "CREATE TABLE " + VITTABLE + "(" + VITID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + VITA + " INTEGER," + VITB + " INTEGER," + VITC + " INTEGER," + VITD + " INTEGER," + VITE + " INTEGER" + ")";

    //mineral table
    public static final String MINID = "minid";
    public static final String CALCIUM = "calcium";
    public static final String IRON = "iron";
    public static final String POTASSIUM = "potassium";
    public static final String BETACAROTENE = "betacarotene";
    public static final String PHOSPHORUS = "phosphorus";
    //mineral table name
    public static final String MINTABLE = "mineraltable";
    //SQL statement to create mineral table
    public static final String FDCREATEMINTABLE = "CREATE TABLE " + MINTABLE + "(" + MINID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + CALCIUM + " INTEGER," + IRON + " INTEGER," + POTASSIUM + " INTEGER," + BETACAROTENE + " INTEGER," + PHOSPHORUS + " INTEGER" + ")";

    FoodDatabaseHelper fooddatabasehelp;
    Context foodcontext;
    SQLiteDatabase fooddatabase;
    //food database constructor
    public FoodDatabaseHandler(Context context) {
        this.foodcontext = context;
        fooddatabasehelp = new FoodDatabaseHelper(context);
    }
    //food database helper class
    private static class FoodDatabaseHelper extends SQLiteOpenHelper {

        public FoodDatabaseHelper(Context context) {
            super(context, FOODDATABASE, null, FDVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //4 tables are created here using SQL create commands declared above
            db.execSQL(FDCREATENUTRIENTSTABLE);
            db.execSQL(FDCREATEFOODGROUPTABLE);
            db.execSQL(FDCREATEVITTABLE);
            db.execSQL(FDCREATEMINTABLE);


            //fruit items are added into the food database
            ContentValues foodvalues = new ContentValues();
            foodvalues.put(ITEMNAME, "Apple");
            foodvalues.put(CARBS, 14);
            foodvalues.put(PROTEIN, 0.3);
            foodvalues.put(SUGARS, 10);
            foodvalues.put(FATS, 0.2);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 2.4);
            foodvalues.put(SODIUM, 0);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 52);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Banana");
            foodvalues.put(CARBS, 23);
            foodvalues.put(PROTEIN, 1.1);
            foodvalues.put(SUGARS, 12);
            foodvalues.put(FATS, 0.3);
            foodvalues.put(SATURATES, 0.1);
            foodvalues.put(FIBER, 2.6);
            foodvalues.put(SODIUM, 1);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 89);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Orange");
            foodvalues.put(CARBS, 12);
            foodvalues.put(PROTEIN, 0.9);
            foodvalues.put(SUGARS, 9);
            foodvalues.put(FATS, 0.1);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 2.4);
            foodvalues.put(SODIUM, 0);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 47);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Pineapple");
            foodvalues.put(CARBS, 13);
            foodvalues.put(PROTEIN, 0.5);
            foodvalues.put(SUGARS, 10);
            foodvalues.put(FATS, 0.1);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 1.4);
            foodvalues.put(SODIUM, 1);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 50);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Pear");
            foodvalues.put(CARBS, 15);
            foodvalues.put(PROTEIN, 0.4);
            foodvalues.put(SUGARS, 10);
            foodvalues.put(FATS, 0.1);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 3.1);
            foodvalues.put(SODIUM, 1);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 57);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Plum");
            foodvalues.put(CARBS, 11);
            foodvalues.put(PROTEIN, 0.7);
            foodvalues.put(SUGARS, 10);
            foodvalues.put(FATS, 0.3);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 1.4);
            foodvalues.put(SODIUM, 0);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 46);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Kiwi");
            foodvalues.put(CARBS, 15);
            foodvalues.put(PROTEIN, 1.1);
            foodvalues.put(SUGARS, 9);
            foodvalues.put(FATS, 0.5);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 3);
            foodvalues.put(SODIUM, 3);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 61);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Peach");
            foodvalues.put(CARBS, 10);
            foodvalues.put(PROTEIN, 0.9);
            foodvalues.put(SUGARS, 8);
            foodvalues.put(FATS, 0.2);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 1.5);
            foodvalues.put(SODIUM, 0);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 39);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Strawberry");
            foodvalues.put(CARBS, 8);
            foodvalues.put(PROTEIN, 0.7);
            foodvalues.put(SUGARS, 4.9);
            foodvalues.put(FATS, 0.3);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 2);
            foodvalues.put(SODIUM, 1);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 33);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Melon");
            foodvalues.put(CARBS, 8);
            foodvalues.put(PROTEIN, 0.8);
            foodvalues.put(SUGARS, 8);
            foodvalues.put(FATS, 0.2);
            foodvalues.put(SATURATES, 0.1);
            foodvalues.put(FIBER, 0.9);
            foodvalues.put(SODIUM, 0.1);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 34);
            foodvalues.put(FGROUPID, 1);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            //vegetable items added
            foodvalues.put(ITEMNAME, "Asparugus");
            foodvalues.put(CARBS, 3.9);
            foodvalues.put(PROTEIN, 2.2);
            foodvalues.put(SUGARS, 1.9);
            foodvalues.put(FATS, 0.1);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 2.1);
            foodvalues.put(SODIUM, 0.2);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 20);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Broccoli");
            foodvalues.put(CARBS, 7);
            foodvalues.put(PROTEIN, 2.8);
            foodvalues.put(SUGARS, 1.7);
            foodvalues.put(FATS, 0.4);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 2.6);
            foodvalues.put(SODIUM, 0.3);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 34);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Brussels Sprouts");
            foodvalues.put(CARBS, 9);
            foodvalues.put(PROTEIN, 3.4);
            foodvalues.put(SUGARS, 2.2);
            foodvalues.put(FATS, 0.3);
            foodvalues.put(SATURATES, 0.1);
            foodvalues.put(FIBER, 3.8);
            foodvalues.put(SODIUM, 0.25);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 43);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Carrot");
            foodvalues.put(CARBS, 10);
            foodvalues.put(PROTEIN, 0.9);
            foodvalues.put(SUGARS, 4.7);
            foodvalues.put(FATS, 0.2);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 2.8);
            foodvalues.put(SODIUM, 0.69);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 41);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Cabbage");
            foodvalues.put(CARBS, 6);
            foodvalues.put(PROTEIN, 1.3);
            foodvalues.put(SUGARS, 3.2);
            foodvalues.put(FATS, 0.1);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 2.5);
            foodvalues.put(SODIUM, 0.18);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 25);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Cauliflower");
            foodvalues.put(CARBS, 58);
            foodvalues.put(PROTEIN, 1.9);
            foodvalues.put(SUGARS, 1.9);
            foodvalues.put(FATS, 0.3);
            foodvalues.put(SATURATES, 0.1);
            foodvalues.put(FIBER, 2);
            foodvalues.put(SODIUM, 0.3);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 25);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Celery");
            foodvalues.put(CARBS, 3);
            foodvalues.put(PROTEIN, 0.7);
            foodvalues.put(SUGARS, 1.8);
            foodvalues.put(FATS, 0.2);
            foodvalues.put(SATURATES, 0.1);
            foodvalues.put(FIBER, 0.2);
            foodvalues.put(SODIUM, 0.8);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 16);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Corn");
            foodvalues.put(CARBS, 74);
            foodvalues.put(PROTEIN, 9);
            foodvalues.put(SUGARS, 8);
            foodvalues.put(FATS, 4.7);
            foodvalues.put(SATURATES, 0.7);
            foodvalues.put(FIBER, 0.9);
            foodvalues.put(SODIUM, 0.1);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 365);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Lettuce");
            foodvalues.put(CARBS, 2.9);
            foodvalues.put(PROTEIN, 1.4);
            foodvalues.put(SUGARS, 0.8);
            foodvalues.put(FATS, 0.2);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 1.3);
            foodvalues.put(SODIUM, 0.28);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 15);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Leek");
            foodvalues.put(CARBS, 14);
            foodvalues.put(PROTEIN, 1.5);
            foodvalues.put(SUGARS, 3.9);
            foodvalues.put(FATS, 0.3);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 1.8);
            foodvalues.put(SODIUM, 0.2);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 61);
            foodvalues.put(FGROUPID, 2);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            //meat items added
            foodvalues.put(ITEMNAME, "Beef");
            foodvalues.put(CARBS, 0);
            foodvalues.put(PROTEIN, 26);
            foodvalues.put(SUGARS, 0);
            foodvalues.put(FATS, 6);
            foodvalues.put(SATURATES, 0.5);
            foodvalues.put(FIBER, 0);
            foodvalues.put(SODIUM, 0.72);
            foodvalues.put(CHOLESTEROL, 0.90);
            foodvalues.put(TOTALCALORIES, 250);
            foodvalues.put(FGROUPID, 3);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Chicken");
            foodvalues.put(CARBS, 0);
            foodvalues.put(PROTEIN, 25);
            foodvalues.put(SUGARS, 0);
            foodvalues.put(FATS, 13);
            foodvalues.put(SATURATES, 3.5);
            foodvalues.put(FIBER, 0);
            foodvalues.put(SODIUM, 0.67);
            foodvalues.put(CHOLESTEROL, 0.78);
            foodvalues.put(TOTALCALORIES, 219);
            foodvalues.put(FGROUPID, 3);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Pork");
            foodvalues.put(CARBS, 0.2);
            foodvalues.put(PROTEIN, 22);
            foodvalues.put(SUGARS, 0);
            foodvalues.put(FATS, 9);
            foodvalues.put(SATURATES, 2.9);
            foodvalues.put(FIBER, 0);
            foodvalues.put(SODIUM, 0.74);
            foodvalues.put(CHOLESTEROL, 0.65);
            foodvalues.put(TOTALCALORIES, 173);
            foodvalues.put(FGROUPID, 3);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Tuna");
            foodvalues.put(CARBS, 0);
            foodvalues.put(PROTEIN, 30);
            foodvalues.put(SUGARS, 0);
            foodvalues.put(FATS, 6);
            foodvalues.put(SATURATES, 1.6);
            foodvalues.put(FIBER, 0);
            foodvalues.put(SODIUM, 0.50);
            foodvalues.put(CHOLESTEROL, 0.49);
            foodvalues.put(TOTALCALORIES, 184);
            foodvalues.put(FGROUPID, 3);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Salmon");
            foodvalues.put(CARBS, 0);
            foodvalues.put(PROTEIN, 20);
            foodvalues.put(SUGARS, 0);
            foodvalues.put(FATS, 13);
            foodvalues.put(SATURATES, 3);
            foodvalues.put(FIBER, 0);
            foodvalues.put(SODIUM, 0.59);
            foodvalues.put(CHOLESTEROL, 0.55);
            foodvalues.put(TOTALCALORIES, 208);
            foodvalues.put(FGROUPID, 3);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Cod");
            foodvalues.put(CARBS, 0);
            foodvalues.put(PROTEIN, 18);
            foodvalues.put(SUGARS, 0);
            foodvalues.put(FATS, 0.7);
            foodvalues.put(SATURATES, 0.1);
            foodvalues.put(FIBER, 0);
            foodvalues.put(SODIUM, 0.54);
            foodvalues.put(CHOLESTEROL, 0.43);
            foodvalues.put(TOTALCALORIES, 82);
            foodvalues.put(FGROUPID, 3);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            //drink items added
            foodvalues.put(ITEMNAME, "Lemonade");
            foodvalues.put(CARBS, 11);
            foodvalues.put(PROTEIN, 0);
            foodvalues.put(SUGARS, 10);
            foodvalues.put(FATS, 0.2);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 0.1);
            foodvalues.put(SODIUM, 0.4);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 43);
            foodvalues.put(FGROUPID, 4);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Orange Juice");
            foodvalues.put(CARBS, 10);
            foodvalues.put(PROTEIN, 0.7);
            foodvalues.put(SUGARS, 8);
            foodvalues.put(FATS, 0.2);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 0.2);
            foodvalues.put(SODIUM, 0.1);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 45);
            foodvalues.put(FGROUPID, 4);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Tea");
            foodvalues.put(CARBS, 0.2);
            foodvalues.put(PROTEIN, 0.1);
            foodvalues.put(SUGARS, 0);
            foodvalues.put(FATS, 0);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 0);
            foodvalues.put(SODIUM, 0.4);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 1);
            foodvalues.put(FGROUPID, 4);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Milk");
            foodvalues.put(CARBS, 5);
            foodvalues.put(PROTEIN, 3.4);
            foodvalues.put(SUGARS, 5);
            foodvalues.put(FATS, 1);
            foodvalues.put(SATURATES, 0.6);
            foodvalues.put(FIBER, 0);
            foodvalues.put(SODIUM, 0.44);
            foodvalues.put(CHOLESTEROL, 0.5);
            foodvalues.put(TOTALCALORIES, 42);
            foodvalues.put(FGROUPID, 4);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Apple Juice");
            foodvalues.put(CARBS, 11);
            foodvalues.put(PROTEIN, 0.1);
            foodvalues.put(SUGARS, 10);
            foodvalues.put(FATS, 0.1);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 0.2);
            foodvalues.put(SODIUM, 0.4);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 46);
            foodvalues.put(FGROUPID, 4);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            foodvalues.put(ITEMNAME, "Cranberry Juice");
            foodvalues.put(CARBS, 12);
            foodvalues.put(PROTEIN, 0.4);
            foodvalues.put(SUGARS, 12);
            foodvalues.put(FATS, 0.1);
            foodvalues.put(SATURATES, 0);
            foodvalues.put(FIBER, 0.1);
            foodvalues.put(SODIUM, 0.2);
            foodvalues.put(CHOLESTEROL, 0);
            foodvalues.put(TOTALCALORIES, 46);
            foodvalues.put(FGROUPID, 4);
            db.insert(NUTRIENTSTABLE, null, foodvalues);

            ContentValues vitvalues = new ContentValues();

            vitvalues.put(FOODNAME, "fruit");
            db.insert(FOODGROUPTABLE,null,vitvalues);

            vitvalues.put(FOODNAME, "vegetables");
            db.insert(FOODGROUPTABLE,null,vitvalues);

            vitvalues.put(FOODNAME, "meat");
            db.insert(FOODGROUPTABLE,null,vitvalues);

            vitvalues.put(FOODNAME, "drink");
            db.insert(FOODGROUPTABLE,null,vitvalues);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //SQL statement to drop table if exists
            db.execSQL("DROP TABLE IF EXISTS " + NUTRIENTSTABLE);
            db.execSQL("DROP TABLE IF EXISTS " + FOODGROUPTABLE);
            db.execSQL("DROP TABLE IF EXISTS " + VITTABLE);
            db.execSQL("DROP TABLE IF EXISTS " + MINTABLE);
            onCreate(db);
        }
    }
    //method to open database
    public FoodDatabaseHandler open(){
        fooddatabase = fooddatabasehelp.getWritableDatabase();
        return this;
    }
    //method to close database
    public void close(){
        fooddatabasehelp.close();
    }
    //method to insert food item
    public long insertfoodItem(String pitemname,Integer pcarbs,Integer pprotein,Integer psugars,Integer pfats,Integer psaturates,Integer pfiber,Integer psodium,Integer pcholesterol, Integer pcalories){

        ContentValues foodvalues = new ContentValues();
        foodvalues.put(ITEMNAME, pitemname);
        foodvalues.put(CARBS, pcarbs);
        foodvalues.put(PROTEIN, pprotein);
        foodvalues.put(SUGARS, psugars);
        foodvalues.put(FATS, pfats);
        foodvalues.put(SATURATES, psaturates);
        foodvalues.put(FIBER, pfiber);
        foodvalues.put(SODIUM, psodium);
        foodvalues.put(CHOLESTEROL, pcholesterol);
        foodvalues.put(TOTALCALORIES, pcalories);
        return fooddatabase.insert(NUTRIENTSTABLE, null, foodvalues);
    }
    //method to return food info with left join, rawQuery used here
    public String returnInfo(String pitem){

        fooddatabase = fooddatabasehelp.getReadableDatabase();
        Cursor f = fooddatabase.rawQuery("SELECT * FROM nutrientstable LEFT JOIN foodgrouptable ON fgroupid=foodgroupid WHERE fgroupid =?",new String[]{pitem});//foodgroup id used here
        StringBuilder buffer = new StringBuilder();

        while(f.moveToNext()){

            int columnindex1 = f.getColumnIndex(FoodDatabaseHandler.ITEMID);
            int columnindex2 = f.getColumnIndex(FoodDatabaseHandler.ITEMNAME);
            int columnindex3 = f.getColumnIndex(FoodDatabaseHandler.CARBS);
            int columnindex4 = f.getColumnIndex(FoodDatabaseHandler.PROTEIN);
            int columnindex5 = f.getColumnIndex(FoodDatabaseHandler.SUGARS);
            int columnindex6 = f.getColumnIndex(FoodDatabaseHandler.FATS);
            int columnindex7 = f.getColumnIndex(FoodDatabaseHandler.SATURATES);
            int columnindex8 = f.getColumnIndex(FoodDatabaseHandler.FIBER);
            int columnindex9 = f.getColumnIndex(FoodDatabaseHandler.SODIUM);
            int columnindex10 = f.getColumnIndex(FoodDatabaseHandler.CHOLESTEROL);
            int columnindex11 = f.getColumnIndex(FoodDatabaseHandler.TOTALCALORIES);
            int columnindex12 = f.getColumnIndex(FoodDatabaseHandler.FOODGROUPID);
            int columnindex13 = f.getColumnIndex(FoodDatabaseHandler.FOODNAME);

            String itemid = f.getString(columnindex1);
            String itemname = f.getString(columnindex2);
            String carbs = f.getString(columnindex3);
            String protein = f.getString(columnindex4);
            String sugars = f.getString(columnindex5);
            String fats = f.getString(columnindex6);
            String saturates = f.getString(columnindex7);
            String fiber = f.getString(columnindex8);
            String sodium = f.getString(columnindex9);
            String cholesterol = f.getString(columnindex10);
            String totalcalories = f.getString(columnindex11);
            String foodgid = f.getString(columnindex12);
            String foodgname = f.getString(columnindex13);

            buffer.append("Item ID - " + itemid +"\nItem name - " + itemname + "\nCarbs - " +carbs+" g" + "\nProtein - "+protein+" g" +"\nSugars - "+sugars+" g" +"\nFats - "+fats+" g" +"\nSaturates - "+saturates+" g" +"\nFiber - "+fiber+" g" +"\nSodium - "+sodium+" g" +"\nCholesterol - "+cholesterol+" g" +"\nTotal Calories - "+totalcalories+"\n\nFood Group Details" + "\nFood Group ID - "+foodgid+ "\nFood Group Name - " + foodgname+"\n\n");
        }
        return buffer.toString();
    }
    //method to return food item with left join and like sql commands, rawQuery used here
    public String returnItem(String pitem){

        fooddatabase = fooddatabasehelp.getReadableDatabase();
        Cursor f = fooddatabase.rawQuery("SELECT * FROM nutrientstable LEFT JOIN foodgrouptable ON fgroupid=foodgroupid WHERE itemname LIKE ?",new String[]{pitem+"%"});
        StringBuilder buffer = new StringBuilder();

        while(f.moveToNext()){

            int columnindex1 = f.getColumnIndex(FoodDatabaseHandler.ITEMID);
            int columnindex2 = f.getColumnIndex(FoodDatabaseHandler.ITEMNAME);
            int columnindex3 = f.getColumnIndex(FoodDatabaseHandler.CARBS);
            int columnindex4 = f.getColumnIndex(FoodDatabaseHandler.PROTEIN);
            int columnindex5 = f.getColumnIndex(FoodDatabaseHandler.SUGARS);
            int columnindex6 = f.getColumnIndex(FoodDatabaseHandler.FATS);
            int columnindex7 = f.getColumnIndex(FoodDatabaseHandler.SATURATES);
            int columnindex8 = f.getColumnIndex(FoodDatabaseHandler.FIBER);
            int columnindex9 = f.getColumnIndex(FoodDatabaseHandler.SODIUM);
            int columnindex10 = f.getColumnIndex(FoodDatabaseHandler.CHOLESTEROL);
            int columnindex11 = f.getColumnIndex(FoodDatabaseHandler.TOTALCALORIES);
            int columnindex12 = f.getColumnIndex(FoodDatabaseHandler.FOODGROUPID);
            int columnindex13 = f.getColumnIndex(FoodDatabaseHandler.FOODNAME);

            String itemid = f.getString(columnindex1);
            String itemname = f.getString(columnindex2);
            String carbs = f.getString(columnindex3);
            String protein = f.getString(columnindex4);
            String sugars = f.getString(columnindex5);
            String fats = f.getString(columnindex6);
            String saturates = f.getString(columnindex7);
            String fiber = f.getString(columnindex8);
            String sodium = f.getString(columnindex9);
            String cholesterol = f.getString(columnindex10);
            String totalcalories = f.getString(columnindex11);
            String foodgid = f.getString(columnindex12);
            String foodgname = f.getString(columnindex13);

            buffer.append("Item name - " + itemname + "\nCarbs - " +carbs+" g" +"\nProtein - "+protein+" g" +"\nSugars - "+sugars+" g" +"\nFats - "+fats+" g" +"\nSaturates - "+saturates+" g" +"\nFiber - "+fiber+" g" +"\nSodium - "+sodium+" g" +"\nCholesterol - "+cholesterol+" g" +"\nTotal Calories - "+totalcalories+ " kCals"+"\n\nFood Group Details" + "\nFood Group Name - " + foodgname+"\n\n");
        }
        return buffer.toString();
    }

    public void deleteData(String pitemname){

        fooddatabase.delete(NUTRIENTSTABLE,ITEMNAME + "=" + "'"+pitemname+"'",null);
    }

    public void updateTable(String pitemtochange,String pitemname,Integer pcarbs,Integer pprotein,Integer psugars,Integer pfats,Integer psaturates,Integer pfiber,Integer psodium,Integer pcholesterol, Integer pcalories){

        fooddatabase = fooddatabasehelp.getReadableDatabase();

        ContentValues foodvalues = new ContentValues();
        foodvalues.put(ITEMNAME, pitemname);
        foodvalues.put(CARBS, pcarbs);
        foodvalues.put(PROTEIN, pprotein);
        foodvalues.put(SUGARS, psugars);
        foodvalues.put(FATS, pfats);
        foodvalues.put(SATURATES, psaturates);
        foodvalues.put(FIBER, pfiber);
        foodvalues.put(SODIUM, psodium);
        foodvalues.put(CHOLESTEROL, pcholesterol);
        foodvalues.put(TOTALCALORIES, pcalories);
        fooddatabase.update(NUTRIENTSTABLE,foodvalues,ITEMNAME + "=" + "'"+pitemtochange+"'",null);
    }
 }
