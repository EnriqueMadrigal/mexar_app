package com.datalabor.soporte.mexar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.datalabor.soporte.mexar.R;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void sig_in(View view) {

        Log.d("Sig_in Pressed","1");
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        finish();
        startActivity(intent);

    }


}
