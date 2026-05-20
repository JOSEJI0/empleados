package itch.tsp.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;
    @Column(name = "email", nullable = true, unique = true, length = 100)
    private String email; 
    @Column(name = "username", nullable = true, unique = true, length = 45)
    private String username;
    @Column(name = "password", nullable = true, length = 100)
    private String password;
    @Column(name = "estatus", nullable = true)
    private Integer estatus;
    @Column(name = "fechaRegistro", nullable = true)
    private Date fechaRegistro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsuarioPerfil",
               joinColumns = @JoinColumn(name = "idUsuario"),
               inverseJoinColumns = @JoinColumn(name = "idPerfil")) 
    private List<Perfil> perfiles;

    // Getters y Setters
    
    public Integer getId() {
		return id;
	}
    
    public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getEstatus() {
		return estatus;
	}
	
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	// Getters y Setters para Perfiles
    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", estatus=" + estatus + "]";
	}
	
	
}