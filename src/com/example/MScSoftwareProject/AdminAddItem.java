package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    21/09/2014
 * This programme allows the adminsitrator to add items to the SQLite food database
 */

public class AdminAddItem extends Activity {
    //variable declared
    EditText item, carbs,protein, sugars, fat, saturates, fibrecontent, sodiumcontent, cholesterolcontent, caloriecontent;
    FoodDatabaseHandler foodhandler;//food database
    Button insertitem;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminadditem);//set screen to adminadditem.xml using unique id
        //variables linked to android widget using unique id
        item = (EditText) findViewById(R.id.itemname);
        carbs = (EditText) findViewById(R.id.carbohydrates);
        protein = (EditText) findViewById(R.id.proteincontent);
        sugars = (EditText) findViewById(R.id.sugar);
        fat = (EditText) findViewById(R.id.fats);
        saturates = (EditText) findViewById(R.id.saturates);
        fibrecontent = (EditText) findViewById(R.id.fiber);
        sodiumcontent = (EditText) findViewById(R.id.sodium);
        cholesterolcontent = (EditText) findViewById(R.id.cholesterol);
        caloriecontent = (EditText) findViewById(R.id.calories);
        //button and onClick listener is set
        insertitem = (Button) findViewById(R.id.buttonitemsubmit);

        insertitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get administrators input
                String getitemName = item.getText().toString();
                Integer getcarbs = Integer.parseInt(carbs.getText().toString());
                Integer getprotein = Integer.parseInt(protein.getText().toString());
                Integer getsugar = Integer.parseInt(sugars.getText().toString());
                Integer getfat = Integer.parseInt(fat.getText().toString());
                Integer getsaturates = Integer.parseInt(saturates.getText().toString());
                Integer getfibre = Integer.parseInt(fibrecontent.getText().toString());
                Integer getsodium = Integer.parseInt(sodiumcontent.getText().toString());
                Integer getcholesterol = Integer.parseInt(cholesterolcontent.getText().toString());
                Integer getcalories = Integer.parseInt(caloriecontent.getText().toString());

                foodhandler = new FoodDatabaseHandler(getBaseContext());//food database created
                foodhandler.open();//food database opened calling open(); method
                foodhandler.insertfoodItem(getitemName,getcarbs,getprotein,getsugar,getfat,getsaturates,getfibre,getsodium,getcholesterol,getcalories);//insert method called
                Toast.makeText(getBaseContext(), "Item Inserted", Toast.LENGTH_LONG).show();//toast message of success
                foodhandler.close();//food database closed calling close(); method
                //set all text messages to blank to allow for the next item to be inserted
                item.setText("");
                carbs.setText("");
                protein.setText("");
                sugars.setText("");
                fat.setText("");
                saturates.setText("");
                fibrecontent.setText("");
                sodiumcontent.setText("");
                cholesterolcontent.setText("");
                caloriecontent.setText("");
            }
        });
    }

    public void happilogoAdminadditem(View v) {
        Intent myIntent = new Intent(this, AdminArea.class);
        startActivity(myIntent);
        finish();
    }
    //disable back button from add screen
    @Override
    public void onBackPressed() {

    }
}