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

    private ArrayList<Presentation> _presentations;
    private ArrayList<Product_Image> product_images;

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

}
