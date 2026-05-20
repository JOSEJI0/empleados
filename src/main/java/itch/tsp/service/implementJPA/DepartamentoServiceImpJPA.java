package itch.tsp.service.implementJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.model.Departamento;
import itch.tsp.repository.DepartamentoRepository;
import itch.tsp.service.IDepartamentoService;

@Primary
@Service
public class DepartamentoServiceImpJPA implements IDepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Override
	public List<Departamento> buscarTodos() {
		
		return departamentoRepository.findAllByActivo(1);
	}

	@Override
	public void guardar(Departamento departamento) {
		departamentoRepository.save(departamento);
	}

	@Override
	public Departamento buscarDepPorId(Integer idDepartamento) {
		Optional<Departamento> optionalDepartamento = departamentoRepository.findById(idDepartamento);
		
		if (optionalDepartamento.isPresent()) {
			return optionalDepartamento.get();
		}
		return null;
	}
	
	@Override
	public void eliminarDepto(Integer idDepartamento) {
		Optional<Departamento> optionalDepartamento = departamentoRepository.findById(idDepartamento);
		
		if (optionalDepartamento.isPresent()) {
			Departamento depto = optionalDepartamento.get();
			depto.setActivo(0);
			departamentoRepository.save(depto);
		}
	}

}
