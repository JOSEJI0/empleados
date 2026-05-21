package itch.tsp.service;

import java.util.List;
import itch.tsp.model.Actividad;

public interface IActividadService {
    List<Actividad> buscarTodas();
    void guardar(Actividad actividad);
    Actividad buscarPorId(Integer idActividad);
    void eliminar(Integer idActividad);
}