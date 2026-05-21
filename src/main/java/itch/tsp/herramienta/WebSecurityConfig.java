package itch.tsp.herramienta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import itch.tsp.security.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // 1. Recursos estáticos
                .requestMatchers("/css/**", "/js/**", "/imagenes/**", "/empleados/**", "/departamentos/**", "/login", "/error").permitAll()
                
                // 2. Rutas del EMPLEADO (Blindado para aceptar roles con o sin prefijo)
                .requestMatchers("/mi-panel", "/mis-contratos", "/mis-proyectos")
                    .hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")
                .requestMatchers("/contrato/exportarPdf/**")
                    .hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")
                
                // 3. Rutas exclusivas del ADMINISTRADOR
                .requestMatchers("/empleado/**", "/departamento/**", "/contrato/**", "/proyectos/**")
                    .hasAnyAuthority("ADMIN", "ROLE_ADMIN")
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    // Verificamos si es admin usando ambas variantes
                    boolean isAdmin = authentication.getAuthorities().stream()
                        .anyMatch(rol -> rol.getAuthority().equals("ROLE_ADMIN") || rol.getAuthority().equals("ADMIN"));
                    
                    if (isAdmin) {
                        response.sendRedirect("/");
                    } else {
                        response.sendRedirect("/mi-panel");
                    }
                })
                .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"));

        return http.build();
    }
}