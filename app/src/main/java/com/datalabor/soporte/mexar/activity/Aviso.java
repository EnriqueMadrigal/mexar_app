package com.datalabor.soporte.mexar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.datalabor.soporte.mexar.R;

public class Aviso extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton backBtn;
    com.datalabor.soporte.mexar.utils.JustifyTextView aviso_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);


        toolbar = (Toolbar) findViewById(R.id.toolbarAviso);
        aviso_text = (com.datalabor.soporte.mexar.utils.JustifyTextView) findViewById(R.id.aviso_Text);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aviso_text.setMovementMethod(new ScrollingMovementMethod());

        backBtn = (ImageButton) findViewById(R.id.backButton_aviso);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    @Override
    public void onBackPressed() {

        Log.e("Main", "BackPressed");
        finish();
    }
}
