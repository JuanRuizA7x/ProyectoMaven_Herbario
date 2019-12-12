package org.persistenciaHerbario;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Imagen
 * 
 * @author Shonny
 */
@Entity

public class Imagen implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_imagen")
	private String id;

	@Column(name = "imagen", nullable = false)
	private String imagen;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "especie", nullable = false)
	private Especie especie;

	private static final long serialVersionUID = 1L;

	public Imagen() {

	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
