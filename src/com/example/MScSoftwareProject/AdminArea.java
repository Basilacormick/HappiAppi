package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    21/09/2014
 * This programme launches the administrator are with 3 buttons to allow the administrator to update the food database
 */
public class AdminArea extends Activity implements View.OnClickListener {
    //buttons created for admin options
    Button insertbutton;
    Button deletebutton;
    Button updatebutton;
    Button scraper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminarea);//admin screen launched using unique id
        //3 buttons linked to xml using unique id and onClick listeners are set
        insertbutton = (Button) findViewById(R.id.launchinsert);
        insertbutton.setOnClickListener(this);

        deletebutton = (Button) findViewById(R.id.deleteitem);
        deletebutton.setOnClickListener(this);

        updatebutton = (Button) findViewById(R.id.update);
        updatebutton.setOnClickListener(this);

        scraper = (Button) findViewById(R.id.launchscraper);
        scraper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //relevant screen launched based on choice made
        if (v.getId() == insertbutton.getId()) {
            Intent myIntent = new Intent(this, AdminAddItem.class);
            startActivityForResult(myIntent, 0);
        } else if (v.getId() == deletebutton.getId()) {
            Intent myIntent = new Intent(this, AdminDeleteItem.class);
            startActivityForResult(myIntent, 0);
        } else if (v.getId() == updatebutton.getId()) {
            Intent myIntent = new Intent(this, AdminUpdateTable.class);
            startActivityForResult(myIntent, 0);
        }else if (v.getId() == scraper.getId()) {
            Intent myIntent = new Intent(this, Scrapertool.class);
            startActivityForResult(myIntent, 0);
        }
    }
    //method to return the user to the welcome screen onClick of logo
    public void happilogoAdminhometime(View v) {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }
    //disable back button from admin area screen
    @Override
    public void onBackPressed() {
    }
}