/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Modelo.DataBean;
import Vista.VistaAdministrador;
import Vista.VistaCajero;
import Vista.Login;
import java.io.FileNotFoundException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import Controlador.LoginControlador;
import DAOMock.BaseDatos;

/**
 * @version 1.0
 * @author Marco
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
      
    @Override
    public void start(Stage primaryStage) throws Exception {
        BaseDatos dao = new BaseDatos();
        // ambito de sesión / ámbito de aplicación ¡inicializar beans!
        // necesita ser pasado el controlador de la aplicacion
       DataBean dataBean = new DataBean(primaryStage);       
       // LLamar el primer controlador
       LoginControlador cont = new LoginControlador(dataBean, dao);
       cont.show();      
    }
    
}
