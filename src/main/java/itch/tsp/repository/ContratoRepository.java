package itch.tsp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import itch.tsp.model.Contrato;

public interface ContratoRepository extends JpaRepository <Contrato, Integer> {
	
	// Listar solo los activos
	List<Contrato> findByActivo(Boolean activo);
	
	@Query("SELECT c FROM Contrato c WHERE c.activo = true AND " +
	       "(:id IS NULL OR c.id = :id) AND " +
	       "(:tipo IS NULL OR c.tipo_contrato LIKE CONCAT('%', :tipo, '%')) AND " +
	       "(:nombre IS NULL OR c.empleado.nombre LIKE CONCAT('%', :nombre, '%') OR c.empleado.apellidos LIKE CONCAT('%', :nombre, '%'))")
	List<Contrato> buscarPorFiltros(@Param("id") Integer id, @Param("tipo") String tipo, @Param("nombre") String nombre);
}