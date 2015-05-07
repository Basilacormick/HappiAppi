package com.example.MScSoftwareProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    09/09/2014.
 * This programme allows the user to log calories consumed manually, using a dialog box or using the scanner facility
 */
public class LogCalories extends Activity implements View.OnClickListener {

    //textviews of user calorie data
    TextView calorieintake;
    TextView calorielog;
    TextView calorietarget;
    TextView calorieburn;
    TextView calorieconsumed;
    //textviews for the date and time
    TextView date;
    TextView time;
    //button to submit calories
    Button submitcalories;
    //image button to launch food dialog or food scanner
    ImageButton choosefood,foodScanner;

    String thedate;
    String thetime;

    ActivityHandler storeactivity;//activity database

    String[]foodgroup = {"Fruit","Vegetable","Meat","Drink"};

    static int getfoodcalories;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logcalories);//set to log calories screen using unique id

        calorieintake = (TextView) findViewById(R.id.targetcalories);
        calorielog = (TextView) findViewById(R.id.inputcalories);
        calorietarget = (TextView) findViewById(R.id.dailytarget);
        calorieburn = (TextView) findViewById(R.id.caloriesburned);
        calorieconsumed = (TextView) findViewById(R.id.caloriesconsumed);
        date = (TextView) findViewById(R.id.currentdate);
        time = (TextView) findViewById(R.id.currenttime);

        //submit calories
        submitcalories = (Button) findViewById(R.id.buttoncaloriesubmit);
        submitcalories.setOnClickListener(this);
        //choose food items from dialog box
        choosefood = (ImageButton) findViewById(R.id.foodchoices);
        choosefood.setOnClickListener(this);
        //scan food item
        foodScanner = (ImageButton) findViewById(R.id.foodBomb);
        foodScanner.setOnClickListener(this);

        //get shared preferences to present information to user
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        int targetcal = preferences.getInt("kcal", 0);//kcal is the target calories, it does not change
        int cals = preferences.getInt("kcals", 0);//kcals is the calorie budget and is changed as the user logs calories
        int calsburned = preferences.getInt("calsburned",0);//this is the calories burned
        //populate text views with information
        calorieintake.setText("" + cals);
        calorietarget.setText(""+targetcal);
        calorieburn.setText(""+calsburned);

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        int calsconsumption = preference.getInt("calsconsumed", 0);//total calories consumed

        calorieconsumed.setText("" + calsconsumption);

        if(cals < 0){
            calorieintake.setTextColor(Color.RED);
            Toast toast = Toast.makeText(this, "You have reached your daily calorie target", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
        }
        else {
            calorieintake.setTextColor(Color.GREEN);
        }

        thedate = new SimpleDateFormat("MMM,dd,yyyy").format(Calendar.getInstance().getTime());
        thetime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        date.setText(thedate);
        time.setText(thetime);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == submitcalories.getId()) {
            //if submit calories is selected the calories consumed and date are stored in a database to be view at viewactivity
            storeactivity = new ActivityHandler(getBaseContext());
            storeactivity.open();
            int calsstore = Integer.parseInt(calorielog.getText().toString());//get calories logged
            String storedate = new SimpleDateFormat("hh:mm a 'on' MMMM dd yyyy").format(Calendar.getInstance().getTime());//current time
            storeactivity.addActivity("Calories Consumed", calsstore, storedate);//activity added
            storeactivity.close();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            int cals = preferences.getInt("kcals", 0);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("kcals", cals - Integer.parseInt(calorielog.getText().toString()));//as calories logged they are subtracted from budget
            editor.commit();

            cals = preferences.getInt("kcals", 0);
            calorieintake.setText("" + cals);

            SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
            int calsconsumed = preference.getInt("calsconsumed", 0);

            SharedPreferences.Editor editor1 = preferences.edit();
            editor1.putInt("calsconsumed", calsconsumed + Integer.parseInt(calorielog.getText().toString()));//calories logged added to calories consumed file
            editor1.commit();

            calsconsumed = preference.getInt("calsconsumed", 0);
            calorieconsumed.setText("" + calsconsumed);

            if (cals < 0) {
                calorieintake.setTextColor(Color.RED);
            } else {
                calorieintake.setTextColor(Color.GREEN);
            }

            Toast toast = Toast.makeText(this, "Calories Logged", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();

            calorielog.setText("");
        } else if (v.getId() == choosefood.getId()) {//if food dialog chosen

            final AlertDialog.Builder foodchoice = new AlertDialog.Builder(this);//set dialog builder

            foodchoice.setTitle("Choose Food Group");
            foodchoice.setItems(foodgroup, new DialogInterface.OnClickListener() {//onClick listener
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String[] fruitconsumed = {"Apple", "Banana", "Orange", "Pineapple", "Grapes", "Avacado","Strawberry"};//fruit options
                    if (which == 0) {
                        AlertDialog.Builder fruitchooser = new AlertDialog.Builder(LogCalories.this);//set dialog builder
                        fruitchooser.setTitle("Select fruit");
                        fruitchooser.setItems(fruitconsumed,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String [] portionsize = {"Small","Medium","Large","Extra Large"};//portion size
                                if(which == 0){
                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);//set dialog builder
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //calories recorded based on item chosen, user can then log calories
                                            if(which == 0){
                                                getfoodcalories += 65;
                                            }else if(which == 1){
                                                getfoodcalories += 72;
                                            }else if(which == 2){
                                                getfoodcalories += 98;
                                            }else if(which == 3){
                                                getfoodcalories += 120;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                }else if(which == 1){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 80;
                                            }else if(which == 1){
                                                getfoodcalories += 95;
                                            }else if(which == 2){
                                                getfoodcalories += 108;
                                            }else if(which == 3){
                                                getfoodcalories += 139;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 2){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 50;
                                            }else if(which == 1){
                                                getfoodcalories += 70;
                                            }else if(which == 2){
                                                getfoodcalories += 90;
                                            }else if(which == 3){
                                                getfoodcalories += 110;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 3){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 50;
                                            }else if(which == 1){
                                                getfoodcalories += 70;
                                            }else if(which == 2){
                                                getfoodcalories += 90;
                                            }else if(which == 3){
                                                getfoodcalories += 110;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 4){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 60;
                                            }else if(which == 1){
                                                getfoodcalories += 80;
                                            }else if(which == 2){
                                                getfoodcalories += 100;
                                            }else if(which == 3){
                                                getfoodcalories += 120;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 5){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 160;
                                            }else if(which == 1){
                                                getfoodcalories += 270;
                                            }else if(which == 2){
                                                getfoodcalories += 340;
                                            }else if(which == 3){
                                                getfoodcalories += 390;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 6){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 33;
                                            }else if(which == 1){
                                                getfoodcalories += 47;
                                            }else if(which == 2){
                                                getfoodcalories += 80;
                                            }else if(which == 3){
                                                getfoodcalories += 113;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                }
                            }
                        });
                        fruitchooser.show();

                    } else if(which == 1){
                        String [] vegetablesconsumed = {"Carrot","Brussel Sprouts","Cabbage","Turnip","Potatoe","Peas","Cauliflower"};
                        AlertDialog.Builder vegchooser = new AlertDialog.Builder(LogCalories.this);
                        vegchooser.setTitle("Select vegetable");
                        vegchooser.setItems(vegetablesconsumed,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String [] portionsize = {"Small","Medium","Large","Extra Large"};
                                if(which == 0){
                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 41;
                                            }else if(which == 2){
                                                getfoodcalories += 55;
                                            }else if(which == 3){
                                                getfoodcalories += 65;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                }else if(which == 1){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 38;
                                            }else if(which == 2){
                                                getfoodcalories += 50;
                                            }else if(which == 3){
                                                getfoodcalories += 80;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 2){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 17;
                                            }else if(which == 1){
                                                getfoodcalories += 25;
                                            }else if(which == 2){
                                                getfoodcalories += 35;
                                            }else if(which == 3){
                                                getfoodcalories += 50;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 3){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 28;
                                            }else if(which == 1){
                                                getfoodcalories += 36;
                                            }else if(which == 2){
                                                getfoodcalories += 51;
                                            }else if(which == 3){
                                                getfoodcalories += 87;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 4){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 77;
                                            }else if(which == 1){
                                                getfoodcalories += 130;
                                            }else if(which == 2){
                                                getfoodcalories += 163;
                                            }else if(which == 3){
                                                getfoodcalories += 283;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 5){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 60;
                                            }else if(which == 1){
                                                getfoodcalories += 90;
                                            }else if(which == 2){
                                                getfoodcalories += 120;
                                            }else if(which == 3){
                                                getfoodcalories += 150;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 6){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 40;
                                            }else if(which == 2){
                                                getfoodcalories += 65;
                                            }else if(which == 3){
                                                getfoodcalories += 95;
                                            }
                                        }
                                    });
                                    portionsizes.show();
                                }
                            }
                        });
                        vegchooser.show();
                    } else if(which == 2){
                        String [] meatsconsumed = {"Beef","Chicken","Pork","Tuna","Salmon","Cod"};
                        AlertDialog.Builder vegchooser = new AlertDialog.Builder(LogCalories.this);
                        vegchooser.setTitle("Select vegetable");
                        vegchooser.setItems(meatsconsumed,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String [] portionsize = {"Small","Medium","Large","Extra Large"};
                                if(which == 0){
                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 41;
                                            }else if(which == 2){
                                                getfoodcalories += 55;
                                            }else if(which == 3){
                                                getfoodcalories += 65;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                }else if(which == 1){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 38;
                                            }else if(which == 2){
                                                getfoodcalories += 50;
                                            }else if(which == 3){
                                                getfoodcalories += 80;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 2){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 17;
                                            }else if(which == 1){
                                                getfoodcalories += 25;
                                            }else if(which == 2){
                                                getfoodcalories += 35;
                                            }else if(which == 3){
                                                getfoodcalories += 50;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 3){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 28;
                                            }else if(which == 1){
                                                getfoodcalories += 36;
                                            }else if(which == 2){
                                                getfoodcalories += 51;
                                            }else if(which == 3){
                                                getfoodcalories += 87;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 4){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 77;
                                            }else if(which == 1){
                                                getfoodcalories += 130;
                                            }else if(which == 2){
                                                getfoodcalories += 163;
                                            }else if(which == 3){
                                                getfoodcalories += 283;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 5){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 60;
                                            }else if(which == 1){
                                                getfoodcalories += 90;
                                            }else if(which == 2){
                                                getfoodcalories += 120;
                                            }else if(which == 3){
                                                getfoodcalories += 150;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 6){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 40;
                                            }else if(which == 2){
                                                getfoodcalories += 65;
                                            }else if(which == 3){
                                                getfoodcalories += 95;
                                            }
                                        }
                                    });
                                    portionsizes.show();
                                }
                            }
                        });
                        vegchooser.show();

                    } else if(which == 3){

                        String [] drinksconsumed = {"Lemonade","Orange Juice","Tea","Milk","Apple Juice","Cranberry"};
                        AlertDialog.Builder vegchooser = new AlertDialog.Builder(LogCalories.this);
                        vegchooser.setTitle("Select vegetable");
                        vegchooser.setItems(drinksconsumed,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String [] portionsize = {"Small","Medium","Large","Extra Large"};
                                if(which == 0){
                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 41;
                                            }else if(which == 2){
                                                getfoodcalories += 55;
                                            }else if(which == 3){
                                                getfoodcalories += 65;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                }else if(which == 1){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 38;
                                            }else if(which == 2){
                                                getfoodcalories += 50;
                                            }else if(which == 3){
                                                getfoodcalories += 80;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 2){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 17;
                                            }else if(which == 1){
                                                getfoodcalories += 25;
                                            }else if(which == 2){
                                                getfoodcalories += 35;
                                            }else if(which == 3){
                                                getfoodcalories += 50;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 3){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 28;
                                            }else if(which == 1){
                                                getfoodcalories += 36;
                                            }else if(which == 2){
                                                getfoodcalories += 51;
                                            }else if(which == 3){
                                                getfoodcalories += 87;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 4){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 77;
                                            }else if(which == 1){
                                                getfoodcalories += 130;
                                            }else if(which == 2){
                                                getfoodcalories += 163;
                                            }else if(which == 3){
                                                getfoodcalories += 283;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 5){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 60;
                                            }else if(which == 1){
                                                getfoodcalories += 90;
                                            }else if(which == 2){
                                                getfoodcalories += 120;
                                            }else if(which == 3){
                                                getfoodcalories += 150;
                                            }
                                        }
                                    });
                                    portionsizes.show();

                                } else if(which == 6){

                                    AlertDialog.Builder portionsizes = new AlertDialog.Builder(LogCalories.this);
                                    portionsizes.setTitle("Select Portion Size");
                                    portionsizes.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            calorielog.setText(""+getfoodcalories);
                                            getfoodcalories = 0;
                                        }
                                    });
                                    portionsizes.setSingleChoiceItems(portionsize,-1,new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if(which == 0){
                                                getfoodcalories += 25;
                                            }else if(which == 1){
                                                getfoodcalories += 40;
                                            }else if(which == 2){
                                                getfoodcalories += 65;
                                            }else if(which == 3){
                                                getfoodcalories += 95;
                                            }
                                        }
                                    });
                                    portionsizes.show();
                                }
                            }
                        });
                        vegchooser.show();
                    }
                }
            });
            foodchoice.show();
            //if scanner is chosen
        }else if(v.getId()== foodScanner.getId()){
            //scanner is launched using the IntentIntegrator class with the result received using the IntentResult created by Sean Owen,
            // Fred Lin, Isaac Potoczny-Jones, Brad Drehmer and gcstang, the software is zxing also know as Zebra crossing found at
            // https://github.com/zxing/zxing/
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    //result of scan is retrieved here
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //results are retrieved and the calorielog text view is set to allow user to log calories
            String scanContent = scanningResult.getContents();
            calorielog.setText(scanContent);
        }else{
            //no scan received
            Toast toast = Toast.makeText(getApplicationContext(),"No scan received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    //method to take user to welcome screen
    public void happilogoCalorie(View v)
    {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }
    //disable back button from welcome screen
    @Override
    public void onBackPressed() {

    }
}