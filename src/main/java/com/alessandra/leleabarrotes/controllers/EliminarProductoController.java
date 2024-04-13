package com.alessandra.leleabarrotes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.alessandra.leleabarrotes.App;
import com.alessandra.leleabarrotes.models.Productos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EliminarProductoController {

    @FXML
    private Button exitButton;

    @FXML
    private TextField nombreProductoText;

    @FXML
    private TableView<Productos> productosTable;

    @FXML
    private TableColumn<Productos, String> nombreColumn;

    @FXML
    private TableColumn<Productos, Double> precioColumn;

    @FXML
    private TableColumn<Productos, String> marcaColumn;

    @FXML
    private TableColumn<Productos, Double> volumenColumn;

    @FXML
    private TableColumn<Productos, String> fechaColumn;

    @FXML
    private TableColumn<Productos, String> idColumn;

    private ObservableList<Productos> productosList = FXCollections.observableArrayList();

    @FXML
    private Button eliminarButton;

    @FXML
    void onMouseClickedExitButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("productos-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/alessandra/leleabarrotes/Imagenes/LeleLogo.png")));
        stage.show();
        cerrarVentana();
    }

    @FXML
    void onMouseClieckedEliminarButton(MouseEvent event) throws IOException {
        Productos productoSeleccionado = productosTable.getSelectionModel().getSelectedItem();
        if (productoSeleccionado == null) {
            showAlert("Seleccione un producto de la tabla para eliminar.");
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmar eliminación");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("¿Está seguro de que desea eliminar el producto seleccionado?");

        if (confirmAlert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            productosList.remove(productoSeleccionado);
            Productos.getListaProductos().remove(productoSeleccionado);
        }
    }


    @FXML
    void initialize() {
        productosTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        productosList.addAll(Productos.getListaProductos());
        productosTable.setItems(productosList);
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        marcaColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        volumenColumn.setCellValueFactory(cellData -> cellData.getValue().volumenProperty().asObject());
        fechaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        FilteredList<Productos> filteredData = new FilteredList<>(productosList, p -> true);
        nombreProductoText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(producto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return producto.getNombre().toLowerCase().contains(lowerCaseFilter);
            });
        });
        productosTable.setItems(filteredData);
    }

    public void cerrarVentana() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
