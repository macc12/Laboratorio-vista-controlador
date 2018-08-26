/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOMock;

import Modelo.Administrador;
import Modelo.Caja;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Producto;
import Modelo.Vendedor;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Marco
 */
public class BaseDatos {
    /*-----------------------Temporal--------------------------*/
    ArrayList<Usuario> usuarios = new ArrayList<>();
    Usuario tep = new Usuario(6875132, "6875132", "Administrador");
    Usuario tep1 = new Usuario(12345, "12345", "Vendedor");
    private Venta data;
    ArrayList<Producto> productos = new ArrayList<Producto>();
    ArrayList<Venta> ventas = new ArrayList<>();
    ArrayList<Cliente> clientes= new ArrayList<>();
    ArrayList<String> facturas = new ArrayList<>();
    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Caja> cajas = new ArrayList<Caja>();
    Administrador adm = new Administrador(6875132, "6875132", "Tomas", "aa-ss-dd", 1, 12124, 1243532);;
    private ObservableList<PieChart.Data> data2d = FXCollections.observableArrayList();
     
    public BaseDatos() {
        usuarios.add(tep);
        usuarios.add(tep1);
        
        productos.add(new Producto("Maiz", 1111, 78, 5000));
        productos.add(new Producto("Arroz", 1112, 98, 5500));
        vendedores.add(new Vendedor(12345, "12345", "Maria", 80, "asd-asd-asd", 235235, 325235));        
        /*
        vendedores.add(new Empleado("Maria", 12345, "Av 123-78", 7897851, 654321, "12345"));
        vendedores.add(new Empleado("Tomas", 6875132, "Av 78-78", 7897851, 654321, "6875132"));
        */
        cajas.add(new Caja(1, 800000, vendedores.get(0), adm));
    }
    /*-----------------------Temporal--------------------------*/
    public void addUser(Usuario user){
        usuarios.add(user);
    }    
    public boolean exist(String nombre, String pass){
        for (int i = 0; i < usuarios.size(); i++) {            
            if(String.valueOf(usuarios.get(i).getId()).compareTo(nombre)==0 && usuarios.get(i).getPass().compareTo(pass)==0){
                return true;
            }
        }
        return false;
    }
    public boolean comprobar(String id, String pass){
        for (int i = 0; i < usuarios.size(); i++) {            
            if(String.valueOf(usuarios.get(i).getId()).compareTo(id)==0 && usuarios.get(i).getPass().compareTo(pass)==0&&
                    usuarios.get(i).getTipo().compareTo("Administrador")==0){
                return true;
            }
        }
        return false;
    }
    /*
    ---------------------------------------------------------------------------
    --------------------------Productos-----------------------------------------
    */
    public boolean ComprobarProducto(String codigo){
        for (int i = 0; i < productos.size(); i++) {
            if(String.valueOf(productos.get(i).getCodigoBarras()).compareTo(codigo)==0){
                return true;
            }
        }
        return false;
    }
    public void crearAgregarProducto(String nombre, String codigo, int cantidad, double precio){
        if(ComprobarProducto(codigo)==true){
            for (int i = 0; i < productos.size(); i++) {
                if(String.valueOf(productos.get(i).getCodigoBarras()).compareTo(codigo)==0){
                    int aux = cantidad+ productos.get(i).getCantidad();
                    productos.get(i).setCantidad(aux);
                }
            }            
        }
        productos.add(new Producto(nombre, cantidad, cantidad, cantidad));        
    }
    
    public Venta obtenerVenta(String codigo, int cantidad){
        if(ComprobarProducto(codigo)==true){
            for (int i = 0; i < productos.size(); i++) {
                if(String.valueOf(productos.get(i).getCodigoBarras()).compareTo(codigo)==0){
                    data = new Venta(codigo, productos.get(i).getNombre(), cantidad, productos.get(i).getPrecio());
                    ventas.add(data);
                    return data;
                }
            }
        }
        return null;
    }
    public Venta obtenerVeInv(String codigo){
        
            System.out.println("existe");
            for (int i = 0; i < productos.size(); i++) {
                System.out.println(productos.get(i).getNombre());
                if(String.valueOf(productos.get(i).getCodigoBarras()).compareTo(codigo)==0){
                    System.out.println(productos.get(i).getNombre());
                    data = new Venta(codigo, productos.get(i).getNombre(), productos.get(i).getCantidad(), productos.get(i).getPrecio());
                    ventas.add(data);
                    return data;
                }
            }
        
        return null;
    }
    
    public double obtenertotal(){
        double total=0;
        for (int i = 0; i < ventas.size(); i++) {            
            total+=ventas.get(i).getPrecioTot();
        }
        return total;
    }
    public String escribirProductos(){
        String cadena="";
        for (int i = 0; i < ventas.size(); i++) {            
            cadena += ventas.get(i).getNombre() + " " + ventas.get(i).getCantidad() + " " + ventas.get(i).getPrecioUn() + " " +ventas.get(i).getPrecioTot()+"\n";
        }
        return cadena;
    }
    
    /*
    ---------------------------------------------------------------------------
    --------------------------Clientes-----------------------------------------
    */
    public void addCliente(String Nombre, String direccion){
        if(exisCliente(Nombre, Nombre)==false){
            clientes.add(new Cliente(Nombre, direccion));
        }
    }
    public boolean exisCliente(String nom, String dir){
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getNombre().compareTo(nom)==0 &&
                    clientes.get(i).getDireccion().compareTo(dir)==0){
                return true;
            }            
        }
        return false;
    }
    /*
    ---------------------------------------------------------------------------
    --------------------------Factura-----------------------------------------
    */
    public void saveFactura(String fact){
        facturas.add(fact);
    }
    /*
    ---------------------------------------------------------------------------
    --------------------------Caja-----------------------------------------
    */
        public void cajas(){
            
        }
    
    /*
    ---------------------------------------------------------------------------
    --------------------------Empleado-----------------------------------------
    */
    public Empleado buscarEmple(String id){
        for (int i = 0; i < vendedores.size(); i++) {            
            if(String.valueOf(vendedores.get(i).getId()).compareTo(id)==0){
                return vendedores.get(i);
            }
        }
        return null;
    }
    public Administrador getAdministrador(){        
        return adm;
    }
    //---------------------------------------------------------------------------
    public ObservableList<PieChart.Data> getDataGrafPas(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("OpenCV", 20));
        data.add(new PieChart.Data("JavaFX", 40));
        data.add(new PieChart.Data("Python", 10));
        data.add(new PieChart.Data("Spring", 15));
        data.add(new PieChart.Data("Qt", 10));
        data.add(new PieChart.Data("SQL", 17));
        return  data;
    }
    //---------------------------------------------------------------------------
    public XYChart.Series[] getSeriesGrafBarr(){
        final String austria = "Austria";
    final  String brazil = "Brazil";
    final  String france = "France";
    final  String italy = "Italy";
    final  String usa = "USA";
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");       
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));
        series1.getData().add(new XYChart.Data(france, 10000));
        series1.getData().add(new XYChart.Data(italy, 35407.15));
        series1.getData().add(new XYChart.Data(usa, 12000));      
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(austria, 57401.85));
        series2.getData().add(new XYChart.Data(brazil, 41941.19));
        series2.getData().add(new XYChart.Data(france, 45263.37));
        series2.getData().add(new XYChart.Data(italy, 117320.16));
        series2.getData().add(new XYChart.Data(usa, 14845.27));  
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(austria, 45000.65));
        series3.getData().add(new XYChart.Data(brazil, 44835.76));
        series3.getData().add(new XYChart.Data(france, 18722.18));
        series3.getData().add(new XYChart.Data(italy, 17557.31));
        series3.getData().add(new XYChart.Data(usa, 92633.68)); 
        XYChart.Series series[] = new XYChart.Series[]{series1, series2,series3};
        return series;
    }
    public XYChart.Series[] getSeriesGrafLine(){
        XYChart.Series series3 = new XYChart.Series();        
        series3.getData().add(new XYChart.Data("enero", 45000.65));
        series3.getData().add(new XYChart.Data("febrero", 44835.76));
        series3.getData().add(new XYChart.Data("marzo", 18722.18));
        series3.getData().add(new XYChart.Data("abril", 17557.31));
        series3.getData().add(new XYChart.Data("Julio", 92633.68));
        XYChart.Series series[] = new XYChart.Series[]{series3};
        return series;
    }
    public XYChart.Series[] getSeriesGrafLineMetas(){
        XYChart.Series series2 = new XYChart.Series();    
        series2.setName("Planeadp");
        series2.getData().add(new XYChart.Data("enero", 45000.65));
        series2.getData().add(new XYChart.Data("febrero", 44835.76));
        series2.getData().add(new XYChart.Data("marzo", 18722.18));
        series2.getData().add(new XYChart.Data("abril", 17557.31));
        series2.getData().add(new XYChart.Data("Julio", 92633.68));         
        
        XYChart.Series series3 = new XYChart.Series();        
        series3.setName("Real");
        series3.getData().add(new XYChart.Data("enero", 5000.65));
        series3.getData().add(new XYChart.Data("febrero", 835.76));
        series3.getData().add(new XYChart.Data("marzo", 1722.18));
        series3.getData().add(new XYChart.Data("abril", 1757.31));
        series3.getData().add(new XYChart.Data("Julio", 9633.68));
        XYChart.Series series[] = new XYChart.Series[]{series2,series3};
        return series;
    }
}
