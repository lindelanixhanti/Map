package com.map.lindelani.map;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if goolge play services APK is not available.
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float last_x, last_y, last_z;

    long lastUpdate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        // setup the accelerometer.
        //sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        //accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];// x value
            float y = event.values[1];// y value
            float z = event.values[2];// z value

            long curTime = System.currentTimeMillis();

            if (Math.abs(curTime - lastUpdate) > 2000) {
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String currentDateTime = date.format(new Date());
                lastUpdate = curTime;

                if (Math.abs(last_x - x) > 10) {
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(37.26062, -80.45198))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                            .title("Hey you moved the x axis on" + currentDateTime));
                }

                if (Math.abs(last_y - y) > 10) {
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(36.25062, -80.44188))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                            .title("Hey you moved the y axis on" + currentDateTime));
                }
                if (Math.abs(last_z - z) > 10) {
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(35.24062, -80.43178))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            .title("Hey you moved the z axis on" + currentDateTime));
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }

        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already installed the map.
        // try to obtain the mapfrom the SupportMapFragment.
        if (mMap == null) {
            SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
            mapFragment.getMapAsync(this);
        }
        // Check if we were successful in obtaining the map.
        if (mMap != null) {
            setmMap();
        }

    }
    /**
     * This is where we can add add makers or lines, add listeners or move the camera. In this case ,we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once when we are sure that {@link#mMap} is not null.
     */
    private void setmMap(){
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.229,-80.424)).title("Virginia Tech"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.229,-80.424),14.9f));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setmMap();
    }
}