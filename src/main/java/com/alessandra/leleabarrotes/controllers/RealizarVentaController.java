package com.alessandra.leleabarrotes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.alessandra.leleabarrotes.App;
import com.alessandra.leleabarrotes.models.Productos;
import com.alessandra.leleabarrotes.models.Venta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RealizarVentaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private Button confirmarButton;

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
    private TextField idVenta;

    @FXML
    private Button buscarButton;

    @FXML
    void onMouseClickedBuscarButton(MouseEvent event) {
        String nombreProducto = nameText.getText().trim();
        if (!nombreProducto.isEmpty()) {
            for (Productos p : Productos.getListaProductos()) {
                if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                    showAlert("Producto encontrado: " + p.getNombre());
                    precioText.setText(String.valueOf(p.getPrecio()));
                    marcaText.setText(p.getMarca());
                    idProducto.setText(p.getId());
                    idVenta.setText(generateID());
                    return;
                }
            }
            showAlertError("Producto no encontrado.");
        } else {
            showAlertError("Por favor, ingrese un nombre de producto.");
        }
    }

    @FXML
    void onMouseClickedConfirmarButton(MouseEvent event) {
        String nombreProducto = nameText.getText().trim();
        String fecha = fechaText.getText().trim();
        String nombreCliente = marcaText.getText().trim();

        if (nombreProducto.isEmpty() || fecha.isEmpty() || nombreCliente.isEmpty()) {
            showAlertError("Por favor, complete todos los campos.");
            return;
        }

        Productos productoVendido = null;
        for (Productos p : Productos.getListaProductos()) {
            if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                productoVendido = p;
                break;
            }
        }

        if (productoVendido != null) {
            Productos.getListaProductos().remove(productoVendido);

            String idVenta = generateID();
            String idProducto = productoVendido.getId();
            double precio = productoVendido.getPrecio();
            double total = precio;
            Venta nuevaVenta = new Venta(fecha, nombreProducto, nombreCliente, idVenta, idProducto, precio, total);
            Venta.getListaVentas().add(nuevaVenta);
            System.out.println("Venta realizada:");
            System.out.println("Fecha: " + nuevaVenta.getFecha());
            System.out.println("Producto: " + nuevaVenta.getNombreproducto());
            System.out.println("Cliente: " + nuevaVenta.getNombreCliente());
            System.out.println("ID Venta: " + nuevaVenta.getIdVenta());
            System.out.println("ID Producto: " + nuevaVenta.getIdProducto());
            System.out.println("Precio: " + nuevaVenta.getPrecio());
            System.out.println("Total: " + nuevaVenta.getTotal());

            showAlert("La venta ha sido realizada exitosamente.");
        } else {
            showAlertError("Producto no encontrado en la lista de productos.");
        }
    }

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
    }

    private String generateID() {
        StringBuilder idBuilder = new StringBuilder();
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            idBuilder.append(characters.charAt(index));
        }
        return idBuilder.toString();
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
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
