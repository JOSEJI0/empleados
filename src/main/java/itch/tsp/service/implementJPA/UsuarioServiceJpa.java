package itch.tsp.service.implementJPA;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itch.tsp.model.Usuario;
import itch.tsp.repository.UsuarioRepository;
import itch.tsp.service.IUsuarioService;

@Service
public class UsuarioServiceJpa implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public void guardarU(Usuario usuario) {
        usuarioRepo.save(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepo.findAll();
    }

    @Override
    public void eliminarU(Integer idUsuario) {
        // SOLUCIÓN: Convertimos el Integer a Long de forma segura para JpaRepository
        if (idUsuario != null) {
            usuarioRepo.deleteById(idUsuario.longValue());
        }
    }
}