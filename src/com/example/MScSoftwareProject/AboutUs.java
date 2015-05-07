package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    06/09/2014
 * This programme loads the about us screen and controls the random quote and email sending facility
 */

public class AboutUs extends Activity {
    //android widget variables declared
    Button sendemail;
    TextView aboutapp, aboutdeveloper,contactdetails;
    EditText emailmessage;
    //Developer information for about us screen
    String [] about =  {"Health App Interactive is a healthy eating and weight loss app designed to assist people with their diet and calorie consumption",
                        "Johnathan McCartney is a software developer studying at the University of Ulster, Coleraine",
                        "Email: basilacor@gmail.com or type message and click send email"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);//sets content to the about using the aboutus unique id for aboutus.xml
        //variables linked to widgets using unique ids
        aboutapp = (TextView) findViewById(R.id.aboutapp);
        aboutdeveloper = (TextView) findViewById(R.id.aboutdeveloper);
        contactdetails = (TextView) findViewById(R.id.contactdetails);
        emailmessage = (EditText) findViewById(R.id.emailmessage);
        //sets developer information to the relevant text views
        aboutapp.setText(about[0]);
        aboutdeveloper.setText(about[1]);
        contactdetails.setText(about[2]);
        //send button onClickListerner established
        sendemail = (Button) findViewById(R.id.sendemailbutton);
        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send email Intent created and putExtra invoked accordingly
                Intent sendemailintent = new Intent(Intent.ACTION_SEND);
                sendemailintent.putExtra(Intent.EXTRA_EMAIL,new String []{"McCartney-J4@email.ulster.ac.uk"});//developers email
                sendemailintent.putExtra(Intent.EXTRA_CC, new String []{"basilacor@gmail.com"});//developers cc email
                sendemailintent.putExtra(Intent.EXTRA_SUBJECT, "Health App Email");//subject of email
                sendemailintent.putExtra(Intent.EXTRA_TEXT, emailmessage.getText().toString());//Additional information user might send

                sendemailintent.setType("message/rfc822");//Standard for the format of ARPA internet text messages
                startActivity(Intent.createChooser(sendemailintent,"Choose an Email Client to send the developer a message"));//open email client chooser
            }
        });
    }
    //method to return user to welcome screen
    public void happilogoAboutus(View v)
    {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }
    //method to launch developer location map screen
    public void mapLocator(View v)
    {
        Intent myIntent = new Intent(this, Developerlocation.class);
        startActivityForResult(myIntent, 0);
    }
    //disable back button from about us screen
    @Override
    public void onBackPressed() {

    }
}