package itch.tsp.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    private String apellidos;
    private Double salario;
    private String foto = "no-imagen.jpg";
    
    private Integer estado = 1; // 1 = Activo, 0 = Inactivo
    private Boolean activo = true; // Borrado lógico
    
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;
    
    @ManyToOne
    @JoinColumn(name = "id_dep")
    private Departamento departamento;
    
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrato> contratos;
    
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActividadEmpleado> actividades;
    

    @ManyToMany
    @JoinTable(
        name = "empleado_habilidad",
        joinColumns = @JoinColumn(name = "id_empleado"),
        inverseJoinColumns = @JoinColumn(name = "id_habilidad")
    )
    private List<Habilidad> habilidades;

    // Relación OneToOne con la cuenta de acceso (Seguridad)
    @OneToOne(mappedBy = "empleado")
    private Usuario usuario;

    public Empleado() {}

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    
    public List<Contrato> getContratos() { return contratos; }
    
    // SETTER BLINDADO PARA CONTRATOS
    public void setContratos(List<Contrato> contratos) { 
        if (this.contratos == null) {
            this.contratos = contratos;
        } else {
            this.contratos.clear();
            if (contratos != null) {
                this.contratos.addAll(contratos);
            }
        }
    }

    public List<ActividadEmpleado> getActividades() { return actividades; }
    
    // SETTER BLINDADO PARA ACTIVIDADES
    public void setActividades(List<ActividadEmpleado> actividades) { 
        if (this.actividades == null) {
            this.actividades = actividades;
        } else {
            this.actividades.clear();
            if (actividades != null) {
                this.actividades.addAll(actividades);
            }
        }
    }

    public List<Habilidad> getHabilidades() { return habilidades; }
    public void setHabilidades(List<Habilidad> habilidades) { this.habilidades = habilidades; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}