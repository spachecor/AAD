package com.spacrod.service.conversores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spacrod.entity.Producto;
import com.spacrod.repository.ProductoDAO;
import com.spacrod.util.Productos;

import java.io.*;
import java.util.List;

public class ConversorJSON extends Conversor{
    private File file;
    private Gson gson;
    public ConversorJSON() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        file = new File("src/main/resources/json/productos.json");
        try{
            if(!file.exists())file.createNewFile();
        }catch(IOException e){
            System.out.println("Imposible crear el archivo");
        }
    }

    @Override
    public File convert(File file) throws IOException {
        if(!file.exists())throw new FileNotFoundException("Archivo no encontrado");
        ExtensionDocumento extensionDocumento = super.obtenerExtensionDocumento(file.getName());
        switch(extensionDocumento){
            case JSON:
                System.out.println("Devolviendo el mismo archivo...");
                return file;
            case XML:
                ProductoDAO productoDAO = new ProductoDAO();
                Productos productos = productoDAO.getProductos();
                boolean crearJSON = cargarEnJSON(productos.getProductos());
                if(crearJSON){
                    return this.file;
                }else throw new IOException("Imposible convertir el archivo.");
            default: throw new IllegalArgumentException("La extensión del documento no es válida.");
        }
    }
    private boolean cargarEnJSON(List<Producto> productos) {
        try(FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(productos, fileWriter);
            System.out.println("Proceso terminado. Conversión de xml a json completada.");
            return true;
        }catch (IOException e){
            System.out.println("Imposible convertir el archivo");
            return false;
        }
    }
}
