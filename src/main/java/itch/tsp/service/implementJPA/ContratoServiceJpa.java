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
public class ContratoServiceJpa implements IContratoService {

    @Autowired
    private ContratoRepository contratoRepo;

    @Override
    public List<Contrato> buscarTodos() {
        return contratoRepo.findAll();
    }

    @Override
    public void guardar(Contrato contrato) {
        contratoRepo.save(contrato);
    }

    @Override
    public Contrato buscarPorId(Integer id) {
        Optional<Contrato> optional = contratoRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        contratoRepo.deleteById(id);
    }
    
    public List<Contrato> buscarPorFiltros(Integer id, String tipo, String nombre) {
        if (tipo != null && tipo.trim().isEmpty()) {
            tipo = null;
        }
        if (nombre != null && nombre.trim().isEmpty()) {
            nombre = null;
        }
        
        return contratoRepo.buscarPorFiltros(id, tipo, nombre);
    }
}