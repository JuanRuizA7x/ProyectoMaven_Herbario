package org.persistenciaHerbario;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Especie
 * 
 * @author Shonny
 */
@Entity
@NamedQueries({ @NamedQuery(name = Especie.LISTAR_TODO_ESPECIE, query = "SELECT e FROM Especie e WHERE e.genero.activo = :activoGen and e.genero.familia.activo = :activoFam"),
		@NamedQuery(name = Especie.LISTAR_ESPECIE_ID, query = "SELECT e FROM Especie e WHERE e.id = :id"),
		@NamedQuery(name = Especie.LISTAR_FAMILIA_ESPECIE, query = "SELECT especie.genero.familia FROM Especie especie WHERE especie.id = :idEspecie"),
		@NamedQuery(name = Especie.LISTAR_GENERO_ESPECIE, query = "SELECT especie.genero FROM Especie especie WHERE especie.nombre = :nombreEspecie"),
		@NamedQuery(name = Especie.OBTENER_NOMBRE_ESPECIE, query = "SELECT e FROM Especie e WHERE e.nombre=:nombre"),
		@NamedQuery(name = Especie.LISTAR_DIVIPOLA_ESPECIE, query = "SELECT especie.divipola FROM Especie especie WHERE especie.nombre= :nombreEspecie"),
		@NamedQuery(name = Especie.LISTAR_FAMILIA_MAYOR_ESPECIE, query = "SELECT MAX(flia_mayor.cantidad) FROM Especie especie, (SELECT especie2.genero.familia.id,COUNT(especie2.id)cantidad FROM Especie especie2 GROUP BY especie2.genero.familia.id) flia_mayor WHERE flia_mayor.id=especie.genero.familia.id") })
public class Especie implements Serializable {

	/**
	 * referencia para listar las especies
	 */
	public static final String OBTENER_NOMBRE_ESPECIE = "ListarNombreEspecie";
	/**
	 * referencia para listar las especies
	 */
	public static final String LISTAR_TODO_ESPECIE = "ListarTodoEspecie";
	/**
	 * referencia para listar familia por especie
	 */
	public static final String LISTAR_FAMILIA_ESPECIE = "ListarFamiliaPorEspecie";
	/**
	 * referencia para listar genero por usuario
	 */
	public static final String LISTAR_GENERO_ESPECIE = "ListarGeneroPorEspecie";
	/**
	 * referencia para listar divipola por especie
	 */
	public static final String LISTAR_DIVIPOLA_ESPECIE = "ListarDepartamentoPorEspecie";
	/**
	 * referencia para listar especie por id
	 */
	public static final String LISTAR_ESPECIE_ID = "ListarEspeciePorId";
	/**
	 * Listar familia con mayor especie
	 */
	public static final String LISTAR_FAMILIA_MAYOR_ESPECIE = "ListarFamiliaMayorEspecie";

	@Id
	@Column(name = "id_especie")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 
	 */
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	/**
	 * 
	 */
	// @Column(name="imagen", nullable=false)
	// private String imagen;
	/**
	 * 
	 */
	@Column(name = "latitud", nullable = false)
	private String latitud;
	/**
	 * 
	 */
	@Column(name = "longitud", nullable = false)
	private String longitud;
	/**
	 * 
	 */
	@Column(name = "observaciones", nullable = false, length = 255)
	private String observaciones;
	/**
	 * 
	 */
	@Column(name = "fecha_recoleccion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRecoleccion;
	/**
	 * 
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genero", nullable = false)
	private Genero genero;
	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "divipola", nullable = false)
	private Divipola divipola;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creacion")
	private Date creacion;

//	@OneToOne(mappedBy = "especie")
//	@JoinColumn(name="registro", nullable=false)
//	private Registro registro;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Especie() {

	}

	/**
	 * @param nombre
	 * @param imagen
	 * @param latitud
	 * @param longitud
	 * @param observaciones
	 * @param fechaRecoleccion
	 * @param genero
	 * @param divipola
	 * @param creacion
	 */
	public Especie(String nombre, String latitud, String longitud, String observaciones,
			Date fechaRecoleccion, Genero genero, Divipola divipola, Date creacion) {
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.observaciones = observaciones;
		this.fechaRecoleccion = fechaRecoleccion;
		this.genero = genero;
		this.divipola = divipola;
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
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the fechaRecoleccion
	 */
	public Date getFechaRecoleccion() {
		return fechaRecoleccion;
	}

	/**
	 * @param fechaRecoleccion the fechaRecoleccion to set
	 */
	public void setFechaRecoleccion(Date fechaRecoleccion) {
		this.fechaRecoleccion = fechaRecoleccion;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return the divipola
	 */
	public Divipola getDivipola() {
		return divipola;
	}

	/**
	 * @param divipola the divipola to set
	 */
	public void setDivipola(Divipola divipola) {
		this.divipola = divipola;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Especie [id=" + id + ", nombre=" + nombre + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", observaciones=" + observaciones + ", fechaRecoleccion=" + fechaRecoleccion + ", genero=" + genero
				+ ", divipola=" + divipola + ", creacion=" + creacion + "]";
	}

}
