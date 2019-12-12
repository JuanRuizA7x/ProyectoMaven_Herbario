package com.system.engineer.ejb;

import java.awt.Panel;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Persona;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;


/**
 * Se encarga de todas las transaciones que realiza el admin
 * @author Shonny
 */
@Stateless
@LocalBean
public class AdminEJB implements AdminEJBRemote {

	/**
	 * medio por el cual se realizan las transaciones
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdminEJB() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * Insertar empleado
	 */
	
	public Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException {
		if (entityManager.find(Usuario.class, empleado.getCedula()) != null) {
			throw new ElementoRepetidoException("Ya existe una persona registrada con esta cedula");
		} else if (buscarPorEmail(empleado.getEmail()) != null) {
			throw new ElementoRepetidoException("Ya existe una persona registrada con este email");
		}
		try {
			entityManager.persist(empleado);
			return empleado;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Buscar empleado por email
	 */
	private Empleado buscarPorEmail(String email) {
		try {
			TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_POR_EMAIL_EMPLEADO,
					Empleado.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * Buscar empleado por cedula
	 * @throws ElementoRepetidoException 
	 */
	public Empleado buscarEmpleadoId(String cedula) throws ElementoRepetidoException {
		if (entityManager.find(Empleado.class, cedula) == null) {
			throw new ElementoRepetidoException("El empleado con esta cedula no esta registrado!!!!");
		} 
		
		try {
			return entityManager.find(Empleado.class, cedula);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Modificar empleado
	 * 
	 * @throws ElementoRepetidoException
	 */
	@Override
	public Empleado modificarEmpleado(Empleado emp) throws ElementoRepetidoException {
		Empleado empleado= entityManager.find(Empleado.class, emp.getCedula());
		if (empleado == null) {
			throw new ElementoRepetidoException("El empleado con esta cedula no esta registrado!!!!");
		
		}
		try {
			empleado = emp;
			entityManager.merge(empleado);
			return empleado;
		} catch (Exception e) {
			return null;
		}

	}
	
	@Override
	public Usuario eliminarPersona(String cedula) throws ElementoNoEncontradoException {
		Usuario usuario = entityManager.find(Empleado.class, cedula);
		if (usuario == null) {
			throw new ElementoNoEncontradoException("Persona no encontrada en los registros");
		} else {
			if(usuario.getActivo()) {
				usuario.setActivo(false);
			}else {
				usuario.setActivo(true);
			}
			entityManager.merge(usuario);
			return usuario;
		}
	}

	/**
	 * Insertar Administrador
	 */
	public Administrador insertarAdministrador(Administrador administrador) throws ElementoRepetidoException {
		if (entityManager.find(Administrador.class, administrador.getCedula()) != null) {
			throw new ElementoRepetidoException("El Administrador con esta cedula ya esta registrado!!!!");
		} else if (buscarAdminPorEmail(administrador.getEmail()) != null) {
			throw new ElementoRepetidoException("El Administrador con este email ya esta registrado!!!!");
		}

		try {
			entityManager.persist(administrador);
			return administrador;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Buscar Administrador por email
	 */
	private Administrador buscarAdminPorEmail(String email) {
		try {
			TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.BUSCAR_ADMIN_EMAIL,
					Administrador.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * Buscar empleado por email
	 */
	public Usuario buscarPorUserEmail(String email) {
		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.BUSCAR_USUARIO_EMAIL, Usuario.class);
		if (query == null) {
			throw new NoResultException("El email no se encuentra registrado!!!!");
		}
		try {
			query.setParameter("email", email);
			query.setParameter("activo", true);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	
	
	
	/**
	 * Buscar Administrador por cedula
	 * @throws ElementoRepetidoException 
	 */
	public Administrador buscarAdministradorId(String cedula) throws ElementoRepetidoException {
		if (entityManager.find(Administrador.class, cedula) == null) {
			throw new ElementoRepetidoException("El Administrador con esta cedula no esta registrado!!!!");
		} 
		
		try {
			return entityManager.find(Administrador.class, cedula);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Modificar Administrador
	 * 
	 * @throws ElementoRepetidoException
	 */
	public Administrador modificarAdministrador(Administrador admin) throws ElementoRepetidoException {
		Administrador administrador = entityManager.find(Administrador.class, admin.getCedula());
		if (administrador == null) {
			throw new ElementoRepetidoException("El Administrador con esta cedula no esta registrado!!!!");
		}

		try {
			administrador = admin;
			entityManager.merge(administrador);
			return administrador;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Insertar Recolector
	 */
	public Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoException {
		if (entityManager.find(Usuario.class, recolector.getCedula()) != null) {
			throw new ElementoRepetidoException("El Recolector con esta cedula ya esta registrado!!!!");
		} else if (buscarRecolectorPorEmail(recolector.getEmail()) != null) {
			throw new ElementoRepetidoException("El Administrador con este email ya esta registrado!!!!");
		}

		try {
			entityManager.persist(recolector);
			return recolector;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Buscar Recolector
	 */
	private Recolector buscarRecolectorPorEmail(String email) {
		try {
			TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.BUSCAR_RECOLECTOR_EMAIL,
					Recolector.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * Buscar Administrador por cedula
	 * @throws ElementoRepetidoException 
	 */
	public Recolector buscarRecolectorId(String cedula) throws ElementoRepetidoException {
		if (entityManager.find(Recolector.class, cedula) == null) {
			throw new ElementoRepetidoException("El Recolector con esta cedula no esta registrado!!!!");
		} 
		
		try {
			return entityManager.find(Recolector.class, cedula);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Modificar empleado
	 * 
	 * @throws ElementoRepetidoException
	 */
	@Override
	public Recolector modificarRecolector(Recolector reco) throws ElementoRepetidoException {
		Recolector recolector= entityManager.find(Recolector.class, reco.getCedula());
		if (recolector == null) {
			throw new ElementoRepetidoException("El empleado con esta cedula no esta registrado!!!!");
		
		}
		try {
			recolector = reco;
			entityManager.merge(recolector);
			return recolector;
		} catch (Exception e) {
			return null;
		}

	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public List<Recolector> listarRecolector() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_TODO_RECOLECTOR, Recolector.class);
		return query.getResultList();
	}



	/**
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public List<Empleado> listarEmpleados() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_TODO_EMPLEADO, Empleado.class);
		return query.getResultList();
	}
	
	@Override
	public Usuario eliminarRecolector(String cedula) throws ElementoNoEncontradoException {
		Usuario usuario = entityManager.find(Recolector.class, cedula);
		if (usuario == null) {
			throw new ElementoNoEncontradoException("Persona no encontrada en los registros");
		} else {
			if(usuario.getActivo()) {
				usuario.setActivo(false);
			}else {
				usuario.setActivo(true);
			}
			entityManager.merge(usuario);
			return usuario;
		}
	}
	
	/**
	 * Buscar Usuario por cedula
	 * @throws ElementoRepetidoException 
	 */
	@Override
	public Usuario buscarUsuarioId(String cedula) throws ElementoNoEncontradoException {
		if (entityManager.find(Usuario.class, cedula) == null) {
			throw new ElementoNoEncontradoException("El usuario no existe");
		} 
		
		try {
			return entityManager.find(Usuario.class, cedula);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Usuario iniciarSesion(Usuario usuario) {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.INICIAR_SESION,
					Usuario.class);
			query.setParameter("cedula", usuario.getCedula());
			query.setParameter("contrasenia",usuario.getContrasenia());
			query.setParameter("activo",true);
			return query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public void enviarConGMail(String destinatario, String asunt, String cuerpo) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google mail.smtp.ssl.trust mail.smtp.host
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "25"); // El puerto SMTP seguro de Google

		Session sesion = Session.getDefaultInstance(props);
		String correoEnvia = "herbariouq01@gmail.com"; 
		String contrasena = "AUTOMATIZADO-2412"; //La clave de la cuenta
		String receptor = destinatario;
		String asunto = asunt;
		String mensaje = cuerpo;

		MimeMessage mail = new MimeMessage(sesion);
		try {
			mail.setFrom(new InternetAddress(correoEnvia));
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
			mail.setSubject(asunto);
			mail.setText(mensaje);

			Transport transportar = sesion.getTransport("smtp");
			transportar.connect(correoEnvia, contrasena);
			transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
			transportar.close();

		} catch (AddressException ex) {
			Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
