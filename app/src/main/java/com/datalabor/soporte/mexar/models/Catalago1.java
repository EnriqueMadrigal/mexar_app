package com.datalabor.soporte.mexar.models;

import java.io.Serializable;

/**
 * Created by Enrique on 14/09/2017.
 */

public class Catalago1 implements Serializable {
    private String Clave;
    private String Valor;
    private int ID;

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
