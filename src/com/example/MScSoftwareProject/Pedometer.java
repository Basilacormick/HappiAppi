package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    28/09/2014.
 * This programme delivers the pedometer element of the application used in the logexercise programme
 */
public class Pedometer extends Activity implements SensorEventListener,View.OnClickListener {

    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;

    Button stopper;

    int counter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedometer);
        count = (TextView) findViewById(R.id.count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);//sensor manager declared here
        stopper = (Button) findViewById(R.id.stoppedometer);
        stopper.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//accelerometer declared here
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor is not available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //sensor activity with low pass filter implemented from http://stackoverflow.com/questions/10699660/unable-to-get-accelerometer-to-sense-shake-event
        float last,current=0,sense =0;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        last = current;
        current = (float) Math.sqrt((double)(x * x + y * y + z * z));//equation to desensitise the accelerometer
        float delta = current - last;
        sense = sense * 0.9f + delta;

        if (sense>20) {//sensitivity set here
            counter = counter + 1;//add one step to pedometer
            if(counter == 100){
                Toast.makeText(this, "Wow!, You are so fit", Toast.LENGTH_LONG).show();
            }else if(counter == 200){
                Toast.makeText(this, "Keep up the hard work", Toast.LENGTH_LONG).show();
            }
        }
        count.setText(String.valueOf(counter));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onClick(View v) {

        int pedometercalories;

        if(v.getId() == stopper.getId()){//stop pedometer
            pedometercalories = (int) (counter*0.044);//calculates calories as 0.044 calories per step
            //puts calories in intents to be retrieved at exercise log
            Intent intent = new Intent(this,LogExercise.class);
            intent.putExtra("pedometercals",pedometercalories);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }
}