package com.example.stef.freeparking;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class sju extends FragmentActivity implements OnMapReadyCallback {
    private Spinner spinner;
    private Button b;
    private Button b2;
    private LatLng mandevilleStreet = new LatLng (39.996916, -75.235666);
    private LatLng barbelinStreet = new LatLng(39.995978, -75.237649);
    private LatLng sourinStreet = new LatLng(39.993195, -75.240930);
    private LatLng merionStreet = new LatLng(39.997436, -75.243526);
    static public LatLng currentStreet;
    static public int bearing = 0;
    GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sju);

        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Saint Joseph's University");
        list.add("Mandeville Parking Lot A");
        list.add("Barbelin Parking Lot");
        list.add("Sourin Parking Lot");
        list.add("Merion Parking Lot");


        ArrayAdapter<String> data = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(data);

        addListenerOnSpinnerItemSelection();
        addListenerOnButton();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.sjuMapFrag);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng sju = new LatLng (39.996408, -75.235913);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(sju, 20));
        myMap = map;
    }


    public void addListenerOnSpinnerItemSelection(){
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton(){

        spinner = (Spinner) findViewById(R.id.spinner);     //gets spinner ID
        b = (Button) findViewById(R.id.button50);           //gets submit button id
        b2 = (Button) findViewById(R.id.streetButton);      //gets show lot button id
        b.setOnClickListener(new View.OnClickListener() {   //sets onclick listener for submit button
            @Override
            public void onClick(View v) {
                if (String.valueOf(spinner.getSelectedItem()).equals("Mandeville Parking Lot A")) {
                    LatLng l = new LatLng(Login.spots[0].latitude, Login.spots[0].longitude);
                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(l, 20));
                    for (int a = 0; a < 3; a++) {
                        LatLng x = new LatLng(Login.spots[a].latitude, Login.spots[a].longitude);
                        Marker spot = myMap.addMarker(new MarkerOptions()
                                .title("Spot " + a)
                                .position(x));
                    }
                } else if (String.valueOf(spinner.getSelectedItem()).equals("Barbelin Parking Lot")) {
                    LatLng l = new LatLng(39.995290, -75.237352);
                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(l, 20));
                } else if (String.valueOf(spinner.getSelectedItem()).equals("Sourin Parking Lot")) {
                    LatLng l = new LatLng(39.993118, -75.239997);
                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(l, 20));
                } else if (String.valueOf(spinner.getSelectedItem()).equals("Merion Parking Lot")) {
                    LatLng l = new LatLng(39.996381, -75.244415);
                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(l, 20));
                } else
                    Toast.makeText(getApplicationContext(), "No Parking Spots Available", Toast.LENGTH_SHORT).show();


            }
        });

        //street view button click
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(spinner.getSelectedItem()).equals("Mandeville Parking Lot A")) {
                    currentStreet = mandevilleStreet;
                    bearing = 180;
                    Intent intent = new Intent(sju.this, streetView.class);
                    startActivity(intent);
                }
                else if (String.valueOf(spinner.getSelectedItem()).equals("Barbelin Parking Lot")) {
                    currentStreet = barbelinStreet;
                    bearing = 180;
                    Intent intent = new Intent(sju.this, streetView.class);
                    startActivity(intent);
                }
                else if (String.valueOf(spinner.getSelectedItem()).equals("Sourin Parking Lot")) {
                    currentStreet = sourinStreet;
                    bearing = 45;
                    Intent intent = new Intent(sju.this, streetView.class);
                    startActivity(intent);
                }
                else if (String.valueOf(spinner.getSelectedItem()).equals("Merion Parking Lot")) {
                    currentStreet = merionStreet;
                    bearing = 160;
                    Intent intent = new Intent(sju.this, streetView.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "No Parking Spots Available", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sju, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
