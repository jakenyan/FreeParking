package com.example.stef.freeparking;

/**
 * Created by Stef on 4/21/2015.
 */
public class Spot {
    double latitude;
    double longitude;
    int vacant;

    public Spot(){
        latitude = 0;
        longitude = 0;
        vacant = 0;

    }

    public Spot(double la, double lo, int v){
        latitude = la;
        longitude = lo;
        vacant = v;
    }

    public Spot( Spot other ){
        this.latitude = other.latitude;
        this.longitude = other.longitude;
        this.vacant = other.vacant;
    }
    public void setLat (double d){
        latitude = d;
    }

    public void setLong (double l){
        longitude = l;
    }

    public double getLat (){
        return latitude;
    }

    public void setVac (int v){
        vacant = v;
    }

    public String toString () {
        return "Latitude = " + latitude + "\t Longitude = "+ longitude;
    }

}