package com.example.stef.freeparking;

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


public class drexel extends FragmentActivity implements OnMapReadyCallback {
    private Spinner spinner;
    private Button b;
    GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drexel);

        spinner = (Spinner) findViewById(R.id.spinnerDrexel);
        List<String> list = new ArrayList<String>();
        list.add("Drexel University");
        list.add("Drexel Parking Lot A");
        list.add("Drexel Parking Lot B");
        list.add("Drexel Parking Lot C");
        list.add("Drexel Parking Lot D");

        ArrayAdapter<String> data = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(data);

        addListenerOnSpinnerItemSelection();
        addListenerOnButton();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.drexelMapFrag);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng drexel = new LatLng (39.955594, -75.191801);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(drexel, 60));
        myMap = map;
    }



    public void addListenerOnSpinnerItemSelection(){
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton(){
        spinner = (Spinner) findViewById(R.id.spinnerDrexel);
        b = (Button) findViewById(R.id.button50);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(spinner.getSelectedItem()).equals("Mandeville Parking Lot A")) {
                    LatLng l = new LatLng(Login.spots[0].latitude, Login.spots[0].longitude);
                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(l, 75));
                    for(int a = 0; a < 3; a++){
                        LatLng x = new LatLng(Login.spots[a].latitude, Login.spots[a].longitude);
                        Marker spot = myMap.addMarker(new MarkerOptions()
                                .title("Spot "+a)
                                .position(x));
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "No Parking Spots Available",Toast.LENGTH_SHORT).show();
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
