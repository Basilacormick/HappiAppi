package com.example.MScSoftwareProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    22/09/2014
 * This programme allows the administrator to delete items from the SQLite food database
 */
public class AdminDeleteItem extends Activity {
    //variables declared
    EditText delete;
    Button submitdeleteitem;
    FoodDatabaseHandler deletefoodhandler;//food database

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admindeleteitem);//delete screen launched

        delete = (EditText) findViewById(R.id.deleteitemtext);
        submitdeleteitem = (Button) findViewById(R.id.submitdelete);

    }

    public void deleteRecord(View v) {
        //dialog box to ensure administrator wants to delete item
        AlertDialog.Builder prompt = new AlertDialog.Builder(this);
        prompt.setTitle("Delete");
        prompt.setMessage("Are you sure you want to delete this record?");
        prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deletefoodhandler = new FoodDatabaseHandler(getBaseContext());//food database created
                deletefoodhandler.open();//food database opened using open(); method
                deletefoodhandler.deleteData(delete.getText().toString());//item deleted using deleteData(); method
                Toast.makeText(getBaseContext(), "Item Deleted", Toast.LENGTH_LONG).show();//Toast message
                deletefoodhandler.close();//database closed using close(); method
            }
        });
        prompt.setNegativeButton("No", null);//item not deleted if administrator selects no
        prompt.show();
    }
    //method to return administrator back to admin area
    public void happilogoAdmindelete(View v) {
        Intent myIntent = new Intent(this, AdminArea.class);
        startActivity(myIntent);
        finish();
    }
    //disable back button from delete screen
    @Override
    public void onBackPressed() {

    }
}
