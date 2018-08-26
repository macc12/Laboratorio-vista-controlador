package Vista;


import Modelo.DataBean;
import java.beans.EventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import sun.security.util.Password;

/**
 *
 * @author Marco
 * @version 1.0
 */
public class Login {
    private GridPane pane;
    private Button btn_log;
    private Scene scene;
    private BorderPane root;
    private TextField user;
    private PasswordField pass; 
    private DataBean dta;
    /**
     * inicia y agrega elementos al Scene
     * @throws Exception 
     */
   
    public  Login(DataBean dta) throws Exception {            
        this.dta=dta;
        root = new BorderPane();
        root.setCenter(getPane());
        scene = new Scene(root, 250, 250);
        
    }
    /**
     * muestra el Stage
     * @param stage 
     */
    public void show(Stage stage){
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * crea un Pane en el que se agregan elementos
     * @return Pane
     */
    private Pane getPane(){
        pane = new GridPane();
        user = new TextField();
        pass = new PasswordField();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12, 12, 12, 12));
        
        pane.setHgap(5);
        pane.setVgap(5);
        pane.add(new Label("Usuario"), 0, 0);
        pane.add(user, 1, 0);
        pane.add(new Label("Password"), 0, 1);
        pane.add(pass, 1, 1);
        btn_log = new Button("Ingresar");
        pane.add(btn_log, 1, 2);
        GridPane.setHalignment(btn_log, HPos.RIGHT);
        return pane;        
    }    
    /**
     * permite agregar un evento al boton de ingreso
     * @param event 
     */
    public Button addEventBtn(){
        return btn_log;
    }
    /**
     * Getter
     * @return usuario
     */
    public String GetUser(){
        return user.getText();
    }
    /**
     * Getter
     * @return password
     */
    public String GetPass(){
        return pass.getText();
    }
}
