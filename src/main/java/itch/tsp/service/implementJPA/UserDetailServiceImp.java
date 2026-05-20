package itch.tsp.service.implementJPA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import itch.tsp.model.Perfil;
import itch.tsp.model.Usuario;
import itch.tsp.repository.UsuarioRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: "));
		
		List<GrantedAuthority> grantedauthorities = new ArrayList<>();
		for(Perfil p: user.getPerfiles()) {
			grantedauthorities.add(() -> p.getPerfil());

		}
		
		return new User(
				user.getUsername(),
				user.getPassword(),
				grantedauthorities
				);
		}
}
