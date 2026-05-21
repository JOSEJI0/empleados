package itch.tsp.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.model.Empleado;

public interface EmpleadoRepository extends JpaRepository <Empleado, Integer> {
	//Buscar empleados por estado ACTIVO
	List<Empleado> findByEstado(Integer estado);
	
	
	// Buscar por salario entre 1000 y 5000 usando between
	List<Empleado> findBySalarioBetween(Double salario1, Double salario2);
	
	// Método anterior pero ordenado descendentemente por salario
	List<Empleado> findBySalarioBetweenOrderBySalarioDesc(Double salario1, Double salario2);
	
	// Buscar por salario entre 1000 y 5000 usando IN 
	List<Empleado> findBySalarioIn(List<Double> salarios);
	
	// Buscar empleado por Departamento
	List<Empleado> findByDepartamentoId(Integer id);
	
	// CORREGIDO: Buscar por activo, departamento y fecha de ingreso
    List<Empleado> findByActivoAndDepartamentoIdAndFechaIngreso(Boolean activo, Integer departamentoId, Date fechaIngreso);
    
    // Buscar empleados que ingresaron en un rango de fechas
    List<Empleado> findByFechaIngresoBetween(Date fecha1, Date fecha2);
	
	// Buscar los empleados por nombre y que terminen con... (Usar Like)
	List<Empleado> findByNombreEndingWith(String nombre);
	
	// Para el borrado lógico
	List<Empleado> findByActivo(Boolean activo);
}