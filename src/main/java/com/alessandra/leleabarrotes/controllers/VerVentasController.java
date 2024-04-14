package com.alessandra.leleabarrotes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.alessandra.leleabarrotes.App;
import com.alessandra.leleabarrotes.models.Productos;
import com.alessandra.leleabarrotes.models.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class VerVentasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private TextField nombreProductoText;

    @FXML
    private TableView<Venta> productosTable;

    @FXML
    private TableColumn<Venta, String> nombreColumn;

    @FXML
    private TableColumn<Venta, String> idVentaColumn;

    @FXML
    private TableColumn<Venta, String> idProductoColumn;

    @FXML
    private TableColumn<Venta, Double> precioColumn;

    @FXML
    private TableColumn<Venta, String> fechaColumn;

    @FXML
    private TableColumn<Venta, Double> totalColumn;

    private ObservableList<Venta> ventasList = FXCollections.observableArrayList();

    @FXML
    void onMouseClickedExitButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu-ventas-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/alessandra/leleabarrotes/Imagenes/LeleLogo.png")));
        stage.show();
        cerrarVentana();
    }

    @FXML
    void initialize() {
        ventasList.addAll(Venta.getListaVentas());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreproductoProperty());
        idVentaColumn.setCellValueFactory(cellData -> cellData.getValue().idVentaProperty());
        idProductoColumn.setCellValueFactory(cellData -> cellData.getValue().idProductoProperty());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        fechaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        totalColumn.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        FilteredList<Venta> filteredData = new FilteredList<>(ventasList, p -> true);
        nombreProductoText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(producto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return producto.getFecha().toLowerCase().contains(lowerCaseFilter);
            });
        });
        productosTable.setItems(filteredData);
    }

    public void cerrarVentana() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
