package org.persistenciaHerbario;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Registro
 * 
 * @author Shonny
 */
@Entity
@NamedQueries({ @NamedQuery(name = Registro.LISTAR_TODO_REGISTRO, query = "SELECT r FROM Registro r"),
		@NamedQuery(name = Registro.LISTAR_POR_OPCION_REGISTRO, query = "SELECT r FROM Registro r WHERE r.especie.genero.activo = :activoGen and r.especie.genero.familia.activo = :activoFam and r.opciones = :opciones"),
		@NamedQuery(name = Registro.LISTAR_POR_GENERO_REGISTRO, query = "SELECT r FROM Registro r WHERE r.especie.genero.activo = :activoGen and r.especie.genero.familia.activo = :activoFam and r.especie.genero = :genero"),
		@NamedQuery(name = Registro.LISTAR_POR_FAMILIA_REGISTRO, query = "SELECT r FROM Registro r WHERE r.especie.genero.activo = :activoGen and r.especie.genero.familia.activo = :activoFam and r.especie.genero.familia = :familia"),
		// @NamedQuery(name = Registro.LISTAR_USUARIOS_POR_REGISTROS, query = "SELECT
		// usuario.cedula, registro.id FROM Usuario usuario LEFT JOIN usuario.registros
		// registro"),
		@NamedQuery(name = Registro.LISTAR_POR_USUARIO, query = "SELECT r FROM Registro r WHERE r.especie.genero.activo = :activoGen and r.especie.genero.familia.activo = :activoFam and r.usuario.cedula = :cedula and r.opciones = :opciones"),
		@NamedQuery(name = Registro.OBTENER_NOMBRE_REGISTRO, query = "SELECT r FROM Registro r WHERE r.especie.nombre=:nombre"),
		@NamedQuery(name = Registro.LISTA_RECOLECTORES_REGISTRO, query = "SELECT DISTINCT registro.usuario from Registro registro WHERE TYPE(registro.usuario) = Recolector"),
		@NamedQuery(name = Registro.LISTA_REGISTRO_FECHA, query = "SELECT registro.id, registro.especie.genero.nombre, registro.especie.id, registro.usuario.cedula, registro.usuario.email FROM Registro registro WHERE registro.creacion = :creacionRegistro"),
		@NamedQuery(name = Registro.LISTA_REGISTRO_FECHA_DTO, query = "SELECT new DTO.ConsultaPunto10DTO(registro.id, registro.especie.genero.nombre, registro.especie.id, registro.usuario.cedula, registro.usuario.email ) FROM Registro registro WHERE registro.creacion = :creacionRegistro"),
		@NamedQuery(name = Registro.CONTAR_USUARIO_REG_ACEPTADO, query = "SELECT CAST(registro.creacion AS date) fechaCreacion, COUNT(DISTINCT registro.usuario) FROM Registro registro WHERE registro.opciones=org.persistenciaHerbario.Opciones.AGREGADA GROUP BY fechaCreacion"),
		@NamedQuery(name = Registro.LISTAR_NUMERO_REG_EMPLEADO, query = "SELECT new DTO.ConsultaPunto4DTO(registro.usuario.cedula, COUNT(registro)) FROM Registro registro WHERE TYPE(registro.usuario) = Empleado OR TYPE(registro.usuario) = Administrador GROUP BY registro.usuario.cedula") })
public class Registro implements Serializable {
	/**
	 * 
	 */
	public static final String LISTAR_TODO_REGISTRO = "ListarTodoRegistro";
	/**
	 * 
	 */
	public static final String LISTAR_POR_USUARIO = "ListaraAceptadasPorUsuario";
	/**
	 * 
	 */
	public static final String LISTAR_POR_OPCION_REGISTRO = "ListarRegistrosPorOpcion";
	/**
	 * 
	 */
	public static final String LISTAR_POR_GENERO_REGISTRO = "ListarRegistrosPorGenero";
	/**
	 * 
	 */
	public static final String LISTAR_POR_FAMILIA_REGISTRO = "ListarRegistrosPorFamilia";
	/**
	 *
	 */
	public static final String OBTENER_NOMBRE_REGISTRO = "ObtenerNombreRegistro";
	/**
	 * 
	 */
	public static final String LISTA_RECOLECTORES_REGISTRO = "ListaRecolectoresRegistro";
	/**
	 * 
	 */
	public static final String LISTA_REGISTRO_FECHA = "ListarRegistroPorFecha";
	/**
	 * 
	 */
	public static final String LISTA_REGISTRO_FECHA_DTO = "ListarRegistroPorFechaDTO";
	/**
	 * 
	 */
	public static final String CONTAR_USUARIO_REG_ACEPTADO = "ContarUsuarioRegistroAceptado";
	/**
	 * 
	 */
	public static final String LISTAR_NUMERO_REG_EMPLEADO = "ListarNumeroRegistrosEmpleado";

	/**
	 * 
	 */
	@Id
	@Column(name = "id_registro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 
	 */
	@Column(name = "descripcion", length = 255)
	private String descripcion;
	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	/**
	 * 
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "especie", unique = true)
	private Especie especie;
	/**
	 * 
	 */
	@Column(name = "opciones", nullable = false)
	@Enumerated(EnumType.STRING)
	private Opciones opciones;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creacion")
	private Date creacion;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Registro() {
		super();
	}

	/**
	 * @param id
	 * @param descripcion
	 * @param usuario
	 * @param especie
	 * @param opciones
	 * @param creacion
	 */
	public Registro(Integer id, String descripcion, Usuario usuario, Especie especie, Opciones opciones,
			Date creacion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.especie = especie;
		this.opciones = opciones;
		this.creacion = creacion;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the especie
	 */
	public Especie getEspecie() {
		return especie;
	}

	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	/**
	 * @return the opciones
	 */
	public Opciones getOpciones() {
		return opciones;
	}

	/**
	 * @param opciones the opciones to set
	 */
	public void setOpciones(Opciones opciones) {
		this.opciones = opciones;
	}

	/**
	 * @return the createAt
	 */
	public Date getCreateAt() {
		return creacion;
	}

	/**
	 * @param createAt the createAt to set
	 */
	public void setCreateAt(Date createAt) {
		this.creacion = createAt;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Registro [id=" + id + ", descripcion=" + descripcion + ", usuario=" + usuario + ", especie=" + especie
				+ ", opciones=" + opciones + ", creacion=" + creacion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
