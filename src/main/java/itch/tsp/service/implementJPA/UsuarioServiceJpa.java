package itch.tsp.service.implementJPA;

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
    public void guardar(Usuario usuario) {
        usuarioRepo.save(usuario);
    }

}