package com.example.MScSoftwareProject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    09/09/2014.
 * This programme allows the user to log exercise manually, using a dialog box, using the scanner or using the pedometer
 */
public class LogExercise extends Activity implements View.OnClickListener {
    //text view declared to hold exercise calories
    TextView calsdailytarget;
    TextView calorietrack;
    TextView exeriselog;
    TextView calsconsumed;
    TextView caloriesburned;
    TextView date;
    TextView time;

    int targetcal;
    String thedate;
    String thetime;

    Button submitcalorieburn;//button to submit calories burned
    Button pedometer;//button to launch pedometer
    ImageButton exercisechoice;//image buttons for dialog and scanner
    ActivityHandler storeactivity;//activity database
    static int getexercisecals = 0;

    String [] exercises = {"Running","Walking","Cycling","Swimming","Football","Squash","Tennis","Rugby"};//exercise options

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logexercise);//set log exercise screen using unique id
        //linked text views to xml using unique ids
        calsdailytarget = (TextView) findViewById(R.id.dailytargets);
        calorietrack = (TextView) findViewById(R.id.targetcalorie);
        exeriselog = (TextView) findViewById(R.id.inputexercise);
        calsconsumed = (TextView) findViewById(R.id.caloriesconsume);
        caloriesburned = (TextView) findViewById(R.id.calorieburn);
        date = (TextView) findViewById(R.id.thecurrentdate);
        time = (TextView) findViewById(R.id.thecurrenttime);
        //submit calories and onclick listener
        submitcalorieburn = (Button) findViewById(R.id.buttoncaloriesubmit);
        submitcalorieburn.setOnClickListener(this);
        //exercise dialog chooser
        exercisechoice = (ImageButton) findViewById(R.id.selectexercise);
        exercisechoice.setOnClickListener(this);
        //button to launch pedometer tool, a key feature
        pedometer = (Button) findViewById(R.id.stepper);
        pedometer.setOnClickListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        targetcal = preferences.getInt("kcal", 0);//daily calorie target from calorie results, does not change
        int cals = preferences.getInt("kcals", 0);//calorie budget, changes as user logs information
        int calsconsumption = preferences.getInt("calsconsumed", 0);//calories consumed presented on screen
        //display information in text views
        calorietrack.setText("" + cals);
        calsdailytarget.setText(""+targetcal);
        calsconsumed.setText(""+calsconsumption);

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        int calsburned = preference.getInt("calsburned", 0);//total calories burned

        caloriesburned.setText("" + calsburned);

        if(cals > targetcal){
            calorietrack.setTextColor(Color.MAGENTA);
            Toast toast = Toast.makeText(this, "You have more budget calories", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
        }
        else {
            calorietrack.setTextColor(Color.GREEN);
        }

        thedate = new SimpleDateFormat("MMM,dd,yyyy").format(Calendar.getInstance().getTime());
        thetime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        date.setText(thedate);
        time.setText(thetime);

        Intent intent = getIntent();
        int pedometercalories = intent.getIntExtra("pedometercals",0);//pedometer calories are retrieved here

        if(pedometercalories>0){

            exeriselog.setText(""+pedometercalories);//if calories greater than 0 set text box to the calories
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == submitcalorieburn.getId()) {
            //if submit calorie burn selected store information in database
            storeactivity = new ActivityHandler(getBaseContext());
            storeactivity.open();
            int calsstore = Integer.parseInt(exeriselog.getText().toString());//user input
            String storedate = new SimpleDateFormat("hh:mm a 'on' MMMM dd yyyy").format(Calendar.getInstance().getTime());//current time
            storeactivity.addActivity("Calories Burned", calsstore, storedate);//add info to activity database
            storeactivity.close();
            //data manipulated in shared preferences
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

            int cals = preferences.getInt("kcals", 0);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("kcals", cals + Integer.parseInt(exeriselog.getText().toString()));//add calories to calorie budget
            editor.commit();
            cals = preferences.getInt("kcals", 0);
            calorietrack.setText("" + cals);

            SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
            int calsburned = preference.getInt("calsburned", 0);

            SharedPreferences.Editor editor1 = preferences.edit();
            editor1.putInt("calsburned", calsburned + Integer.parseInt(exeriselog.getText().toString()));//add to total calories burned
            editor1.commit();

            calsburned = preference.getInt("calsburned", 0);
            caloriesburned.setText("" + calsburned);

            if (cals > targetcal) {
                calorietrack.setTextColor(Color.MAGENTA);
                Toast toast = Toast.makeText(this, "You have more budget calories", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, -10, 25);
                toast.show();
            } else {
                calorietrack.setTextColor(Color.GREEN);
            }

            Toast toast = Toast.makeText(this, "Calorie burn logged", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();

            exeriselog.setText("");

        } else if (v.getId() == exercisechoice.getId()) {//if exercise dialog chosen launch this

            //remove pedometer buttons as it is not in use
            pedometer.setVisibility(View.INVISIBLE);

            final AlertDialog.Builder exercisechoice = new AlertDialog.Builder(this);//dialog created

            exercisechoice.setTitle("Choose Exercise");
            exercisechoice.setItems(exercises, new DialogInterface.OnClickListener() {//choose exercise
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //choose intensity
                    String [] intensity = {"low 10 mins","low 30 mins","low 60 mins","Medium 10 mins","Medium 30 mins","Medium 60 mins","High 10 mins","High 30 mins","High 60 mins","Very High 10 mins","Very High 30 mins","Very High 60 mins"};
                    if (which == 0) {
                        AlertDialog.Builder exerciseintensity = new AlertDialog.Builder(LogExercise.this);//dialog created
                        exerciseintensity.setTitle("Select intensity");
                        exerciseintensity.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                exeriselog.setText(""+getexercisecals);
                                getexercisecals = 0;
                            }
                        });
                        exerciseintensity.setSingleChoiceItems(intensity,-1,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //calories returned based on exercise chosen and intensity
                                if (which == 0) {
                                    getexercisecals = getexercisecals + 100;
                                } else if (which == 1) {
                                    getexercisecals = getexercisecals + 200;
                                }else if (which == 2) {
                                    getexercisecals = getexercisecals + 350;
                                }else if (which == 3) {
                                    getexercisecals = getexercisecals + 200;
                                }else if (which == 4) {
                                    getexercisecals = getexercisecals + 350;
                                }else if (which == 5) {
                                    getexercisecals = getexercisecals + 500;
                                }else if (which == 6) {
                                    getexercisecals = getexercisecals + 300;
                                }else if (which == 7) {
                                    getexercisecals = getexercisecals + 450;
                                }else if (which == 8) {
                                    getexercisecals = getexercisecals + 600;
                                }else if (which == 9) {
                                    getexercisecals = getexercisecals + 350;
                                }else if (which == 10) {
                                    getexercisecals = getexercisecals + 550;
                                }else if (which == 11) {
                                    getexercisecals = getexercisecals + 800;
                                }
                            }
                        });
                        exerciseintensity.show();
                    } else if (which == 1) {
                            AlertDialog.Builder exerciseintensity = new AlertDialog.Builder(LogExercise.this);
                            exerciseintensity.setTitle("Select intensity");
                            exerciseintensity.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    exeriselog.setText(""+getexercisecals);
                                    getexercisecals = 0;
                                }
                            });
                            exerciseintensity.setSingleChoiceItems(intensity,-1,new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if (which == 0) {
                                        getexercisecals = getexercisecals + 16;
                                    } else if (which == 1) {
                                        getexercisecals = getexercisecals + 49;
                                    }else if (which == 2) {
                                        getexercisecals = getexercisecals + 98;
                                    }else if (which == 3) {
                                        getexercisecals = getexercisecals + 30;
                                    }else if (which == 4) {
                                        getexercisecals = getexercisecals + 90;
                                    }else if (which == 5) {
                                        getexercisecals = getexercisecals + 180;
                                    }else if (which == 6) {
                                        getexercisecals = getexercisecals + 50;
                                    }else if (which == 7) {
                                        getexercisecals = getexercisecals + 150;
                                    }else if (which == 8) {
                                        getexercisecals = getexercisecals + 300;
                                    }else if (which == 9) {
                                        getexercisecals = getexercisecals + 75;
                                    }else if (which == 10) {
                                        getexercisecals = getexercisecals + 225;
                                    }else if (which == 11) {
                                        getexercisecals = getexercisecals + 500;
                                    }
                                }
                            });
                            exerciseintensity.show();

                    } else if (which == 2) {
                        AlertDialog.Builder exerciseintensity = new AlertDialog.Builder(LogExercise.this);
                        exerciseintensity.setTitle("Select intensity");
                        exerciseintensity.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                exeriselog.setText(""+getexercisecals);
                                getexercisecals = 0;
                            }
                        });
                        exerciseintensity.setSingleChoiceItems(intensity,-1,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (which == 0) {
                                    getexercisecals = getexercisecals + 35;
                                } else if (which == 1) {
                                    getexercisecals = getexercisecals + 100;
                                }else if (which == 2) {
                                    getexercisecals = getexercisecals + 200;
                                }else if (which == 3) {
                                    getexercisecals = getexercisecals + 115;
                                }else if (which == 4) {
                                    getexercisecals = getexercisecals + 350;
                                }else if (which == 5) {
                                    getexercisecals = getexercisecals + 700;
                                }else if (which == 6) {
                                    getexercisecals = getexercisecals + 150;
                                }else if (which == 7) {
                                    getexercisecals = getexercisecals + 450;
                                }else if (which == 8) {
                                    getexercisecals = getexercisecals + 900;
                                }else if (which == 9) {
                                    getexercisecals = getexercisecals + 200;
                                }else if (which == 10) {
                                    getexercisecals = getexercisecals + 600;
                                }else if (which == 11) {
                                    getexercisecals = getexercisecals + 1200;
                                }
                            }
                        });
                        exerciseintensity.show();

                    } else if (which == 3) {
                        AlertDialog.Builder exerciseintensity = new AlertDialog.Builder(LogExercise.this);
                        exerciseintensity.setTitle("Select intensity");
                        exerciseintensity.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                exeriselog.setText(""+getexercisecals);
                                getexercisecals = 0;
                            }
                        });
                        exerciseintensity.setSingleChoiceItems(intensity,-1,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (which == 0) {
                                    getexercisecals = getexercisecals + 80;
                                } else if (which == 1) {
                                    getexercisecals = getexercisecals + 240;
                                }else if (which == 2) {
                                    getexercisecals = getexercisecals + 480;
                                }else if (which == 3) {
                                    getexercisecals = getexercisecals + 126;
                                }else if (which == 4) {
                                    getexercisecals = getexercisecals + 257;
                                }else if (which == 5) {
                                    getexercisecals = getexercisecals + 514;
                                }else if (which == 6) {
                                    getexercisecals = getexercisecals + 202;
                                }else if (which == 7) {
                                    getexercisecals = getexercisecals + 404;
                                }else if (which == 8) {
                                    getexercisecals = getexercisecals + 808;
                                }else if (which == 9) {
                                    getexercisecals = getexercisecals + 260;
                                }else if (which == 10) {
                                    getexercisecals = getexercisecals + 780;
                                }else if (which == 11) {
                                    getexercisecals = getexercisecals + 1560;
                                }
                            }
                        });
                        exerciseintensity.show();

                    } else if (which == 4) {
                        AlertDialog.Builder exerciseintensity = new AlertDialog.Builder(LogExercise.this);
                        exerciseintensity.setTitle("Select intensity");
                        exerciseintensity.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                exeriselog.setText(""+getexercisecals);
                                getexercisecals = 0;
                            }
                        });
                        exerciseintensity.setSingleChoiceItems(intensity,-1,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (which == 0) {
                                    getexercisecals = getexercisecals + 60;
                                } else if (which == 1) {
                                    getexercisecals = getexercisecals + 180;
                                }else if (which == 2) {
                                    getexercisecals = getexercisecals + 360;
                                }else if (which == 3) {
                                    getexercisecals = getexercisecals + 102;
                                }else if (which == 4) {
                                    getexercisecals = getexercisecals + 304;
                                }else if (which == 5) {
                                    getexercisecals = getexercisecals + 608;
                                }else if (which == 6) {
                                    getexercisecals = getexercisecals + 140;
                                }else if (which == 7) {
                                    getexercisecals = getexercisecals + 420;
                                }else if (which == 8) {
                                    getexercisecals = getexercisecals + 840;
                                }else if (which == 9) {
                                    getexercisecals = getexercisecals + 180;
                                }else if (which == 10) {
                                    getexercisecals = getexercisecals + 540;
                                }else if (which == 11) {
                                    getexercisecals = getexercisecals + 1080;
                                }
                            }
                        });
                        exerciseintensity.show();

                    } else if (which == 5) {
                        AlertDialog.Builder exerciseintensity = new AlertDialog.Builder(LogExercise.this);
                        exerciseintensity.setTitle("Select intensity");
                        exerciseintensity.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                exeriselog.setText(""+getexercisecals);
                                getexercisecals = 0;
                            }
                        });
                        exerciseintensity.setSingleChoiceItems(intensity,-1,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (which == 0) {
                                    getexercisecals = getexercisecals + 90;
                                } else if (which == 1) {
                                    getexercisecals = getexercisecals + 270;
                                }else if (which == 2) {
                                    getexercisecals = getexercisecals + 540;
                                }else if (which == 3) {
                                    getexercisecals = getexercisecals + 120;
                                }else if (which == 4) {
                                    getexercisecals = getexercisecals + 360;
                                }else if (which == 5) {
                                    getexercisecals = getexercisecals + 720;
                                }else if (which == 6) {
                                    getexercisecals = getexercisecals + 172;
                                }else if (which == 7) {
                                    getexercisecals = getexercisecals + 517;
                                }else if (which == 8) {
                                    getexercisecals = getexercisecals + 1034;
                                }else if (which == 9) {
                                    getexercisecals = getexercisecals + 290;
                                }else if (which == 10) {
                                    getexercisecals = getexercisecals + 870;
                                }else if (which == 11) {
                                    getexercisecals = getexercisecals + 1740;
                                }
                            }
                        });
                        exerciseintensity.show();

                    } else if (which == 6) {
                        AlertDialog.Builder exerciseintensity = new AlertDialog.Builder(LogExercise.this);
                        exerciseintensity.setTitle("Select intensity");
                        exerciseintensity.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                exeriselog.setText(""+getexercisecals);
                                getexercisecals = 0;
                            }
                        });
                        exerciseintensity.setSingleChoiceItems(intensity,-1,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (which == 0) {
                                    getexercisecals = getexercisecals + 50;
                                } else if (which == 1) {
                                    getexercisecals = getexercisecals + 150;
                                }else if (which == 2) {
                                    getexercisecals = getexercisecals + 300;
                                }else if (which == 3) {
                                    getexercisecals = getexercisecals + 102;
                                }else if (which == 4) {
                                    getexercisecals = getexercisecals + 306;
                                }else if (which == 5) {
                                    getexercisecals = getexercisecals + 612;
                                }else if (which == 6) {
                                    getexercisecals = getexercisecals + 120;
                                }else if (which == 7) {
                                    getexercisecals = getexercisecals + 360;
                                }else if (which == 8) {
                                    getexercisecals = getexercisecals + 720;
                                }else if (which == 9) {
                                    getexercisecals = getexercisecals + 160;
                                }else if (which == 10) {
                                    getexercisecals = getexercisecals + 420;
                                }else if (which == 11) {
                                    getexercisecals = getexercisecals + 840;
                                }
                            }
                        });
                        exerciseintensity.show();

                    } else if (which == 7) {
                        AlertDialog.Builder exerciseintensity = new AlertDialog.Builder(LogExercise.this);
                        exerciseintensity.setTitle("Select intensity");
                        exerciseintensity.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                exeriselog.setText(""+getexercisecals);
                                getexercisecals = 0;
                            }
                        });
                        exerciseintensity.setSingleChoiceItems(intensity,-1,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (which == 0) {
                                    getexercisecals = getexercisecals + 75;
                                } else if (which == 1) {
                                    getexercisecals = getexercisecals + 225;
                                }else if (which == 2) {
                                    getexercisecals = getexercisecals + 450;
                                }else if (which == 3) {
                                    getexercisecals = getexercisecals + 100;
                                }else if (which == 4) {
                                    getexercisecals = getexercisecals + 300;
                                }else if (which == 5) {
                                    getexercisecals = getexercisecals + 600;
                                }else if (which == 6) {
                                    getexercisecals = getexercisecals + 136;
                                }else if (which == 7) {
                                    getexercisecals = getexercisecals + 408;
                                }else if (which == 8) {
                                    getexercisecals = getexercisecals + 817;
                                }else if (which == 9) {
                                    getexercisecals = getexercisecals + 160;
                                }else if (which == 10) {
                                    getexercisecals = getexercisecals + 480;
                                }else if (which == 11) {
                                    getexercisecals = getexercisecals + 960;
                                }
                            }
                        });
                        exerciseintensity.show();
                    }
                }
            });
            exercisechoice.show();
         //if pedometer chosen the pedometer class is launched
        }else if(v.getId()== pedometer.getId()){
            Intent myIntent = new Intent(this, Pedometer.class);
            startActivityForResult(myIntent, 0);
        }
    }
    //method to take user back to welcome screen
    public void happilogoExercise(View v)
    {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }

    //disable back button from welcome screen
    @Override
    public void onBackPressed() {

    }
}