package com.example.MScSoftwareProject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    06/09/2014.
 * This loads the log in screen and controls whether the user can proceed or not
 */
public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    EditText username;
    EditText password;
    Button login,cancel,registration;
    TextView errormessage;

    LoginHandler userlogin;//log in database
    ProgressDialog progress;//progress bar
    CheckBox remember;//check box to remember username and password

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//set screen to main using unique id
        //user log in and error message
        username = (EditText)findViewById(R.id.usertext);
        password = (EditText)findViewById(R.id.passtext);
        errormessage = (TextView)findViewById(R.id.errormessage);
        //buttons to log in, cancel, registration
        login = (Button)findViewById(R.id.submitLogin);
        cancel = (Button)findViewById(R.id.cancel);
        registration = (Button) findViewById(R.id.registrationbutton);
        //user login database
        userlogin = new LoginHandler(getBaseContext());
        //onClick listeners set
        login.setOnClickListener(this);
        cancel.setOnClickListener(this);
        registration.setOnClickListener(this);
        username.setOnClickListener(this);
        errormessage.setOnClickListener(this);

        remember = (CheckBox) findViewById(R.id.rememberMe);
    }

    @Override
    public void onClick(View v) {
        //user details entered are stored here
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(remember.isChecked()){

            //shared preference to store calories
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user", user);
            editor.putString("pass", pass);
            editor.commit();
        }
        //if login is pressed
        if (v.getId() == login.getId()) {
            //if user login is equal to 1
            if (userlogin.returnResult(user,pass)== 1) {

                Toast toast = Toast.makeText(this, "Correct LogIn!!", 4000);
                toast.setGravity(Gravity.BOTTOM, -10, 25);
                toast.show();
                progress = new ProgressDialog(MyActivity.this);
                progress.setTitle("Processing.......");
                progress.setMessage("Loading...");
                progress.setIndeterminate(true);
                progress.show();
                Intent myIntent = new Intent(this, WelcomeMenu.class);//correct login loads welcome screen
                startActivityForResult(myIntent, 0);

            } else {
                //incorrect login results in toast message and erro message being presented
                Toast toast = Toast.makeText(this, "Incorrect Login, try again!!", 4000);
                toast.setGravity(Gravity.BOTTOM, -10, 25);
                toast.show();

                errormessage.setText("INCORRECT Login, Please try again!");
                errormessage.setVisibility(View.VISIBLE);
                errormessage.setTextColor(Color.RED);
            }
        //cancel button clears text
        }else if (v.getId() == cancel.getId()){

            username.setText("");
            password.setText("");
        //registration button launches registration screen
        }else if(v.getId() == registration.getId()){

            Intent myIntent = new Intent(this, Userregistration.class);
            startActivityForResult(myIntent,0);

        }else if (v.getId() == username.getId()){

            SharedPreferences memoryprefs = PreferenceManager.getDefaultSharedPreferences(this);
            String usermemory = memoryprefs.getString("user", null);
            String passmemory = memoryprefs.getString("pass", null);
            username.setText(usermemory);
            password.setText(passmemory);

            if(usermemory != null || passmemory != null) {

                errormessage.setText("Click here if not you!!!");
                errormessage.setVisibility(View.VISIBLE);
                errormessage.setTextColor(Color.YELLOW);
            }

        } else if(v.getId() == errormessage.getId()){

            username.setText("");
            password.setText("");

            showKeyboard(username);//call to showKeyboard method
        }
    }
    //method to set username edit box to blank
    public void setUsernameblank(View v)
    {
        username.setText("");
    }
    //method to set password edit box to blank
    public void setPasswordblank(View v)
    {
        password.setText("");
    }
    //method to return user to the welcome screen
    public void happilogoAdminLogInScreen(View v)
    {
        Intent myIntent = new Intent(this, AdministratorLogin.class);
        startActivity(myIntent);
        finish();
    }
    //show phone keyboard when user clicks on error message
    public void showKeyboard(View view){

        if(view.requestFocus()) {
            InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
    public void onBackPressed(){
    }
}