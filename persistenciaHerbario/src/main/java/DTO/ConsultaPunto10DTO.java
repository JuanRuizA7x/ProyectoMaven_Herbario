/**
 * 
 */
package DTO;


/**
 * @author Shonny
 *
 */
public class ConsultaPunto10DTO {
	
	/**
	 * 
	 */

	private String idRegistro;
	private String nombreGenero;
	private String idEspecie;
	private String cedulaUsuario;
	private String emailUsuario;
	/**
	 * @param idRegistro
	 * @param nombreGenero
	 * @param idEspecie
	 * @param cedulaUsuario
	 * @param emailUsuario
	 */
	public ConsultaPunto10DTO(String idRegistro, String nombreGenero, String idEspecie, String cedulaUsuario,
			String emailUsuario) {
		this.idRegistro = idRegistro;
		this.nombreGenero = nombreGenero;
		this.idEspecie = idEspecie;
		this.cedulaUsuario = cedulaUsuario;
		this.emailUsuario = emailUsuario;
	}
	/**
	 * @return the idRegistro
	 */
	public String getIdRegistro() {
		return idRegistro;
	}
	/**
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}
	/**
	 * @return the nombreGenero
	 */
	public String getNombreGenero() {
		return nombreGenero;
	}
	/**
	 * @param nombreGenero the nombreGenero to set
	 */
	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}
	/**
	 * @return the idEspecie
	 */
	public String getIdEspecie() {
		return idEspecie;
	}
	/**
	 * @param idEspecie the idEspecie to set
	 */
	public void setIdEspecie(String idEspecie) {
		this.idEspecie = idEspecie;
	}
	/**
	 * @return the cedulaUsuario
	 */
	public String getCedulaUsuario() {
		return cedulaUsuario;
	}
	/**
	 * @param cedulaUsuario the cedulaUsuario to set
	 */
	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}
	/**
	 * @return the emailUsuario
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}
	/**
	 * @param emailUsuario the emailUsuario to set
	 */
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaPunto10DTO [idRegistro=" + idRegistro + ", nombreGenero=" + nombreGenero + ", idEspecie="
				+ idEspecie + ", cedulaUsuario=" + cedulaUsuario + ", emailUsuario=" + emailUsuario + "]";
	}
	
	
	
}
