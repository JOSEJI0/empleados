package itch.tsp.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import itch.tsp.model.Departamento;


@Service
public class DepartamentoServiceImplement implements IDepartamentoService {
	
	private List<Departamento> listaDepartamentos;
	
	public DepartamentoServiceImplement() {
		listaDepartamentos = new LinkedList<>();
		
		Departamento dep1 = new Departamento();
		dep1.setId(1);
		dep1.setNombre("Recursos Humanos");
		dep1.setDescripcion("Departamento encargado de gestionar el talento humano de la empresa.");
		dep1.setActivo(true);
		dep1.setFotoDep("/departamentos/foto1Dep.jpg");
		
		Departamento dep2 = new Departamento();
		dep2.setId(2);
		dep2.setNombre("Tecnología");
		dep2.setDescripcion("Departamento encargado de desarrollar y mantener la infraestructura tecnológica de la empresa.");
		dep2.setActivo(true);
		dep2.setFotoDep("/departamentos/foto2Dep.jpg");
		
		Departamento dep3 = new Departamento();
		dep3.setId(3);
		dep3.setNombre("Ventas");
		dep3.setDescripcion("Departamento encargado de gestionar las ventas y relaciones con los clientes.");
		dep3.setActivo(true);
		dep3.setFotoDep("/departamentos/foto3Dep.jpg");
		
		listaDepartamentos.add(dep1);
		listaDepartamentos.add(dep2);
		listaDepartamentos.add(dep3);
	}
	
	@Override
	public List<Departamento> buscarTodosDep() {
		return listaDepartamentos;
	}
	
	@Override
	public void guardarDep(Departamento departamento) {
		listaDepartamentos.add(departamento);
	}
	
	@Override
	public Departamento buscarDepPorId(Integer id) {
		for (Departamento departamento : listaDepartamentos) {
			// Uso de equals para comparar objetos Integer de forma segura
			if (departamento.getId().equals(id)) {
				return departamento;
			}
		}
		return null;
	}
}