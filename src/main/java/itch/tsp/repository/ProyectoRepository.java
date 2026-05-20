package itch.tsp.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import itch.tsp.model.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    List<Proyecto> findByActivo(Integer activo);
    List<Proyecto> findByActivoAndFechaFinIsNull(Integer activo);
    List<Proyecto> findByNombreContainingAndActivo(String nombre, Integer activo);
    List<Proyecto> findByFechaInicioBetweenAndActivo(Date inicio, Date fin, Integer activo);
}