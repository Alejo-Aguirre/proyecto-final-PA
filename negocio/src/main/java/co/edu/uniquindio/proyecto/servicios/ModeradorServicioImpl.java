package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;

    public ModeradorServicioImpl(ModeradorRepo moderadorRepo) {
        this.moderadorRepo = moderadorRepo;
    }


    @Override
    public Moderador registrarModerador(@NotNull Moderador m) throws Exception {
        Optional<Moderador> moderador = moderadorRepo.findByEmail(m.getEmail());
        if (moderador.isPresent()) {
            throw new Exception("El correo electrónico ya está registrado");
        }

        moderador = moderadorRepo.findById(m.getCodigo());
        if (moderador.isPresent()) {
            throw new Exception("El código ya está registrado");
        }

        moderador = moderadorRepo.findByNombre(m.getNombre());
        if (moderador.isPresent()) {
            throw new Exception("El nombre ya está registrado");
        }

        //String password = passwordEncoder.encode(m.getPassword());
        //m.setPassword(password);
        return moderadorRepo.save(m);
    }


    @Override
    public Moderador actualizarModerador(Moderador m) throws Exception {
        Optional<Moderador> moderadorExistente = moderadorRepo.findById(m.getCodigo());
        if (!moderadorExistente.isPresent()) {
            throw new Exception("El moderador con el código " + m.getCodigo() + " no existe");
        }
        Moderador moderador = moderadorExistente.get();
        moderador.setNombre(m.getNombre());
        moderador.setEmail(m.getEmail());
        moderador.setPassword(m.getPassword());
        return moderadorRepo.save(moderador);
    }

    @Override
    public void eliminarModerador(String codigo) throws Exception {
        Optional<Moderador> moderadorExistente = moderadorRepo.findById(codigo);
        if (!moderadorExistente.isPresent()) {
            throw new Exception("El moderador con el código " + codigo + " no existe");
        }
        Moderador moderador = moderadorExistente.get();
        moderadorRepo.delete(moderador);
    }

    @Override
    public List<Moderador> listarModeradores() {
        return moderadorRepo.findAll();
    }

    @Override
    public Moderador buscarModeradorPorCodigo(String codigo) throws Exception {
        Optional<Moderador> moderadorExistente = moderadorRepo.findById(codigo);
        if (!moderadorExistente.isPresent()) {
            throw new Exception("El moderador con el código " + codigo + " no existe");
        }
        return moderadorExistente.get();
    }

    @Override
    public Moderador iniciarSesion(String email, String password) throws Exception {
        Optional<Moderador> moderadorExistente = moderadorRepo.findByEmailAndPassword(email, password);
        if (!moderadorExistente.isPresent()) {
            throw new Exception("El correo electrónico o la contraseña son incorrectos");
        }
        return moderadorExistente.get();
    }

    @Override
    public Moderador obtenerModerador(String codigo) throws Exception {
        Optional<Moderador> moderadorExistente = moderadorRepo.findById(codigo);
        if (!moderadorExistente.isPresent()) {
            throw new Exception("El moderador con el código " + codigo + " no existe");
        }
        return moderadorExistente.get();
    }
}

