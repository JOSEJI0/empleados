package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import itch.tsp.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    @Query("SELECT c FROM Contrato c WHERE c.activo = 1 AND " +
           "(:nombre IS NULL OR concat(c.empleado.nombreE, ' ', c.empleado.apellido) LIKE %:nombre%) AND " +
           "(:numero IS NULL OR CAST(c.id AS string) LIKE %:numero%) AND " +
           "(:tipo IS NULL OR c.tipoContrato LIKE %:tipo%)")
    List<Contrato> buscarPorFiltros(@Param("nombre") String nombre, 
                                    @Param("numero") String numero, 
                                    @Param("tipo") String tipo);
    
    List<Contrato> findByActivo(Integer activo);
}
