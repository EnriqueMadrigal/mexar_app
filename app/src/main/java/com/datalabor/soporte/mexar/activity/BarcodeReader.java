package com.datalabor.soporte.mexar.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.Manifest;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import android.view.View;
import android.util.Log;

import com.datalabor.soporte.mexar.Common;
import com.datalabor.soporte.mexar.R;
import com.datalabor.soporte.mexar.models.Barcode;
import com.datalabor.soporte.mexar.models.Product;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import layout.productDetail;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class BarcodeReader extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ImageButton barcode_button;
    Context context;

    private ZXingScannerView mScannerView;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private String TAG = "BarcodeReader";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);

        context = this;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }



    }


    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("main", rawResult.getText()); // Prints scan results
        Log.v("main", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)


        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Resultado");
                builder.setMessage(rawResult.getText());
                AlertDialog alert1 = builder.create();
                alert1.show();
    */




        mScannerView.stopCamera();           // Stop camera on pause
        mScannerView = null;


        Barcode curBarcode = SearchCodeBar(rawResult.getText());

        //int curProduct = SearchCodeBar(rawResult.getText());


        Intent returnIntent = new Intent();
        returnIntent.putExtra("Barcode", curBarcode);
        //returnIntent.putExtra("productID",curBarcode.get_productId());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();


        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScannerView != null) {
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();          // Start camera on resume
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mScannerView != null) {
            mScannerView.stopCamera();           // Stop camera on pause
            mScannerView = null;
        }
    }




    public void escanear(View v)
    {
        Log.d ("Main", "Escanner");

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view<br />
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.<br />
        mScannerView.startCamera();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    private Barcode SearchCodeBar(String curcode){
        ////////////
        int curProduct = 0;

        //////


        String jsonBarcodes = Common.loadJSONFromAsset(context,"barcodes.json");
        JSONObject obj_barcodes;
        JSONObject barcode;


        Barcode newBarcode = new Barcode();
        ///////////////
        try {

            obj_barcodes = new JSONObject(jsonBarcodes);
            JSONArray res = obj_barcodes.getJSONArray("barcodes");

            for (int i = 0; i < res.length(); i++) {
                barcode = res.getJSONObject(i).getJSONObject("barcode");
                int id = barcode.getInt("id");
                int productId = barcode.getInt("product_id");
                String code = barcode.getString("code");
                String image = barcode.getString("image");

                if (code.equals(curcode)){
                    curProduct = productId;
                    newBarcode.set_id(id);
                    newBarcode.set_productId(productId);
                    newBarcode.setCode(code);
                    newBarcode.setImage(image);
                    break;
                }



            }



        }

        catch (Exception e)
        {
            Log.d(TAG,"Can not read json file barcodes");
            return null;

        }


        ////////////


        return  newBarcode;
    }




}
