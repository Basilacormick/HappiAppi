package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    12/09/2014.
 * This programme allows the user to search the food database by food group using a spinner
 */
public class SearchFoodGroup extends Activity implements AdapterView.OnItemSelectedListener {

    TextView foodgroup;
    TextView foodgroupnutrientitle;
    TextView foodgroupservingtitle;
    Spinner foodspinner;//food spinner created
    ArrayList<String> fooditems = new ArrayList<String>();

    String[]additems = {"Fruit","Vegetable","Meat","Drink"};//items for group search

    FoodDatabaseHandler foodgrouphandler;//food database

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfoodgroup);//set screen to foodgroup search using unique id

        foodgroup = (TextView) findViewById(R.id.foodgroupresult);
        foodgroupnutrientitle = (TextView) findViewById(R.id.foodgroupNutrienttitle);
        foodgroupservingtitle = (TextView) findViewById(R.id.foodgroupServingsize);

        foodspinner = (Spinner) findViewById(R.id.foodgroupspinner);
        foodspinner.setOnItemSelectedListener(this);//set on item selected for food spinner

        for(int i = 0;i <4;i ++){
            fooditems.add(additems[i]);//add items to spinner
        }

        ArrayAdapter<String>foodAdapter = new ArrayAdapter<String>(SearchFoodGroup.this,android.R.layout.simple_spinner_dropdown_item,fooditems);//link to xml with unique id

        foodspinner.setAdapter(foodAdapter);
        foodspinner.setOnItemSelectedListener(this);

        foodgrouphandler = new FoodDatabaseHandler(getBaseContext());//food database and current context

        foodgroup.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position == 0){
            String foodgroupdata = foodgrouphandler.returnInfo("1");//search performed using food groupid and returnInfo(); method which uses a left join
            foodgroupnutrientitle.setVisibility(View.VISIBLE);
            foodgroupservingtitle.setVisibility(View.VISIBLE);
            foodgroup.setText(foodgroupdata);//set result
            Toast.makeText(getBaseContext(), "Fruit", Toast.LENGTH_LONG).show();//toast message
        }else if(position == 1){
            foodgroupnutrientitle.setVisibility(View.VISIBLE);
            foodgroupservingtitle.setVisibility(View.VISIBLE);
            String foodgroupdata = foodgrouphandler.returnInfo("2");
            foodgroup.setText(foodgroupdata);
            Toast.makeText(getBaseContext(), "Vegetables", Toast.LENGTH_LONG).show();
        }else if(position == 2){
            foodgroupnutrientitle.setVisibility(View.VISIBLE);
            foodgroupservingtitle.setVisibility(View.VISIBLE);
            String foodgroupdata = foodgrouphandler.returnInfo("3");
            foodgroup.setText(foodgroupdata);
            Toast.makeText(getBaseContext(), "Meat", Toast.LENGTH_LONG).show();
        }else if(position == 3){
            foodgroupnutrientitle.setVisibility(View.VISIBLE);
            foodgroupservingtitle.setVisibility(View.VISIBLE);
            String foodgroupdata = foodgrouphandler.returnInfo("4");
            foodgroup.setText(foodgroupdata);
            Toast.makeText(getBaseContext(), "Drink", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //method to return the user to the welcome screen
    public void happilogoSearchfoodgroup(View v)
    {
        Intent myIntent = new Intent(this, FoodNutritionSearch.class);
        startActivity(myIntent);
        finish();
    }
    @Override
    public void onBackPressed() {

    }
}