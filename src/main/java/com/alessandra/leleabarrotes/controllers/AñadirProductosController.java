package com.alessandra.leleabarrotes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.alessandra.leleabarrotes.App;
import com.alessandra.leleabarrotes.models.Productos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AñadirProductosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private Button añadirButton;

    @FXML
    private Button verButton;

    @FXML
    private TextField nameText;

    @FXML
    private TextField precioText;

    @FXML
    private TextField fechaText;

    @FXML
    private TextField marcaText;

    @FXML
    private TextField volumeText;

    @FXML
    private TextField idProducto;

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
    void onMouseClickedVerButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ver-productos-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/alessandra/leleabarrotes/Imagenes/LeleLogo.png")));
        stage.show();
        cerrarVentana();
    }

    @FXML
    void onMouseClickedañadirButton(MouseEvent event) {
        String nombre = nameText.getText();
        String precioString = precioText.getText();
        String fecha = fechaText.getText();
        String marca = marcaText.getText();
        String volumenString = volumeText.getText();
        if (nombre.isEmpty() || precioString.isEmpty() || fecha.isEmpty() || marca.isEmpty() || volumenString.isEmpty()) {
            mostrarAlerta("Por favor complete todos los campos.");
            return;
        }
        try {
            double precio = Double.parseDouble(precioString);
            double volumen = Double.parseDouble(volumenString);
            String id = generateID();
            Productos nuevoProducto = new Productos(nombre, precio, fecha, marca, volumen, id);
            Productos.getListaProductos().add(nuevoProducto);
            nameText.clear();
            precioText.clear();
            fechaText.clear();
            marcaText.clear();
            volumeText.clear();
            idProducto.setText(generateID());
            mostrarAlertaExito("Producto añadido correctamente");
        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor ingrese un número válido en los campos de precio y volumen.");
        }
    }

    private String generateID() {
        StringBuilder idBuilder = new StringBuilder();
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            idBuilder.append(characters.charAt(index));
        }
        return idBuilder.toString();
    }

    @FXML
    void initialize() {
        idProducto.setText(generateID());

    }

    public void cerrarVentana() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void mostrarAlertaExito(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
