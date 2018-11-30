package com.datalabor.soporte.mexar.models;


import java.io.Serializable;

public class Barcode implements  Serializable{

    private int _id;
    private int _productId;
    private String desc;
    private String code;
    private String item;
    private String unidad;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_productId() {
        return _productId;
    }

    public void set_productId(int _productId) {
        this._productId = _productId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getPzas() {
        return pzas;
    }

    public void setPzas(String pzas) {
        this.pzas = pzas;
    }

    private String pzas;



}
