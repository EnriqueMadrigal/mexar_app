package com.datalabor.soporte.mexar.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.models.Barcode;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.File;

import layout.busca_distribuidores;
import layout.calculadora;
import layout.calculadoras;
import layout.cedulas;
import layout.contacto;
import layout.distribuidores;
import layout.mainf;
import layout.productDetail;
import layout.productos;
import layout.products_search;
import layout.promociones;
import layout.social_network;
import layout.subCategory;
import layout.youtube;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    Toolbar toolbar;
    public NavigationView navView;
    public DrawerLayout drawerLayout;
    private boolean _isSearchVisible = false;
    Context context;

    private GoogleApiClient mGoogleApiClient;
    private final  String TAG ="MainActivity";


    mainf MainFragment;
    //distribuidores _distribuidores;
    promociones _promociones;
    youtube _youtube;
    contacto _contacto;
    calculadoras _calculadoras;
    busca_distribuidores _distribuidores;
    social_network _redes;


    static final int BARCODE_READER_REQUEST = 1001;  // The request code


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconohamburguesa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle( "" );
        ImageButton btnClear = (ImageButton) toolbar.findViewById(R.id.btnClear);
        EditText txtSearch = (EditText) toolbar.findViewById(R.id.txtSearch);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearSearchText();
            }
        });
        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                search();
                return true;
            }
        });



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


//// Nav  oobject
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        switch (menuItem.getItemId()) {

                            case R.id.menu_home:


                                if (Common.GetPage()==0) break;

                                else {
                                    Common.SetPage(0);
                                    //clearBackStack();
                                    MainFragment = new mainf();

                                    getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.fragment_container, MainFragment, "HOME").commit();


                                }
                                break;

                            case R.id.menu_tutoriales:
                                //Common.SetPage(1);
                                //clearBackStack();

                                //getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _youtube, "Tutoriales" ).commit();

                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UClb61WrMZ9xT5mMne_-9pMw")));

                                break;

                            case R.id.menu_calcualdora:
                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _calculadoras, "Calculadoras" ).commit();


                                break;


                            case R.id.menu_distribuidores:

                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _distribuidores, "Distribuidores" ).commit();


                                break;


                            case R.id.menu_promociones:
                             //   Common.SetPage(1);
                             //   clearBackStack();

                           //     getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _promociones, "Promociones" ).commit();

                                break;


                            case R.id.menu_cerrrar:
                                confirmLogout();

                                break;


                            case R.id.menu_contacto:
                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _contacto, "Contacto" ).commit();

                                break;


                            case R.id.menu_redes:
                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _redes, "Redes" ).commit();

                                break;


                            case R.id.menu_escaner:
                                Common.SetPage(1);
                                clearBackStack();

                               // getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _escaner, "Escaner" ).commit();


                                Intent intent = new Intent();
                                intent.setClass(context, BarcodeReader.class);
                                //Bundle bundle = new Bundle();
                                //bundle.putSerializable("distribuidor", curDist);
                                //intent.putExtras(bundle);

                                //finish();
                                //startActivity(intent);
                                startActivityForResult(intent ,BARCODE_READER_REQUEST);

                                break;


                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });


        /////Nav Object

/// cargar el Main fragment

       MainFragment = new mainf();
        _distribuidores = new busca_distribuidores();
        _promociones = new promociones();
        _youtube = new youtube();
        _contacto = new contacto();
        _redes = new social_network();
        _calculadoras = new calculadoras();
        Common.SetPage(0);
        getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, MainFragment, "HOME" ).commit();


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Common.setCurWidth(metrics.widthPixels);
        Common.setCurHeight(metrics.heightPixels);
        Common.setCurDensity(metrics.density);


    }


    public void confirmLogout()
    {
        new AlertDialog.Builder( this )
                .setMessage( "¿Estás seguro de que deseas cerrar tu sesión?" )
                .setCancelable( false )
                .setPositiveButton( "Si", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id )
                    {
                       logout();
                    }
                } )
                .setNegativeButton( "No", null )
                .show();
    }


    @Override
    public void onBackPressed() {

        Log.e("Main", "BackPressed");
        clearBackStack();
        //MainFragment = new mainf();
        //getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, MainFragment, "HOME" ).commit();

        ////////////////------------

        if( getSupportFragmentManager().getBackStackEntryCount() == 0 )
        {
            int curPage = Common.GetPage();
            int curCategoria = Common.getCategoria();
            int curSubCategoria = Common.getSubCategoria();
            String curBusqueda = Common.get_curBusquda();

            Log.d(TAG, String.valueOf(curPage));
            Log.d(TAG, String.valueOf(curCategoria));
            Log.d(TAG, String.valueOf(curSubCategoria));


            //clearBackStack();


            switch (curPage)
            {
                case 0:
                    super.onBackPressed();

                    break;

                case 1:
                    Common.SetPage(0);
                    MainFragment = new mainf();
                    getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  MainFragment, "HOME" ).commit();
                break;


                case 2:



                        subCategory _subCategory = subCategory.newInstance(curCategoria);
                        Common.SetPage(1);
                        Common.setCategoria(curCategoria);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.fragment_container, _subCategory, "Sub Categoria").commit();

                        break;

                case 3:

                    if (curBusqueda.length()>0) {
                        products_search _newsearch = products_search.newInstance(curBusqueda);
                        Common.SetPage(1);
                        Common.set_curBusquda("");
                        //Common.setCategoria(curCategoria);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, _newsearch, "New Search" ).commit();


                    }

                    else {


                        productos _productos = productos.newInstance(curSubCategoria);
                        Common.SetPage(2);
                        Common.setSubCategoria(curSubCategoria);
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.fragment_container, _productos, "Productos").commit();

                    }
                        break;



                case 9: // Cedulas

                    Common.SetPage(1);
                    clearBackStack();

                    getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _calculadoras, "Calculadoras" ).commit();


                  break;


                case 10:

                    cedulas _cedulas;
                    _cedulas = new cedulas();
                    Common.SetPage(9);
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.fragment_container, _cedulas, "Cedulas").commit();


                    break;

                default:
                    super.onBackPressed();
                    break;

            }



        }
        else
        {
            //super.onBackPressed();
        }


        ///////////////--------------



    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        switch(item.getItemId()) {

            /*
            case R.id.show_menu:
               //drawerLayout.openDrawer(GravityCompat.START);
               //drawerLayout.openDrawer(GravityCompat.END);
                return true;
            */


            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;



            //  case R.id.go_home:
            //      fmFragment fragment4 = new fmFragment();
            //      navigate( fragment4, "FM" );

            //      return true;


            case R.id.go_search:
                handleSearch();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }



    private void handleSearch()
    {
        RelativeLayout pnlSearch = (RelativeLayout) toolbar.findViewById(R.id.pnlSearch);
        EditText txtSearch = (EditText) toolbar.findViewById( R.id.txtSearch );
        if( pnlSearch.getVisibility() == View.GONE )
        {
            _isSearchVisible = true;
            getSupportActionBar().setDisplayUseLogoEnabled( false );
            pnlSearch.setVisibility( View.VISIBLE );
            txtSearch.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
            imm.showSoftInput( txtSearch, InputMethodManager.SHOW_IMPLICIT );

        }
        else
        {
            search();
        }
    }


    private void search()
    {
        closeSearch();
        EditText txtSearch = (EditText) toolbar.findViewById( R.id.txtSearch );
        String search = txtSearch.getText().toString().trim();
        txtSearch.setText( "" );
        if( search.length() > 0 )
        {
            android.util.Log.d( "TEST", "SEARCH: " + search );

            products_search _newsearch = products_search.newInstance(search);
            Common.SetPage(1);
            Common.set_curBusquda(search);
            //Common.setCategoria(curCategoria);
            getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, _newsearch, "New Search" ).commit();


        }
    }
///////

    private void onClearSearchText()
    {
        EditText txtSearch = (EditText) toolbar.findViewById( R.id.txtSearch );
        txtSearch.setText( "" );
    }
///////////

    private void closeSearch()
    {
        _isSearchVisible = false;
        getSupportActionBar().setDisplayUseLogoEnabled( true );
        RelativeLayout pnlSearch = (RelativeLayout) toolbar.findViewById( R.id.pnlSearch );
        EditText txtSearch = (EditText) toolbar.findViewById( R.id.txtSearch );
        InputMethodManager imm = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
        imm.hideSoftInputFromWindow( txtSearch.getWindowToken(), 0 );
        pnlSearch.setVisibility( View.GONE );
    }


    private void clearBackStack()
    {
        FragmentManager manager = getSupportFragmentManager();
        if( manager.getBackStackEntryCount() > 0 )
        {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt( 0 );
            manager.popBackStack( first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE );
        }
    }


    private void logout()
    {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences( this );
        SharedPreferences.Editor editor = sharedPref.edit();
        String user_uid = sharedPref.getString( Common.VAR_LOGIN_TYPE, "" );

        // Borrar el login
        editor.putString( Common.VAR_LOGIN_TYPE, "");
        editor.putString( Common.VAR_USER_NAME, "");
        editor.putString( Common.VAR_USER_EMAIL, "");

        editor.commit();

        if (user_uid.contains("google"))
        {
            signOut();
        }

        if (user_uid.contains("facebook"))
        {
            LoginManager.getInstance().logOut();
        }


        Intent intent = new Intent();
        intent.setClass(context, login.class);
        finish();
        startActivity(intent);

    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        // updateUI(false);
                        // [END_EXCLUDE]
                        Intent intent = new Intent();
                        intent.setClass(context, login.class);
                        finish();
                        startActivity(intent);

                    }
                });
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == BARCODE_READER_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)

                //int curProductId = data.getIntExtra("productID", 0);
                Barcode curBarcode = (Barcode) data.getSerializableExtra("Barcode");
                if (curBarcode == null) return;
                if (curBarcode.get_productId() == 0)
                {
                    Common.showWarningDialog("Mensaje" , "Producto no encontrado", context);
                    return;

                }
                Log.d(TAG,"Producto Encontrado=" + String.valueOf(curBarcode.get_productId()));

                Common.SetPage(1);
                clearBackStack();


                productDetail productdetail = productDetail.newInstance(curBarcode.get_productId() , getInteger(curBarcode.getImage()));

                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  productdetail, "Product Detail" ).commitAllowingStateLoss();




                /*

                 productDetail productdetail = productDetail.newInstance(curProduct.getId());

                Common.SetPage(3);
                myContext.getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, productdetail, "Product Detail" ).commit();


                 */


            }
        }
    }


    private Integer getInteger(final String number) {
        try {
            return Integer.valueOf(number);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }


}
