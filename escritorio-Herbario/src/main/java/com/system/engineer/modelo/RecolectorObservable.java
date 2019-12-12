/**
 * 
 */
package com.system.engineer.modelo;

import java.util.Date;

import org.persistenciaHerbario.Recolector;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Shonny
 *
 */
public class RecolectorObservable {
	
	/**
	 * cedula observable de un empleado
	 */
	private StringProperty cedula;
	/**
	 * nombre observable de una persona
	 */
	private StringProperty nombre;
	/**
	 * apellido observable de un empleado
	 */
	private StringProperty apellido;
	/**
	 * email observable de un empleado
	 */
	private StringProperty email;
	/**
	 * clave observable de un empleado
	 */
	private StringProperty contrasenia;
	/**
	 * activo observable de un empleado
	 */
	private SimpleBooleanProperty activo;
	/**
	 * activo observable de un empleado
	 */
	private ObjectProperty<Date> creacion;
	/**
	 * empleado asociado
	 */
	private Recolector recolector;
	
	/**
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param contrasenia
	 * @param activo
	 * @param creacion
	 * @param empleado
	 */
	public RecolectorObservable(Recolector recolector) {
		
		this.recolector = recolector;
		this.cedula = new SimpleStringProperty(recolector.getCedula());
		this.nombre = new SimpleStringProperty(recolector.getNombre());
		this.apellido = new SimpleStringProperty(recolector.getApellido());
		this.email = new SimpleStringProperty(recolector.getEmail());
		this.contrasenia = new SimpleStringProperty(recolector.getContrasenia());
		this.activo = new SimpleBooleanProperty(recolector.getActivo());
		this.creacion = new SimpleObjectProperty<>(recolector.getCreacion());
	}

	/**
	 * @return the cedula
	 */
	public StringProperty getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(StringProperty cedula) {
		this.cedula = cedula;
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
	 * @return the apellido
	 */
	public StringProperty getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(StringProperty apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the email
	 */
	public StringProperty getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(StringProperty email) {
		this.email = email;
	}

	/**
	 * @return the contrasenia
	 */
	public StringProperty getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(StringProperty contrasenia) {
		this.contrasenia = contrasenia;
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
	 * @return the recolector
	 */
	public Recolector getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(Recolector recolector) {
		this.recolector = recolector;
	}
	

	

}
