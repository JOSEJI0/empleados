package itch.tsp.herramienta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner init(RoleRepository roleRepository, UsuarioRepository usuarioRepository, 
	                       EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder) {
	    return args -> {
            // Crear roles si no existen
            Role admin = roleRepository.findByNombre("ROLE_ADMIN").orElseGet(() -> {
                Role r = new Role(); r.setNombre("ROLE_ADMIN"); return roleRepository.save(r);
            });
            Role user = roleRepository.findByNombre("ROLE_USER").orElseGet(() -> {
                Role r = new Role(); r.setNombre("ROLE_USER"); return roleRepository.save(r);
            });

            // Crear usuario admin
            usuarioRepository.findByUsername("admin").orElseGet(() -> {
                Usuario u = new Usuario();
                u.setUsername("admin");
                u.setPassword(passwordEncoder.encode("admin123"));
                Set<Role> roles = new HashSet<>(); roles.add(admin); roles.add(user);
                u.setRoles(roles);
                return usuarioRepository.save(u);
            });

         // Crear usuario normal y vincularlo a un empleado existente
            usuarioRepository.findByUsername("user").orElseGet(() -> {
                Usuario u = new Usuario();
                u.setUsername("user");
                u.setPassword(passwordEncoder.encode("user123"));
                Set<Role> roles = new HashSet<>(); roles.add(user);
                u.setRoles(roles);
                
                // BUSCAMOS A MARIA LOPEZ (ID 2 del import.sql) Y LA VINCULAMOS
                empleadoRepository.findById(2).ifPresent(emp -> u.setEmpleado(emp));
                
                return usuarioRepository.save(u);
            });
        };
    }
}
