/**
 * 
 */
package org.Web.Herbario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import com.system.engineer.ejb.AdminEJB;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * @author Shonny
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("empleadoBean")
@ApplicationScoped
public class EmpleadoBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Empleado empleado;
	private List<Empleado> empleados;
	/**
	 * Referencia al EJB de negocio para admin
	 */
	@EJB
	private AdminEJB adminEJB;
	
	@PostConstruct
	public void init() {
		empleado= new Empleado();
		empleado.setActivo(true);
		
	}
	public String registrar() {
		try {
			empleado.setCreacion(new Date());
			adminEJB.insertarEmpleado(empleado);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "Listo";
		} catch (ElementoRepetidoException e) {
			// e.printStackTrace();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	/**
	 * Permite activar o desactivar un empleado
	 * 
	 * @param id
	 * @return
	 */
	public void cambiarEstado(String cedula) {
		try {
			adminEJB.eliminarPersona(cedula);
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
		}
	}
	
	
	public String actualizar(Empleado emp) {
		try {
			adminEJB.modificarEmpleado(emp);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado actualizada correctamente",
					"Empleado actualizada correctamente");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_empleado?faces-redirect=true";
		} catch (ElementoRepetidoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	
	/**
	 * Permite editar los datos de un empleado
	 * 
	 * @param id
	 * @return
	 */
	public String editar(String cedula) {
		Empleado emp = null;
		String redireccion = "";
		try {
			emp = adminEJB.buscarEmpleadoId(cedula);
			if (emp != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("emple", emp);
				redireccion = "editar_empleado?faces-redirect=true";
			}
			return redireccion;
		} catch (ElementoRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	
	
	/**
	 * @return the empleados
	 */
	public List<Empleado> getEmpleados() {
		empleados=adminEJB.listarEmpleados();
		return empleados;
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
	 * @return the adminEJB
	 */
	public AdminEJB getAdminEJB() {
		return adminEJB;
	}
	/**
	 * @param adminEJB the adminEJB to set
	 */
	public void setAdminEJB(AdminEJB adminEJB) {
		this.adminEJB = adminEJB;
	}



}
