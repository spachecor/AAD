package com.spachecor.proyectoproductos.model.conversores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spachecor.proyectoproductos.model.entity.Producto;
import com.spachecor.proyectoproductos.model.repository.ProductoDAO;
import com.spachecor.proyectoproductos.model.util.Productos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase que es el conversor de archivos XML a JSON
 * @see Conversor
 * @author Selene
 * @version 1.0
 */
public class ConversorJSON extends Conversor{
    private File file;
    private Gson gson;
    public ConversorJSON() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        file = new File("src/main/resources/com/spachecor/proyectoproductos/json/productos.json");
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
    /**
     * Método que crea el File de tipo JSON con una lista de productos
     * @param productos La lista de productos que contendrá el archivo JSON
     * @return true o false según si ha conseguido cargar en el JSON o no
     */
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
