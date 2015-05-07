package com.example.MScSoftwareProject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    15/11/2014
 * This programme plot the user weight against a graph showing various weight levels.
 */
public class CalorieGraph extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphview);

        //height and weight are retrieved from their shared preferences file.
        SharedPreferences graphpreference = PreferenceManager.getDefaultSharedPreferences(this);
        int weight = graphpreference.getInt("graphweight", 0);//total calories consumed
        int height = graphpreference.getInt("graphheight",0);

            //height converted according to input
            if(height < 140){
                height = 1;
            }else if(height < 150){
                height = 2;
            }else if(height < 160){
                height = 3;
            }else if(height < 170){
                height = 4;
            }else if(height < 180){
                height = 5;
            }else if(height < 190){
                height = 6;
            }else if(height < 200){
                height = 7;
            }
        //underweight graph data plot
        GraphViewSeries underweightgraphData = new GraphViewSeries("underweight",new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(255, 165, 0), 6),new GraphView.GraphViewData[] {
                  new GraphView.GraphViewData(1, 40)
                , new GraphView.GraphViewData(2, 45)
                , new GraphView.GraphViewData(3, 50)
                , new GraphView.GraphViewData(4, 55)
                , new GraphView.GraphViewData(5, 60)
                , new GraphView.GraphViewData(6, 65)
                , new GraphView.GraphViewData(7, 70)
        });
        //healthy weight graph data plot
        GraphViewSeries healthyweightData = new GraphViewSeries("healthy weight",new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(51, 255, 51), 6),new GraphView.GraphViewData[] {
                  new GraphView.GraphViewData(1, 47)
                , new GraphView.GraphViewData(2, 57)
                , new GraphView.GraphViewData(3, 67)
                , new GraphView.GraphViewData(4, 77)
                , new GraphView.GraphViewData(5, 87)
                , new GraphView.GraphViewData(6, 97)
                , new GraphView.GraphViewData(7, 107)
        });
        //over weight graph data plot
        GraphViewSeries overweightData = new GraphViewSeries("over weight",new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(0,105,148), 6),new GraphView.GraphViewData[] {
                  new GraphView.GraphViewData(1, 56)
                , new GraphView.GraphViewData(2, 66)
                , new GraphView.GraphViewData(3, 76)
                , new GraphView.GraphViewData(4, 86)
                , new GraphView.GraphViewData(5, 96)
                , new GraphView.GraphViewData(6, 106)
                , new GraphView.GraphViewData(7, 116)
        });
        //obese graph data plot
        GraphViewSeries obeseData = new GraphViewSeries("obese",new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(150,158,80), 6),new GraphView.GraphViewData[] {
                  new GraphView.GraphViewData(1, 70)
                , new GraphView.GraphViewData(2, 80)
                , new GraphView.GraphViewData(3, 90)
                , new GraphView.GraphViewData(4, 100)
                , new GraphView.GraphViewData(5, 110)
                , new GraphView.GraphViewData(6, 120)
                , new GraphView.GraphViewData(7, 125)
        });
        //seriously obese graph data plot
        GraphViewSeries seriouslyobeseData = new GraphViewSeries("seriously obese",new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(253,188,180), 6),new GraphView.GraphViewData[] {
                new GraphView.GraphViewData(1, 120)
              , new GraphView.GraphViewData(2, 125)

        });
        //users weight graph data plot
        GraphViewSeries yourweight = new GraphViewSeries("yourweight",new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(255,55,255), 7),new GraphView.GraphViewData[] {
                new GraphView.GraphViewData((double)height, (double)weight)
                ,new GraphView.GraphViewData((double)height+0.1, (double)weight)
        });
        //adds line series to line graph
        LineGraphView healthgraph = new LineGraphView(this, "Ideal weight/height");
        healthgraph.addSeries(underweightgraphData);
        healthgraph.addSeries(healthyweightData);
        healthgraph.addSeries(overweightData);
        healthgraph.addSeries(obeseData);
        healthgraph.addSeries(seriouslyobeseData);
        healthgraph.addSeries(yourweight);

        //horizontal and vertical labels are set here
        healthgraph.setHorizontalLabels(new String[]{"1.3","1.4","1.5","1.60","1.70","1.80","1.90","2.0(m)"});
        healthgraph.setVerticalLabels(new String[]{"125", "120", "115", "110", "105", "100", "95", "90", "85", "80", "75", "70", "65", "60", "55", "50", "45\n(kg)"});
        //layout instantiated and graph added to layout.
        LinearLayout layout = (LinearLayout) findViewById(R.id.graphviewer);
        layout.addView(healthgraph);

        //set graph text size to 18
        healthgraph.getGraphViewStyle().setTextSize(18);
        //show graph legend for user interpretation
        healthgraph.setShowLegend(true);
        healthgraph.setLegendAlign(GraphView.LegendAlign.BOTTOM);
        healthgraph.setLegendWidth(200);
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(this, WelcomeMenu.class);
        startActivity(myIntent);
        finish();
    }
}