package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;

    private final UsuarioRepo usuarioRepo;
    private final ComentarioRepo comentarioRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo,ComentarioRepo comentarioRepo,UsuarioRepo usuarioRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo=comentarioRepo;
        this.usuarioRepo=usuarioRepo;
    }

    @Override
    public Producto registrarProducto(Producto p) throws Exception {
        Optional<Producto> buscado= productoRepo.findById(p.getCodigo());
        if (buscado.isPresent()){
            throw new Exception("El codigo del producto ya existe");
        }

        return productoRepo.save(p);
    }

    @Override
    public Producto publicarProductoVender(Producto p, Usuario u) throws Exception {

        Optional<Producto> buscado= productoRepo.findById(p.getCodigo());
        if (buscado.isPresent()){
            throw new Exception("El codigo del producto ya existe");
        }

        p.setMiUsuario(u);
        return productoRepo.save(p);



    }



    @Override
    public void actualizarProducto(Producto p) throws Exception {

    }

    @Override
    public void eliminarProducto(String codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);

        if (producto.isEmpty()){
            throw new Exception("No existe ningun producto con ese codigo");
        }

        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(String codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(()-> new Exception("el codigo del producto no es valido") );
    }

    @Override
    public List<Producto> listarProductosPorCategoria(Categoria categoria) {
        return null;
    }

    @Override
    public void comentarProducto(String mensaje, Usuario usuario, Producto producto) throws Exception {
        LocalDate ld = LocalDate.now();
        Comentario comentario=new Comentario("32",mensaje,ld);
        comentario.setMiUsuario(usuario);
        comentario.setMiProducto(producto);
        try {
            comentarioRepo.save(comentario);
        }catch (Exception e ){
            e.getMessage();
        }





    }

    @Override
    public void guardarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception {

        if (producto==null || usuario==null){
            throw new Exception("el producto o el usuario son nulos ");
        }

        producto.getUsuariosFavoritos().add(usuario);
        usuario.getProductosFavoritos().add(producto);

        productoRepo.save(producto);
        usuarioRepo.save(usuario);

    }

    @Override
    public void eliminarProductofavorito(Producto producto, Usuario usuario) throws Exception {
        if (producto==null || usuario==null){
            throw new Exception("el producto o el usuario son nulos ");
        }

        producto.getUsuariosFavoritos().remove(usuario);
        usuario.getProductosFavoritos().remove(producto);

        productoRepo.save(producto);
        usuarioRepo.save(usuario);

    }

    @Override
    public void comprarProductos(DetalleCompra detalleCompra, Producto producto) {

    }

    @Override
    public List<Producto> buscarProductoPorNombre(String nombre, String[] producto) {

        return productoRepo.buscarProductoNombre(nombre);
    }

    @Override
    public List<Producto> buscarProducto(String nombre, String[] producto) {
        return null;
    }

    @Override
    public List<Producto> listarProductos(String codigoUsuario) throws Exception {
        return null;
    }
}
