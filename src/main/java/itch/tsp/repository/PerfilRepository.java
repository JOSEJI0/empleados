package itch.tsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import itch.tsp.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}