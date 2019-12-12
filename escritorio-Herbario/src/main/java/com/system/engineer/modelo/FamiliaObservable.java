/**
 * 
 */
package com.system.engineer.modelo;

import java.util.Date;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Familia;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Permite transformar un Empleado en formato observable
 * 
 * @author Juank
 *
 */
public class FamiliaObservable {

	/**
	 * id observable de una Familia
	 */
	private StringProperty id;
	/**
	 * nombre observable de una familia
	 */
	private StringProperty nombre;
	/**
	 * Descripcion observable de una Familia
	 */
	private StringProperty descripcion;
	/**
	 * activo observable de una Familia
	 */
	private SimpleBooleanProperty activo;
	/**
	 * Fecha de creación observable de una Familia
	 */
	private ObjectProperty<Date> creacion;
	/**
	 * familia asociada
	 */
	private Familia familia;
	
	
	/**
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param activo
	 * @param creacion
	 * @param familia
	 */
	public FamiliaObservable(Familia familia) {
		
		this.familia = familia;
		this.id = new SimpleStringProperty(familia.getId().toString());
		this.nombre = new SimpleStringProperty(familia.getNombre());
		this.descripcion = new SimpleStringProperty(familia.getDescripcion());
		this.activo = new SimpleBooleanProperty(familia.getActivo());
		this.creacion = new SimpleObjectProperty<>(familia.getCreateAt());
	}

	/**
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param activo
	 * @param creacion
	 * @param familia
	 */
	public FamiliaObservable(String id, String nombre, String descripcion, Boolean activo, Date creacion) {
		this.id = new SimpleStringProperty(id);
		this.nombre = new SimpleStringProperty(nombre);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.activo = new SimpleBooleanProperty(activo);
		this.creacion = new SimpleObjectProperty<>(creacion);
	}

	public StringProperty getId() {
		return id;
	}

	public void setId(StringProperty id) {
		this.id = id;
	}

	public StringProperty getNombre() {
		return nombre;
	}

	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public StringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

	public SimpleBooleanProperty getActivo() {
		return activo;
	}

	public void setActivo(SimpleBooleanProperty activo) {
		this.activo = activo;
	}

	public ObjectProperty<Date> getCreacion() {
		return creacion;
	}

	public void setCreacion(ObjectProperty<Date> creacion) {
		this.creacion = creacion;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}


}
