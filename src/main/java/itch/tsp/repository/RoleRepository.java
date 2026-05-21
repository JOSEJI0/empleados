package itch.tsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import itch.tsp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNombre(String nombre);
}
