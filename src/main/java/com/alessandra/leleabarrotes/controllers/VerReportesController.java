package com.alessandra.leleabarrotes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.alessandra.leleabarrotes.App;
import com.alessandra.leleabarrotes.models.Reporte;
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

public class VerReportesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private TextField nombreProductoText;

    @FXML
    private TableView<Reporte> reportesTable;

    @FXML
    private TableColumn<Reporte, String> fechaColumn;

    @FXML
    private TableColumn<Reporte, String> descripcionColumn;

    @FXML
    private TableColumn<Reporte, Double> totalVendidoColumn;

    private ObservableList<Reporte> reportesList = FXCollections.observableArrayList();

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
        reportesList.addAll(Reporte.getListaReportes());

        fechaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        descripcionColumn.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        totalVendidoColumn.setCellValueFactory(cellData -> cellData.getValue().totalVendidoProperty().asObject());

        FilteredList<Reporte> filteredData = new FilteredList<>(reportesList, p -> true);
        nombreProductoText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(producto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return producto.getFecha().toLowerCase().contains(lowerCaseFilter);
            });
        });
        reportesTable.setItems(filteredData);
    }

    public void cerrarVentana() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
