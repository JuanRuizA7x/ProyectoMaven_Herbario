/**
 * 
 */
package com.system.engineer.modelo;

import java.util.Date;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Usuario;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Permite transformar un Empleado en formato observable
 * 
 * @author Shonny
 *
 */
public class EmpleadoObservable {

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
	 * salario observable de un empleado
	 */
	private SimpleDoubleProperty salario;
	/**
	 * activo observable de un empleado
	 */
	private ObjectProperty<Date> creacion;
	/**
	 * empleado asociado
	 */
	private Empleado empleado;
	
	
	/**
	 * @param empleado
	 */
	public EmpleadoObservable(Empleado empleado) {
		
		this.empleado = empleado;
		this.cedula = new SimpleStringProperty(empleado.getCedula());
		this.nombre = new SimpleStringProperty(empleado.getNombre());
		this.apellido = new SimpleStringProperty(empleado.getApellido());
		this.email = new SimpleStringProperty(empleado.getEmail());
		this.contrasenia = new SimpleStringProperty(empleado.getContrasenia());
		this.salario = new SimpleDoubleProperty(empleado.getSalario());
		this.activo = new SimpleBooleanProperty(empleado.getActivo());
		this.creacion = new SimpleObjectProperty<>(empleado.getCreacion());
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
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the salario
	 */
	public SimpleDoubleProperty getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(SimpleDoubleProperty salario) {
		this.salario = salario;
	}
	

}
