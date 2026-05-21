package itch.tsp.service;

import java.util.List;

import itch.tsp.model.Departamento;

public interface IDepartamentoService {
	
	//Creación de la lista de departamentos
	List<Departamento> buscarTodosDep();
	
	void guardarDep(Departamento departamento);
	
	Departamento buscarDepPorId(Integer id);
}
