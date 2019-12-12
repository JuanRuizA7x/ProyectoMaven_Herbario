package com.system.engineer.ejb;


import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.persistenciaHerbario.Administrador;

/**
 * Session Bean implementation class SetupEJB
 * @author Shonny
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SetupEJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Se encarga de generar la configuracion inicial
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	private void init() {
		agregarAdmin();

	}

	private void agregarAdmin() {
		TypedQuery<Long> query = entityManager.createNamedQuery(Administrador.CONTAR_ADMINISTRADOR, Long.class);
		long contarAdmin = query.getSingleResult();

		if (contarAdmin == 0) {

			Administrador a = new Administrador("9999999999", "Admin", "Admin", "Admin@Admin.com", "12345", true,
					new Date(), 11111.0);

			entityManager.persist(a);
		}
	}


}
