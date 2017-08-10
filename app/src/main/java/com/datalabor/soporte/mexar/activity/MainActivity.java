package com.datalabor.soporte.mexar.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.datalabor.soporte.mexar.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    public NavigationView navView;
    public DrawerLayout drawerLayout;
    private boolean _isSearchVisible = false;
    Context context;

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
                               break;


                            case R.id.menu_lookbook:

                                break;

                            case R.id.menu_tips:

                                break;


                            case R.id.menu_perfil:
                                break;


                            case R.id.menu_actualizar:
                                break;

                                                    /*
                            case R.id.menu_preferencias:
                                _currentPage = 4;
                                //_navigationDrawerAdapter.setSelected( 3 );
                                SettingsFragment fragment4 = new SettingsFragment();
                                navigate( fragment4, "SETTINGS" );
                                break;
                            */

                            case R.id.menu_cerrrar:
                                break;






                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });


        /////Nav Object


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





}
