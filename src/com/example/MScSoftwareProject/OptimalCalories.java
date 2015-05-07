package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.text.DecimalFormat;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    10/09/2014.
 * This programme accepts the user personal details and calculate details such as BMI, ideal weight and ideal calories
 */
public class OptimalCalories extends Activity implements SeekBar.OnSeekBarChangeListener,View.OnClickListener {
    //variable declared
    Button buttondetails;
    TextView calorieResult;
    //male female radio buttons
    private RadioGroup maleFemale;
    private RadioButton male;
    private RadioButton female;
    //activity level radio buttons
    private RadioGroup activitygroup;
    private RadioButton sedentary;
    private RadioButton active;
    private RadioButton moderatelyactive;
    private RadioButton veryactive;
    //age, height, weight seekbars
    SeekBar seekbarage;
    SeekBar seekbarheight;
    SeekBar seekbarweight;
    //text views show info
    TextView height;
    TextView age;
    TextView weight;
    TextView optimalcalorie;

    static double bmi = 0;//bmi
    static int setcalories = 0;//calories
    static double idealweight;//ideal weight

    DecimalFormat formatnum = new DecimalFormat("##.##");//format data to 2 decimal places

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optimalcalories);//set to optimal calorie screen using unique id

        maleFemale = (RadioGroup) findViewById(R.id.radiogroupmalefemale);
        male = (RadioButton) findViewById(R.id.radiobuttonMale);
        female = (RadioButton) findViewById(R.id.radiobuttonFemale);

        seekbarheight = (SeekBar) findViewById(R.id.seekbarHeight);
        seekbarheight.setOnSeekBarChangeListener(this);
        height = (TextView) findViewById(R.id.currentheight);

        seekbarage = (SeekBar) findViewById(R.id.seekbarAge);
        seekbarage.setOnSeekBarChangeListener(this);
        age = (TextView) findViewById(R.id.currentage);

        seekbarweight = (SeekBar) findViewById(R.id.seekBarweight);
        seekbarweight.setOnSeekBarChangeListener(this);
        weight = (TextView) findViewById(R.id.currentWeight);

        activitygroup = (RadioGroup) findViewById(R.id.radiogroupActive);
        sedentary = (RadioButton) findViewById(R.id.radiobuttonSedentary);
        active = (RadioButton) findViewById(R.id.radiobuttonActive);
        moderatelyactive = (RadioButton) findViewById(R.id.radiobuttonModeratelyActive);
        veryactive = (RadioButton) findViewById(R.id.radiobuttonVeryActive);

        buttondetails = (Button) findViewById(R.id.buttonsubmitdetails);
        buttondetails.setOnClickListener(this);

        optimalcalorie = (TextView) findViewById(R.id.calorieresult);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //as seek bar progress changes the textviews are updated
        if (seekBar.getId()==seekbarheight.getId()){
            height.setText("" + progress);
        }
        else if(seekBar.getId()==seekbarage.getId()){
            age.setText("" + progress);
        }
        else if(seekBar.getId()==seekbarweight.getId()){
            weight.setText("" + progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // as seek bar move the toast messages are presented
        if (seekBar.getId()==seekbarheight.getId()){
            Toast toast = Toast.makeText(this, "Height is in centimetres", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
        }
        else if(seekBar.getId()==seekbarage.getId()){
            Toast toast = Toast.makeText(this, "Enter your age", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
        }
        else if(seekBar.getId()==seekbarweight.getId()){
            Toast toast = Toast.makeText(this, "Weight is in kilos", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, -10, 25);
            toast.show();
        }
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
    //method to take user back to welcome screen
    public void happilogoCalories(View v)
    {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }
    @Override
    public void onClick(View v) {
        //if male radio button checked
       if (male.isChecked()){
           //male weight and height retrieved and bmi calculated
           double maleweight = seekbarweight.getProgress();
           double maleheight = seekbarheight.getProgress();
           bmi = (maleweight / (maleheight / 100))/(maleheight/100);//bmi calculation

           bmi = Double.parseDouble(formatnum.format(bmi));//result formatted

           Person person = new Person();//person object created
           person.setHeight(seekbarheight.getProgress());
           person.setWeight(seekbarweight.getProgress());

           String bmitext = person.getBMIText(bmi);//bmi text result sought here

           idealweight = person.maleidealWeight(maleheight);//ideal weight returned here
           //ideal calories set here
           setcalories = (int) (88.362 + (13.397 * seekbarweight.getProgress()) + (4.799 * seekbarheight.getProgress()) - (5.677 * seekbarage.getProgress()));
           //if activity level radio button checked
           if(sedentary.isChecked()){
              setcalories = (int) (setcalories * 1.2);
           }else if (active.isChecked()){
              setcalories = (int) (setcalories * 1.375);
           }else if (moderatelyactive.isChecked()){
              setcalories = (int) (setcalories * 1.55);
           }else if (veryactive.isChecked()){
                setcalories = (int) (setcalories * 1.72);
           }
           //information is put as extras in intents
           Intent intent = new Intent(this,CalorieResults.class);
           intent.putExtra("Calories",setcalories);
           intent.putExtra("Bmi",bmi);
           intent.putExtra("Bmitext",bmitext);
           intent.putExtra("Currentweight",maleweight);
           intent.putExtra("Idealweight",idealweight);
           startActivity(intent);

           SharedPreferences graphpreferences = PreferenceManager.getDefaultSharedPreferences(this);

           SharedPreferences.Editor editor = graphpreferences.edit();
           editor.putInt("graphweight", (int)maleweight);
           editor.putInt("graphheight",(int)maleheight);
           editor.commit();
         //else if female radio button is checked the same process is carried out for female as above
        }else if (female.isChecked()) {

           double femaleweight = seekbarweight.getProgress();
           double femaleheight = seekbarheight.getProgress();
           bmi = (femaleweight / (femaleheight / 100))/(femaleheight/100);

           bmi = Double.parseDouble(formatnum.format(bmi));

           Person person = new Person();
           person.setHeight(seekbarheight.getProgress());
           person.setWeight(seekbarweight.getProgress());

           String bmitext = person.getBMIText(bmi);

           idealweight = person.femaleidealWeight(femaleheight);

           setcalories = (int) (655.0955+(9.5634*seekbarweight.getProgress())+(1.8496*((seekbarheight.getProgress()/100)))- (4.6756 * seekbarage.getProgress()));

           if(sedentary.isChecked()){
              setcalories = (int) (setcalories * 1.2);
           }else if (active.isChecked()){
              setcalories = (int) (setcalories * 1.375);
           }else if (moderatelyactive.isChecked()){
              setcalories = (int) (setcalories * 1.55);
           }else if (veryactive.isChecked()){
              setcalories = (int) (setcalories * 1.72);
           }

           Intent intent = new Intent(this,CalorieResults.class);
           intent.putExtra("Calories",setcalories);
           intent.putExtra("Bmi",bmi);
           intent.putExtra("Bmitext",bmitext);
           intent.putExtra("Currentweight",femaleweight);
           intent.putExtra("Idealweight",idealweight);
           startActivity(intent);

           SharedPreferences graphpreferences = PreferenceManager.getDefaultSharedPreferences(this);

           SharedPreferences.Editor editor = graphpreferences.edit();
           editor.putInt("graphweight", (int)femaleweight);
           editor.putInt("graphheight",(int)femaleheight);
           editor.commit();
        }
    }
    @Override
    public void onBackPressed() {

    }
 }