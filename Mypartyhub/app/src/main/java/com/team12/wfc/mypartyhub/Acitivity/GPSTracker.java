package com.team12.wfc.mypartyhub.Acitivity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Nick Zwaans on 2-6-2015.
 */
public class GPSTracker extends Service implements LocationListener {

    private final Context mContext;

    //flag for gps & network status
    boolean isGPSEnabled;
    boolean isNetworkEnabled;
    boolean canGetLocation;

    Location location;
    double latitude;
    double longitude;

    //minimum distance to change location update
    private static final long MIN_DISTANCE_FOR_UPDATES = 10; // 10 meter

    //minimum time between updates
    private static final long MIN_TIME_FOR_UPDATES = 1000*60*1; //1 minute

    protected LocationManager locationManager;

    public GPSTracker(Context context){
        this.mContext = context;
        isGPSEnabled = false;
        isNetworkEnabled = false;
        canGetLocation = false;

        getLocation();
    }

    public Location getLocation(){
        try{
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            //get gps & network status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled){
                // no network provider is enabled
            }else{
                this.canGetLocation = true;
                //get loc from network provider
                if (isNetworkEnabled){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_FOR_UPDATES, MIN_DISTANCE_FOR_UPDATES, this);
                    Log.d("Network", "Network");

                    if (locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                if (isGPSEnabled){
                    if (location == null){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_FOR_UPDATES, MIN_DISTANCE_FOR_UPDATES, this);
                        Log.d("Gps enabled", "GPS enabled");

                        if (locationManager != null){
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Function to get latitude
     */

    public double getLatitude(){
        if (location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

    /**
     * Function to get latitude
     */

    public double getLongitude(){
        if (location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }

    /**
     * Function to check if network provider can get location
     * @return boolean
     */
    public boolean canGetLocation(){
        return this.canGetLocation;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }
    }
    public float getDistance(double lat, double lon){
        float distance;
        Location locationA = new Location("");

        locationA.setLatitude(getLatitude());
        locationA.setLongitude(getLongitude());

        Location locationB = new Location("");

        locationB.setLatitude(lat);
        locationB.setLongitude(lon);

        System.out.println("Location A: " + locationA + " Location B: " + locationB);

        distance = locationA.distanceTo(locationB);



        return distance;
    }
    /**
     * Function to show settings alert dialog
     */
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle("Gps settings");
        alertDialog.setMessage("Gps is not enabled, do you want to go to the settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //Show message
        alertDialog.show();
    }

}
