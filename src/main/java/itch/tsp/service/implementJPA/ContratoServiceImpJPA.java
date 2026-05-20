package itch.tsp.service.implementJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.model.Contrato;
import itch.tsp.repository.ContratoRepository;
import itch.tsp.service.IContratoService;

@Primary
@Service
public class ContratoServiceImpJPA implements IContratoService{
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	@Override
	public List<Contrato> buscarTodosContrato() {
		return contratoRepository.findByActivo(1);
	}
	
	@Override
	public void guardarContrato(Contrato contrato) {
		contratoRepository.save(contrato);
	}
	
	@Override
	public Contrato buscarContratoPorId(Integer idContrato) {
		Optional<Contrato> optionalContrato = contratoRepository.findById(idContrato);
		
		if (optionalContrato.isPresent()) {
			return optionalContrato.get();
		}
		return null;
	}
	
	@Override
	public List<Contrato> buscarPorFiltros(String nombre, String numero, String tipo) {
	    String n = (nombre != null && !nombre.isEmpty()) ? nombre : null;
	    String num = (numero != null && !numero.isEmpty()) ? numero : null;
	    String t = (tipo != null && !tipo.isEmpty()) ? tipo : null;
	    return contratoRepository.buscarPorFiltros(n, num, t);
	}
	
	@Override
	public void eliminarContrato(Integer idContrato) {
	    Optional<Contrato> optional = contratoRepository.findById(idContrato);
	    if(optional.isPresent()) {
	        Contrato contrato = optional.get();
	        contrato.setActivo(0); 
	        contratoRepository.save(contrato);
	    }
	}
	
}
