/**
 * 
 */
package com.system.engineer.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.persistenciaHerbario.Especie;

import com.system.engineer.ejb.RegistroEJBRemote;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Delegado que permite conectar la capa de negocio con la de presentacion
 * 
 * @author Juank
 *
 */
public class EspecieDelegado {
	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private RegistroEJBRemote especieEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static EspecieDelegado especieDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private EspecieDelegado() {
		try {
			especieEJB = (RegistroEJBRemote) new InitialContext().lookup(RegistroEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static EspecieDelegado instancia() {

		if (especieDelegado == null) {
			especieDelegado = new EspecieDelegado();
			return especieDelegado;
		}
		return especieDelegado;
	}

	/**
	 * pemite registar una nueva especie
	 * 
	 * @param especie a agregar
	 * @return devuelve true si la especie fue agregada
	 */
	public boolean registrarEspecie(Especie especie) {
		try {
			return especieEJB.insertarEspecie(especie) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * pemite editar una especie
	 * 
	 * @param especie a editar
	 * @return devuelve true si la especie fue editada
	 */
	public boolean modificarEspecie(Especie especie) {
		try {
			return especieEJB.modificarEspecie(especie) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * devuvel la lista de especies que estan en la base de datos
	 * 
	 * @return todos las especies
	 */
	public List<Especie> listarEspecies() {
		return especieEJB.listarEspecies();
	}

	/**
	 * genera una lista de especies observables
	 * 
	 * @return todos las especies obsevables
	 */
	public ObservableList<EspecieObservable> listarEspeciesObservables() {
		List<Especie> especies = listarEspecies();
		ObservableList<EspecieObservable> especiesObservables = FXCollections.observableArrayList();
		for (Especie especie : especies) {
			especiesObservables.add(new EspecieObservable(especie));
		}
		return especiesObservables;
	}

}
