/**
 * 
 */
package com.system.engineer.modelo;

import java.util.Date;

import org.persistenciaHerbario.Empleado;
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
public class RegistroObservable {

	/**
	 * id observable de un registro
	 */
	private StringProperty id;
	/**
	 * descripcio observable de un registro
	 */
	private StringProperty descripcion;
	/**
	 * Usuario observable de un registro
	 */
	private UsuarioObservable usuario;
	
	/**
	 * Opciones observable de un registro
	 */
//	private OpcionesObservable opciones;
	
	/**
	 * Opciones observable de un registro
	 */
	private StringProperty opciones;
	/**
	 * Creacion observable de un registro
	 */
	private ObjectProperty<Date> creacion;
	/**
	 * Epecie observable de un registro
	 */
	private EspecieObservable especie;
	
	/**
	 * Registro asociado
	 */
	private Registro registro;
	
	/**
	 * @param Registro
	 */
//	public RegistroObservable(Registro registro) {
//		
//		this.registro = registro;
//		this.id = new SimpleStringProperty(registro.getId().toString());
//		this.descripcion = new SimpleStringProperty(registro.getDescripcion());
//		this.usuario = new UsuarioObservable(registro.getUsuario(), null);
//		this.opciones = new SimpleStringProperty (registro.getOpciones().toString());
//		this.especie = new EspecieObservable(registro.getEspecie());
//		this.creacion = new SimpleObjectProperty<Date>(registro.getCreateAt());
//	}

	public StringProperty getId() {
		return id;
	}

	public void setId(StringProperty id) {
		this.id = id;
	}

	public StringProperty getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

	public UsuarioObservable getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioObservable usuario) {
		this.usuario = usuario;
	}

	public StringProperty getOpciones() {
		return opciones;
	}

	public void setOpciones(StringProperty opciones) {
		this.opciones = opciones;
	}

	public ObjectProperty<Date> getCreacion() {
		return creacion;
	}

	public void setCreacion(ObjectProperty<Date> creacion) {
		this.creacion = creacion;
	}

	public EspecieObservable getEspecie() {
		return especie;
	}

	public void setEspecie(EspecieObservable especie) {
		this.especie = especie;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	
}

