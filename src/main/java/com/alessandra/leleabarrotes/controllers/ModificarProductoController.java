package com.alessandra.leleabarrotes.controllers;

import java.io.IOException;
import java.net.URL;
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

public class ModificarProductoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private Button añadirButton;

    @FXML
    private Button buscarButton;

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
    void onMouseClickedBuscarButton(MouseEvent event) {
        String idBuscado = idProducto.getText();
        Productos productoEncontrado = buscarProductoPorId(idBuscado);
        if (productoEncontrado != null) {
            showAlert("Producto encontrado exitosamente.");
            autorellenarInformacion(productoEncontrado);
        } else {
            showAlertError("No se encontró ningún producto con el ID especificado.");
        }
    }

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
    void onMouseClickedModficarButton(MouseEvent event) {
        String id = idProducto.getText();
        Productos productoModificado = buscarProductoPorId(id);
        if (productoModificado != null) {
            actualizarProducto(productoModificado);
            showAlert("Producto modificado exitosamente.");
        } else {
            showAlertError("No se encontró ningún producto con el ID especificado.");
        }
    }

    private void actualizarProducto(Productos producto) {
        producto.setNombre(nameText.getText());
        producto.setPrecio(Double.parseDouble(precioText.getText()));
        producto.setFecha(fechaText.getText());
        producto.setMarca(marcaText.getText());
        producto.setVolumen(Double.parseDouble(volumeText.getText()));
    }

    @FXML
    void initialize() {
    }

    private Productos buscarProductoPorId(String id) {
        for (Productos producto : Productos.getListaProductos()) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    private void autorellenarInformacion(Productos producto) {
        nameText.setText(producto.getNombre());
        precioText.setText(String.valueOf(producto.getPrecio()));
        fechaText.setText(producto.getFecha());
        marcaText.setText(producto.getMarca());
        volumeText.setText(String.valueOf(producto.getVolumen()));
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
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public void cerrarVentana() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
