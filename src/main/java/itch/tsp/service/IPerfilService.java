package itch.tsp.service;

import java.util.List;
import itch.tsp.model.Perfil;

public interface IPerfilService {
    List<Perfil> buscarTodos();
    void guardar(Perfil perfil);
    void eliminar(Integer idPerfil);
    Perfil buscarPorId(Integer idPerfil);
}