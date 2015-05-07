package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    09/09/2014
 * This programme launches and controls the food list database search element of the application
 */
public class ExpandableFoodList extends Activity {
    //variable declared
    ExpandableListView nutritionallist;//expandablelistview
    TextView nutritionalinfotitle;
    TextView nutritionalinfo;
    TextView foodserving;
    FoodDatabaseHandler foodhandler;//food database

    private static int lastposition = -1;

    final Animation textFadeIn = new AlphaAnimation(0.0f, 1.0f);//animation to fade text in

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablefoodlist);//set to expandablefoodlist screen using unique id

        nutritionallist = (ExpandableListView) findViewById(R.id.expandableFoodLister);
        nutritionallist.setAdapter(new ExpandableFoodAdapter(this));//set expandablelist adapter

        nutritionalinfo = (TextView) findViewById(R.id.nutritionalinfo);
        nutritionalinfotitle = (TextView) findViewById(R.id.nutritionaltitle);

        foodserving = (TextView) findViewById(R.id.servingsize);

        foodhandler = new FoodDatabaseHandler(getBaseContext());//food database current context

        textFadeIn.setDuration(1750);//fade duration

        nutritionallist.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if(lastposition != -1 && groupPosition != lastposition){
                    nutritionallist.collapseGroup(lastposition);
                }
                lastposition = groupPosition;
            }
        });
        nutritionallist.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int group_position, int child_position, long id)
            {
                //if first upper level selected and first lower level selected
                if(group_position==0 && child_position==0) {

                    nutritionalinfotitle.setVisibility(1);//title set visible
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Apple");//database search for apple

                    Pattern findpattern = Pattern.compile("Apple");//pattern declared
                    Matcher findmatch = findpattern.matcher(fooddata);//matcher to find match of pattern

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);//text found stored in SpannableStringBuilder
                    //change all words searched for to desired colour, search length of text returned
                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.GREEN), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }
                        nutritionalinfo.setText(textspanner);//set text to information
                        nutritionalinfo.startAnimation(textFadeIn);//set text to fade
                    }
                }//same process conducted for subsequent code
                else if(group_position==0 && child_position==1) {
                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Banana");

                    Pattern findpattern = Pattern.compile("Banana");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.YELLOW), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }
                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==0 && child_position==2) {

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Orange");
                    Pattern findpattern = Pattern.compile("Orange");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(255, 165, 0)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }
                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==0 && child_position==3) {

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Pineapple");
                    Pattern findpattern = Pattern.compile("Pineapple");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.YELLOW), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }
                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==0 && child_position==4) {

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Pear");
                    Pattern findpattern = Pattern.compile("Pear");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(50, 205, 50)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==0 && child_position==5) {

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Plum");
                    Pattern findpattern = Pattern.compile("Plum");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(159, 0, 255)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==0 && child_position==6) {

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Kiwi");
                    Pattern findpattern = Pattern.compile("Kiwi");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(51, 255, 51)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }
                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==0 && child_position==7) {

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Peach");
                    Pattern findpattern = Pattern.compile("Peach");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(250, 223, 173)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }
                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==0 && child_position==8) {

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Strawberry");
                    Pattern findpattern = Pattern.compile("Strawberry");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.RED), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }
                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==0 && child_position==9) {

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Melon");
                    Pattern findpattern = Pattern.compile("Melon");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(253,188,180)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                else if(group_position==1 && child_position==0){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Asparugus");
                    Pattern findpattern = Pattern.compile("Asparugus");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(130,159,83)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==1){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Broccoli");
                    Pattern findpattern = Pattern.compile("Broccoli");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(109,124,75)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==2){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Brussels Sprouts");
                    Pattern findpattern = Pattern.compile("Brussels Sprouts");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(150,158,80)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==3){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Carrot");
                    Pattern findpattern = Pattern.compile("Carrot");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(235,137,33)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==4){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Cabbage");
                    Pattern findpattern = Pattern.compile("Cabbage");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(61,73,53)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==5){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Cauliflower");
                    Pattern findpattern = Pattern.compile("Cauliflower");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(240,227,199)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==6){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Celery");
                    Pattern findpattern = Pattern.compile("Celery");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.GREEN), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==7){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Corn");
                    Pattern findpattern = Pattern.compile("Corn");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.YELLOW), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==8){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Lettuce");
                    Pattern findpattern = Pattern.compile("Lettuce");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.GREEN), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==1 && child_position==9){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Leek");
                    Pattern findpattern = Pattern.compile("Leek");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.GREEN), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==2 && child_position==0){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Beef");
                    Pattern findpattern = Pattern.compile("Beef");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.MAGENTA), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==2 && child_position==1){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Chicken");
                    Pattern findpattern = Pattern.compile("Chicken");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.YELLOW), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==2 && child_position==2){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Pork");
                    Pattern findpattern = Pattern.compile("Pork");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(255,182,193)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==2 && child_position==3){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Tuna");
                    Pattern findpattern = Pattern.compile("Tuna");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.BLUE), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==2 && child_position==4){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Salmon");
                    Pattern findpattern = Pattern.compile("Salmon");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(255,145,164)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==2 && child_position==5){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Cod");
                    Pattern findpattern = Pattern.compile("Cod");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(0,105,148)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==3 && child_position==0){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Lemonade");
                    Pattern findpattern = Pattern.compile("Lemonade");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.YELLOW), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==3 && child_position==1){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Orange Juice");
                    Pattern findpattern = Pattern.compile("Orange Juice");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.rgb(255, 165, 0)), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==3 && child_position==2){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Tea");
                    Pattern findpattern = Pattern.compile("Tea");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.CYAN), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==3 && child_position==3){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Milk");
                    Pattern findpattern = Pattern.compile("Milk");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.WHITE), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==3 && child_position==4){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Apple Juice");
                    Pattern findpattern = Pattern.compile("Apple Juice");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.GREEN), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }else if(group_position==3 && child_position==5){

                    nutritionalinfotitle.setVisibility(1);
                    foodserving.setVisibility(1);
                    String fooddata = foodhandler.returnItem("Cranberry Juice");
                    Pattern findpattern = Pattern.compile("Cranberry Juice");
                    Matcher findmatch = findpattern.matcher(fooddata);

                    SpannableStringBuilder textspanner = new SpannableStringBuilder(fooddata);

                    for (int x = 0; x < fooddata.length(); x++) {
                        if (findmatch.find()) {
                            textspanner.setSpan(new ForegroundColorSpan(Color.RED), findmatch.start(), findmatch.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        } else {
                        }

                        nutritionalinfo.setText(textspanner);
                        nutritionalinfo.startAnimation(textFadeIn);
                    }
                }
                return false;
            }
        });
    }
    //method to return user to welcome screen
    public void happilogoExpandableList(View v)
    {
        Intent myIntent = new Intent(this, FoodNutritionSearch.class);
        startActivity(myIntent);
        finish();
    }
    @Override
    public void onBackPressed() {

    }
}