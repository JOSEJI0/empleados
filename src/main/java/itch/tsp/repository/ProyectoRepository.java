package itch.tsp.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import itch.tsp.model.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    
    List<Proyecto> findByActivoTrue();
    
    @Query("SELECT p FROM Proyecto p WHERE p.activo = true AND p.fechaFin IS NULL")
    List<Proyecto> findProyectosActivosSinFechaFin();
    List<Proyecto> findByNombreContainingAndFechaInicioAndActivoTrueAndFechaFinIsNull(String nombre, Date fechaInicio);
    List<Proyecto> findByNombreContainingAndActivoTrueAndFechaFinIsNull(String nombre);
    List<Proyecto> findByFechaInicioAndActivoTrueAndFechaFinIsNull(Date fechaInicio);
}