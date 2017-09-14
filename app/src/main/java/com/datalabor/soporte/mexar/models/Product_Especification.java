package com.datalabor.soporte.mexar.models;

import java.io.Serializable;

/**
 * Created by Enrique on 13/09/2017.
 */

public class Product_Especification implements Serializable {

    private String _especification;

    public String get_especification() {
        return _especification;
    }

    public void set_especification(String _especification) {
        this._especification = _especification;
    }
}
