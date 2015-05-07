package com.example.MScSoftwareProject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    07/09/2014
 * This programme launches the administrator log in screen
 */
public class AdministratorLogin extends Activity implements View.OnClickListener {
    //variables declared
    EditText username;
    EditText password;
    Button login, cancel;
    TextView errormessage;

    ProgressDialog progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administratorlogin);//launch screen based on unique id

        username = (EditText) findViewById(R.id.usertext);
        password = (EditText) findViewById(R.id.passtext);
        errormessage = (TextView) findViewById(R.id.errormessage);
        login = (Button) findViewById(R.id.submitLogin);
        cancel = (Button) findViewById(R.id.cancel);

        login.setOnClickListener(this);
        cancel.setOnClickListener(this);
        username.setOnClickListener(this);
        password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //administrator user name and passwrod
        String user = "";
        String pass = "";
        //if login pressed
        if (v.getId() == login.getId()) {
            //if details match those stored launch admin area
            if (username.getText().toString().equals(user) && password.getText().toString().equals(pass)) {

                Toast toast = Toast.makeText(this, "Correct LogIn!!", 4000);
                toast.setGravity(Gravity.BOTTOM, -10, 25);
                toast.show();
                progress = new ProgressDialog(AdministratorLogin.this);
                progress.setTitle("Processing.......");
                progress.setMessage("Loading...");
                progress.setIndeterminate(true);
                progress.show();
                Intent myIntent = new Intent(this, AdminArea.class);
                startActivityForResult(myIntent, 0);
                this.finish();

            } else {//else if detail incorrect toast this
                Toast toast = Toast.makeText(this, "Incorrect Login, try again!!", 4000);
                toast.setGravity(Gravity.BOTTOM, -10, 25);
                toast.show();

                errormessage.setVisibility(1);//make error message visible
            }
        //if cancel pressed set edit boxes to blank
        } else if (v.getId() == cancel.getId()) {

            username.setText("");
            password.setText("");

        } else if (v.getId() == username.getId()) {
            if(username.hasFocus()) {
                username.setText("");
                password.setText("");
            }
        }
    }

    //method to return administrator back to welcome screen
    public void happilogoAdministratorlogin(View v)
    {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }
}