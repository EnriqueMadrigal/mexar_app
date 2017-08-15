package com.datalabor.soporte.mexar.models;

import java.io.Serializable;

/**
 * Created by Enrique on 14/08/2017.
 */

public class Category implements Serializable
{
    private long _id;
    private String _name;
    private int _resId;


    public Category()
    {

    }


    public long getId()
    {
        return _id;
    }

    public void setId( long id )
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
