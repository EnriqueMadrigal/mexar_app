package com.datalabor.soporte.mexar.models;

import java.io.Serializable;

/**
 * Created by soporte on 15/08/2017.
 */

public class Distribuidor implements Serializable
{
    private int _id;
    private String _name;
    private String _comercial_name;
    private String _estado;
    private String _ciudad;
    private String _colonia;
    private String _telefono1;
    private String _telefono2;
    private String _direccion;
    private String _cp;
    private int _resId;

    private String _latitud;
    private String _longitud;


    public Distribuidor() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public String get_telefono2() {
        return _telefono2;
    }

    public void set_telefono2(String _telefono2) {
        this._telefono2 = _telefono2;
    }

    public String get_comercial_name() {
        return _comercial_name;
    }

    public void set_comercial_name(String _comercial_name) {
        this._comercial_name = _comercial_name;
    }

    public String get_cp() {
        return _cp;
    }

    public void set_cp(String _cp) {
        this._cp = _cp;
    }

    public String get_latitud() {
        return _latitud;
    }

    public void set_latitud(String _latitud) {
        this._latitud = _latitud;
    }

    public String get_longitud() {
        return _longitud;
    }

    public void set_longitud(String _longitud) {
        this._longitud = _longitud;
    }
}
