package com.example.MScSoftwareProject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapOptions;
import com.esri.android.map.MapView;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.TextSymbol;

/**
 * Created by Johnathan McCartney
 * User:    Johnathan McCartney
 * Date:    28/09/2014
 * This programme shows the developers location making use of the arcGIS library for Android
 */
public class Developerlocation extends Activity {
    //map view declared
    MapView mymap;
    //map options allows the user to toggle between different base mapping
    MapOptions topomap = new MapOptions(MapOptions.MapType.TOPO);
    MapOptions streetmap = new MapOptions(MapOptions.MapType.STREETS);
    MapOptions satellitemap = new MapOptions(MapOptions.MapType.SATELLITE);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developerlocation);//set to developer location screen using unique id

        mymap = (MapView) findViewById(R.id.mapview);//link map view to xml using unique id
        mymap.setEsriLogoVisible(true);//ESRI (owners of arcGIS) logo set visible

        SimpleMarkerSymbol mycustomsymbol = new SimpleMarkerSymbol(Color.BLUE,14, SimpleMarkerSymbol.STYLE.CIRCLE);//simple marker

        TextSymbol myText = new TextSymbol(14, "Developers Location", Color.BLUE);//text for map

        GraphicsLayer graphicsLayer = new GraphicsLayer();//create graphics layer
        graphicsLayer.addGraphic(new Graphic(new Point(-813400, 7356500), mycustomsymbol));//coordinates of symbol
        graphicsLayer.addGraphic(new Graphic(new Point(-813000, 7356900), myText));//coordinates of text
        mymap.addLayer(graphicsLayer);//add graphic to graphic layer
    }
    //do this on map pause
    @Override
    protected void onPause() {
        super.onPause();
        mymap.pause();
    }
    //do this on map resume
    @Override
    protected void onResume() {
        super.onResume();
        mymap.unpause();
    }
    //method to set base map to topographic mapping
    public void setTopomap(View v){
        mymap.setMapOptions(topomap);
    }
    //method to set base map to street mapping
    public void setStreetmap(View v){
        mymap.setMapOptions(streetmap);
    }
    //method to set base map to satellite mapping
    public void setSatellitemap(View v){
        mymap.setMapOptions(satellitemap);
    }
}