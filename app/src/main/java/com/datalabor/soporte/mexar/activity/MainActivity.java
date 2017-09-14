package com.datalabor.soporte.mexar.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;

import java.io.File;

import layout.calculadora;
import layout.contacto;
import layout.distribuidores;
import layout.mainf;
import layout.promociones;
import layout.youtube;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    public NavigationView navView;
    public DrawerLayout drawerLayout;
    private boolean _isSearchVisible = false;
    Context context;

    mainf MainFragment;
    distribuidores _distribuidores;
    promociones _promociones;
    youtube _youtube;
    contacto _contacto;
    calculadora _calculadora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconohamburguesa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle( "" );

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
                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _youtube, "Tutoriales" ).commit();


                                break;

                            case R.id.menu_calcualdora:
                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _calculadora, "Calculadora" ).commit();


                                break;


                            case R.id.menu_distribuidores:

                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _distribuidores, "Distribuidores" ).commit();


                                break;


                            case R.id.menu_promociones:
                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _promociones, "Promociones" ).commit();

                                break;


                            case R.id.menu_cerrrar:
                                break;


                            case R.id.menu_contacto:
                                Common.SetPage(1);
                                clearBackStack();

                                getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  _contacto, "Contacto" ).commit();

                                break;






                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });


        /////Nav Object

/// cargar el Main fragment

       MainFragment = new mainf();
        _distribuidores = new distribuidores();
        _promociones = new promociones();
        _youtube = new youtube();
        _contacto = new contacto();
        _calculadora = new calculadora();
        Common.SetPage(0);
        getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container, MainFragment, "HOME" ).commit();



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

         //clearBackStack();
            Common.SetPage(0);
            MainFragment = new mainf();
            getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left, android.R.anim.slide_out_right ).replace( R.id.fragment_container,  MainFragment, "HOME" ).commit();



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
           // new SearchTask( search ).execute();

            //PiaguiApplication application = (PiaguiApplication)getApplication();
            //Tracker tracker = application.getDefaultTracker();
            //tracker.setScreenName( "MAIN" );
            //tracker.send( new HitBuilders.EventBuilder().setCategory( "ACTION" ).setAction( "SEARCH" ).setLabel( search ).build() );
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


}
