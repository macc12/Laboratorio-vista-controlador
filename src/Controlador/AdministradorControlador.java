/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAOMock.BaseDatos;
import DAOMock.Venta;
import Modelo.Administrador;
import Modelo.DataBean;
import Modelo.Empleado;
import Vista.Login;
import Vista.VistaAdministrador;
import Vista.VistaGraficas;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Marco
 */
public class AdministradorControlador {
    private DataBean dataBean;
    private Login vistaLog;
    private VistaAdministrador vistaAdministrador;
    private BaseDatos base;
    private Date fecha;
    AdministradorControlador(DataBean dataBean, /*Temporal*/BaseDatos bd) throws Exception {
        this.dataBean = dataBean;
        this.vistaLog= new Login(dataBean);
        this.vistaAdministrador = new VistaAdministrador(dataBean);
        this.base = bd;
        //agregar evento
        vistaAdministrador.gettBtnAgregar().setOnAction(new eventoAgregar());
        vistaAdministrador.getBtnHome().setOnAction(new eventoHome());
        vistaAdministrador.getBtnReport().setOnAction(new eventoGraficas());        
    }
    public void show(){        
        vistaAdministrador.showInvVentana(dataBean.getPrimaryStage());                
        Administrador nombreb = base.getAdministrador();        
        vistaAdministrador.addNombre(nombreb.getNombre());
        fecha = new Date();
        vistaAdministrador.addNombre(nombreb.getNombre());
        vistaAdministrador.addFecha(fecha.toString());
    }
    
    class eventoAgregar implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                String codigo = vistaAdministrador.getTxt_produc();
                String Nombre = vistaAdministrador.getTxt_nombre();
                int cantidad =  Integer.parseInt(vistaAdministrador.getTxt_cantidad());
                double precio = Double.parseDouble(vistaAdministrador.getTxt_precio());
                base.crearAgregarProducto(Nombre, codigo, cantidad, precio);                
                
                Venta venta = base.obtenerVeInv(codigo);                
                vistaAdministrador.agregarTabla().add(venta);
                vistaAdministrador.ActualizarTabla();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    }
    class eventoHome implements EventHandler<ActionEvent>{

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
    class eventoGraficas implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                
                GraficasControlador gc = new GraficasControlador(dataBean, base);
                gc.Show();
            } catch (Exception ex) {
                Logger.getLogger(AdministradorControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }    
}
