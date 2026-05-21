package itch.tsp.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;



@Entity
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombreE", nullable = true, length = 50)
	private String nombreE;
	@Column(name = "apellido", nullable = true, length = 50)
	private String apellido;
	@Column(name = "salario", nullable = true)
	private Double salario;
	@Column(name = "fechaIngreso", nullable = true)
	private Date fechaIngreso;
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Contrato> contratos;
	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "EmpleadoHabilidad",
			joinColumns = @JoinColumn(name = "empleado_id"),
			inverseJoinColumns = @JoinColumn(name = "habilidad_id")
			)
	private List<Habilidad> habilidades;
	@Column(name = "activo", nullable = true)
	private Integer activo;
	@Column(name = "foto", nullable = true, length = 250)
	private String foto="no-imagen.jpg";
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombreE() {
		return nombreE;
	}
	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	public List<Habilidad> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombreE=" + nombreE + ", apellido=" + apellido + ", salario=" + salario
				+ ", fechaIngreso=" + fechaIngreso + ", contratos=" + contratos + ", departamento=" + departamento
				+ ", habilidades=" + habilidades + ", activo=" + activo + ", foto=" + foto + "]";
	}
			
}
