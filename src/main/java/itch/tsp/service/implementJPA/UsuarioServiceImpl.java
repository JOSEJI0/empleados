package itch.tsp.service.implementJPA;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import itch.tsp.model.Perfil;
import itch.tsp.model.Usuario;
import itch.tsp.repository.PerfilRepository;
import itch.tsp.repository.UsuarioRepository;
import itch.tsp.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired 
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PerfilRepository perfilRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void guardarU(Usuario usuario) {
        usuario.setEstatus(1);
        usuario.setFechaRegistro(new Date());
        
        String pwPlana = usuario.getPassword();        
        usuario.setPassword(passwordEncoder.encode(pwPlana));	 
        
        Perfil perfilUsuario = perfilRepository.findById(2).orElse(null); 
        if(perfilUsuario != null) {
            usuario.setPerfiles(Arrays.asList(perfilUsuario)); 
        }
        usuarioRepository.save(usuario); 
    }
    
    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEstatus() == 1) 
                .toList();
    }

    @Override
    public void eliminarU(Integer idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
        
        if (optional.isPresent()) {
            Usuario temp = optional.get();
            temp.setEstatus(0);
            usuarioRepository.save(temp);
        }
    }
}