package com.spacrod.service.conversores;

import com.spacrod.entity.Producto;
import com.spacrod.repository.ProductoDAO;
import com.spacrod.util.Productos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ConversorCSV extends Conversor {
    private File file;
    public ConversorCSV() {
        file = new File("src/main/resources/csv/productos.csv");
        try{
            if(!file.exists())file.createNewFile();
        }catch(IOException e){
            System.out.println("Imposible crear el archivo");
        }
    }

    @Override
    public File convert(File file) throws IOException {
        if(!file.exists())throw new FileNotFoundException("Archivo no encontrado");
        //tomamos la extensión del fichero para saber si es csv o xml
        ExtensionDocumento extensionDocumento = super.obtenerExtensionDocumento(file.getName());
        switch(extensionDocumento){
            case CSV:
                System.out.println("Devolviendo el mismo archivo...");
                return file;
            case XML:
                ProductoDAO productoDAO = new ProductoDAO();
                Productos productos = productoDAO.getProductos();
                //generamos el csv con el metodo concreto
                boolean crearCSV = cargarEnCSV(productos.getProductos());
                if(crearCSV){
                    return this.file;
                }else throw new IOException("Imposible convertir el archivo");
            default: throw new IllegalArgumentException("La extensión del documento no es válida para hacer la conversión.");
        }
    }
    private boolean cargarEnCSV(List<Producto> productos){
        Path path = Path.of(this.file.getPath());
        try{
            Files.writeString(path, "nombre, precio, cantidad\n", StandardCharsets.UTF_8);
            for(Producto producto: productos){
                Files.writeString(
                        path,
                        producto.getNombre()+","+producto.getPrecio()+","+producto.getCantidad()+"\n",
                        StandardCharsets.UTF_8,
                        StandardOpenOption.APPEND
                );
            }
            System.out.println("Proceso terminado. Conversión de xml a csv completada.");
            return true;
        }catch(IOException e){
            System.out.println("Imposible convertir el archivo");
            return false;
        }
    }
}
