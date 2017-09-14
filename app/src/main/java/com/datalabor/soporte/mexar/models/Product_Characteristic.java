package com.datalabor.soporte.mexar.models;

import java.io.Serializable;

/**
 * Created by Enrique on 14/09/2017.
 */

public class Product_Characteristic implements Serializable {

    private String _characteristic;

    public String get_characteristic() {
        return _characteristic;
    }

    public void set_characteristic(String _characteristic) {
        this._characteristic = _characteristic;
    }
}
