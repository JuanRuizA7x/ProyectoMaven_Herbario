/**
 * 
 */
package com.system.engineer.controlador;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import com.system.engineer.util.Utilidades;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Juan Ruiz
 *
 */
public class EdicionGeneroControlador {
	
	/**
	 * Etiqueta del Id
	 */
	@FXML
	private Label labelId;
	/**
	 * campo para el nombre
	 */
	@FXML
	private TextField txtNombre;
	/**
	 * campo para la descripcion
	 */
	@FXML
	private TextArea txtDescripcion;
	/**
	 * choicebox para la familia
	 */
	@FXML
	private ComboBox<Familia> comboBoxFamilia;
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
	 * instancia para lista de familias
	 */
	List <Familia> familias;
	
	/**
	 * 
	 */
	@FXML
	private void initialize() {
		
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
	 * permite cargar la informacion de un genero para realizar una edicion
	 * 
	 * @param genero a editar
	 */
	public void cargarGenero(Genero genero) {
		labelId.setText(genero.getId().toString());
		txtNombre.setText(genero.getNombre());
		txtDescripcion.setText(genero.getDescripcion());
	}
	
	/**
	 * Permite listar las familias activas en el comboBoxFamilia
	 * @param f
	 */
	public void llenarComboBox(List<Familia> f) {
		if(comboBoxFamilia.getItems().size()==0) {
			familias = f;
			for (Familia familia : familias) {
				if(familia.getActivo()) {
					comboBoxFamilia.getItems().add(familia);
				}
			}
		}
	}
	
	/**
	 * permite registrar un genero en la base de datos
	 */
	@FXML
	public void registrarGenero() {

		try{
			
			String nombre = txtNombre.getText();
			String descripcion = txtDescripcion.getText(); 
			Familia familia = comboBoxFamilia.getValue();
			Boolean activo = true;
			Date creacion = new Date();
			
			Genero genero = new Genero(nombre, descripcion, activo, familia, creacion);

			if (manejador.registrarGenero(genero)) {

				//manejador.agregarALista(genero);
				Utilidades.mostrarMensaje("Registro", "Registro exitoso!");
				escenarioEditar.close();
			} else {
				Utilidades.mostrarMensaje("Registro", "Error en registro!");
			}
		}catch (Exception e) {
			Utilidades.mostrarMensaje("Error", e.getMessage());
		}
	}
	
	/**
	 * permite editar la informacion de un genero
	 */
	@FXML
	private void modificarGenero() {

		Genero genero = new Genero();
		genero.setId(Integer.parseInt(labelId.getText()));
		genero.setNombre(txtNombre.getText());
		genero.setDescripcion(txtDescripcion.getText());
		genero.setFamilia(comboBoxFamilia.getValue());
		genero.setActivo(true);
		genero.setCreateAt(new Date());
		if (manejador.modificarGenero(genero)) {
 
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
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {
		this.escenarioEditar = escenarioEditar;
	}
}