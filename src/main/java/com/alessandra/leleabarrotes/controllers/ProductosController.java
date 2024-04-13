package com.alessandra.leleabarrotes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.alessandra.leleabarrotes.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProductosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private Button añadirButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button buscarButton;

    @FXML
    private Button verButton;

    @FXML
    void onMouseClickedBuscarButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("buscar-productos-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/alessandra/leleabarrotes/Imagenes/LeleLogo.png")));
        stage.show();
        cerrarVentana();
    }

    @FXML
    void onMouseClickedEliminarButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("eliminar-producto-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/alessandra/leleabarrotes/Imagenes/LeleLogo.png")));
        stage.show();
        cerrarVentana();
    }

    @FXML
    void onMouseClickedExitButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/alessandra/leleabarrotes/Imagenes/LeleLogo.png")));
        stage.show();
        cerrarVentana();
    }

    @FXML
    void onMouseClickedModificarButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("modificar-producto-view.fxml"));
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
    void onMouseClickedañadirButton(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("añadir-productos-view.fxml"));
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
