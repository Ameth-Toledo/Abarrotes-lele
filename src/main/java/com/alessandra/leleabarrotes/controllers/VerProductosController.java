package com.alessandra.leleabarrotes.controllers;

import com.alessandra.leleabarrotes.App;
import com.alessandra.leleabarrotes.models.Imagen;
import com.alessandra.leleabarrotes.models.Productos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class VerProductosController {

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
    private Button subirImagenButton;

    @FXML
    private Button eliminarImagenButton;

    @FXML
    private ScrollPane catalogoProductos;

    private HBox hbox;

    private boolean eliminarImagenPresionado = false;

    @FXML
    void onMouseClickedEliminarImagenButton(MouseEvent event) {
        eliminarImagenPresionado = true;
        showAlert("Por favor, seleccione una imagen a eliminar.");
        recargarImagenes();
    }

    private void recargarImagenes() {
        hbox.getChildren().clear();
        ObservableList<String> imagenes = FXCollections.observableArrayList(Imagen.getImagenes());
        cargarImagenes(imagenes);
    }

    @FXML
    void onMouseClickedSubirImagen(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(subirImagenButton.getScene().getWindow());
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(270);
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                hbox.getChildren().add(imageView);

                String imagePath = selectedFile.toURI().toString();
                Imagen.agregarImagen(imagePath);
                showAlert("Imagen subida exitosamente.");
            } catch (IOException e) {
                e.printStackTrace();
                showAlertError("Error al cargar la imagen.");
            }
        }
    }

    @FXML
    void onMouseClickedExitButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("productos-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        cerrarVentana();
    }

    @FXML
    void initialize() {
        hbox = new HBox();
        hbox.setSpacing(0);
        catalogoProductos.setContent(hbox);
        catalogoProductos.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        catalogoProductos.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        if (eliminarImagenPresionado) {
            ObservableList<String> imagenes = FXCollections.observableArrayList(Imagen.getImagenes());
            cargarImagenes(imagenes);
        }

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

    private void cargarImagenes(ObservableList<String> imagenes) {
        for (String imagePath : imagenes) {
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(270);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);

            imageView.setPickOnBounds(true);

            if (eliminarImagenPresionado) {
                imageView.setOnMouseClicked(event -> {
                    hbox.getChildren().remove(imageView);
                    Imagen.eliminarImagen(imagePath);
                    showAlert("Imagen eliminada exitosamente.");
                });
            }
            hbox.getChildren().add(imageView);
        }
    }

    public void cerrarVentana() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void showAlertError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
