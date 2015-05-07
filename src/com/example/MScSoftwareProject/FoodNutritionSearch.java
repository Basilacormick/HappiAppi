package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    09/09/2014.
 * This programme shows the screen options available to search the food database
 */
public class FoodNutritionSearch extends Activity implements View.OnClickListener {
    //buttons declared for 3 options
    Button foodlist;
    Button foodsearch;
    Button foodgroupsearch;

    MediaPlayer soundButtonClick;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodnutritionsearch);//foodnutritionsearch set using unique id

        soundButtonClick = MediaPlayer.create(this, R.raw.buttonclick);//sound file

        //buttons linked to xml using unique ids and onClickListener set
        foodlist = (Button) findViewById(R.id.foodlistbutton);
        foodsearch = (Button) findViewById(R.id.foodsearchbutton);
        foodgroupsearch = (Button)findViewById(R.id.foodgroupbutton);

        foodlist.setOnClickListener(this);
        foodsearch.setOnClickListener(this);
        foodgroupsearch.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        //load relevant screen according to which button pressed
        if (v.getId() == foodlist.getId()) {
            soundButtonClick.start();
            Intent myIntent = new Intent(this, ExpandableFoodList.class);
            startActivityForResult(myIntent, 0);
            this.finish();
        } else if (v.getId() == foodsearch.getId()) {
            soundButtonClick.start();
            Intent myIntent = new Intent(this, FoodDataSearch.class);
            startActivityForResult(myIntent, 0);
            this.finish();
        } else if (v.getId() == foodgroupsearch.getId()) {
            soundButtonClick.start();
            Intent myIntent = new Intent(this, SearchFoodGroup.class);
            startActivityForResult(myIntent, 0);
            this.finish();
        }
    }
    //method to return user to welcome screen
    public void happilogoFoodSearch(View v) {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}