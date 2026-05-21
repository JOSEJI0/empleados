package itch.tsp.service;

import java.util.List;
import itch.tsp.model.Usuario;

public interface IUsuarioService {
    void guardarU(Usuario usuario);
    List<Usuario> buscarTodos();
    void eliminarU(Integer idUsuario);
}