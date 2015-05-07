package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    10/09/2014
 * This programme shows the user results once the user has input their user profile at OptimalCalories
 */
    public class CalorieResults extends Activity implements View.OnClickListener {

        //text view variables show information
        TextView calorieintake;
        TextView bmi;
        TextView bmititle;
        TextView bmiresulttext;
        TextView currentweight;
        TextView idealweight;

        static int calories;

        //timer set to 24 hours
        private countDown calorieTimer;
        long timelimit = 15000;
        long segments = 1000;

        Button plotter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorieresults);//calorie results screen launched using unique id

        //textviews linked to xml using unique id
        calorieintake = (TextView) findViewById(R.id.calorieResults);
        bmi = (TextView) findViewById(R.id.bmiresult);
        bmititle = (TextView) findViewById(R.id.bmiresulttitle);
        bmiresulttext = (TextView) findViewById(R.id.bmitextresult);
        currentweight = (TextView) findViewById(R.id.usercurrentweight);
        idealweight = (TextView) findViewById(R.id.useridealweight);

        plotter = (Button) findViewById(R.id.plotresults);
        plotter.setOnClickListener(this);
        //new 24 hour calorie timer is created
        calorieTimer = new countDown(timelimit,segments);
        //getIntent is to get values put as extras in the OptimalCalorie area
        Intent intent = getIntent();
        calories = intent.getIntExtra("Calories", 0);
        double bmiresult = intent.getDoubleExtra("Bmi", 0);
        String bmitextresult = intent.getStringExtra("Bmitext");
        double userscurrentweight = intent.getDoubleExtra("Currentweight",0);
        double idealcurrentweight = intent.getDoubleExtra("Idealweight",0);

        //set textview information for user to view
        calorieintake.setText(""+calories);
        bmi.setText(""+bmiresult);
        bmititle.setVisibility(1);
        bmiresulttext.setText(bmitextresult);
        currentweight.setText(""+userscurrentweight+" kgs");
        idealweight.setText(""+idealcurrentweight+" kgs");

        //shared preference to store calories
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        int counter = preferences.getInt("kcals",0);
        //kcals is the value updated using calorie or exercise log, kcal is static and shows daily target
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("kcals", calories);
        editor.putInt("kcal", calories);
        editor.commit();

        calorieTimer.start();//timer started
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==plotter.getId()){
            Intent myIntent = new Intent(this, CalorieGraph.class);
            startActivityForResult(myIntent, 0);
        }
    }

    public class countDown extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public countDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            //once timer completes the values are set to their original starting values the day before
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CalorieResults.this);
            int counter = preferences.getInt("kcals",0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("kcals", calories);
            editor.putInt("kcal", calories);
            editor.putInt("calsconsumed", 0);
            editor.putInt("calsburned", 0);
            editor.commit();

            calorieTimer.start();//timer starts over again for another 24 hour period
        }
     }

        public void calorieResultLogo(View v)
        {
            Intent myIntent = new Intent(this, WelcomeMenu.class);
            startActivityForResult(myIntent, 0);
            finish();
        }

        @Override
        public void onBackPressed() {

        }
  }
