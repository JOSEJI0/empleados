package itch.tsp.service;

import java.util.Date;
import java.util.List;
import itch.tsp.model.Proyecto;

public interface IProyectoService {
    List<Proyecto> buscarTodos();
    void guardar(Proyecto proyecto);
    Proyecto buscarPorId(Integer id);
    void eliminar(Integer id);
    
    List<Proyecto> findProyectosActivosSinFechaFin();     
    List<Proyecto> buscarPorFiltros(String nombre, Date fechaInicio);    
}