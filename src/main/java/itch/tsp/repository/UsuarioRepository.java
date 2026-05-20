package itch.tsp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import itch.tsp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByEstatus(Integer estatus);
    Optional<Usuario> findByUsername(String username);
}
