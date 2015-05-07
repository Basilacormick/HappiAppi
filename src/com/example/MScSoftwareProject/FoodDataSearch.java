package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    09/09/2014
 * This programme facilates both the manual and scan searches of the database
 */
public class FoodDataSearch extends Activity implements View.OnClickListener {
    //declare variables
    EditText search;
    Button submitsearch;
    ImageButton foodscanner;
    FoodDatabaseHandler foodhandler;
    TextView searchresult;
    TextView nutrienttitle;
    TextView servsizetitle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fooddatasearch);//set xml screen using unique id

        search = (EditText) findViewById(R.id.searchitem);
        submitsearch = (Button) findViewById(R.id.buttonsubmitsearch);//manual search button
        foodscanner = (ImageButton) findViewById(R.id.scansearch);//launch scanner image button
        searchresult = (TextView) findViewById(R.id.searchreturn);
        nutrienttitle = (TextView) findViewById(R.id.nutrientTitle);
        servsizetitle = (TextView) findViewById(R.id.servingsizetitle);
        //onclick listeners set for both buttons
        submitsearch.setOnClickListener(this);
        foodscanner.setOnClickListener(this);

        foodhandler = new FoodDatabaseHandler(getBaseContext());//food database

    }

    @Override
    public void onClick(View v) {
        //if submit search selected we get the user input and search database
        if(v.getId()==submitsearch.getId()) {
            nutrienttitle.setVisibility(1);
            servsizetitle.setVisibility(1);
            String searchdata = foodhandler.returnItem(search.getText().toString());//database search based on users input
            searchresult.setText(searchdata);
        }else if(v.getId()==foodscanner.getId()){//food scanner choosen
            //scanner is launched using the IntentIntegrator class with the result received using the IntentResult created by Sean Owen,
            // Fred Lin, Isaac Potoczny-Jones, Brad Drehmer and gcstang, the software is zxing also know as Zebra crossing found at
            // https://github.com/zxing/zxing/
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    //the results from the scan are received here
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //result is returned using IntentResult
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

            String databasescan = foodhandler.returnItem(scanningResult.getContents());//scan result used to search database
            searchresult.setText(databasescan);

        }else{
            //toast message if no info received
            Toast toast = Toast.makeText(getApplicationContext(),"No scan received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void happilogoFoodSearch(View v)
    {
        Intent myIntent = new Intent(this, FoodNutritionSearch.class);
        startActivity(myIntent);
        finish();
    }
    @Override
    public void onBackPressed() {

    }
}