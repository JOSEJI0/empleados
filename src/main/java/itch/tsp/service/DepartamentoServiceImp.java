package itch.tsp.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import itch.tsp.model.Departamento;

@Service
public class DepartamentoServiceImp implements IDepartamentoService {

	private List<Departamento> listaDep = new LinkedList<Departamento>();

	public DepartamentoServiceImp() {
		Departamento d1 = new Departamento();
		d1.setId(1);
		d1.setNombre("Sistemas");
		d1.setActivo(1);
		d1.setFotoUbicacion("/imagenesDepartamento/sistemas.png");

		Departamento d2 = new Departamento();
		d2.setId(2);
		d2.setNombre("Recursos Humanos");
		d2.setActivo(0);
		d2.setFotoUbicacion("/imagenesDepartamento/tecedif.jpg");

		listaDep.add(d1);
		listaDep.add(d2);
	}

	@Override
	public List<Departamento> buscarTodos() {
		return listaDep;
	}

	@Override
	public void guardar(Departamento departamento) {
		listaDep.add(departamento);
	}

	@Override
	public Departamento buscarDepPorId(Integer idDepartamento) {
		for (Departamento depto : listaDep) {
			if (depto.getId().equals(idDepartamento)) {
				return depto;
			}
		}
		return null;
	}
	
	@Override
	public void eliminarDepto(Integer idDepartamento) {
		
	}
	
}