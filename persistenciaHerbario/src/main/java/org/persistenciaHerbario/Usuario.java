package org.persistenciaHerbario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Usuario
 * 
 * @author Shonny
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({  
	@NamedQuery(name = Usuario.LISTAR_TODO_USUARIO, query = "SELECT u FROM Usuario u"),
	@NamedQuery(name = Usuario.LISTAR_POR_ID_USUARIO, query = "SELECT u FROM Usuario u WHERE u.cedula = :cedula"),
	@NamedQuery(name = Usuario.INICIAR_SESION, query = "SELECT u FROM Usuario u WHERE u.cedula=:cedula and u.contrasenia =:contrasenia and u.activo =:activo"),
	@NamedQuery(name = Usuario.BUSCAR_USUARIO_EMAIL, query = "SELECT u FROM Usuario u WHERE u.email = :email and u.activo=:activo")})
	//@NamedQuery(name = Usuario.LISTAR_REGISTROS_USUARIO, query = "SELECT u FROM Usuario u INNER JOIN u.registros registro WHERE u.cedula = :cedula"),
	//@NamedQuery(name = Usuario.USUARIO_SIN_REGISTRO, query = "SELECT usuario.cedula FROM Usuario usuario WHERE usuario.registros IS EMPTY")})

public class Usuario implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * referencia para listar los usuario
	 */
	public static final String LISTAR_TODO_USUARIO = "ListarLosUsuarios";
	/**
	 * referencia para listar por usuario
	 */
	public static final String LISTAR_POR_ID_USUARIO = "ListarUsuarioPorId";
	/**
	 * referencia para listar por usuario
	 */
	public static final String INICIAR_SESION = "IniciarSesion";
	/**
	 * referencia para listar por usuario
	 */
	public static final String BUSCAR_USUARIO_EMAIL = "BuscarUsuarioEmail";
	/**
	 * referencia para listar por usuario
	 */
	//public static final String LISTAR_REGISTROS_USUARIO = "ListarRegistrosUsuario";
	/**
	 * mostrar los usuarios que no tienen registros
	 */
	//public static final String USUARIO_SIN_REGISTRO = "ListarUsuarioSinRegistro";
	
	/**
	 * 
	 */
	@Id
	@Column(name="cedula_id")
	private String cedula;
	/**
	 * 
	 */
	@Column(name="nombre", nullable=false, length=20)
	private String nombre;
	/**
	 * 
	 */
	@Column(name="apellido", nullable=false, length=20)
	private String apellido;
	/**
	 * 
	 */
	@Column(name="email", nullable=false, length=35)
	private String email;
	/**
	 * 
	 */
	@Column(name="contrasenia", nullable=false, length=20)
	private String contrasenia;
	/**
	 * 
	 */
	@Column(name="activo", nullable=false)
	private Boolean activo;
	/**
	 * 
	 */
	//@OneToMany(mappedBy="usuario",cascade = CascadeType.ALL)
	//@JoinColumn(name = "registros")
	//private List<Registro> registros;
	
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creacion", nullable=false)
	private Date creacion;
	
	/**
	 * 
	 */
	public Usuario() {

	}

	/**
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param contrasenia
	 * @param activo
	 * @param creacion
	 */
	public Usuario(String cedula, String nombre, String apellido, String email, String contrasenia, Boolean activo,
			Date creacion) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasenia = contrasenia;
		this.activo = true;
		this.creacion = new Date();
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}
	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	/**
	 * @return the creacion
	 */
	public Date getCreacion() {
		return creacion;
	}
	/**
	 * @param creacion the creacion to set
	 */
	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", contrasenia=" + contrasenia + ", activo=" + activo + ", creacion="
				+ creacion + "]";
	}
	
}

