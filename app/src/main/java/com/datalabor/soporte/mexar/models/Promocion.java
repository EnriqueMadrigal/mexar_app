package com.datalabor.soporte.mexar.models;

import java.io.Serializable;

/**
 * Created by soporte on 16/08/2017.
 */

public class Promocion implements Serializable
{
    private long _id;
    private String _name;
    private String _desc;
    private int _resId;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public int get_resId() {
        return _resId;
    }

    public void set_resId(int _resId) {
        this._resId = _resId;
    }
}
