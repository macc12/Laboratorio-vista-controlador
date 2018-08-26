/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOMock;

/**
 *
 * @author Marco
 */
public class Venta {
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precioUn;
    private double precioTot;

    public Venta(String codigo, String nombre, int cantidad, double precioUn) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUn = precioUn;
        this.precioTot = precioUn*cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUn() {
        return precioUn;
    }

    public void setPrecioUn(double precioUn) {
        this.precioUn = precioUn;
    }

    public double getPrecioTot() {
        return precioTot;
    }

    public void setPrecioTot(double precioTot) {
        this.precioTot = precioTot;
    }        
    
}
