/**
 * 
 */
package com.system.engineer.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import com.system.engineer.ejb.FamiliaEJBRemote;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Delegado que permite conectar la capa de negocio con la de presentación
 * @author Juan Ruiz
 * @version 1.0
 */
public class FamiliaDelegado {
	
	/**
	 * Instancia que representa el ejb remoto de Familia
	 */
	private FamiliaEJBRemote familiaEJB;
	
	/**
	 * Permite manejar una unica instancia para el manejo de delegados
	 */
	public static FamiliaDelegado familiaDelegado = instancia();

	/**
	 * Constructor para conectar con la capa de negocio
	 */
	private FamiliaDelegado() {
		
		try {
			familiaEJB = (FamiliaEJBRemote) new InitialContext().lookup(FamiliaEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static FamiliaDelegado instancia() {

		if (familiaDelegado == null) {
			familiaDelegado = new FamiliaDelegado();
			return familiaDelegado;
		}
		return familiaDelegado;
	}
	
	/**
	 * Permite insertar una nueva familia
	 * @param familia
	 * @return
	 */
	public boolean registrarFamilia(Familia familia) {
		try {
			return familiaEJB.insertarFamilia(familia) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Permite modificar una familia
	 * @param familia
	 * @return
	 */
	public boolean modificarFamilia(Familia familia) {
		try {
			return familiaEJB.modificarFamilia(familia) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * permite eliminar a la familia por nombre
	 * @param familia
	 * @return familia si fue eliminada
	 */
	public boolean eliminarFamilia(Familia familia) {
		try {
			return familiaEJB.eliminarFamilia(familia.getNombre()) != null;
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * devuelve la lista de familia que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Familia> listarFamilias() {
		List<Familia> familias = familiaEJB.listarFamilias();
		return familias;
	}
	
	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<FamiliaObservable> listarFamiliasObservables() {
		List<Familia> familias = listarFamilias();
		ObservableList<FamiliaObservable> familiasObservables = FXCollections.observableArrayList();
		for (Familia familia : familias) {
			familiasObservables.add(new FamiliaObservable(familia));
		}
		return familiasObservables;
	}
	
	/**
	 * Devuelve la familia con el nombre señalado
	 * @param nombre
	 * @return
	 */
	public Familia buscarFamiliaPorNombre(String nombre) {
		return familiaEJB.buscarFamiliaPorNombre(nombre);
	}
	
	/**
	 * Permite insertar un nuevo genero
	 * @param familia
	 * @return
	 */
	public boolean registrarGenero(Genero genero) {
		try {
			return familiaEJB.insertarGenero(genero) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Permite modificar un genero
	 * @param genero
	 * @return
	 */
	public boolean modificarGenero(Genero genero) {
		try {
			return familiaEJB.modificarGenero(genero) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * permite eliminar el genero por nombre
	 * @param genero
	 * @return genero si fue eliminar
	 */
	public boolean eliminarGenero(Genero genero) {
		try {
			return familiaEJB.eliminarGenero(genero.getNombre()) != null;
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * devuelve la lista de familia que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Genero> listarGeneros() {
		List<Genero> generos = familiaEJB.listarGeneros();
		for (Genero genero : generos) {
			System.out.println(genero.toString());
		}
		return generos;
	}
	
	/**
	 * genera una lista de generos observables
	 * 
	 * @return todos los generos obsevables
	 */
	public ObservableList<GeneroObservable> listarGenerosObservables() {
		List<Genero> generos = listarGeneros();
		ObservableList<GeneroObservable> generosObservables = FXCollections.observableArrayList();
		for (Genero genero : generos) {
			generosObservables.add(new GeneroObservable(genero));
		}
		return generosObservables;
	}
	
	/**
	 * Devuelve el genero con el nombre señalado
	 * @param nombre
	 * @return
	 */
	public Genero buscarGeneroPorNombre(String nombre) {
		return familiaEJB.buscarGeneroPorNombre(nombre);
	}
}