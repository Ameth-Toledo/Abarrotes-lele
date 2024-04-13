package com.alessandra.leleabarrotes.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Productos {
    private SimpleStringProperty nombre;
    private SimpleDoubleProperty precio;
    private SimpleStringProperty fecha;
    private SimpleStringProperty marca;
    private SimpleDoubleProperty volumen;
    private SimpleStringProperty id;

    public static List<Productos> listaProductos = new ArrayList<>();

    public Productos(String nombre, double precio, String fecha, String marca, double volumen, String id) {
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleDoubleProperty(precio);
        this.fecha = new SimpleStringProperty(fecha);
        this.marca = new SimpleStringProperty(marca);
        this.volumen = new SimpleDoubleProperty(volumen);
        this.id = new SimpleStringProperty(id);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public double getPrecio() {
        return precio.get();
    }

    public SimpleDoubleProperty precioProperty() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public String getFecha() {
        return fecha.get();
    }

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getMarca() {
        return marca.get();
    }

    public SimpleStringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public double getVolumen() {
        return volumen.get();
    }

    public SimpleDoubleProperty volumenProperty() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen.set(volumen);
    }

    public static List<Productos> getListaProductos() {
        return listaProductos;
    }

    public static void setListaProductos(List<Productos> listaProductos) {
        Productos.listaProductos = listaProductos;
    }

}
