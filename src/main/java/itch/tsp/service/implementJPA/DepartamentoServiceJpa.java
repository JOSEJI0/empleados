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
public class DepartamentoServiceJpa implements IDepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	@Override
	public List<Departamento> buscarTodosDep() {
		
		return departamentoRepository.findByActivo(true);
	}

	@Override
	public void guardarDep(Departamento departamento) {
		departamentoRepository.save(departamento);

	}

	@Override
	public Departamento buscarDepPorId(Integer id) {
		Optional<Departamento> optional = departamentoRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void eliminarDep(Integer id) {
		Departamento departamento = buscarDepPorId(id);
		if (departamento != null) {
			departamento.setActivo(false);
			departamentoRepository.save(departamento); 
		}
	}
	
}
