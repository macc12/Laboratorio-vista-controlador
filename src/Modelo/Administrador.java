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
public class Administrador extends Empleado{
    private int Oficina;

    public Administrador(int id, String Clave,String Nombre,String Direccion,int Oficina,  int Telefono, float Salario) {
        super(Nombre, id, Direccion, Telefono, Salario, Clave);
        this.Oficina = Oficina;
    }

    public int getOficina() {
        return Oficina;
    }

    public void setOficina(int Oficina) {
        this.Oficina = Oficina;
    }
    
}
