package org.persistenciaHerbario;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Familia
 * 
 * @author Shonny
 */
@Entity
@NamedQueries({ @NamedQuery(name = Familia.LISTAR_TODO_FAMILIA, query = "SELECT f FROM Familia f"),
		@NamedQuery(name = Familia.LISTAR_POR_NOMBRE_FAMILIA, query = "SELECT f FROM Familia f WHERE f.nombre = :nombre"),
		// @NamedQuery(name = Familia.LISTAR_ESPECIES_POR_FAMILIA, query = "SELECT e
		// FROM Familia f JOIN f.genero g JOIN g.especie e WHERE f.id = :id"),
		@NamedQuery(name = Familia.CONTAR_FAMILIAS, query = "SELECT COUNT(familia) FROM Familia familia"),
		@NamedQuery(name = Familia.OBTENER_NOMBRE_FAMILIA, query = "SELECT f FROM Familia f WHERE f.nombre=:nombre"),
		@NamedQuery(name = Familia.OBTENER_ID_FAMILIA, query = "SELECT f FROM Familia f WHERE f.id=:id")})
// @NamedQuery(name = Familia.PRUEBA2, query = "SELECT f.id, COUNT(DISTINCT
// e.id) FROM Familia f JOIN f.genero g JOIN g.especie e GROUP BY f.id ORDER BY
// COUNT(DISTINCT e.id) DESC")})

public class Familia implements Serializable {

	/**
	 * referencia para listar todas las familias
	 */
	public static final String LISTAR_TODO_FAMILIA = "ListarLasFamilias";
	/**
	 * referencia para listar familia por nombre
	 */
	public static final String LISTAR_POR_NOMBRE_FAMILIA = "ListarFamiliasPorNombre";
	/**
	 * referencia para listar especies por familia
	 */
	// public static final String LISTAR_ESPECIES_POR_FAMILIA =
	// "ListarEspeciesPorFamilia";
	/**
	 * referencia para contar las familias registradas
	 */
	public static final String CONTAR_FAMILIAS = "ContarFamilias";
	/**
	 * referencia para contar las familias registradas
	 */
	public static final String OBTENER_NOMBRE_FAMILIA = "ObtenerNombreFamilia";
	/**
	 * referencia para buscar familia por id
	 */
	public static final String OBTENER_ID_FAMILIA = "ObtenerIdFamilia";
	/**
	 * referencia para contar las familias registradas
	 */
	// public static final String PRUEBA2 = "Prueba2";
	/**
	 * Id de la familia
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "familia_id", updatable = false, nullable = false)
	private Integer id;
	/**
	 * Nombre de la familia
	 */
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	/**
	 * Descripcion de la familia
	 */
	@Column(name = "descripcion", nullable = false, length = 255)
	private String descripcion;
	/**
	 * Estado de la familia
	 */
	@Column(name = "activo", nullable = false)
	private Boolean activo;
	/**
	 * Generos de la familia
	 */
	// @OneToMany(mappedBy="familia", cascade = CascadeType.ALL)
	// @JoinColumn(name="genero")
	// private List<Genero> genero;
	/**
	 * Fecha de creacion de la familia
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creacion")
	private Date creacion;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Familia() {

	}

	/**
	 * @param nombre
	 * @param descripcion
	 * @param activo
	 * @param genero
	 * @param creacion
	 */
	public Familia(String nombre, String descripcion, Boolean activo, Date creacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
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
		Familia other = (Familia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
//	@Override
//	public String toString() {
//		// return "Familia [id=" + id + ", nombre=" + nombre + ", descripcion=" +
//		// descripcion + ", activo=" + activo
//		// + ", creacion=" + creacion + "]";
//		return nombre;
//	}
	
	

}
