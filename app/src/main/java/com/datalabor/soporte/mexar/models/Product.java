package com.datalabor.soporte.mexar.models;

import java.io.Serializable;

/**
 * Created by Enrique on 14/08/2017.
 */

public class Product implements Serializable
{
    private long _id;
    private String _name;
    private String _family;
    private String _trend;
    private String _silhouette;
    private String _technology;
    private String _consumer;
    private double _price;
    private int _arrivalMonth;
    private String _gender;
    private String _brand;
    private String _group;


    public Product()
    {

    }

    @Override
    public String toString()
    {
        return String.format( "ID: %d NM: %s FM: %s TR: %s SH: %s TE: %s CO: %s GE: %s BR: %s GR: %s", _id, _name, _family, _trend, _silhouette, _technology, _consumer, _gender, _brand, _group );
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

    public String getFamily()
    {
        return _family;
    }

    public void setFamily( String family )
    {
        _family = family;
    }

    public String getTrend()
    {
        return _trend;
    }

    public void setTrend( String trend )
    {
        _trend = trend;
    }

    public String getSilhouette()
    {
        return _silhouette;
    }

    public void setSilhouette( String silhouette )
    {
        _silhouette = silhouette;
    }

    public String getTechnology()
    {
        return _technology;
    }

    public void setTechnology( String technology )
    {
        _technology = technology;
    }

    public String getConsumer()
    {
        return _consumer;
    }

    public void setConsumer( String consumer )
    {
        _consumer = consumer;
    }

    public double getPrice()
    {
        return _price;
    }

    public void setPrice( double price )
    {
        _price = price;
    }

    public int getArrivalMonth()
    {
        return _arrivalMonth;
    }

    public void setArrivalMonth( int arrivalMonth )
    {
        _arrivalMonth = arrivalMonth;
    }

    public String getGender()
    {
        return _gender;
    }

    public void setGender( String gender )
    {
        _gender = gender;
    }

    public String getBrand()
    {
        return _brand;
    }

    public void setBrand( String brand )
    {
        _brand = brand;
    }

    public String getGroup()
    {
        return _group;
    }

    public void setGroup( String group )
    {
        _group = group;
    }


}
