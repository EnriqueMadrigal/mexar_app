package com.datalabor.soporte.mexar.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by soporte on 15/08/2017.
 */

public class Product implements Serializable
{
    private int _id;
    private String _name;
    private int _resId;
    private String _description;
    private String _desc_complete;
    private String _ficha_tecnica;
    private String _nota;

    private ArrayList<Presentation> _presentations;
    private ArrayList<Product_Image> product_images;
    private ArrayList<Product_Aplication> product_aplications;
    private ArrayList<Product_Especification> product_especifications;
    private ArrayList<Product_Characteristic> product_characteristics;
    private ArrayList<Product_Adhiere> product_adhieres;
    private ArrayList<Product_Color> product_colors;

    public ArrayList<Product_Color> getProduct_colors() {
        return product_colors;
    }

    public void setProduct_colors(ArrayList<Product_Color> product_colors) {
        this.product_colors = product_colors;
    }

    public ArrayList<Product_Characteristic> getProduct_characteristics() {
        return product_characteristics;
    }

    public void setProduct_characteristics(ArrayList<Product_Characteristic> product_characteristics) {
        this.product_characteristics = product_characteristics;
    }

    public ArrayList<Product_Adhiere> getProduct_adhieres() {
        return product_adhieres;
    }

    public void setProduct_adhieres(ArrayList<Product_Adhiere> product_adhieres) {
        this.product_adhieres = product_adhieres;
    }

    public ArrayList<Product_Aplication> getProduct_aplications() {
        return product_aplications;
    }

    public void setProduct_aplications(ArrayList<Product_Aplication> product_aplications) {
        this.product_aplications = product_aplications;
    }

    public ArrayList<Product_Especification> getProduct_especifications() {
        return product_especifications;
    }

    public void setProduct_especifications(ArrayList<Product_Especification> product_especifications) {
        this.product_especifications = product_especifications;
    }

    public String get_ficha_tecnica() {
        return _ficha_tecnica;
    }

    public void set_ficha_tecnica(String _ficha_tecnica) {
        this._ficha_tecnica = _ficha_tecnica;
    }

    public ArrayList<Product_Image> getProduct_images() {
        return product_images;
    }

    public void setProduct_images(ArrayList<Product_Image> product_images) {
        this.product_images = product_images;
    }

    public ArrayList<Presentation> get_presentations() {
        return _presentations;
    }

    public void set_presentations(ArrayList<Presentation> _presentations) {
        this._presentations = _presentations;
    }

    public String get_desc_complete() {
        return _desc_complete;
    }

    public void set_desc_complete(String _desc_complete) {
        this._desc_complete = _desc_complete;
    }




    public Product()
    {

    }

    public int getId()
    {
        return _id;
    }

    public void setId( int id )
    {
        _id = id;
    }

    public String getName()
    {
        return _name;
    }

    public void setName( String name )
    {
        _name = name;
    }

    public void setResId(int resId){_resId = resId;}
    public int getResId() {return _resId;}

    public void setDescription(String desc) {_description = desc;}
    public String getDescription() {return _description;}

    public String get_nota() {
        return _nota;
    }

    public void set_nota(String _nota) {
        this._nota = _nota;
    }
}
