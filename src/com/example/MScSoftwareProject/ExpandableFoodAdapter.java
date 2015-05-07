package com.example.MScSoftwareProject;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    09/09/2014
 * This programme provides an adapter to populate the expandablelistview implemented at ExpandableFoodList and
 * controls some functionality
 */
public class ExpandableFoodAdapter extends BaseExpandableListAdapter {

    private Context foodcontext;
    //1d array holds items at upper level
    String [] upperlevel = {"Fruit","Vegetables","Meat","Drink"};
    //2d array holds the items that are present at the lower level
    String[][]  lowerlevel = {{"Apple","Banana","Orange","Pineapple","Pear","Plum","Kiwi","Peach","Strawberry","Melon"},
                              {"Asparugus","Broccoli","Brussels Sprouts","Carrots","Cabbage","Cauliflower","Celery","Corn","Lettuce","Leek"},
                              {"Beef","Chicken","Pork","Tuna","Salmon","Cod"},
                              {"Lemonade","Orange Juice","Tea","Milk","Apple Juice","Cranberry Juice"}};
    //default methods are implemented once the BasExpandableListAdapter is extended
    public ExpandableFoodAdapter(Context context) {
        this.foodcontext = context;
    }

    @Override
    public int getGroupCount() {
        return upperlevel.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return lowerlevel[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //sets view to the upper level array items
        TextView parentText = new TextView(foodcontext);
        parentText.setText(upperlevel[groupPosition]);//1d array
        parentText.setPadding(50,0,0,0);
        parentText.setTextSize(30);
        parentText.setTextColor(Color.WHITE);
        return parentText;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //sets view to the lower level array items
        TextView childText = new TextView(foodcontext);
        childText.setText(lowerlevel[groupPosition][childPosition]);//2d array
        childText.setPadding(30,10,10,10);
        childText.setTextSize(28);
        childText.setTextColor(Color.WHITE);
        return childText;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}