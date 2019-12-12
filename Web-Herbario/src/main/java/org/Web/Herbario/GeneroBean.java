package org.Web.Herbario;

import java.io.Serializable;
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

import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import com.system.engineer.ejb.FamiliaEJB;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * Permiete gestionar los generos
 * @author Shonny
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("generoBean")
@ApplicationScoped
public class GeneroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Genero genero;
	private List<Genero> generos;

	/**
	 * iNSTANCIA que permite acceder a las operaciones de negocio
	 */
	@EJB
	private FamiliaEJB familiaEJB;

	@PostConstruct
	public void init() {
		genero = new Genero();
		genero.setActivo(true);	
	}
	
	/**
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		generos=familiaEJB.listarGeneros();
		return generos;
	}

	public String registrar() {
		try {
			genero.setCreateAt(new Date());
			familiaEJB.insertarGenero(genero);

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
	 * Permite activar o desactivar un genero
	 * 
	 * @param nombre
	 * @return
	 */
	public void cambiarEstado(String nombre) {
		try {
			familiaEJB.cambiarEstadoGenero(nombre);
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permite editar los datos de un genero
	 * 
	 * @param nombre
	 * @return
	 */
	public String editar(String nombre) {
		Genero gene = null;
		String redireccion = "";
		gene = familiaEJB.buscarGeneroPorNombre(nombre);
		if (gene != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("gen", gene);
			redireccion = "editar_genero?faces-redirect=true";
		}

		return redireccion;
	}
	
	/**
	 * Modifica los datos de una familia en la base de datos
	 * 
	 * @param gen
	 * @return
	 */
	public String actualizar(Genero gen) {
		try {
			familiaEJB.modificarGenero(gen);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Genero actualizado correctamente",
					"Genero actualizado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_genero?faces-redirect=true";
		} catch (ElementoRepetidoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
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
	
}