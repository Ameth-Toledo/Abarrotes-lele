package com.alessandra.leleabarrotes.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Reporte {
    private SimpleStringProperty fecha;
    private SimpleStringProperty descripcion;
    private SimpleDoubleProperty totalVendido;
    public static List<Reporte> listaReportes = new ArrayList<>();

    public Reporte(String fecha, String descripcion, double totalVendido) {
        this.fecha = new SimpleStringProperty(fecha);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.totalVendido = new SimpleDoubleProperty(totalVendido);
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

    public String getDescripcion() {
        return descripcion.get();
    }

    public SimpleStringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public double getTotalVendido() {
        return totalVendido.get();
    }

    public SimpleDoubleProperty totalVendidoProperty() {
        return totalVendido;
    }

    public void setTotalVendido(double totalVendido) {
        this.totalVendido.set(totalVendido);
    }

    public static List<Reporte> getListaReportes() {
        return listaReportes;
    }

    public static void setListaReportes(List<Reporte> listaReportes) {
        Reporte.listaReportes = listaReportes;
    }
}
