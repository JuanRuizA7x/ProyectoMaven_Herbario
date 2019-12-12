/**
 * 
 */
package com.system.engineer.controlador;

import javax.swing.JOptionPane;

import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.modelo.UsuarioObservable;
import com.system.engineer.util.Utilidades;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class LoginControlador {

	/**
	 * campo para la cedula
	 */
	@FXML
	private TextField txtCedula;
	/**
	 * campo para la Clave
	 */
	@FXML
	private TextField passClave;
	
	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;
	
	/**
	 * instancia del manejador de los escenario
	 */
	private ManejadorEscenarios manejador;
	
	public static UsuarioObservable usuarioObservableLogin;
	
	private InicioControlador inicioControlador;
	/**
	 * 
	 */
	public LoginControlador() {

	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void login() {
		String cedula = txtCedula.getText();
		String contrasenia = passClave.getText();
		usuarioObservableLogin = manejador.login(cedula);
		//JOptionPane.showMessageDialog(null,usuarioObservableLogin.getNombre().get()+"---------------------");
		if(usuarioObservableLogin!=null && usuarioObservableLogin.getContrasenia().get().equals(contrasenia)) {
			if(!usuarioObservableLogin.getActivo().get()) {
				Utilidades.mostrarMensaje("Error!!!!", "El usuario se encuentra desactivado!!!!");
			}else {
				//JOptionPane.showMessageDialog(null,usuarioObservableLogin.getNombre().get()+"******************");
				Utilidades.mostrarMensaje("Has iniciado sesión!!!!", "Has iniciado sesión!!!!");
				escenarioEditar.close();
			}
		}else if(usuarioObservableLogin==null){
			Utilidades.mostrarMensaje("Error", "Usuario no encontrado!!!!!");
		}else if(!(usuarioObservableLogin.getContrasenia().get().equals(contrasenia))) {
			Utilidades.mostrarMensaje("Error", "Contraseña incorrecta!!!!");
		}
	}

	/**
	 * @return the txtCedula
	 */
	public TextField getTxtCedula() {
		return txtCedula;
	}

	/**
	 * @param txtCedula the txtCedula to set
	 */
	public void setTxtCedula(TextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	/**
	 * @return the passClave
	 */
	public TextField getPassClave() {
		return passClave;
	}

	/**
	 * @param passClave the passClave to set
	 */
	public void setPassClave(TextField passClave) {
		this.passClave = passClave;
	}
	
	
	/**
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {
		this.escenarioEditar = escenarioEditar;
	}

	/**
	 * @return the manejador
	 */
	public ManejadorEscenarios getManejador() {
		return manejador;
	}

	/**
	 * @param manejador the manejador to set
	 */
	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

	/**
	 * @return the usuarioObservableLogin
	 */
	public UsuarioObservable getUsuarioObservableLogin() {
		return usuarioObservableLogin;
	}

	/**
	 * @param usuarioObservableLogin the usuarioObservableLogin to set
	 */
	public void setUsuarioObservableLogin(UsuarioObservable usuarioObservableLogin) {
		this.usuarioObservableLogin = usuarioObservableLogin;
	}

	
	
	
}
