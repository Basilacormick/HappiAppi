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
 * Date:    29/09/2014.
 * This programme enables the user to register an account which is stored in the login database
 */
public class Userregistration extends Activity {

    //variables for user information
    EditText usersfirstname, userslastname, username, password;
    //button to submit registration
    Button submitregistration;

    LoginHandler adduser;//login database

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregistration);//set userregistration screen using unique id
        //link variables to xml using unique ids
        usersfirstname = (EditText) findViewById(R.id.firstname);
        userslastname = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.usersusername);
        password = (EditText) findViewById(R.id.userpassword);

        submitregistration = (Button) findViewById(R.id.registeruser);
        //onclick listener for submit registration button
        submitregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick the user details are retrieved from their input
                String usersname = usersfirstname.getText().toString();
                String userslname = userslastname.getText().toString();
                String usersusername = username.getText().toString();
                String userspassword = password.getText().toString();
                //log in database instantiated
                adduser = new LoginHandler(getBaseContext());
                adduser.open();//method to open log in database
                adduser.addUser(usersname,userslname,usersusername,userspassword);//method to insert user details
                Toast.makeText(getBaseContext(), "User added", Toast.LENGTH_LONG).show();//toast message
                adduser.close();//method to close login database
                happilogoUserregistration(submitregistration);//method called to return user back to log in screen
            }
        });
    }
    //method to return user to log in screen
    public void happilogoUserregistration(View v) {
        Intent myIntent = new Intent(this, MyActivity.class);
        startActivity(myIntent);
        finish();
    }
}