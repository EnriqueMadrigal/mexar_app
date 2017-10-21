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

    public static void DecrementPage()
    {
        _curPage--;
    }



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
