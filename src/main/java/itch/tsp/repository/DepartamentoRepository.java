package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import itch.tsp.model.Departamento;


public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
	
	//Buscar por status
	List<Departamento> findByActivo(Boolean activo);
	
	//Métopdo para buscar por nombre y activo ordenado por id de forma descendente
	List<Departamento> findByNombreAndActivoOrderByIdDesc(String nombre, Boolean activo);

}
