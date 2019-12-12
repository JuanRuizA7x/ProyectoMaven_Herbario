package DTO;

public class ConsultaPunto4DTO {
	private String cedulaEmpleado;
	private Long numeroRegistros;
	/**
	 * @param cedulaEmpleado
	 * @param numeroRegistros
	 */
	public ConsultaPunto4DTO(String cedulaEmpleado, Long numeroRegistros) {
		super();
		this.cedulaEmpleado = cedulaEmpleado;
		this.numeroRegistros = numeroRegistros;
	}
	/**
	 * @return the cedulaEmpleado
	 */
	public String getCedulaEmpleado() {
		return cedulaEmpleado;
	}
	/**
	 * @param cedulaEmpleado the cedulaEmpleado to set
	 */
	public void setCedulaEmpleado(String cedulaEmpleado) {
		this.cedulaEmpleado = cedulaEmpleado;
	}
	/**
	 * @return the numeroRegistros
	 */
	public Long getNumeroRegistros() {
		return numeroRegistros;
	}
	/**
	 * @param numeroRegistros the numeroRegistros to set
	 */
	public void setNumeroRegistros(Long numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaPunto4DTO [cedulaEmpleado=" + cedulaEmpleado + ", numeroRegistros=" + numeroRegistros + "]";
	}
	
	
}
