package com.alessandra.leleabarrotes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.alessandra.leleabarrotes.App;
import com.alessandra.leleabarrotes.models.Reporte;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GenerarReportesController {

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
    private TextField marcaText;

    @FXML
    private TextArea descripcionArea;

    @FXML
    void onMouseClickedConfirmarButton(MouseEvent event) {
        String fecha = nameText.getText().trim();
        String descripcion = descripcionArea.getText().trim();
        double totalVendido = Double.parseDouble(marcaText.getText().trim());

        if (!fecha.isEmpty() && !descripcion.isEmpty()) {
            Reporte nuevoReporte = new Reporte(fecha, descripcion, totalVendido);
            Reporte.getListaReportes().add(nuevoReporte);

            showAlert("Reporte generado exitosamente.");
        } else {
            showAlert("Por favor, complete todos los campos.");
        }
    }

    private void showAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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

    public void cerrarVentana() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
