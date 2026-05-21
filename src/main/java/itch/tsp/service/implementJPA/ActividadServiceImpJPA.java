package itch.tsp.service.implementJPA;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itch.tsp.model.Actividad;
import itch.tsp.repository.ActividadRepository;
import itch.tsp.service.IActividadService;

@Service
public class ActividadServiceImpJPA implements IActividadService {

    @Autowired
    private ActividadRepository actividadRepo;

    @Override
    public List<Actividad> buscarTodas() {
        return actividadRepo.findAll();
    }

    @Override
    public void guardar(Actividad actividad) {
        actividadRepo.save(actividad);
    }

    @Override
    public Actividad buscarPorId(Integer idActividad) {
        return actividadRepo.findById(idActividad).orElse(null);
    }

    @Override
    public void eliminar(Integer idActividad) {
        actividadRepo.deleteById(idActividad);
    }
}