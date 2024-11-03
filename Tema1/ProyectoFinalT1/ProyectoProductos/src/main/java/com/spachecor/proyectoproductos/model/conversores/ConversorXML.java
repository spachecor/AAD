package com.spachecor.proyectoproductos.model.conversores;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spachecor.proyectoproductos.model.entity.Producto;
import com.spachecor.proyectoproductos.model.repository.ProductoDAO;
import com.spachecor.proyectoproductos.model.util.Productos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * Clase que es el conversor de archivos CSV a XML o del JSON a XML
 * @see Conversor
 * @author Selene
 * @version 1.0
 */
public class ConversorXML extends Conversor{
    private File file;
    private Gson gson;
    private ProductoDAO productoDAO;
    public ConversorXML() {
        productoDAO = new ProductoDAO();
        gson = new Gson();
        file = new File("src/main/resources/com/spachecor/proyectoproductos/xml/productos.xml");
    }

    @Override
    public File convert(File file) throws IOException {
        if(!file.exists()) throw new FileNotFoundException("Archivo no encontrado");
        Productos productos = new Productos();
        ExtensionDocumento extensionDocumento = super.obtenerExtensionDocumento(file.getName());
        switch(extensionDocumento){
            case XML:
                System.out.println("Devolviendo el mismo archivo...");
                return file;
            case JSON:
                try (FileReader fileReader = new FileReader(file)){
                    productos.setProductos(gson.fromJson(fileReader, new TypeToken<ArrayList<Producto>>(){}.getType()));
                    boolean crearXML = productoDAO.constructorXML(productos);
                    if(crearXML){
                        System.out.println("Proceso terminado. Conversión de json a xml completada.");
                        return this.file;
                    }else throw new Exception("Imposible convertir el archivo");
                }catch(Exception e){
                    System.out.println("Imposible encontrar los registros.");
                    return null;
                }
            case CSV:
                List<String> allLines = Files.readAllLines(Path.of(file.getPath()));
                try {
                    for (int i = 0; i < allLines.size(); i++) {
                        StringTokenizer st = new StringTokenizer(allLines.get(i), ",");
                        if(i!=0) {
                            Producto producto = new Producto();
                            producto.setNombre(st.nextToken());
                            producto.setPrecio(Double.valueOf(st.nextToken()));
                            producto.setCantidad(Integer.valueOf(st.nextToken()));
                            productos.getProductos().add(producto);
                        }
                    }
                    boolean crearXML = productoDAO.constructorXML(productos);
                    if (crearXML){
                        System.out.println("Proceso terminado. Conversión de csv a xml completada.");
                        return this.file;
                    }else throw new Exception("Imposible convertir el archivo");
                }catch (Exception e){
                    System.out.println("Imposible crear el documento xml del archivo ingresado.");
                }
            default: throw new IllegalArgumentException("La extensión del documento no es válida.");
        }
    }
}
