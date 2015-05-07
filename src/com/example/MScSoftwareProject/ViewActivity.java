package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    29/10/2014.
 * This programme shows the contents of the SQLite activity database and represented a log of the user calories consumed
 * and calories burned
 */
public class ViewActivity extends Activity {

    TextView activities;
    ActivityHandler activityhandler;//activity database declared

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewactivity);//set to viewactivity using unique id

        activities = (TextView) findViewById(R.id.activitys);
        activityhandler = new ActivityHandler(getBaseContext());//activity database instantiated

        String getactivities = activityhandler.returnActivities();//method called to return all activities

        //patterns and matches sought to change colour of text for user
        Pattern findpattern = Pattern.compile("Calories Consumed");
        Pattern findpattern1 = Pattern.compile("Calories Burned");
        Matcher findmatch = findpattern.matcher(getactivities);
        Matcher findmatch1 = findpattern1.matcher(getactivities);
        //text stored here
        SpannableStringBuilder textspanner = new SpannableStringBuilder(getactivities);
        //text search here for matches and colours are applied accordingly
        for (int x = 0;x < getactivities.length();x++) {
            if (findmatch.find()) {
                textspanner.setSpan(new ForegroundColorSpan(Color.YELLOW), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            } else if (findmatch1.find()) {
                textspanner.setSpan(new ForegroundColorSpan(Color.GREEN), findmatch1.start(), findmatch1.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
        activities.setText(textspanner);//textview set with all text with colours applied to selected text
    }
    //method to return user to welcome screen
    public void happilogoViewactivity(View v)
    {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }
    @Override
    public void onBackPressed() {

    }
}