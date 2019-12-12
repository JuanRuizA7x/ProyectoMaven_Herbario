/**
 * 
 */
package com.system.engineer.modelo;

import java.util.Date;

import org.persistenciaHerbario.Divipola;
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
public class DivipolaObservable {

	/**
	 * id observable de una Divipola
	 */
	private StringProperty id;
	/**
	 * municipio observable de una Divipola
	 */
	private StringProperty municipio;
	/**
	 * Departamento observable de una Divipola
	 */
	private StringProperty departamento;
	
	/**
	 * dicvipola asociada
	 */
	private Divipola divipola;
	
	
	/**
	 * @param divipola
	 */
	public DivipolaObservable(Divipola divipola) {
		
		this.divipola = divipola;
		this.id = new SimpleStringProperty(divipola.getId().toString());
		this.municipio = new SimpleStringProperty(divipola.getMunicipio());
		this.departamento = new SimpleStringProperty(divipola.getDepartamento());
	}


	/**
	 * @param id
	 * @param municipio
	 * @param departamento
	 */
	public DivipolaObservable(StringProperty id, StringProperty municipio, StringProperty departamento) {
		super();
		this.id = id;
		this.municipio = municipio;
		this.departamento = departamento;
	}


	public StringProperty getId() {
		return id;
	}


	public void setId(StringProperty id) {
		this.id = id;
	}


	public StringProperty getMunicipio() {
		return municipio;
	}


	public void setMunicipio(StringProperty municipio) {
		this.municipio = municipio;
	}


	public StringProperty getDepartamento() {
		return departamento;
	}


	public void setDepartamento(StringProperty departamento) {
		this.departamento = departamento;
	}


	public Divipola getDivipola() {
		return divipola;
	}


	public void setDivipola(Divipola divipola) {
		this.divipola = divipola;
	}

}

