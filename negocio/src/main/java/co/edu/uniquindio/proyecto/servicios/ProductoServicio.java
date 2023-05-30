package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ProductoServicio {


    Producto publicarProductoVender(Producto p,Usuario u) throws Exception;

    Producto registrarProducto(Producto p) throws Exception;
    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(String codigo) throws Exception;

    Producto obtenerProducto(String codigo) throws Exception;
    List<Producto> listarProductosPorCategoria(Categoria categoria);
    List<Producto> listarTodosProductos()throws Exception;

    void comentarProducto(String mensaje,Usuario usuario,Producto producto) throws Exception;

    void guardarProductoEnFavoritos(Producto producto,Usuario usuario) throws Exception;
    void eliminarProductofavorito(Producto producto,Usuario usuario)throws Exception;
    void comprarProductos(DetalleCompra detalleCompra,Producto producto);

    List<Producto> buscarProductoPorNombre(String nombre, String [] producto);

    List<Producto> buscarProducto(String nombre, String[] producto);

    List<Producto> listarProductos(String codigoUsuario)throws Exception;


    List<Producto> obtenerProductosPorCategoria(String categoriaSeleccionada);
}
