//package src.main.java.edu.upc.prop.clusterxx;   <- marcad src como root para no poner el path entero -Marcel
package edu.upc.prop.clusterxx;

/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import java.lang.reflect.Type;
//import com.google.gson.reflect.TypeToken;

/**
 * Controlador de los productos, contiene una lista de todos los productos.
 * El id de un producto es su posición en la lista.
 */
public class ListaProductos implements Serializable, Cloneable {
    private ArrayList<Producto> productos;
    /*private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();*/

    /**
     * Constructora, el estado inicial es una lista vacía.
     */
    public ListaProductos() {
        productos = new ArrayList<>();
    }

    /**
     * Añade un solo producto.
     * @param producto producto que añadir.
     */
    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    /*
     * Determina el tipo de archivo y carga los datos.
     * @param nombreArchivo el nombre del archivo.
     */
    /* Gestión de archivos para más adelante
    public void ImportarLista(String nombreArchivo) {
        try {
            String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
            List<Producto> nuevosProductos = new ArrayList<>();

            switch (extension) {
                case "csv":
                    nuevosProductos = cargarDesdeArchivoDelimitado(nombreArchivo, ",");
                    break;
                case "txt":
                    nuevosProductos = cargarDesdeArchivoDelimitado(nombreArchivo, "\\|");
                    break;
                case "json":
                    nuevosProductos = cargarDesdeJSON(nombreArchivo);
                    break;
                default:
                    throw new IllegalArgumentException("Formato de archivo no soportado: " + extension);
            }

            productos.addAll(nuevosProductos);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Error al parsear JSON: " + e.getMessage());
        }
    }

    /**
     * Carga la lista desde un archivo delimitado.
     * @param nombreArchivo nombre del archivo.
     * @param delimitador delimitador.
     * @return lista de productos cargada desde el archivo.
     * @throws IOException en caso de error al leer el archivo.
     *
    private List<Producto> cargarDesdeArchivoDelimitado(String nombreArchivo, String delimitador) throws IOException {
        List<Producto> nuevosProductos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(delimitador);
                if (datos.length == 3) {
                    nuevosProductos.add(new Producto(
                            datos[1].trim(),
                            Double.parseDouble(datos[2].trim())
                    ));
                }
            }
        }
        return nuevosProductos;
    }

    /**
     * Carga la lista de productos desde un archivo JSON
     * @param nombreArchivo nombre del archivo.
     * @return lista de productos cargada desde el JSON.
     * @throws IOException en caso de error al leer el archivo.
     *
    private List<Producto> cargarDesdeJSON(String nombreArchivo) throws IOException {
        try (Reader reader = new FileReader(nombreArchivo)) {
            Gson gson = new Gson();
            Type tipoListaProductos = new TypeToken<List<Producto>>(){}.getType();
            return gson.fromJson(reader, tipoListaProductos);
        }
    }


    /**
     * Exports the product list to a JSON file
     * @param nombreArchivo the path and name of the file to create
     * @throws IOException if there's an error writing the file
     *
    public void exportarAJSON(String nombreArchivo) throws IOException {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(productos, writer);
        }
    }

    /**
     * Converts the product list to a JSON string
     * @return JSON string representation of the product list
     *
    public String toJSON() {
        return gson.toJson(productos);
    }

    /**
     * Exports the product list to a JSON file with error handling
     * @param nombreArchivo the path and name of the file to create
     * @return true if export was successful, false otherwise
     *
    public boolean exportarAJSONConManejodeErrores(String nombreArchivo) {
        try {
            exportarAJSON(nombreArchivo);
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar a JSON: " + e.getMessage());
            return false;
        }
    }
    */
    /**
     * Devuelve el tamaño de la lista
     * @return cantidad de productos.
     */
    public int getCantidadProductos() {
        return productos.size();
    }

    /**
     * @return el arraylist de los productos.
     */
    public List<Producto> getListaProductos() {
        return productos;
    }

    /**
     * @return una copia de la lista de productos.
     */
    @Override
    public ListaProductos clone() {
        ListaProductos copia = new ListaProductos();
        ArrayList<Producto> l = new ArrayList<>();
        for (Producto producto : productos) {
            Producto p = new Producto(producto.getNombre(), producto.getPrecio());
            l.add(p);
        }
        copia.productos = l;
        return copia;
    }

    /**
     * Elimina un producto de la lista
     * @param id identificador del producto que queremos eliminar.
     * @return true si se ha podido eliminar y false si no.
     */
    public boolean eliminarProducto(int id) {
        return productos.remove(id) != null;
    }

    /**
     * Devuelve un producto.
     * @param id identificador del producto que queremos.
     * @return instancia del producto, en caso de que no exista NULL.
     */
    public Optional<Producto> getProducto(int id) {
        return Optional.ofNullable(productos.get(id));
    }

    @Override
    public String toString() {
        return "ListaProductos{" +
                "productos=" + productos +
                '}';
    }
}

