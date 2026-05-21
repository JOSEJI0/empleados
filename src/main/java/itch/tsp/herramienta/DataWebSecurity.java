package itch.tsp.herramienta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DataWebSecurity {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            // Recursos estáticos y subidas locales libres
            .requestMatchers("/bootstrap/**", "/css/**", "/js/**", "/images/**", "/imagenes/**").permitAll()
            .requestMatchers("/", "/login", "/usuarios/signup", "/usuarios/save").permitAll()
            
            // Permisos de Administración del sistema
            .requestMatchers("/usuarios/index", "/usuarios/delete/**", "/perfiles/**").hasAuthority("ADMIN")
            
            // Todo lo demás requiere autenticación tradicional
            .anyRequest().authenticated()
        ).formLogin(form -> form
            .loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/", true)
            .permitAll()
        ).logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );
        
        return http.build();
    }
}