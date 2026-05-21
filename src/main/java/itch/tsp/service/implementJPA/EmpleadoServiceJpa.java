package itch.tsp.service.implementJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.model.Empleado;
import itch.tsp.repository.EmpleadoRepository;
import itch.tsp.service.IEmpleadoService;

@Primary
@Service
public class EmpleadoServiceJpa implements IEmpleadoService {
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Override
    public List<Empleado> buscarTodosEmp() {
        return empleadoRepository.findByActivo(true); 
    }

	@Override
	public void guardarEmp(Empleado empleado) {
	empleadoRepository.save(empleado);
		
	}

	@Override
	public Empleado buscarEmpPorId(Integer id) {
		Optional<Empleado> optional = empleadoRepository.findById(id);
		
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void eliminarEmp(Integer id) {
		Empleado empleado = buscarEmpPorId(id);
		if (empleado != null) {
			empleado.setActivo(false);
			empleadoRepository.save(empleado); 
		}
	}

}
