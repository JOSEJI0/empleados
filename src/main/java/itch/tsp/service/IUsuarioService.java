package itch.tsp.service;

import java.util.List;

import itch.tsp.model.Usuario;

public interface IUsuarioService {
    void guardarU(Usuario usuario);
    void eliminarU(Integer estatus);
    List<Usuario> buscarTodos(); 
}


