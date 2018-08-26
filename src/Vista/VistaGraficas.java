/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAOMock.BaseDatos;
import DAOMock.Venta;
import Modelo.DataBean;
import java.io.FileInputStream;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Marco
 */
public class VistaGraficas {
    private HBox hboxg;
    private Stage stage;
    private Button btn_VentProduc;
    private Button btn_VentVendPeriodo;
    private Button btn_VentMes;
    private Button btn_CumpMetas;
    private VBox root2;
    private GridPane grPast;
    private ObservableList<PieChart.Data> data2d = FXCollections.observableArrayList();
    private PieChart pieChart;
    private GridPane grBarr;
    private XYChart.Series series1[];
    private GridPane grLine;
    private XYChart.Series series2[];
    private LineChart<String, Number> line;
    private DataBean dt;
    private BaseDatos bd;
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

    public VistaGraficas(DataBean bean) {
        this.dt=bean;        
        root2 = new VBox();        
        root2.getChildren().add(getHBox());
        root2.getChildren().add(getHBoxg());
        scene2 = new Scene(root2, 1000, 700);
    }
    public void showGrafventana(Stage stage){        
        stage.setResizable(false);
        stage.setTitle("POS");
        stage.setScene(scene2);
        stage.show();
    }
     /**
     * crea la ventana inicial de las graficas
     * @throws Exception 
     */
    public void GrafVentana() throws Exception {        
        root2 = new VBox();        
        root2.getChildren().add(getHBox());
        root2.getChildren().add(getHBoxg());
        scene2 = new Scene(root2, 1000, 700);
        
    }
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
     * crea la ventana de graficas con la grafica de ventas por producto
     * @throws Exception 
     */
    public void GrafVentanaWtVentPr(ObservableList<PieChart.Data> data, Stage primaryStage) throws Exception {
        
        PieChart pie = new PieChart(data);
        pie.setTitle("PieChart Tutorial 2017");
        pie.setLegendSide(Side.LEFT);
        pie.setTitleSide(Side.BOTTOM);
        pie.setLabelLineLength(60);
        pie.setLabelsVisible(true);

        pie.getData().forEach(this::installTooltip);

        StackPane root = new StackPane(pie);
        Scene scene = new Scene(root, 800, 600);
        
        /*
        primaryStage.setTitle("JavaFX PieChart");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(scene);
 
            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);
 
            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(primaryStage);
 
            // Set position of second window, related to primary window.
            newWindow.setX(primaryStage.getX() + 200);
            newWindow.setY(primaryStage.getY() + 100);
 
            newWindow.show();
        
    }
    public void installTooltip(PieChart.Data d) {

        String msg = String.format("%s : %s", d.getName(), d.getPieValue());

        Tooltip tt = new Tooltip(msg);
        tt.setStyle("-fx-background-color: gray; -fx-text-fill: whitesmoke;");
        
        Tooltip.install(d.getNode(), tt);
    }
    /**
     * crea la ventana de graficas con la grafica de ventas de vendedor por periodo
     * @throws Exception 
     */
    public void GrafVentanaWtVentVen(XYChart.Series[] series, Stage primaryStage) throws Exception {
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("Persona");       
        yAxis.setLabel("Valor");
        bc.getData().addAll(series);
        StackPane root = new StackPane(bc);
        Scene scene = new Scene(root, 800, 600);
        
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(scene);
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);
        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);
        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);
        newWindow.show();
    }
    /**
     * crea la ventana de graficas con la grafica de linea
     * @throws Exception 
     */
    public void GrafVentanaWtGarfLine(XYChart.Series series[], Stage primaryStage) throws Exception {        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> bc = 
            new LineChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("Mes");       
        yAxis.setLabel("Valor");
        bc.getData().addAll(series);
        StackPane root = new StackPane(bc);
        Scene scene = new Scene(root, 800, 600);
        
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(scene);
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);
        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);
        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);
        newWindow.show();
    }
    
    
    /**
     * crea un Hbox y le agrega elementos
     * @return hboxg
     */
    public HBox getHBoxg(){        
        hboxg = new HBox();
        btn_VentProduc = new Button("Ventas por Producto");
        btn_VentVendPeriodo = new Button("Ventas Vendedor Periodo");
        btn_VentMes = new Button("Ventas por Mes");
        btn_CumpMetas = new Button("Cumplimiento de metas");
        hboxg.getChildren().add(btn_VentProduc);
        hboxg.getChildren().add(btn_VentVendPeriodo);
        hboxg.getChildren().add(btn_VentMes);
        hboxg.getChildren().add(btn_CumpMetas);
        return hboxg;
    }
    
       
    
     
    //--------------------------------------------------------------------------
    //                   grafica 3 / linea
    //--------------------------------------------------------------------------
    /**
     * crea un pane y le agrega elementos
     * @return grLine 
     */
    public Pane getGrLine(){
        grLine = new GridPane();
        line.getData().addAll(getSeriesGarfLine());
        return grLine;
    }
    /**
     * Setter: asigna los valores para la grafica
     * @param sere 
     */
    public void setSeriesGrafLine(XYChart.Series[] sere){
        this.series2 = sere;
    }
    /**
     * Getter: entrega el vector de los valores para la grafica
     * @return series2
     */
    public XYChart.Series[] getSeriesGarfLine(){
        return series2;
    }   

    public Button getBtn_VentProduc() {
        return btn_VentProduc;
    }

    public void setBtn_VentProduc(Button btn_VentProduc) {
        this.btn_VentProduc = btn_VentProduc;
    }

    public Button getBtn_VentVendPeriodo() {
        return btn_VentVendPeriodo;
    }

    public void setBtn_VentVendPeriodo(Button btn_VentVendPeriodo) {
        this.btn_VentVendPeriodo = btn_VentVendPeriodo;
    }

    public Button getBtn_VentMes() {
        return btn_VentMes;
    }

    public void setBtn_VentMes(Button btn_VentMes) {
        this.btn_VentMes = btn_VentMes;
    }

    public Button getBtn_CumpMetas() {
        return btn_CumpMetas;
    }

    public void setBtn_CumpMetas(Button btn_CumpMetas) {
        this.btn_CumpMetas = btn_CumpMetas;
    }

    public Button getBtn_hom() {
        return btn_hom;
    }

    public void setBtn_hom(Button btn_hom) {
        this.btn_hom = btn_hom;
    }

    public Button getBtn_invent() {
        return btn_invent;
    }

    public void setBtn_invent(Button btn_invent) {
        this.btn_invent = btn_invent;
    }
    
    
    
}
