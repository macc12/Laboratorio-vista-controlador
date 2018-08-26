/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAOMock.BaseDatos;
import Modelo.DataBean;
import Vista.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Marco
 */
public class LoginControlador {
    private DataBean dataBean;
    private Login vistaLog;
    //--------------------------------------
    BaseDatos bd;
    //--------------------------------------

    public LoginControlador(DataBean dataBean, BaseDatos db) throws Exception {
        this.bd=db;
        this.dataBean = dataBean;
        this.vistaLog= new Login(dataBean);
        
        //agregar evento
        vistaLog.addEventBtn().setOnAction(new eventoIniciarSesion());
    }
    
    public void show(){
      vistaLog.show(dataBean.getPrimaryStage());
   }
    
    //eventos
    class eventoIniciarSesion implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                String user = vistaLog.GetUser();
                String pass = vistaLog.GetPass();
                if(bd.exist(user, pass)==true){
                    if(bd.comprobar(user, pass)==true){
                        //abrir ventana administrador
                        AdministradorControlador cont = new AdministradorControlador(dataBean, bd);
                        cont.show();
                    }else{
                        //abrir ventana vendedor
                        CajeroControlador caj = new CajeroControlador(dataBean, bd);
                        caj.show(user);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
    }
}
