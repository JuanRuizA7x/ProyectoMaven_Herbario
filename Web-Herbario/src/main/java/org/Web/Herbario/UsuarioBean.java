package org.Web.Herbario;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Opciones;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.AdminEJB;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * Session Bean implementation class UsuarioBean
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("usuarioBean")
@ApplicationScoped
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Usuario usu;
	private Administrador admin;
	private Empleado emple;
	private Recolector recole;
	
	private String destinatario;

	@EJB
	private AdminEJB adminEJB;

	/**
	 * Default constructor.
	 */
	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}
	
	/**
	 * Permite iniciar sesion
	 * 
	 *@param cedula
	 * @return
	 */
	public String iniciarSesion() {
		Usuario us = null;
		String redireccion = "";
		try {
			us = adminEJB.iniciarSesion(usuario);
			if (us != null) {
				// almacenar en la sesion de JSF
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
				redireccion = "index?faces-redirect=true";
			} else {
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales incorrectas!!!!",
						"Credenciales incorrectas!!!!");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}

		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		}
		return redireccion;
	}

	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	/**
	 * Prueba de jhony
	 * 
	 *
	 * @return
	 */
	public void prueba() {
		Usuario user = null;
		try {
			user = adminEJB.buscarPorUserEmail(destinatario);
			if (user != null) {
				String asunto = "Contraseña";
				String cuerpo = "La contraseña es: " + user.getContrasenia();
				adminEJB.enviarConGMail(destinatario, asunto, cuerpo);
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se envió la contraseña a tu bandeja de entrada",
						"Se envió la contraseña a tu bandeja de entrada");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}else {
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Email no existe!!!!",
						"Email no existe!!!!");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}

		} catch (Exception e) {
			// TODO: handle exception
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		}
	}

	/**
	 * Permite editar los datos de un usuario
	 * 
	 *@param cedula
	 * @return
	 */
	public String editar() {
		usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		String redireccion = "";
		try {
			if ((usu.getClass().equals(Administrador.class))) {
				admin = adminEJB.buscarAdministradorId(usu.getCedula());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", admin);
				redireccion = "editar_perfil_admin?faces-redirect=true";
			}else if((usu.getClass().equals(Empleado.class))) {
				emple = adminEJB.buscarEmpleadoId(usu.getCedula());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("emple", emple);
				redireccion = "editar_perfil_emple?faces-redirect=true";
			}else if((usu.getClass().equals(Recolector.class))) {
				recole = adminEJB.buscarRecolectorId(usu.getCedula());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("recole", recole);
				redireccion = "editar_perfil_reco?faces-redirect=true";
			}
			return redireccion;
		} catch (ElementoRepetidoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Actualiza los datos de un usuario
	 * 
	 *@param usuario
	 * @return
	 */
	public String actualizar(Administrador admin) {
		try {
			adminEJB.modificarAdministrador(admin);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Adminsitrador actualizado correctamente",
					"Recolector actualizado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "index?faces-redirect=true";
		} catch (ElementoRepetidoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	/**
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
