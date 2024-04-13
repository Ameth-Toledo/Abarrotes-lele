package com.alessandra.leleabarrotes.models;

import java.util.ArrayList;
import java.util.List;

public class Imagen {
    private static List<String> imagenes = new ArrayList<>();

    public static List<String> getImagenes() {
        return imagenes;
    }

    public static void setImagenes(List<String> imagenes) {
        Imagen.imagenes = imagenes;
    }

    public static void agregarImagen(String imagen) {
        imagenes.add(imagen);
    }

    public static void eliminarImagen(String imagen) {
        imagenes.remove(imagen);
    }
}
