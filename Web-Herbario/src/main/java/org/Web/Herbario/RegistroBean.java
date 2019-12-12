/**
 * 
 */
package org.Web.Herbario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.swing.JOptionPane;

import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;
import org.persistenciaHerbario.Opciones;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Registro;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.RegistroEJB;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * @author Shonny
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("registroBean")
@ApplicationScoped
public class RegistroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Registro registro;
	private Especie especie;
	private Usuario user;
	private Genero genero;
	private Familia familia;
	private List<Registro> registros;
	private List<Registro> registrosAceptados;
	private List<Registro> registrosRechazados;
	private List<Registro> registrosPendientes;
	private List<Registro> registrosPorGenero;
	private List<Registro> registrosPorFamilia;

	@EJB
	private RegistroEJB registroEJB;

	/**
	 * Default constructor.
	 */
	public RegistroBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		registro = new Registro();
		especie = new Especie();
	}
	
	/**
	 * @return the registros aceptados
	 */
	public List<Registro> getRegistros() {
		registros = registroEJB.listarRegistros();
		return registros;
	}
	
	/**
	 * @return the registros aceptados
	 */
	public List<Registro> getRegistrosAceptados() {
		registrosAceptados = registroEJB.listarRegistrosAceptados();
		return registrosAceptados;
	}
	
	/**
	 * @return the registros rechazados
	 */
	public List<Registro> getRegistrosRechazados() {
		registrosRechazados = registroEJB.listarRegistrosRechazados();
		return registrosRechazados;
	}
	
	/**
	 * @return the registros pendientes
	 */
	public List<Registro> getRegistrosPendientes() {
		registrosPendientes = registroEJB.listarRegistrosPendientes();
		return registrosPendientes;
	}
	
	/**
	 * @return the registros por genero
	 */
	public List<Registro> getRegistrosPorGenero() {
		registrosPorGenero = registroEJB.listarRegistrosPorGenero(genero);
		return registrosPorGenero;
	}
	
	/**
	 * @return the registros por familia
	 */
	public List<Registro> getRegistrosPorFamilia() {
		registrosPorFamilia = registroEJB.listarRegistrosPorFamilia(familia);
		return registrosPorFamilia;
	}

	/**
	 * @return the registros aceptados por usuario
	 */
	public List<Registro> getRegistrosAceptadosPorUsuario() {
		user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		registrosAceptados = registroEJB.listarRegistrosAceptadosPorUsuario(user.getCedula());
		return registrosAceptados;
	}
	
	/**
	 * @return the registros rechazados por usuario
	 */
	public List<Registro> getRegistrosRechazadosPorUsuario() {
		user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		registrosRechazados = registroEJB.listarRegistrosRechazadosPorUsuario(user.getCedula());
		return registrosRechazados;
	}
	
	/**
	 * @return the registros pendientes por usuario
	 */
	public List<Registro> getRegistrosPendientesPorUsuario() {
		user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		registrosPendientes = registroEJB.listarRegistrosPendientesPorUsuario(user.getCedula());
		return registrosPendientes;
	}
	
	public String registrar() {
		try {
			user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			
			
			if ((user.getClass().equals(Recolector.class))) {
				registro.setOpciones(Opciones.PENDIENTE);
			}else {
				registro.setOpciones(Opciones.AGREGADA);
			}
			
			especie.setCreacion(new Date());
			registro.setCreateAt(new Date());
			
			especie = registroEJB.insertarEspecie(especie);
			registro.setUsuario(user);
			registro.setEspecie(especie);
			registroEJB.insertarRegistro(registro);
			

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "Listo";
		} catch (ElementoNoEncontradoException | ElementoRepetidoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}

	public String registrarEspecie() {
		try {

			registroEJB.insertarEspecie(especie);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "Listo";
		} catch (ElementoRepetidoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	
	/**
	 * Permite aceptar un registro
	 * @param regis
	 */
	public String aceptar(Registro regis) {
		try {
			registroEJB.aceptarRegistro(regis);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_especies_aceptadas";
		} catch (ElementoNoEncontradoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	/**
	 * Permite rechazar un registro
	 * @param regis
	 */
	public String rechazar(Registro regis) {
		try {
			registroEJB.rechazarRegistro(regis);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_especies_rechazadas";
		} catch (ElementoNoEncontradoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	
	/**
	 * Permite aceptar un registro
	 * @param regis
	 */
	public String aceptarAdmin(Registro regis) {
		try {
			registroEJB.aceptarRegistro(regis);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_especies_pendientes";
		} catch (ElementoNoEncontradoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	/**
	 * Permite rechazar un registro
	 * @param regis
	 */
	public String rechazarAdmin(Registro regis) {
		try {
			registroEJB.rechazarRegistro(regis);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_especies_pendientes";
		} catch (ElementoNoEncontradoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	/**
	 * Permite ver los detalles de una especie
	 * 
	 * @param nombre
	 * @return
	 */
	public String verDetalles(String nombre) {
		Registro regis = null;
		String redireccion = "";
		regis = registroEJB.buscarRegistroPorNombre(nombre);
		if (regis != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reg", regis);
			redireccion = 
					"detalle_especie";
		}

		return redireccion;
	}
	
	/**
	 * Permite ver los detalles de una especie para así aceptarla
	 * 
	 * @param nombre
	 * @return
	 */
	public String verDetallesAceptar(String nombre) {
		Registro regis = null;
		String redireccion = "";
		regis = registroEJB.buscarRegistroPorNombre(nombre);
		if (regis != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reg", regis);
			redireccion = 
					"detalle_especie_admin_aceptar";
		}

		return redireccion;
	}
	
	/**
	 * Permite ver los detalles de una especie para rechazarla 
	 * 
	 * @param nombre
	 * @return
	 */
	public String verDetallesRechazar(String nombre) {
		Registro regis = null;
		String redireccion = "";
		regis = registroEJB.buscarRegistroPorNombre(nombre);
		if (regis != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reg", regis);
			redireccion = 
					"detalle_especie_admin_rechazar";
		}

		return redireccion;
	}
	
	/**
	 * Permite ver los detalles de una especie para así aceptarla o rechazarla 
	 * 
	 * @param nombre
	 * @return
	 */
	public String verDetallesAdmin (String nombre) {
		Registro regis = null;
		String redireccion = "";
		regis = registroEJB.buscarRegistroPorNombre(nombre);
		if (regis != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reg", regis);
			redireccion = 
					"detalle_especie_admin";
		}

		return redireccion;
	}
	
	/**
	 * Permite editar los datos de una especie
	 * 
	 * @param nombre
	 * @return
	 */
	public String editar(String nombre) {
		Registro regis = null;
		String redireccion = "";
		regis = registroEJB.buscarRegistroPorNombre(nombre);
		if (regis != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reg", regis);
			redireccion = "editar_especie?faces-redirect=true";
		}

		return redireccion;
	}
	
	/**
	 * Modifica los datos de una especie en la base de datos
	 * 
	 * @param regis
	 * @return
	 */
	public String actualizar(Registro regis) {
		try {
			registroEJB.modificarEspecie(regis.getEspecie());
			registroEJB.modificarRegistro(regis);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Especie actualizada correctamente",
					"Especie actualizada correctamente");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "index?faces-redirect=true";
		} catch (ElementoRepetidoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}

	/**
	 * @return the registro
	 */
	public Registro getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	/**
	 * @return the especie
	 */
	public Especie getEspecie() {
		return especie;
	}

	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	/**
	 * @return the user
	 */
	public Usuario getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Usuario user) {
		this.user = user;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	/**
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

}