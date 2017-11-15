package com.datalabor.soporte.mexar.activity;

import android.Manifest;
import android.content.Context;
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


public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
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
    ArrayList<Distribuidor> _distribuidores;
    Context context;
    private final String TAG = "MapsActivity 1";

    Location curLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        context = this;

        LoadDistribuidores();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map3);
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


        LoadDistribuidores();



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
                handleNewLocation(location);  // Poner la localización actual...
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


            for (Distribuidor item: _distribuidores)
            {


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
                        continue;
                    }

                }

                else continue;

                if (isNumeric(lng))
                {
                    try
                    {
                        curLongitude = Double.parseDouble(lng); // Make use of autoboxing.  It's also easier to read.
                    } catch (NumberFormatException e)
                    {
                        continue;
                    }

                }

                else continue;



                LatLng latLng = new LatLng(curLatitude, curLongitude);
                Marker myMarker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("The Home Depot")
                        .snippet(title)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.homedepot_marker)));

                        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                //myMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.homedepot_marker));

                eventMarkerMap.put(myMarker, 0);


                LatLng newMarker = new LatLng(Double.valueOf(lat), Double.valueOf(lng));
                mMap.addMarker(new MarkerOptions()
                        .position(newMarker)
                        .title("The Home Depot")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.homedepot_marker)));


                float zoomLevel = 12.0f; //This goes up to 21
                // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newMarker, zoomLevel));

                // mMap.moveCamera(CameraUpdateFactory.newLatLng(newMarker));

            }


            MarkersLoaded = true;
            //Log.d("MarkersLoaded","true");

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   == PackageManager.PERMISSION_GRANTED)
            {
                mMap.setMyLocationEnabled(true);
            }


            else {
                Toast.makeText(MapsActivity3.this, "Usted ha aceptado!", Toast.LENGTH_LONG).show();
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

    private void LoadDistribuidores()
    {

        _distribuidores = new ArrayList<>();

        String jsonDistribuidores = Common.loadJSONFromAsset(context,"homedepot.json");
        JSONObject obj_distribuidores;
        JSONObject distribuidor;

        ///////////////
        try {

            obj_distribuidores = new JSONObject(jsonDistribuidores);
            JSONArray res = obj_distribuidores.getJSONArray("distribuidores");

            for (int i = 0; i < res.length(); i++) {
                distribuidor = res.getJSONObject(i).getJSONObject("distribuidor");
                int id = distribuidor.getInt("id");
                String name = distribuidor.getString("name");
                // String clave = distribuidor.getString("clave");
                String comercial_name = distribuidor.getString("comercial_name");
                String estado = distribuidor.getString("state");
                String ciudad = distribuidor.getString("city");
                String cp = distribuidor.getString("cp");
                String direccion = distribuidor.getString("address");
               // String numInt = distribuidor.getString("interior");
               // String numExt = distribuidor.getString("exterior");
                String colonia = distribuidor.getString("colonia");
                String phone1 = distribuidor.getString("phone1");
                String phone2 = distribuidor.getString("phone2");
                String lat = distribuidor.getString("lat");
                String lng = distribuidor.getString("lng");


                Distribuidor dist1 = new Distribuidor();
                dist1.set_id(id);
                dist1.set_comercial_name(comercial_name);
                dist1.set_name(name);
                dist1.set_ciudad(ciudad);
                dist1.set_estado(estado);
                dist1.set_colonia(colonia);
                //dist1.set_direccion(direccion + " #" + numExt + " " + numInt);
                dist1.set_direccion(direccion);
                dist1.set_cp(cp);
                dist1.set_telefono1(phone1);
                dist1.set_latitud(lat);
                dist1.set_longitud(lng);

                if (phone2.length()>1)
                {
                    dist1.set_telefono2("," + phone2);
                }
                _distribuidores.add(dist1);



            }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file distribuidores");
            //return null;

        }


    }

}
