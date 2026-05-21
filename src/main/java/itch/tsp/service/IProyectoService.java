package itch.tsp.service;

import java.util.Date;
import java.util.List;
import itch.tsp.model.Proyecto;

public interface IProyectoService {
    void guardar(Proyecto proyecto);
    void eliminarLogico(Integer id);
    List<Proyecto> buscarParaInicio();
    List<Proyecto> buscarTodosActivos();
    Proyecto buscarPorId(Integer id);
    List<Proyecto> buscarPorFiltrosInicio(String nombre, Date inicio, Date fin);
}