package com.datalabor.soporte.mexar.custom;

/**
 * Created by Enrique on 18/11/2017.
 */

public class SynchronizeResult {

    private int _result;
    private int _totalImages;

    public SynchronizeResult() {
        _result = 0;
        _totalImages = 0;
    }

    public int getResult() {
        return _result;
    }

    public void setResult(int result) {
        _result = result;
    }

    public int getTotalImages() {
        return _totalImages;
    }

    public void setTotalImages(int totalImages) {
        _totalImages = totalImages;

    }

}