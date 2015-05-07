package com.example.MScSoftwareProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    26/09/2014.
 * This programme loads web browser screen and delivers functionality for it
 */
public class WebResources extends Activity implements View.OnClickListener {

    //variables declared
    EditText enterurl;
    Button submiturl;
    Button refresh;
    ImageButton webchooser;
    WebView newbrowser;
    //website for dialog chooser
    String [] websites = {"NHS","British Heart Foundation","British Nutrition Foundation","NICHS","FoodNI","AgeUK","HSENI","Patient"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webresources);//sets to webresources screen using unique id

        newbrowser = (WebView) findViewById(R.id.webviewer);//link to xml using unique id
        newbrowser.setWebViewClient(new webClient());//set to webclient so browser opens within application

        WebSettings mywebsettings = newbrowser.getSettings();//get settings
        mywebsettings.setJavaScriptEnabled(true);//set this in case of javascript enabled websites
        newbrowser.getSettings().setLoadWithOverviewMode(true);//method to set overview
        newbrowser.getSettings().setUseWideViewPort(true);//port view full screen
        mywebsettings.setBuiltInZoomControls(true);//zoom controls set, allows user to zoom
        mywebsettings.setSupportZoom(true);//set zoom support to true

        newbrowser.loadUrl("https://www.nhs.uk/conditions/Obesity");//loads default website

        enterurl = (EditText) findViewById(R.id.urlinput);
        submiturl = (Button) findViewById(R.id.launchsearch);
        refresh = (Button) findViewById(R.id.refreshbutton);
        webchooser = (ImageButton) findViewById(R.id.webchoices);

        submiturl.setOnClickListener(this);
        refresh.setOnClickListener(this);
        webchooser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.launchsearch://launch search selected
                String webbrowser = enterurl.getText().toString();//get user website

                newbrowser.loadUrl("http://" + webbrowser);//load website here
                break;
            case R.id.webchoices://webchoices selected
                AlertDialog.Builder weblinks = new AlertDialog.Builder(this);//show dialog box

                weblinks.setTitle("Web Options");//set title
                weblinks.setItems(websites, new DialogInterface.OnClickListener() {//web options shown
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if choice made load selected url
                        if(which == 0){
                            newbrowser.loadUrl("https://www.nhs.uk/conditions/Obesity");
                        } else if(which == 1){
                            newbrowser.loadUrl("http://www.bhf.org.uk");
                        } else if(which == 2){
                            newbrowser.loadUrl("http://www.nutrition.org.uk/healthyliving/healthyeating");
                        } else if(which == 3){
                            newbrowser.loadUrl("http://www.nichs.org.uk/45/healthy-eating");
                        } else if(which == 4){
                            newbrowser.loadUrl("http://www.food.gov.uk/northern-ireland/nutritionni");
                        } else if(which == 5){
                            newbrowser.loadUrl("http://www.ageuk.org.uk/northern-ireland/health--wellbeing/healthy-eating/");
                        } else if(which == 6){
                            newbrowser.loadUrl("http://www.hseni.gov.uk/..guidance/topics/healthy_eating");
                        } else if(which == 7){
                            newbrowser.loadUrl("http://www.patient.co.uk/health/healthy-eating");
                        }
                    }
                });
                weblinks.show();
            case R.id.refreshbutton://refresh button reloads default website
                newbrowser.loadUrl("https://www.nhs.uk/conditions/Obesity");
        }
    }
    //on back pressed enabled for web browser
    @Override
    public void onBackPressed() {
        newbrowser.goBack();
    }
    //method to take user back to welcome screen
    public void happilogowebView(View v)
    {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }
}