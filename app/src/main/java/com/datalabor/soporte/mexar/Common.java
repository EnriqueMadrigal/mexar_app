package com.datalabor.soporte.mexar;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by soporte on 15/08/2017.
 */

public class Common {

    private static int _curPage = 0;
    private static int _curCategoria = 0;
    private static int _curSubCategoria = 0;
    public static String _curBusquda = "";
    public static int curWidth = 0;
    public static int curHeight = 0;

    public static float getCurDensity() {
        return curDensity;
    }

    public static void setCurDensity(float curDensity) {
        Common.curDensity = curDensity;
    }

    public static float curDensity = 0;

    public static int getCurWidth() {
        return curWidth;
    }

    public static void setCurWidth(int curWidth) {
        Common.curWidth = curWidth;
    }

    public static int getCurHeight() {
        return curHeight;
    }

    public static void setCurHeight(int curHeight) {
        Common.curHeight = curHeight;
    }



    public static void SetPage(int curPage)
    {
        _curPage = curPage;
    }
    public static int GetPage()
    {
        return _curPage;
    }


    public static void setCategoria(int curCategoria) {_curCategoria = curCategoria;}
    public static int getCategoria() { return _curCategoria;}

    public static void setSubCategoria(int curSubCategoria) {_curSubCategoria = curSubCategoria;}
    public static int getSubCategoria() {return _curSubCategoria;}

    public static String get_curBusquda() {
        return _curBusquda;
    }

    public static void set_curBusquda(String _curBusquda) {
        Common._curBusquda = _curBusquda;
    }

    public static final String VAR_USER_NAME = "USER_NAME";
    public static final String VAR_LOGIN_TYPE = "LOGIN_TYPE"; // google, facebook, email
    public static final String VAR_USER_EMAIL = "USER_EMAIL";


    public static void DecrementPage()
    {
        _curPage--;
    }

    public static final String API_URL_BASE = "http://104.131.34.72/backend/appserver/public/index.php/api/";
    //public static final String API_URL_BASE = "http://192.168.15.26/backend/appserver/public/index.php/api/";


    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(filename);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }



}
