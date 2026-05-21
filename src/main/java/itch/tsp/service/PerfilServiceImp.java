package itch.tsp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itch.tsp.model.Perfil;
import itch.tsp.repository.PerfilRepository;

@Service
public class PerfilServiceImp implements IPerfilService {

    @Autowired
    private PerfilRepository perfilRepo;

    @Override
    public List<Perfil> buscarTodos() {
        return perfilRepo.findAll();
    }

    @Override
    public void guardar(Perfil perfil) {
        perfilRepo.save(perfil);
    }

    @Override
    public void eliminar(Integer idPerfil) {
        perfilRepo.deleteById(idPerfil);
    }

    @Override
    public Perfil buscarPorId(Integer idPerfil) {
        return perfilRepo.findById(idPerfil).orElse(null);
    }
}