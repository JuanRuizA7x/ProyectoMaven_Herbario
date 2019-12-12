/**
 * 
 */
package com.system.engineer.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;
import org.persistenciaHerbario.Registro;

import com.system.engineer.ejb.FamiliaEJBRemote;
import com.system.engineer.ejb.RegistroEJBRemote;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Delegado que permite conectar la capa de negocio con la de presentación
 * @author Juan Ruiz
 * @version 1.0
 */
public class RegistroDelegado {
	
	/**
	 * Instancia que representa el ejb remoto de Familia
	 */
	private RegistroEJBRemote registroEJB;
	
	/**
	 * Permite manejar una unica instancia para el manejo de delegados
	 */
	public static RegistroDelegado registroDelegado = instancia();
	/**
	 * Constructor para conectar con la capa de negocio
	 */
	private RegistroDelegado() {
		
		try {
			registroEJB = (RegistroEJBRemote) new InitialContext().lookup(RegistroEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * devuelve la lista de registros que estan en la base de datos
	 * 
	 * @return todos los registros
	 */
	public List<Registro> listarRegistros() {
		List<Registro> registros = registroEJB.listarRegistros();
		return registros;
	}
	
	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static RegistroDelegado instancia() {

		if (registroDelegado == null) {
			registroDelegado = new RegistroDelegado();
			return registroDelegado;
		}
		return registroDelegado;
	}
	
	/**
	 * Permite insertar un nuevo registro
	 * @param registro
	 * @return
	 */
	public boolean insertarRegistro(Registro registro) {
		try {
			return registroEJB.insertarRegistro(registro) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Permite modificar un registro
	 * @param registro
	 * @return
	 */
	public boolean modificarRegistro(Registro registro) {
		try {
			return registroEJB.modificarRegistro(registro) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
//	/**
//	 * Permite insertar un nuevo genero
//	 * @param familia
//	 * @return
//	 */
//	public boolean registrarGenero(Genero genero) {
//		try {
//			return familiaEJB.insertarGenero(genero) != null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	/**
//	 * Permite modificar un género
//	 * @param genero
//	 * @return
//	 */
//	public boolean modificarGenero(Genero genero) {
//		try {
//			return familiaEJB.insertarGenero(genero) != null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
	
	/**
	 * genera una lista de registros observables
	 * 
	 * @return todos los registros obsevables
	 */
//	public ObservableList<RegistroObservable> listarRegistrosObservables() {
//		List<Registro> registros = listarRegistros();
//		ObservableList<RegistroObservable> registrosObservables = FXCollections.observableArrayList();
//		for (Registro registro : registros) {
//			registrosObservables.add(new RegistroObservable(registro));
//		}
//		return registrosObservables;
//	}
	
	/**
	 * Devuelve la familia con el nombre señalado
	 * @param nombre
	 * @return
	 */
	public Especie buscarEspeciePorNombre(String nombre) {
		return registroEJB.buscarEspeciePorNombre(nombre);
	}
	
//	/**
//	 * genera una lista de generos observables
//	 * 
//	 * @return todos los generos obsevables
//	 */
//	public ObservableList<GeneroObservable> listarGenerosObservables() {
//		List<Genero> generos = listarGeneros();
//		ObservableList<GeneroObservable> generosObservables = FXCollections.observableArrayList();
//		for (Genero genero : generos) {
//			generosObservables.add(new GeneroObservable(genero));
//		}
//		return generosObservables;
//	}
}
