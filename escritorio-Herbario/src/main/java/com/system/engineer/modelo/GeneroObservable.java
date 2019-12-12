/**
 * 
 */
package com.system.engineer.modelo;

import java.util.Date;

import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Permite transformar un Genero en formato observable
 * @author Juan Ruiz
 *
 */
public class GeneroObservable {
	
	/**
	 * Id observable de un Género
	 */
	private StringProperty id;
	/**
	 * Nombre observable de un Género
	 */
	private StringProperty nombre;
	/**
	 * Descripción observable de un Género
	 */
	private StringProperty descripcion;
	/**
	 * Estado observable de un Género
	 */
	private SimpleBooleanProperty activo;
	/**
	 * Familia observable de un Género
	 */
	private StringProperty familia;
	/**
	 * Fecha de creación observable de un Género
	 */
	private ObjectProperty<Date> creacion;
	/**
	 * Género asociada
	 */
	private Genero genero;
	
	/**
	 * @param genero
	 */
	public GeneroObservable(Genero genero) {
		this.genero = genero;
		this.id = new SimpleStringProperty(genero.getId().toString());
		this.nombre = new SimpleStringProperty(genero.getNombre());
		this.descripcion = new SimpleStringProperty(genero.getDescripcion());
		this.activo = new SimpleBooleanProperty(genero.getActivo());
		System.out.println("Listado desde GeneroObservable");
		System.out.println(genero.toString());
		this.familia = new SimpleStringProperty("" + genero.getFamilia().getNombre());
		this.creacion = new SimpleObjectProperty<>(genero.getCreateAt());
	}

	/**
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param activo
	 * @param familia
	 * @param creacion
	 */
	public GeneroObservable(String id, String nombre, String descripcion,
			Boolean activo, Familia familia, Date creacion) {
		this.id = new SimpleStringProperty(id);
		this.nombre = new SimpleStringProperty(nombre);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.activo = new SimpleBooleanProperty(activo);
		this.familia = new SimpleStringProperty(familia.getNombre());
		this.creacion = new SimpleObjectProperty<>(creacion);
	}

	/**
	 * @return the id
	 */
	public StringProperty getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(StringProperty id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public StringProperty getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public StringProperty getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the activo
	 */
	public SimpleBooleanProperty getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(SimpleBooleanProperty activo) {
		this.activo = activo;
	}

	/**
	 * @return the familia
	 */
	public StringProperty getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(StringProperty familia) {
		this.familia = familia;
	}

	/**
	 * @return the creacion
	 */
	public ObjectProperty<Date> getCreacion() {
		return creacion;
	}

	/**
	 * @param creacion the creacion to set
	 */
	public void setCreacion(ObjectProperty<Date> creacion) {
		this.creacion = creacion;
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
	
}
