package com.datalabor.soporte.mexar.activity;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.datalabor.soporte.mexar.R;

public class presentacion extends AppCompatActivity {

    ConstraintLayout presentacion_screen;
Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);
        context = this;

        presentacion_screen = (ConstraintLayout) findViewById(R.id.presentacion_screen);

        presentacion_screen .setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                finish();
                startActivity(intent);

            }

        });


    }
}
