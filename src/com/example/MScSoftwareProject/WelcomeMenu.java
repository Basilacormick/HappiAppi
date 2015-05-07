package com.example.MScSoftwareProject;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    06/10/2014.
 * This programme loads the welcome screen and presents the user with the range of options to select from
 */
public class WelcomeMenu extends Activity implements View.OnClickListener {

    //buttons created for user to choose from
    Button welbutton1;
    Button welbutton2;
    Button welbutton3;
    Button welbutton4;
    Button welbutton5;
    Button welbutton6;
    Button welbutton7;
    Button welbutton8;
    Button welbutton9;
    //random message is set here
    TextView randommessage;
    //button click noise set here
    MediaPlayer soundButtonClick;

    private countDown notificationtimer;
    long timelimit = 6000 *10*60*2;
    long segments = 1000;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomemenu);//sets to welcome menu using unique id

        soundButtonClick = MediaPlayer.create(this, R.raw.buttonclick);//button set from raw folder
        //welcome button linked to xml using unique id
        welbutton1 = (Button) findViewById(R.id.welcomebutton1);
        welbutton2 = (Button) findViewById(R.id.welcomebutton2);
        welbutton3 = (Button) findViewById(R.id.welcomebutton3);
        welbutton4 = (Button) findViewById(R.id.welcomebutton4);
        welbutton5 = (Button) findViewById(R.id.welcomebutton5);
        welbutton6 = (Button) findViewById(R.id.welcomebutton6);
        welbutton7 = (Button) findViewById(R.id.welcomebutton7);
        welbutton8 = (Button) findViewById(R.id.welcomebutton8);
        welbutton9 = (Button) findViewById(R.id.welcomebutton9);
        //onClick listeners set for each button
        welbutton1.setOnClickListener(this);
        welbutton2.setOnClickListener(this);
        welbutton3.setOnClickListener(this);
        welbutton4.setOnClickListener(this);
        welbutton5.setOnClickListener(this);
        welbutton6.setOnClickListener(this);
        welbutton7.setOnClickListener(this);
        welbutton8.setOnClickListener(this);
        welbutton9.setOnClickListener(this);
        //random message linked to xml using unique id
        randommessage = (TextView) findViewById(R.id.textmotivator);
        //random messages
        String [] messagecontainer = {"Dieting is the only game where you win when you lose!, Karl Lagerfeld","Food is an important part of a balanced diet. Fran Lebowitz","No disease that can be treated by diet should be treated with any other means.Maimonides", "If I don't eat junk, I don't gain weight. Paulina Christensen", "Your diet is a bank account. Good food choices are good investments.\n" +
                "Bethenny Frankel\n","The only way to keep your health is to eat what you don't want, drink what you don't like, and do what you'd rather not.Mark Twain"};
        //random message number generator
        int randomgenerator = (int)((Math.random()*6));
        //textview set to text in array using randomgenerator number created
        randommessage.setText(messagecontainer[randomgenerator]);
        //noyification timer set and started passing timelimit, segments declared
        notificationtimer = new countDown(timelimit,segments);
        notificationtimer.start();
        //inflater created for custom toast
        LayoutInflater toastinflate = getLayoutInflater();
        View toaster = toastinflate.inflate(R.layout.happitoast, (android.view.ViewGroup) findViewById(R.id.displaytoast));
        //create text view and set text of toast message, position and duration of toast also specified
        TextView toasttext = (TextView) toaster.findViewById(R.id.toasttext);
        toasttext.setText("On each screen the logo will navigate you home");
        Toast logotoast = new Toast(getApplicationContext());
        logotoast.setGravity(Gravity.TOP,0,100);
        logotoast.setDuration(Toast.LENGTH_LONG);
        logotoast.setView(toaster);
        logotoast.show();
    }

    @Override
    public void onClick(View v) {
        //if button clicked then set of toast message and load relevant screen making using the unique transition animation
        if (v.getId() == welbutton1.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "You have chosen About Us", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this, AboutUs.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);//animation
            this.finish();
        } else if (v.getId() == welbutton2.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "You have chosen Food Search", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this, FoodNutritionSearch.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);
            this.finish();
        } else if (v.getId() == welbutton3.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "You have chosen Optimal Calories", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this, OptimalCalories.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);
            this.finish();
        } else if (v.getId() == welbutton4.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "You have chosen View Activity", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this,ViewActivity.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);
            this.finish();
        } else if (v.getId() == welbutton5.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "You have chosen Calorie Intake log", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this, LogCalories.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);
            this.finish();
        } else if (v.getId() == welbutton6.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "You have chosen Exercise log", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this, LogExercise.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);
            this.finish();
        } else if (v.getId() == welbutton7.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "Welcome Administrator, Please enter your login details", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this, AdministratorLogin.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);
            this.finish();
        } else if (v.getId() == welbutton8.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "Web Resources launched", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this, WebResources.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);
            this.finish();
        } else if (v.getId() == welbutton9.getId()) {
            soundButtonClick.start();
            Toast toast = Toast.makeText(this, "Graph View launched", 4000);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
            Intent myIntent = new Intent(this, CalorieGraph.class);
            startActivityForResult(myIntent, 0);
            overridePendingTransition(R.anim.animation1,R.anim.animation2);
            this.finish();
        }
    }

    public class countDown extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public countDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {

           int randomgenerator = (int)((Math.random() * 5));

            String [] randomhealthtip = {"Blueberries are an antioxidant that contain high concentrations of Vitamin C and Vitamin K","Chocolate in moderation may help reduce cholesterol and stroke","Nuts are full of protein, fibre and essential fats","Oranges contain a large amount of Vitamin C","Beef contains alot of Vitamin B12"};
            String title = "Health Tip 101";

            Toast.makeText(getBaseContext(), randomhealthtip[randomgenerator], Toast.LENGTH_LONG).show();
            Uri phonetone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Intent intent = new Intent(WelcomeMenu.this, WelcomeMenu.class);
            PendingIntent pIntent = PendingIntent.getActivity(WelcomeMenu.this, 0, intent, 0);

            Notification usernote = new Notification.Builder(WelcomeMenu.this)
                    .setContentTitle(title)
                    .setContentText(randomhealthtip[randomgenerator])
                    .setSmallIcon(R.drawable.happilogo)
                    .setContentIntent(pIntent)
                    .setSound(phonetone)
                    .build();

            NotificationManager noteManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            noteManager.notify(0, usernote);
            notificationtimer.start();
        }
    }
    //disable back button from welcome screen
    @Override
    public void onBackPressed() {

    }
    //method to return the user to the welcome screen
    public void happilogowelcomelogout(View v)
    {
        AlertDialog.Builder exitprompt = new AlertDialog.Builder(WelcomeMenu.this);//create dialog for exit prompt

        exitprompt.setTitle("Log Off");
        exitprompt.setMessage("Are you sure you want to logout?");
        exitprompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {//yes
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(WelcomeMenu.this, MyActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        exitprompt.setNegativeButton("No", new DialogInterface.OnClickListener() {//user does not exit
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        exitprompt.show();

    }
}