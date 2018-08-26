/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author JHOHAN
 */
public class Arbol {

    private RandomAccessFile raf;
    private RandomAccessFile ref;

    public Arbol(String nombre, int valor, Object t) {
        try {
            raf = new RandomAccessFile("Arbol" + nombre, "rw");
            ref = new RandomAccessFile(nombre, "rw");
            raf.writeLong(valor);   
            raf.writeInt(-1);
            raf.writeInt(-1);
            raf.writeLong(ref.length());
            switch(nombre){
                case "Producto":
                 Producto p = (Producto)t;
                 addProducto(p.getNombre(),p.getCodigoBarras(),p.getCantidad(),p.getPrecio());
                 break;
                case "Cliente":
                    Cliente c=(Cliente)t;
                    addCli(c.getNombre(),c.getDireccion());
                    break;
                case "Administrador":
                    Administrador a=(Administrador)t;
                    addAdmin(a.getOficina(),a.getNombre(),a.getId(),a.getDireccion(),a.getTelefono(),a.getSalario(),a.getClave());
                    break;
                 case"Vendedor":
                     Vendedor v=(Vendedor)t;
                     addVen(v.getId(),v.getClave(),v.getNombre(),v.getNoVentas(),v.getDireccion(),v.getTelefono(),v.getSalario());
                     break;
                 case "Caja":
                     Caja ca=(Caja)t;
                     addCaja(ca.getNumero(),ca.getSaldo(),ca.getCajero(),ca.getAdministrador());
                     break;
                     
            }
        } catch (Exception e) {

        }
    }

    public void agregar(String nombre, long valor,Object t) throws IOException {
        raf.seek(0);   
        agregar2(nombre,valor,t);
    }
    public void addProducto(String Nombre, int CodigoBarras, int Cantidad, float Precio)throws IOException{
        ref.seek(ref.length());
        ref.writeUTF(Nombre);
        ref.writeInt(CodigoBarras);
        ref.writeInt(Cantidad);
        ref.writeFloat(Precio);    
    }
    public void addAdmin(int Oficina, String Nombre, int id, String Direccion, int Telefono, float Salario, String Clave)throws IOException {
        ref.seek(ref.length());
         ref.writeInt(id);
         ref.writeUTF(Clave);
         ref.writeUTF(Nombre);
         ref.writeUTF(Direccion);
         ref.writeInt(Oficina);
         ref.writeInt(Telefono);
         ref.writeFloat(Salario);  
    }
    public void addVen(int id,String Clave, String Nombre,int NoVentas, String Direccion, int Telefono, float Salario)throws IOException {
         ref.seek(ref.length());
         ref.writeInt(id);
         ref.writeUTF(Clave);
         ref.writeUTF(Nombre);
         ref.writeInt(NoVentas);
         ref.writeUTF(Direccion);
         ref.writeInt(Telefono);
         ref.writeFloat(Salario);  
    }
    public void addCli(String Nombre, String Direccion)throws IOException {
         ref.seek(ref.length());
         ref.writeUTF(Nombre);
         ref.writeUTF(Direccion);
    }
    public void addCaja(int Numero, float Saldo, Vendedor Cajero, Administrador administrador)throws IOException{
         ref.seek(ref.length());
         ref.writeInt(Numero);
         ref.writeFloat(Saldo);
         addVen(Cajero.getId(),Cajero.getClave(),Cajero.getNombre(),Cajero.getNoVentas(),Cajero.getDireccion(),Cajero.getTelefono(),Cajero.getSalario());
         addAdmin(administrador.getOficina(),administrador.getNombre(),administrador.getId(),administrador.getDireccion(),administrador.getTelefono(),administrador.getSalario(),administrador.getClave()); 
    }
      
  
    public void agregar2(String nombre,long valor, Object t) throws IOException {

        if (valor > raf.readLong()) {
            raf.skipBytes(4);
            long pos = raf.getFilePointer();
            if (raf.readInt() == -1) {
                raf.seek(pos);
                raf.writeInt((int) raf.length());
                raf.seek(raf.length());
                raf.writeLong(valor);
                raf.writeInt(-1);
                raf.writeInt(-1);
                raf.writeLong(ref.length());
switch(nombre){
                case "Producto":
                 Producto p = (Producto)t;
                 addProducto(p.getNombre(),p.getCodigoBarras(),p.getCantidad(),p.getPrecio());
                 break;
                case "Cliente":
                    Cliente c=(Cliente)t;
                    addCli(c.getNombre(),c.getDireccion());
                    break;
                case "Administrador":
                    Administrador a=(Administrador)t;
                    addAdmin(a.getOficina(),a.getNombre(),a.getId(),a.getDireccion(),a.getTelefono(),a.getSalario(),a.getClave());
                    break;
                 case"Vendedor":
                     Vendedor v=(Vendedor)t;
                     addVen(v.getId(),v.getClave(),v.getNombre(),v.getNoVentas(),v.getDireccion(),v.getTelefono(),v.getSalario());
                     break;
                 case "Caja":
                     Caja ca=(Caja)t;
                     addCaja(ca.getNumero(),ca.getSaldo(),ca.getCajero(),ca.getAdministrador());
                     break;
                     
            }
            } else {

                raf.seek(pos);
                int ns = raf.readInt();
                raf.seek(ns);
                agregar2(nombre,valor, t);
            }
        } else {
            int pos = (int) raf.getFilePointer();
            if (raf.readInt() == -1) {
                raf.seek(pos);
                raf.writeInt((int) raf.length());
                raf.seek(raf.length());
                raf.writeLong(valor);
                raf.writeInt(-1);
                raf.writeInt(-1);
                raf.writeLong(ref.length());
switch(nombre){
                case "Producto":
                 Producto p = (Producto)t;
                 addProducto(p.getNombre(),p.getCodigoBarras(),p.getCantidad(),p.getPrecio());
                 break;
                case "Cliente":
                    Cliente c=(Cliente)t;
                    addCli(c.getNombre(),c.getDireccion());
                    break;
                case "Administrador":
                    Administrador a=(Administrador)t;
                    addAdmin(a.getOficina(),a.getNombre(),a.getId(),a.getDireccion(),a.getTelefono(),a.getSalario(),a.getClave());
                    break;
                 case"Vendedor":
                     Vendedor v=(Vendedor)t;
                     addVen(v.getId(),v.getClave(),v.getNombre(),v.getNoVentas(),v.getDireccion(),v.getTelefono(),v.getSalario());
                     break;
                 case "Caja":
                     Caja ca=(Caja)t;
                     addCaja(ca.getNumero(),ca.getSaldo(),ca.getCajero(),ca.getAdministrador());
                     break;                
            }
            } else {
                raf.seek(pos);
                int ns = raf.readInt();
                raf.seek(ns);
                agregar2(nombre,valor,t);
            }
        }

    }

    public void imprimirArbol() {
        try {
            raf.seek(0);
            while (raf.getFilePointer() <= raf.length()) {
                System.out.print(raf.readLong() + " ");
                System.out.print(raf.readInt() + " ");
                System.out.print(raf.readInt() + " ");
                System.out.print(raf.readLong() + " ");
                System.out.println("");
            }
        } catch (Exception e) {

        }
    }

    public void imprimirCliente() {
        try {
            ref.seek(0);
            while (ref.getFilePointer() <= ref.length()) {
              System.out.println(ref.readLong() + " ");
            }
        } catch (Exception e) {

        }
    }

    public Object buscar(String nombre,int valor) throws IOException {
        raf.seek(0);
        ref.seek(0);
        return busqueda(nombre,valor);
    }
public Object readProd()throws IOException{
      Producto p= new Producto(ref.readUTF(),ref.readInt(),ref.readInt(),ref.readFloat());
       return p;
}
public Object readAdmin()throws IOException{
      Administrador p= new Administrador(ref.readInt(),ref.readUTF(),ref.readUTF(),ref.readUTF(),ref.readInt(),ref.readInt(),ref.readFloat());
       return p;
}
public Object readVen()throws IOException{
      Vendedor p= new Vendedor(ref.readInt(),ref.readUTF(),ref.readUTF(),ref.readInt(),ref.readUTF(),ref.readInt(),ref.readFloat());
       return p;
}
public Object readClie()throws IOException{
      Cliente p= new Cliente(ref.readUTF(),ref.readUTF());
       return p;
}
public Object readCaja()throws IOException{

    Caja c=new Caja(ref.readInt(),ref.readFloat(),(Vendedor)readVen(),(Administrador)readAdmin());
    return c;
}
    public Object busqueda(String nombre, int valor) throws IOException {
        long clave = raf.readLong();
        if (clave == valor) {
            raf.skipBytes(8);
            ref.seek(raf.readLong());
            switch (nombre) {
                case "Producto":
                    return readProd();
                case "Cliente":
                    return readClie();
                case "Administrador":
                    return readAdmin();
                case "Vendedor":
                    return readVen();
                case "Caja":
                    return readCaja();
            }
        } else if (clave > valor) {
            long p = raf.readInt();
            raf.seek(p);
            return busqueda(nombre, valor);
        } else {
            raf.skipBytes(4);
            raf.seek(raf.readInt());
            return busqueda(nombre, valor);
        }
        return null;
    }

}
