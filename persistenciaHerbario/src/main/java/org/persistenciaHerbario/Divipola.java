package org.persistenciaHerbario;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Divipola
 * 
 * @author Shonny
 */
@Entity
@NamedQueries({ @NamedQuery(name = Divipola.CONTAR_DIVIPOLA, query = "SELECT COUNT(d) FROM Divipola d"),
				@NamedQuery(name = Divipola.LISTAR_TODO_DIVIPOLA, query = "SELECT d FROM Divipola d"),
				@NamedQuery(name = Divipola.BUSCAR_MUNICIPIO, query = "SELECT d FROM Divipola d WHERE d.municipio=:municipio and d.departamento =:departamento")
		// @NamedQuery(name = Divipola.LISTAR_ESPECIES_MINICIPIO, query = "SELECT
		// especie FROM Divipola divipola, IN(divipola.especie) especie WHERE
		// divipola.municipio=:municipio"),
		// @NamedQuery(name = Divipola.LISTAR_ESPECIES_DEPARTAMENTO, query="SELECT
		// especie FROM Divipola divipola, IN(divipola.especie) especie WHERE
		// divipola.departamento=:departamento")
})
public class Divipola implements Serializable {
	/**
	 * Referencia para listar las especies de un municipio
	 */
	public static final String CONTAR_DIVIPOLA = "ContarDivipola";
	
	/**
	 * Referencia para listar las especies de un municipio
	 */
	public static final String LISTAR_TODO_DIVIPOLA = "ListarDivipolas";
	
	/**
	 * Referencia para listar las especies de un municipio
	 */
	public static final String BUSCAR_MUNICIPIO = "BuscarMunicipio";
	/**
	 * Referencia para listar las especies de un municipio
	 */
	// public static final String LISTAR_ESPECIES_MINICIPIO =
	// "ListarEspeciesPorMunicipio";
	/**
	 * Referencia para listar las especies de un departamento
	 */
	// public static final String LISTAR_ESPECIES_DEPARTAMENTO =
	// "ListarEspeciesPorDepartamento";
	/**
	 * id de la divipola
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "divipolas_id",updatable = false, nullable = false)
	private Integer id;
	/**
	 * nombre del municipio de la divipola
	 */
	@Column(name = "municipio", nullable = false)
	private String municipio;
	/**
	 * nombre del departamento de la divipola
	 */
	@Column(name = "departamento", nullable = false)
	private String departamento;
	/**
	 * Especies de la divipola
	 */
	// @OneToMany(mappedBy="divipola", cascade = CascadeType.ALL)
	// @JoinColumn(name="especie")
	// private List<Especie> especie;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Divipola() {

	}

	/**
	 * @param id
	 * @param municipio
	 * @param departamento
	 */
	public Divipola(String municipio, String departamento) {
		this.municipio = municipio;
		this.departamento = departamento;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Divipola other = (Divipola) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

}
