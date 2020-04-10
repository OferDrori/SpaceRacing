package com.example.Space_Racing;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.example.Space_Racing.Keys.KEY_PLAYER_LATITUDE;
import static com.example.Space_Racing.Keys.KEY_PLAYER_LONGITUDE;

public class LocationSensor {
        private LocationManager locationManager;
        private LocationListener locationListener;
        private MySharedPreferences msp;

        public LocationSensor(Activity activity) {            //Location Settings
            msp = new MySharedPreferences(activity);
            resetLatLong();
            locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    msp.putFlut(KEY_PLAYER_LONGITUDE,(float)longitude);
                    msp.putFlut(KEY_PLAYER_LATITUDE,(float)latitude);
                    Log.i("location",(float)longitude+"");
                    Log.i("location2",(float)latitude+"");
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };

            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }


        }

        /**
         * Clear the LatLong from previous player
         */
        private void resetLatLong(){
            msp.putFlut(KEY_PLAYER_LONGITUDE,  0);
            msp.putFlut(KEY_PLAYER_LATITUDE,  0);
        }
    }




