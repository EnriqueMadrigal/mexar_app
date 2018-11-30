package com.datalabor.soporte.mexar.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.models.Distribuidor;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private boolean GoogleMapReady = false;
    private boolean MarkersReady = false;
    private boolean MarkersLoaded= false;

    private HashMap<Marker, Integer> eventMarkerMap =  new HashMap<Marker, Integer>();
    Distribuidor _distribuidor;
    Context context;
    private final String TAG = "MapsActivity 1";

    Location curLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        context = this;


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        _distribuidor = (Distribuidor) bundle.getSerializable("distribuidor");

        //LoadDistribuidores();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds



        //LoadDistribuidores();

Log.d(TAG,"test");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap = googleMap;
        GoogleMapReady = true;




        // Add a marker in Sydney and move the camera
        /*
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */
        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
        mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener)  this);
        LoadMarkers();



    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }


    @Override
    public void onConnected(Bundle bundle) {
        Location location;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   == PackageManager.PERMISSION_GRANTED)
        {
            location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
            else {
               // handleNewLocation(location);  // Poner la localización actual...
                curLocation = location;
            }

        }



    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    /*
     * Google Play services can resolve some errors it detects.
     * If the error has a resolution, try sending an Intent to
     * start a Google Play services activity that can resolve
     * error.
     */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            /*
             * Thrown if Google Play services canceled the original
             * PendingIntent
             */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
        /*
         * If no resolution is available, display a dialog to the
         * user with the error.
         */
            Log.i("MapsActivity1", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMap();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    private void handleNewLocation(Location location) {
        //Log.d("handlenewLocation", location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        curLocation = location;

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("Ubicación Actual!");
        // mMap.addMarker(options);

        float zoomLevel = 12.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        //Log.d("marcador",marker.getTitle());
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        Integer idestetica = eventMarkerMap.get(marker);

        if (idestetica!=null) {
            //Log.d("marcador idestetica=", String.valueOf(idestetica));
            /*
            Intent i = new Intent(context, muestra1.class );
            dataAccess p = dataAccess.getInstance();
            p.setCurrentEstetica(idestetica);
            startActivity(i);
            */
        }

    }




    public void LoadMarkers()
    {


        if (MarkersLoaded) return;  // Ya se habian cargado
        //if (Tiendas==null) return;
        //if (Tiendas.size()<1) return;

        if (GoogleMapReady)
        {


             Distribuidor item = _distribuidor;

                String title = item.get_comercial_name();
                String lat = item.get_latitud();
                String lng = item.get_longitud();

                Double curLatitude=0.0;
                Double curLongitude=0.0;

                //Integer idEstetica = Integer.parseInt(oslist.get(i).get(Estetica_idestetica));

                if (isNumeric(lat))
                {
                    try
                    {
                        curLatitude = Double.parseDouble(lat);
                    } catch (NumberFormatException e)
                    {
                        //  did not contain a valid double
                    return;
                    }

                }

                else return;

                if (isNumeric(lng))
                {
                    try
                    {
                        curLongitude = Double.parseDouble(lng); // Make use of autoboxing.  It's also easier to read.
                    } catch (NumberFormatException e)
                    {
                        return;
                    }

                }

                else return;



                LatLng latLng = new LatLng(curLatitude, curLongitude);
                Marker myMarker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(title)
                        .snippet("!")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                eventMarkerMap.put(myMarker, 0);


                LatLng newMarker = new LatLng(Double.valueOf(lat), Double.valueOf(lng));
                mMap.addMarker(new MarkerOptions().position(newMarker).title(title));

                float zoomLevel = 12.0f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newMarker, zoomLevel));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newMarker));


            MarkersLoaded = true;
            //Log.d("MarkersLoaded","true");

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   == PackageManager.PERMISSION_GRANTED)
            {
                mMap.setMyLocationEnabled(true);
            }


            else {
                //Toast.makeText(MapsActivity2.this, "Usted ha aceptado!", Toast.LENGTH_LONG).show();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }



            }





        }

        else return;

    }


    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }


}
