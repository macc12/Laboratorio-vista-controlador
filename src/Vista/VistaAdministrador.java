package Vista;

import DAOMock.BaseDatos;
import DAOMock.Venta;
import Modelo.DataBean;
import java.beans.EventHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * @version 1.0
 * @author Marco
 */
public class VistaAdministrador {
    private GridPane pane;
    private Scene scene;
    private VBox root;
    private VBox vbox;
    private HBox hbox;
    private Button btn_hom;
    private Button btn_sales;
    private Button btn_invent;
    private Button btn_repor;
    private Button btn_agregar;
    private TableView table;
    private TableColumn nro;
    private TableColumn codigo;
    private TableColumn nombre;
    private TableColumn cantidad;
    private TableColumn precioUni;
    private TableColumn precioTot;
    private GridPane pane2;
    private TextField txt_nomb;
    private TextField txt_fecha;
    private HBox hbox2;
    private TextField txt_produc;   
    private TextField txt_cantidad;
    private TextField txt_nombre;   
    private TextField txt_precio;        
    private Scene scene2;
    private ObservableList<Venta> ventas;

    public String getTxt_produc() {
        return txt_produc.getText();
    }
    public String getTxt_cantidad() {
        return txt_cantidad.getText();
    }

    public String getTxt_nombre() {
        return txt_nombre.getText();
    }

    public String getTxt_precio() {
        return txt_precio.getText();
    }
   
    private HBox hboxg;
    private Stage stage;
    private Button btn_VentProduc;
    private Button btn_VentVendPeriodo;
    private Button btn_VentMes;
    private Button btn_CumpMetas;
    private VBox root2;
    private GridPane grPast;
    private ObservableList<Data> data2d = FXCollections.observableArrayList();
    private PieChart pieChart;
    private GridPane grBarr;
    private XYChart.Series series1[];
    private GridPane grLine;
    private XYChart.Series series2[];
    private LineChart<String, Number> line;
    private DataBean dt;
    private BaseDatos bd;
    /**
     * crea un Scene y le agrega elementos
     */   
    
    public VistaAdministrador(DataBean bean) {
        this.dt=bean;
        this.bd=bd;
        root = new VBox();        
        root.getChildren().add(getHBox());
        root.getChildren().add(getVBox());
        root.getChildren().add(getHBox2());
        scene = new Scene(root, 1000, 700); 
        this.ventas = FXCollections.observableArrayList();
    }    
    
    public void invVentana() {
        root = new VBox();        
        root.getChildren().add(getHBox());
        root.getChildren().add(getPane2());
        root.getChildren().add(getHBox2());
        root.getChildren().add(getVBox());
                      
        scene = new Scene(root, 1000, 700);        
    }
    /**
     * muestra la ventana de inventario
     * @param stage 
     */
    public void showInvVentana(Stage stage){
        this.stage = stage;
        stage.setResizable(false);
        stage.setTitle("invVentana");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * crea un Pane y le agrega elementos
     * @return Pane
     */
    private Pane getPane(){
        pane = new GridPane();        
        table = new TableView();
        table.setEditable(true);        
        codigo = new TableColumn<Venta,String>("Codigo");
        nombre = new TableColumn<Venta,String>("Nombre");
        cantidad = new TableColumn<Venta,Integer>("Catidad");
        precioUni = new TableColumn<Venta,Double>("Precio Unidad");
        precioTot = new TableColumn<Venta,Double>("Precio Total");
                
        codigo.setCellValueFactory(new PropertyValueFactory<Venta,String>("Codigo"));
        nombre.setCellValueFactory(new PropertyValueFactory<Venta,String>("Nombre"));
        cantidad.setCellValueFactory(new PropertyValueFactory<Venta,Integer>("Cantidad"));
        precioUni.setCellValueFactory(new PropertyValueFactory<Venta,Double>("Precio Unidad"));
        precioTot.setCellValueFactory(new PropertyValueFactory<Venta,Double>("Precio Total"));
        table.setItems(ventas);
        table.getColumns().addAll(codigo,nombre,cantidad,precioUni,precioTot);
        pane.add(table, 0, 0);
        return pane;
    }
    /**
     * crea un Pane y le agrega elementos
     * @return Pane
     */
    public Pane getPane2(){
        pane2 = new GridPane();
        txt_fecha = new TextField();
        txt_nomb = new TextField();
        txt_nomb.setEditable(false);
        txt_fecha.setEditable(false);
        pane2.add(new Label("Nombre"), 0, 0);
        pane2.add(txt_nomb, 1, 0);
        pane2.add(new Label("Fecha"), 0, 1);
        pane2.add(txt_fecha, 1, 1);
        return pane2;
    }
    /**
     * crea un VBox y le agrega elementos
     * @return vbox 
     */
    public VBox getVBox(){
        vbox = new VBox();
        vbox.getChildren().add(getPane2());
        vbox.getChildren().add(getPane());
        return vbox;
    }
    /**
     * crea un HBox y le agreaga elementos
     * @return hbox
     */
    private HBox getHBox() {
        try{
            hbox = new HBox();
            //importar imagenes     
            FileInputStream input = 
                new FileInputStream("Recursos/home.png");
            FileInputStream input2 = 
                new FileInputStream("Recursos/sales.png");
            FileInputStream input3 = 
                new FileInputStream("Recursos/carton.png");
            FileInputStream input4 = 
                new FileInputStream("Recursos/grafi.png");
            
            Image image = new Image(input);
            Image image1 = new Image(input2);
            Image image2 = new Image(input3);
            Image image3 = new Image(input4);
            //crear botones
            btn_hom = new Button("", new ImageView(image));        
            btn_sales = new Button("", new ImageView(image1));
            btn_invent = new Button("", new ImageView(image2));
            btn_repor = new Button("", new ImageView(image3));
            //agregar botones al HBox            
            hbox.getChildren().add(btn_hom);
            hbox.getChildren().add(btn_sales);
            hbox.getChildren().add(btn_invent);
            hbox.getChildren().add(btn_repor);            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return hbox;
    }    
    /**
     * crea un HBox y le agreaga elementos
     * @return hbox
     */
    public HBox getHBox2(){
        GridPane panei = new GridPane();
        panei.setHgap(10);
        panei.setVgap(10);        
        hbox2 = new HBox();                
        txt_produc = new TextField();        
        txt_cantidad = new TextField();
        
        panei.add(new Label("Codigo Producto"), 0, 1);
        panei.add(txt_produc, 1, 1);
        panei.add(new Label("Cantidad Producto"), 0, 3);
        panei.add(txt_cantidad, 1, 3);
        
        GridPane panec = new GridPane();
        panec.setHgap(10);
        panec.setVgap(10);
        txt_nombre = new TextField();
        txt_precio = new TextField();      
        
        panec.add(new Label("Nombre"), 0, 1);
        panec.add(txt_nombre, 1, 1);        
        panec.add(new Label("Precio"), 0, 3);
        panec.add(txt_precio, 1, 3);
        
        GridPane paned = new GridPane();
        paned.setHgap(10);
        paned.setVgap(10);
                        
        btn_agregar = new Button("Agregar");
                                        
        paned.add(btn_agregar, 2, 5);
        
        hbox2.getChildren().add(panei);
        hbox2.getChildren().add(panec);
        hbox2.getChildren().add(paned);
        
        
        return hbox2;
    }
    public ObservableList agregarTabla(){
        return ventas;
    }
    public void ActualizarTabla(){
        table.setItems(ventas);
    }
    
    //--------------------------------------------------------------------------
    //                     second stage graficas
    //--------------------------------------------------------------------------
    
   
    
    
    //eventos botones
    
    public Button getBtnHome(){
        return btn_hom;
    }
        
    public Button getBtnInv(){
       return btn_invent;
    }
    
    public Button getBtnReport(){
        return btn_repor;
    }
    
    public Button gettBtnAgregar(){
    return btn_agregar;
    }
    
    public Button getBtnPorVentProdu(){
        return btn_VentProduc;
    }
    
    public Button getBtnVentPeri(){
        return btn_VentVendPeriodo;
    }
    
    public Button getBtnVentMes(){
        return btn_VentMes;
    }
    
    public Button getBtnMetasCum(){
        return btn_CumpMetas;
    }
    
    public void agregarTabla(ObservableList data){
        table.setItems(data);
    }
    /**
     * elimina todos los elemntos de la tabla
     */
    public void clearTable() {
        table.getItems().clear();
    }
    /**
     * agrega el nombre al textLabel para mostrarlo
     * @param nombre 
     */
    public void addNombre(String nombre){
        txt_nomb.setText(nombre);
    }
    /**
     * agrega la fecha al textLabel para mostrarla
     * @param fecha 
     */
    public void addFecha(String fecha){
        txt_fecha.setText(fecha);
    }
}
