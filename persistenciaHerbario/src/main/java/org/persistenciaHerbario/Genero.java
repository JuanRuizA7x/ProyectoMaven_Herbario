package org.persistenciaHerbario;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Genero
 * 
 * @author Shonny
 */
@Entity
@NamedQueries({ @NamedQuery(name = Genero.LISTAR_TODO_GENERO, query = "SELECT g FROM Genero g WHERE g.familia.activo = :activo"),
		@NamedQuery(name = Genero.LISTAR_GENERO_POR_NOMBRE, query = "SELECT g FROM Genero g WHERE g.nombre = :nombre"),
		// @NamedQuery(name = Genero.LISTAR_ESPECIES_POR_GENERO, query = "SELECT e FROM
		// Genero g, IN(g.especie) e WHERE g.id = :id"),
		@NamedQuery(name = Genero.OBTENER_NOMBRE_GENERO, query = "SELECT g FROM Genero g WHERE g.nombre=:nombre") })

public class Genero implements Serializable {

	/**
	 * referencia para listar todos los generos
	 */
	public static final String LISTAR_TODO_GENERO = "ListarLosGeneros";
	/**
	 * referencia para listar genero por nombre
	 */
	public static final String LISTAR_GENERO_POR_NOMBRE = "ListarGenerosPorNombre";
	/**
	 * referencia para listar especies por genero
	 */
	// public static final String LISTAR_ESPECIES_POR_GENERO =
	// "ListarEspeciesPorGenero";
	/**
	 * referencia para obtener el genero dado un nombre
	 */
	public static final String OBTENER_NOMBRE_GENERO = "ObtenerNombreGenero";
	/**
	 * id del Genero
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genero_id", updatable = false, nullable = false)
	private Integer id;
	/**
	 * Nombre del Genero
	 */
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	/**
	 * Descripcion del Genero
	 */
	@Column(name = "descripcion", nullable = false, length = 255)
	private String descripcion;
	/**
	 * Estado del Genero
	 */
	@Column(name = "activo", nullable = false)
	private Boolean activo;
	/**
	 * Familia a la que pertenece el Genero
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "familia", nullable = false)
	private Familia familia;
	/**
	 * Especies del Genero
	 */
	// @OneToMany(mappedBy="genero", cascade = CascadeType.ALL)
	// @JoinColumn(name="especie")
	// private List<Especie> especie;
	/**
	 * Fecha de creacion del genero
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creacion")
	private Date creacion;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Genero() {

	}

	/**
	 * @param nombre
	 * @param descripcion
	 * @param activo
	 * @param familia
	 * @param especie
	 * @param creacion
	 */
	public Genero(String nombre, String descripcion, Boolean activo, Familia familia, Date creacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
		this.familia = familia;
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
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Genero [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo
				+ ", familia=" + familia + ", creacion=" + creacion + "]";
	}

}