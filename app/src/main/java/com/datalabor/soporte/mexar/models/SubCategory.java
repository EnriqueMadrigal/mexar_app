package com.datalabor.soporte.mexar.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by soporte on 15/08/2017.
 */

public class SubCategory implements Serializable
{
    private int _id;
    private String _name;
    private int _resId;
    private String _desc;
    private int _categoryid;

    public int get_categoryid() {
        return _categoryid;
    }

    public void set_categoryid(int _categoryid) {
        this._categoryid = _categoryid;
    }




    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }






    public SubCategory()
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

}
