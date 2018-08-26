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
public class Caja {
    private int Numero;
    private float Saldo;
    private Vendedor Cajero;
    private Administrador administrador;

    public Caja(int Numero, float Saldo, Vendedor Cajero, Administrador administrador) {
        this.Numero = Numero;
        this.Saldo = Saldo;
        this.Cajero = Cajero;
        this.administrador = administrador;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float Saldo) {
        this.Saldo = Saldo;
    }

    public Vendedor getCajero() {
        return Cajero;
    }

    public void setCajero(Vendedor Cajero) {
        this.Cajero = Cajero;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    
}
