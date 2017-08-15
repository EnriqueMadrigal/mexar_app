package com.datalabor.soporte.mexar;

/**
 * Created by soporte on 15/08/2017.
 */

public class Common {

    private static int _curPage = 0;

    public static void SetPage(int curPage)
    {
        _curPage = curPage;
    }

    public static int GetPage()
    {
        return _curPage;
    }

    public static void DecrementPage()
    {
        _curPage--;
    }


}
