package itch.tsp.service;

import java.util.List;

import itch.tsp.model.Empleado;

public interface IEmpleadoService {
	List<Empleado> buscarTodosEmp();
	
	//Guardar un nuevo empleado
	void guardarEmp(Empleado emp);
	
	Empleado buscarEmpPorId(Integer idEmpleado);

	void eliminarEmp(Integer idEmpleado);

}
