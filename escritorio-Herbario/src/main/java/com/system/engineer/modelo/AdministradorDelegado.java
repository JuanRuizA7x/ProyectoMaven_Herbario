/**
 * 
 */
package com.system.engineer.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.AdminEJBRemote;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 * Delegado que permite conectar la capa de negocio con la de presentación
 * @author Shonny
 *
 */
public class AdministradorDelegado {
	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private AdminEJBRemote adminEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static AdministradorDelegado administradorDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private AdministradorDelegado() { 
		try {
			adminEJB = (AdminEJBRemote) new InitialContext().lookup(AdminEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static AdministradorDelegado instancia() {

		if (administradorDelegado == null) {
			administradorDelegado = new AdministradorDelegado();
			return administradorDelegado;
		}
		return administradorDelegado;
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */
	public boolean registrarEmpleado(Empleado empleado) {
		try {
			return adminEJB.insertarEmpleado(empleado) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificarEmpleado(Empleado empleado) {
		try {
			return adminEJB.modificarEmpleado(empleado) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Empleado> listarEmpleado() {
		return adminEJB.listarEmpleados();
	}

	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public boolean eliminarEmpleado(Empleado empleado) {
		try {
			return adminEJB.eliminarPersona(empleado.getCedula()) != null;
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<EmpleadoObservable> listarEmpleadosObservables() {
		List<Empleado> empleados = listarEmpleado();
		ObservableList<EmpleadoObservable> empleadosObservables = FXCollections.observableArrayList();
		for (Empleado persona : empleados) {
			empleadosObservables.add(new EmpleadoObservable(persona));
		}
		return empleadosObservables;
	}
	
	/**
	 * pemite registar un nuevo recolector
	 * 
	 * @param recolector empleado a agregar
	 * @return devuelve true si el recolector fue eliminado
	 */
	public boolean registrarRecolector(Recolector recolector) {
		try {
			return adminEJB.insertarRecolector(recolector) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificarRecolector(Recolector recolector) {
		try {
			return adminEJB.modificarRecolector(recolector) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 */
	public List<Recolector> listarRecolector() {
		return adminEJB.listarRecolector();
	}
	
	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public boolean eliminarRecolector(Recolector recolector) {
		try {
			return adminEJB.eliminarRecolector(recolector.getCedula()) != null;
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<RecolectorObservable> listarRecolectoresObservables() {
		List<Recolector> recolectores = listarRecolector();
		ObservableList<RecolectorObservable> recolectoresObservables = FXCollections.observableArrayList();
		for (Recolector reco : recolectores) {
			recolectoresObservables.add(new RecolectorObservable(reco));
		}
		return recolectoresObservables;
	}
	
	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */
	public UsuarioObservable login(String cedula) {
		Usuario usuario;
		try {
			usuario = adminEJB.buscarUsuarioId(cedula);
			Object tipo = usuario.getClass();

			UsuarioObservable usuarioObservable = new UsuarioObservable(usuario,tipo);
			return usuarioObservable ;
		} catch (ElementoNoEncontradoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

	}
	
	
	
	
	
	

}
