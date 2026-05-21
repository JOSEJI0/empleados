package itch.tsp.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import itch.tsp.model.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    
    List<Proyecto> findByActivoAndFechaFinIsNull(Integer activo);
    
    @Query("SELECT p FROM Proyecto p WHERE p.activo = 1 AND p.fechaFin IS NULL AND " +
           "(:nombre IS NULL OR p.nombre LIKE %:nombre%) AND " +
           "(:inicio IS NULL OR :fin IS NULL OR p.fechaInicio BETWEEN :inicio AND :fin)")
    List<Proyecto> buscarProyectosInicioConFiltros(@Param("nombre") String nombre, 
                                                   @Param("inicio") Date inicio, 
                                                   @Param("fin") Date fin);
    
    List<Proyecto> findByActivo(Integer activo);
    List<Proyecto> findByNombreContainingAndActivo(String nombre, Integer activo);
    List<Proyecto> findByFechaInicioBetweenAndActivo(Date inicio, Date fin, Integer activo);
}