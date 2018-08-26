/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAOMock.BaseDatos;
import DAOMock.Venta;
import Modelo.DataBean;
import Modelo.Empleado;
import Vista.Login;
import Vista.VistaCajero;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 *
 * @author Marco
 */
public class CajeroControlador {
    private DataBean dataBean;
    private Login vistaLog;
    private VistaCajero vistaCajero;
    private BaseDatos base;
    private Date fecha;
    private Venta ventas;
    CajeroControlador(DataBean dataBean, /*Temporal*/BaseDatos bd) throws Exception {
        this.dataBean = dataBean;
        this.vistaLog= new Login(dataBean);
        this.vistaCajero = new VistaCajero(dataBean);
        this.base = bd;
        //agregar evento
        vistaCajero.getBtnBuscar().setOnAction(new eventoBuscar());
        vistaCajero.getBtnAgregar().setOnAction(new eventoAgregar());
        vistaCajero.getBtnPago().setOnAction(new eventoDinero());
        vistaCajero.getBtnEliminar().setOnAction(new eventoeliminar());
        vistaCajero.gettBtnImprimir().setOnAction(new eventoImprimir());
        vistaCajero.getBtnHome().setOnAction(new eventoInicioMenu());
    }
    public void show(String id){
        vistaCajero.show(dataBean.getPrimaryStage());
        Empleado nombreb = base.buscarEmple(id);
        vistaCajero.addNombre(nombreb.getNombre());
        fecha = new Date();
        vistaCajero.addFecha(fecha.toString());
    }
    
    class eventoBuscar implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                String codigo = vistaCajero.getCodigoV();                
                if(base.ComprobarProducto(codigo)==false){    
                    Alert dialog = new Alert(Alert.AlertType.ERROR);
                    dialog.setHeaderText("Producto no Encontrado!!");
                    dialog.show();
                }                
            } catch (Exception e) {
            }
        }
        
    } 
    class eventoAgregar implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                String codigo = vistaCajero.getCodigoV();
                int cantidad =  Integer.parseInt(vistaCajero.getTxtCantidad());
                Venta venta = base.obtenerVenta(codigo, cantidad);                
                vistaCajero.agregarTabla().add(venta);
                vistaCajero.ActualizarTabla();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    class eventoDinero implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                double dinero = vistaCajero.getDineroV();
                double total = base.obtenertotal();
                vistaCajero.setTotalV(String.valueOf(total));                
                if(dinero>=total){
                    double cambio = dinero-total;
                    vistaCajero.setCambio(String.valueOf(cambio));
                } else {
                    Alert dialog = new Alert(Alert.AlertType.ERROR);
                    dialog.setHeaderText("Dinero insuficiente para la compra!!");
                    dialog.show();
                }                
                
            } catch (Exception e) {
            }
        }
        
    }
    class eventoeliminar implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            vistaCajero.limpiarVista();
        }
        
    }
    class eventoImprimir implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            String cadena ="";
            cadena += fecha.toString()+"\n";
            cadena += base.escribirProductos()+ "\n";
            cadena += "Total: " + base.obtenertotal()+"\n";
            cadena += "Efectivo" + vistaCajero.getDineroV()+"\n";
            cadena += "Cambio" + vistaCajero.getCambio() + "\n";
            cadena += "Nombre Cliente: " + vistaCajero.getNomClien() +"\n";
            cadena += "Direccion Cliente" + vistaCajero.getDirecClien()+"\n";
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Factura");
            dialog.setHeaderText("Centro Comercial");
            dialog.setContentText(cadena);
            dialog.show();
            String nomt = vistaCajero.getNomClien();
            String diret = vistaCajero.getDirecClien();
            base.addCliente(nomt, diret);
            vistaCajero.limpiarVista();
        }
    
    }
    class eventoInicioMenu implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                LoginControlador log = new LoginControlador(dataBean, base);
                log.show();
            } catch (Exception ex) {
                Logger.getLogger(CajeroControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
