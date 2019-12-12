/**
 * 
 */
package com.system.engineer.controlador;


import java.util.Date;

import javax.swing.JOptionPane;

import org.persistenciaHerbario.Familia;

import com.system.engineer.modelo.FamiliaDelegado;
import com.system.engineer.util.Utilidades;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class EdicionFamiliaControlador {
	


	/**
	 * campo para el nombre
	 */
	@FXML
	private TextField txtNombre;
	
	/**
	 * Etiqueta del Id
	 */
	@FXML
	private Label labelId;
	
	/**
	 * campo para la descripcion
	 */
	@FXML
	private TextArea txtDescripcion;


	/**
	 * Boton agregar
	 */
	@FXML
	private Button btnAgregar;
	

	/**
	 * Boton modificar
	 */
	@FXML
	private Button btnModificar;
	
	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;
	/**
	 * instancia del manejador de los escenario
	 */
	private ManejadorEscenarios manejador;
	

	/**
	 * 
	 */
	@FXML
	private void initialize() {

	}
	
	/**
	 * permite cargar la informacion de una familia para realizar una edicion
	 * 
	 * @param familia a editar
	 */
	public void cargarFamilia(Familia familia) {

		labelId.setText(familia.getId().toString());
		txtNombre.setText(familia.getNombre());
		txtDescripcion.setText(familia.getDescripcion());
	}

	/**
	 * permite registrar una familia en la base de datos
	 */
	@FXML
	public void registrarFamilia() {

		Familia familia = new Familia();
		familia.setNombre(txtNombre.getText());
		familia.setDescripcion(txtDescripcion.getText());
		familia.setActivo(true);
		familia.setCreateAt(new Date());

		if (manejador.registrarFamilia(familia)) {

			Utilidades.mostrarMensaje("Registro", "Registro exitoso");
			escenarioEditar.close();
		} else {
			Utilidades.mostrarMensaje("Registro", "Error en registro!");
		}
	}

	/**
	 * permite editar la informacion de una familia
	 */
	@FXML
	private void modificarFamilia() {

		Familia familia = new Familia();
		familia.setId(Integer.parseInt(labelId.getText()));
		familia.setNombre(txtNombre.getText());
		familia.setDescripcion(txtDescripcion.getText());
		familia.setActivo(true);
		familia.setCreateAt(new Date());
		if (manejador.modificarFamilia(familia)) {
 
			Utilidades.mostrarMensaje("Registro modificado", "Registro modificado con exito!");
			escenarioEditar.close();
		} else {
			Utilidades.mostrarMensaje("Registro modificado", "Error en modificar registro!");
		}
	}

	/**
	 * permite cerrar la ventana de editar y crear
	 */
	@FXML
	private void cancelar() {
		escenarioEditar.close();
	}

	/**
	 * permite cargar el manejador de escenarios
	 * 
	 * @param manejador
	 */
	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

	/**
	 * Oculta o muestra un boton
	 * 
	 * @param tipo
	 */
	public void botones(boolean tipo) {
		if(tipo) {
			btnAgregar.setVisible(true);
			btnModificar.setVisible(false);
		}else {
			btnAgregar.setVisible(false);
			btnModificar.setVisible(true);
		}
	}
	
	/**
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {
		this.escenarioEditar = escenarioEditar;
	}

}