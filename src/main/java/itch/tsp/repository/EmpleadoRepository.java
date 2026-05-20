package itch.tsp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.model.Empleado;


public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
	
	//Método para buscar de los empleados que su salario este entre dos cantidades, por ejemplo (entre 1000 y 5000). (Utiliza Between y otro con IN)
	List<Empleado> findBySalarioBetween(Double min, Double max);
	List<Empleado> findBySalarioIn(List<Double> salarios);
	//Del método anterior, agrega que los precios los muestre ordenados de manera descendente.
	List<Empleado> findBySalarioBetweenOrderBySalarioDesc(Double min, Double max);
	//Buscar empleados por Departamento
	List<Empleado> findByDepartamentoId(Integer id);
	//Buscar empleados por activo, departamento y fechaIngreso
	List<Empleado> findByActivoAndDepartamentoIdAndFechaIngreso(Integer activo, Integer departamentoId, Date fechaIngreso);//Buscar empleados que ingresaron en un rango de fechas
	List<Empleado> findByFechaIngresoBetween(Date fechaInicio, Date fechaFin);
	//Buscar los empleados por nombre y que termine el nombre con... (este ejercicio es el uso de LIKE), ¿cómo resolverías este método?
	List<Empleado> findByNombreEEndingWith(String nombre);
	
	List<Empleado> findByActivo(Integer activo);
}
