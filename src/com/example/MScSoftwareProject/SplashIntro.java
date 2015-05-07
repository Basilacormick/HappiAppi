package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    10/09/2014.
 * This programme creates a splash screen that is completed before the initial activity is loaded
 */
public class SplashIntro extends Activity {
    long milliseconds=0;
    long screenTimer=1000;//splash screen active in milliseconds
    boolean screenTimerActive = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashintro);//set to splash screen using unique id

        Thread myThread = new Thread(){//creates new thread
            public void run(){
                try {
                    //while sleep 100 seconds and add 100 milli seconds to milliseconds variables
                    while (screenTimerActive && milliseconds < screenTimer) {
                        sleep(100);
                        milliseconds=milliseconds+100;

                    }
                } catch(Exception e) {

                }
                //this code launches the myactivity screen when the splash finishes
                finally {
                    Intent intent = new Intent(SplashIntro.this, MyActivity.class);
                    startActivity(intent);

                }
            }
        };
        myThread.start();
    }
}
