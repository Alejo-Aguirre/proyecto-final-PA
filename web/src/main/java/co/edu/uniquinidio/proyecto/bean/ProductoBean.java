package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @PostConstruct
    public void inicializar(){
        producto = new Producto();
    }

    public String crearProducto(){
        try {

            LocalDate ldn = LocalDate.now();
            producto.setMiUsuario(usuarioServicio.obtenerUsuario("123"));
            producto.setMiCategoria(categoriaServicio.obtenerCategoria("302"));
            producto.setFechaCreacion(ldn);
            productoServicio.registrarProducto(producto);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","La creacion del producto fue exitosa");
            FacesContext.getCurrentInstance().addMessage(null,msg);
            return "producto_creado?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
        }

        return null;
    }


}
