package com.datalabor.soporte.mexar.models;

import java.io.Serializable;

/**
 * Created by soporte on 15/08/2017.
 */

public class Distribuidor implements Serializable
{
    private long _id;
    private String _name;
    private String _estado;
    private String _ciudad;
    private String _colonia;
    private String _telefono1;
    private String _direccion;
    private int _resId;


    public Distribuidor() {
    }

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

    public String get_estado() {
        return _estado;
    }

    public void set_estado(String _estado) {
        this._estado = _estado;
    }

    public String get_ciudad() {
        return _ciudad;
    }

    public void set_ciudad(String _ciudad) {
        this._ciudad = _ciudad;
    }

    public String get_colonia() {
        return _colonia;
    }

    public void set_colonia(String _colonia) {
        this._colonia = _colonia;
    }

    public String get_telefono1() {
        return _telefono1;
    }

    public void set_telefono1(String _telefono1) {
        this._telefono1 = _telefono1;
    }

    public String get_direccion() {
        return _direccion;
    }

    public void set_direccion(String _direccion) {
        this._direccion = _direccion;
    }

    public int get_resId() {
        return _resId;
    }

    public void set_resId(int _resId) {
        this._resId = _resId;
    }
}
