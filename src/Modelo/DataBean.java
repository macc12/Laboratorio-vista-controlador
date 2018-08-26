/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.HashMap;
import java.util.Map;
import javafx.stage.Stage;

/**
 *
 * @author LabingXEON
 */
public class DataBean {
    private Stage primaryStage = null;  
    private Map<String , String> namePwMap = null;
    
    public DataBean(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.namePwMap = new HashMap<>();
    }
 
    public Map<String, String> getNamePwMap() {
        return namePwMap;
    }
 
    public Stage getPrimaryStage() {
      return primaryStage;
   }
}
