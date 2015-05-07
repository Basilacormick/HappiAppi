package com.example.MScSoftwareProject;

import java.text.DecimalFormat;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    10/09/2014.
 * This programme delivers a no application class that contains methods to return additional information on calories
 * and weight, contains methods to set weight and height also
 */

public class Person {

    DecimalFormat df = new DecimalFormat("##.##");//formats results

    //variables are declared
    private double height;
    private double weight;
    private int age;

    //method to get height
    public double getHeight() {
        return height;
    }
    //method to set height
    public void setHeight(double pHeight) {
        height = pHeight;
    }
    //method to get weight
    public double getWeight() {
        return weight;
    }
    //method to set weight
    public void setWeight(double pWeight) {
        weight = pWeight;
    }
    //returns message based on users bmi
    public String getBMIText(double pbmi) {
        String returnValue;
        if (pbmi < 18.5) {
            returnValue = "You are underweight, eat a balanced diet ";
        } else if (pbmi < 24.9) {
            returnValue = "You are a healthy weight, maintain your current eating patterns";
        } else {
            returnValue = "You are over overweight, eat a balanced diet and take more exercise";
        }
        return returnValue;
    }
    //method to return male ideal weight
    public Double maleidealWeight(double pheight){
        double maleheightinch;
        double maleidealweight;
        maleheightinch = pheight * 0.39;
        maleidealweight = 106 + (maleheightinch - 60) * 5;
        maleidealweight = maleidealweight * 0.456;
        return Double.valueOf(df.format(maleidealweight));
    }
    //method to return female ideal weight
    public Double femaleidealWeight(double pheight){
        double femaleheightinch;
        double femaleidealweight;
        femaleheightinch = pheight * 0.39;
        femaleidealweight = 100 + (femaleheightinch - 60) * 5;
        femaleidealweight = femaleidealweight * 0.456;
        return Double.valueOf(df.format(femaleidealweight));
    }
}
