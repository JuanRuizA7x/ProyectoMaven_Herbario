/**
 * 
 */
package com.system.engineer.modelo;

import java.util.Date;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Registro;
import org.persistenciaHerbario.Usuario;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Permite transformar un Registro en formato observable
 * 
 * @author Juank
 *
 */
public class EspecieObservable {

	/**
	 * id observable de una especie
	 */
	private StringProperty id;
	/**
	 * nombre observable de una especie
	 */
	private StringProperty nombre;
	/**
	 * latitud observable de una especie
	 */
	private StringProperty latitud;
	/**
	 * latitud observable de una especie
	 */
	private StringProperty longitud;
	/**
	 * observaciones observable de una especie
	 */
	private StringProperty observaciones;
	/**
	 * fecha de recoleccion observable de una especie
	 */
	private ObjectProperty<Date> fechaRecolección;
	/**
	 * genero observable de una especie
	 */
	private GeneroObservable genero;
	
	/**
	 * divipola observable de una especie
	 */
	private DivipolaObservable divipola;
	/**
	 * fecha creacion observable de una especie
	 */
	private ObjectProperty<Date> fechaCreacion;

	/**
	 * Especie
	 */
	private Especie especie;
	
	
	/**
	 * @param especie
	 */
	public EspecieObservable(Especie especie) {
		
		this.especie = especie;
		this.id = new SimpleStringProperty(especie.getId().toString());
		this.nombre = new SimpleStringProperty(especie.getNombre());
		this.latitud = new SimpleStringProperty(especie.getLatitud());
		this.longitud = new SimpleStringProperty(especie.getLongitud());
		this.observaciones = new SimpleStringProperty(especie.getObservaciones());
		this.fechaRecolección = new SimpleObjectProperty<Date>(especie.getFechaRecoleccion());
		this.genero = new GeneroObservable(especie.getGenero());
		this.divipola = new DivipolaObservable(especie.getDivipola());
		this.fechaCreacion = new SimpleObjectProperty<Date>(especie.getCreacion());
	}


	/**
	 * @param id
	 * @param nombre
	 * @param latitud
	 * @param longitud
	 * @param observaciones
	 * @param fechaRecollecion
	 * @param genero
	 * @param divipola
	 * @param fechaCreacion
	 */
	public EspecieObservable(StringProperty id, StringProperty nombre, StringProperty latitud, StringProperty longitud,
			StringProperty observaciones, ObjectProperty<Date> fechaRecolección, GeneroObservable genero,
			DivipolaObservable divipola, ObjectProperty<Date> fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.observaciones = observaciones;
		this.fechaRecolección = fechaRecolección;
		this.genero = genero;
		this.divipola = divipola;
		this.fechaCreacion = fechaCreacion;
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


	public StringProperty getLatitud() {
		return latitud;
	}


	public void setLatitud(StringProperty latitud) {
		this.latitud = latitud;
	}


	public StringProperty getLongitud() {
		return longitud;
	}


	public void setLongitud(StringProperty longitud) {
		this.longitud = longitud;
	}


	public StringProperty getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(StringProperty observaciones) {
		this.observaciones = observaciones;
	}


	public ObjectProperty<Date> getFechaRecolección() {
		return fechaRecolección;
	}


	public void setFechaRecolección(ObjectProperty<Date> fechaRecolección) {
		this.fechaRecolección = fechaRecolección;
	}


	public GeneroObservable getGenero() {
		return genero;
	}


	public void setGenero(GeneroObservable genero) {
		this.genero = genero;
	}


	public DivipolaObservable getDivipola() {
		return divipola;
	}


	public void setDivipola(DivipolaObservable divipola) {
		this.divipola = divipola;
	}


	public ObjectProperty<Date> getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(ObjectProperty<Date> fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public Especie getEspecie() {
		return especie;
	}


	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
	

}


