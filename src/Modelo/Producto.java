/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author JHOHAN
 */
public class Producto implements Serializable{
    private String Nombre;
    private int CodigoBarras;
    private int Cantidad;
    private float Precio;

    public Producto(String Nombre, int CodigoBarras, int Cantidad, float Precio) {
        this.Nombre = Nombre;
        this.CodigoBarras = CodigoBarras;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(int CodigoBarras) {
        this.CodigoBarras = CodigoBarras;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }
    
}
