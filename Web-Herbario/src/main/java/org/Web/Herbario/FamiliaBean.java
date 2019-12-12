package org.Web.Herbario;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.faces.model.SelectItem;
import javax.inject.Named;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.FamiliaEJB;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * Encargado de gestionar las operaciones con empleado
 * 
 * @author JuanK
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("familiaBean")
@ApplicationScoped
public class FamiliaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Familia familia;
	private List<Familia> familias;

	/**
	 * iNSTANCIA que permite acceder a las operaciones de negocio
	 */
	@EJB
	private FamiliaEJB familiaEJB;

	@PostConstruct
	public void init() {
		familia = new Familia();
		familia.setActivo(true);

	}

	public String registrar() {
		try {
			familia.setCreateAt(new Date());
			familiaEJB.insertarFamilia(familia);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_familia?faces-redirect=true";
		} catch (ElementoRepetidoException e) {
			// e.printStackTrace();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}

	}

	/**
	 * Permite editar los datos de una familia
	 * 
	 * @param id
	 * @return
	 */
	public String editar(String id) {
		Familia fami = null;
		String redireccion = "";
		Integer idCast = Integer.parseInt(id);
		fami = familiaEJB.buscarFamiliaPorId(idCast);
		if (fami != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fam", fami);
			redireccion = "editar_familia?faces-redirect=true";
		}

		return redireccion;
	}

	/**
	 * Modifica los datos de una familia en la base de datos
	 * 
	 * @param fam
	 * @return
	 */
	public String actualizar(Familia fam) {
		try {
			familiaEJB.modificarFamilia(fam);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Familia actualizada correctamente",
					"Familia actualizada correctamente");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_familia?faces-redirect=true";
		} catch (ElementoRepetidoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}

	/**
	 * Desactiva una familia
	 * 
	 * @param id
	 * @return
	 */
//	public String desactivar(Integer id) {
//		try {
//			Familia familia = new Familia();
//			familia = familiaEJB.buscarFamiliaPorId(id);
//			
//			//Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//			//sessionMap.put("fam", familia);
//			
//			familiaEJB.eliminarFamilia(familia.getNombre());
//
//			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Familia desactivada correctamente",
//					"Familia desactivada correctamente");
//			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
//			return "listar_familia";
//		} catch (ElementoNoEncontradoException e) {
//			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
//			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
//			return null;
//		}
//	}
	
	/**
	 * Permite activar o desactivar una familia
	 * 
	 * @param nombre
	 * @return
	 */
	public void cambiarEstado(String nombre) {
		try {
			familiaEJB.cambiarEstadoFamilia(nombre);
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @return the familias
	 */
	public List<Familia> getFamilias() {
		familias = familiaEJB.listarFamilias();
		return familias;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	/**
	 * @return the familiaEJB
	 */
	public FamiliaEJB getFamiliaEJB() {
		return familiaEJB;
	}

	/**
	 * @param familiaEJB the familiaEJB to set
	 */
	public void setFamiliaEJB(FamiliaEJB familiaEJB) {
		this.familiaEJB = familiaEJB;
	}

}
