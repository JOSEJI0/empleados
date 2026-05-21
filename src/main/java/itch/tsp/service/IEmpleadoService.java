package itch.tsp.service;

import java.util.List;

import itch.tsp.model.Empleado;

public interface IEmpleadoService {

	List<Empleado> buscarTodosEmp();
	
	void guardarEmp(Empleado empleado);
	
	Empleado buscarEmpPorId(Integer id);
	
	
}
