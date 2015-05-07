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
 * This programme allows the adminsitrator to modify items on the SQLite food database
 */
public class AdminUpdateTable extends Activity {

    //variable declared
    EditText itemchange,items, carbos,proteins, sugar, fats, saturate, fibre, sodium, cholesterol, calorie;
    FoodDatabaseHandler foodhandlerdatabase;//food database
    Button updateitem;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminupdateitem);

        itemchange = (EditText) findViewById(R.id.itemchanger);
        items = (EditText) findViewById(R.id.updateitemname);
        carbos = (EditText) findViewById(R.id.updatecarbohydrates);
        proteins = (EditText) findViewById(R.id.updateproteincontent);
        sugar = (EditText) findViewById(R.id.updatesugar);
        fats = (EditText) findViewById(R.id.updatefats);
        saturate = (EditText) findViewById(R.id.updatesaturates);
        fibre = (EditText) findViewById(R.id.updatefiber);
        sodium = (EditText) findViewById(R.id.updatesodium);
        cholesterol = (EditText) findViewById(R.id.updatecholesterol);
        calorie = (EditText) findViewById(R.id.updatecalories);

        updateitem = (Button) findViewById(R.id.buttonupdateitemsubmit);

        updateitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get administrators input
                String getitemtochange = itemchange.getText().toString();
                String getitemname = items.getText().toString();
                Integer getcarb = Integer.parseInt(carbos.getText().toString());
                Integer getproteins = Integer.parseInt(proteins.getText().toString());
                Integer getsugars = Integer.parseInt(sugar.getText().toString());
                Integer getfats = Integer.parseInt(fats.getText().toString());
                Integer getsaturate = Integer.parseInt(saturate.getText().toString());
                Integer getfibres = Integer.parseInt(fibre.getText().toString());
                Integer getsodiums = Integer.parseInt(sodium.getText().toString());
                Integer getcholesterols = Integer.parseInt(cholesterol.getText().toString());
                Integer getcalorie = Integer.parseInt(calorie.getText().toString());

                foodhandlerdatabase = new FoodDatabaseHandler(getBaseContext());//food database created
                foodhandlerdatabase.open();//food database opened calling open(); method
                foodhandlerdatabase.updateTable(getitemtochange,getitemname, getcarb, getproteins, getsugars, getfats, getsaturate, getfibres, getsodiums, getcholesterols, getcalorie);//insert method called
                Toast.makeText(getBaseContext(), "Item Changed", Toast.LENGTH_LONG).show();//toast message of success
                foodhandlerdatabase.close();//food database closed calling close(); method
                //set all text messages to blank to allow for the next item to be inserted
                itemchange.setText("");
                items.setText("");
                carbos.setText("");
                proteins.setText("");
                sugar.setText("");
                fats.setText("");
                saturate.setText("");
                fibre.setText("");
                sodium.setText("");
                cholesterol.setText("");
                calorie.setText("");
            }
        });
    }
    public void happilogoUpdateitem(View v) {
        Intent myIntent = new Intent(this, AdminArea.class);
        startActivity(myIntent);
        finish();
    }
    //disable back button from modify screen
    @Override
    public void onBackPressed() {

    }
}