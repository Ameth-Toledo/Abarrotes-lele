package com.alessandra.leleabarrotes.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private SimpleStringProperty fecha;
    private SimpleStringProperty nombreproducto;
    private SimpleStringProperty nombreCliente;
    private SimpleStringProperty idVenta;
    private SimpleStringProperty idProducto;
    private SimpleDoubleProperty precio;
    private SimpleDoubleProperty total;

    public static List<Venta> listaVentas = new ArrayList<>();

    public Venta(String fecha, String nombreproducto, String nombreCliente, String idVenta, String idProducto, double precio, double total) {
        this.fecha = new SimpleStringProperty(fecha);
        this.nombreproducto = new SimpleStringProperty(nombreproducto);
        this.nombreCliente = new SimpleStringProperty(nombreCliente);
        this.idVenta = new SimpleStringProperty(idVenta);
        this.idProducto = new SimpleStringProperty(idProducto);
        this.precio = new SimpleDoubleProperty(precio);
        this.total = new SimpleDoubleProperty(total);
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

    public String getNombreproducto() {
        return nombreproducto.get();
    }

    public SimpleStringProperty nombreproductoProperty() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto.set(nombreproducto);
    }

    public String getNombreCliente() {
        return nombreCliente.get();
    }

    public SimpleStringProperty nombreClienteProperty() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente.set(nombreCliente);
    }

    public String getIdVenta() {
        return idVenta.get();
    }

    public SimpleStringProperty idVentaProperty() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta.set(idVenta);
    }

    public String getIdProducto() {
        return idProducto.get();
    }

    public SimpleStringProperty idProductoProperty() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto.set(idProducto);
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

    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public static List<Venta> getListaVentas() {
        return listaVentas;
    }

    public static void setListaVentas(List<Venta> listaVentas) {
        Venta.listaVentas = listaVentas;
    }
}
