package itch.tsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
	List<Departamento> findAllByOrderByNombreAsc();
	List<Departamento> findAllByActivo(Integer activo);
	List<Departamento> findAllByNombreAndActivoOrderByIdDesc(String nombre, Integer activo);
}
