package com.example.MScSoftwareProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    10/10/2014.
 * This programme delivers the scraper tool that allows the administrator to scrape information from a website
 */
public class Scrapertool extends Activity {

    Button scrape;
    TextView resultofscrape;
    EditText enterwebsite;
    EditText enteritem;
    ProgressDialog myprogressdialog;
    FoodDatabaseHandler foodhandler;
    ImageButton webchooser;

    String [] websites = {"Food Buddy"};

    private FetchWebsiteData.countingDown storagecounter;//sets timer to 5 seconds to prompt user if they want to store
    long timelimit = 5000;
    long segments = 1000;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrapertool);
        //scrape result is stored here
        resultofscrape = (TextView) findViewById(R.id.scraperesult);
        enterwebsite = (EditText) findViewById(R.id.scrapewebsite);
        enteritem = (EditText) findViewById(R.id.scrapeitem);
        webchooser = (ImageButton) findViewById(R.id.Scrapeweb);

        scrape = (Button) findViewById(R.id.submitscrapebutton);
        scrape.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new FetchWebsiteData().execute();//pressing scrape button launched fetchwebsitedata
            }
        });
    }
    private class FetchWebsiteData extends AsyncTask<Void, Void, Void> {
        //columns of table
        String column1 = "";
        String column2 = "";
        String column3 = "";
        String column4 = "";
        String column5 = "";
        //before scrape execution
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myprogressdialog = new ProgressDialog(Scrapertool.this);
            myprogressdialog.setTitle("Processing.......");
            myprogressdialog.setMessage("Loading...");
            myprogressdialog.setIndeterminate(true);
            myprogressdialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            //get administrators website and item input
            String getwebsite = enterwebsite.getText().toString();
            String getinfo = enteritem.getText().toString();

            try {
                String scrapeurl = "http://"+getwebsite;
                Document doc = Jsoup.connect(scrapeurl).get();

                for (Element element : doc.select("table")) {//search for table
                    for (Element row : element.select("tr")) {//iterate over rows
                        Elements tds = row.select("td");//select row
                        //if row contains details get the following columns
                        if (tds.get(0).text().contains(getinfo)) {
                            column1 = tds.get(0).text();
                            column2 = tds.get(1).text();
                            column3 = tds.get(2).text();
                            column4 = tds.get(3).text();
                            column5 = tds.get(4).text();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //after scrape execution
        @Override
        protected void onPostExecute(Void result) {
            //set result here
            resultofscrape.setText(column1 + "  " + "  " + column2 + "  " + column3 + "  " + column4 + "  " + column5);
            myprogressdialog.dismiss();
            storagecounter = new FetchWebsiteData.countingDown(timelimit,segments);//start timer
            storagecounter.start();

        }

        public class countingDown extends CountDownTimer {

            /**
             * @param millisInFuture    The number of millis in the future from the call
             *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
             *                          is called.
             * @param countDownInterval The interval along the way to receive
             *                          {@link #onTick(long)} callbacks.
             */
            public countingDown(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {//when 5 second timer has finished
                //column info stored here
                final int calories = Integer.parseInt(column2);
                final int protein = Integer.parseInt(column3);
                final int fat = Integer.parseInt(column4);
                final int carbs = Integer.parseInt(column5);

                AlertDialog.Builder prompt = new AlertDialog.Builder(Scrapertool.this);//create dialog
                //prompt administrator if they want to store information to food database
                prompt.setTitle("Save to database");
                prompt.setMessage("Do you want to store this information?");
                prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {//yes
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        foodhandler = new FoodDatabaseHandler(getBaseContext());//food database
                        foodhandler.open();//method to open food database
                        foodhandler.insertfoodItem(column1, carbs, protein, fat, 0, 0, 0, 0, 0, calories);//method to insert data
                        foodhandler.close();//method to close food database
                    }
                });
                prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {//data not stored
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                prompt.show();
            }
        }
    }
    public void happilogoScraper(View v)
    {
        Intent myIntent = new Intent(this, AdminArea.class);
        startActivity(myIntent);
        finish();
    }

    public void websrapechoice(View v){

        AlertDialog.Builder weblinks = new AlertDialog.Builder(this);//show dialog box

        weblinks.setTitle("Scrape Options");//set title
        weblinks.setItems(websites, new DialogInterface.OnClickListener() {//web options shown
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if choice made load selected url
                if(which == 0){
                    enterwebsite.setText("www.myfoodbuddy.com/foodCalorieTable.htm");
                }
            }
        });
        weblinks.show();
    }
    //disable back button from scraper screen
    @Override
    public void onBackPressed() {

    }
}