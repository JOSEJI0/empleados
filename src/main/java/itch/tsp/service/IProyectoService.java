package itch.tsp.service;

import java.util.Date;
import java.util.List;
import itch.tsp.model.Proyecto;

public interface IProyectoService {
    List<Proyecto> buscarTodos();
    void guardar(Proyecto proyecto);
    Proyecto buscarPorId(Integer id);
    void eliminar(Integer id);
    
    List<Proyecto> buscarParaInicio();
    List<Proyecto> buscarPorFiltrosInicio(String nombre, Date inicio, Date fin);
}