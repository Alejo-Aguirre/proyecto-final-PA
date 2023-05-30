package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Getter
    @Setter
    private List<Producto> productos;


    @PostConstruct
    public void inicializar()
    {
        try {
            this.productos = productoServicio.listarTodosProductos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
