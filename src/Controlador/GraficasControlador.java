
package Controlador;

import DAOMock.BaseDatos;
import Modelo.DataBean;
import Vista.Login;
import Vista.VistaAdministrador;
import Vista.VistaGraficas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Marco
 */
public class GraficasControlador {
    private DataBean bean;
    private BaseDatos bd;
    private VistaGraficas vistaGraficas;
    private VistaAdministrador vistaAdministrador;
    private Login login;
    private ObservableList<Data> data2d = FXCollections.observableArrayList();
    public GraficasControlador(DataBean bean, BaseDatos datos) {
        try {
            this.login = new Login(bean);
            this.vistaAdministrador = new VistaAdministrador(bean);
            this.vistaGraficas = new VistaGraficas(bean);
            this.bd=datos;
            this.bean=bean;
            
            //eventos
            vistaGraficas.getBtn_hom().setOnAction(new eventoHome());
            vistaGraficas.getBtn_invent().setOnAction(new eventoInventario());
            vistaGraficas.getBtn_VentProduc().setOnAction(new eventoGraficaVentasProducto());
            vistaGraficas.getBtn_VentVendPeriodo().setOnAction(new eventoGraficaventasPeriodo());
            vistaGraficas.getBtn_VentMes().setOnAction(new eventoVentasporMes());
            vistaGraficas.getBtn_CumpMetas().setOnAction(new eventoMetasCumplidas());
        } catch (Exception ex) {
            Logger.getLogger(GraficasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void Show (){
        vistaGraficas.showGrafventana(bean.getPrimaryStage());
    }
    
    class eventoHome implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                LoginControlador log = new LoginControlador(bean, bd);
                log.show();
            } catch (Exception ex) {
                Logger.getLogger(CajeroControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    class eventoInventario implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                AdministradorControlador ac = new AdministradorControlador(bean, bd);
                ac.show();
            } catch (Exception ex) {
                Logger.getLogger(GraficasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    } 
    class eventoGraficaVentasProducto implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                data2d = bd.getDataGrafPas();                
                vistaGraficas.GrafVentanaWtVentPr(data2d, bean.getPrimaryStage());
                
            } catch (Exception ex) {
                Logger.getLogger(GraficasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    class eventoGraficaventasPeriodo implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                XYChart.Series series[] = bd.getSeriesGrafBarr();
                vistaGraficas.GrafVentanaWtVentVen(series, bean.getPrimaryStage());
            } catch (Exception e) {
            }
        }
        
    }
    class eventoVentasporMes implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                XYChart.Series series[] = bd.getSeriesGrafLine();
                vistaGraficas.GrafVentanaWtGarfLine(series, bean.getPrimaryStage());
            } catch (Exception ex) {
                Logger.getLogger(GraficasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    class eventoMetasCumplidas implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                XYChart.Series series[] = bd.getSeriesGrafLineMetas();
                vistaGraficas.GrafVentanaWtGarfLine(series, bean.getPrimaryStage());
            } catch (Exception ex) {
                Logger.getLogger(GraficasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
