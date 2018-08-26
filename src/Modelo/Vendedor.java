/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author JHOHAN
 */
public class Vendedor extends Empleado {
    private int NoVentas;

    public Vendedor(int id,String Clave, String Nombre,int NoVentas, String Direccion, int Telefono, float Salario) {
        super(Nombre, id, Direccion, Telefono, Salario, Clave);
        this.NoVentas = NoVentas;
    }

    public int getNoVentas() {
        return NoVentas;
    }

    public void setNoVentas(int NoVentas) {
        this.NoVentas = NoVentas;
    }
    
}
