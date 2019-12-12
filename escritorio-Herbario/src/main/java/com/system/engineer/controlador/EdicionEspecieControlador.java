/**
 * 
 */
package com.system.engineer.controlador;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.persistenciaHerbario.Divipola;
import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import com.system.engineer.modelo.AdministradorDelegado;
import com.system.engineer.modelo.EmpleadoObservable;
import com.system.engineer.util.Utilidades;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class EdicionEspecieControlador {

	
	/**
	 * campo para el Nombre
	 */
	@FXML
	private TextField txtNombre;
	/**
	 * campo para la Apellido
	 */
	@FXML
	private TextArea txtDescripcion;
	/**
	 * choicebox para la familia
	 */
	@FXML
	private ComboBox<Genero> comboBoxGenero;
	/**
	 * choicebox para la Divipola
	 */
	@FXML
	private ComboBox<Divipola> comboBoxDivipola;
	/**
	 * campo para la latitud
	 */
	@FXML
	private TextField txtLatitud;
	/**
	 * campo para la longitud
	 */
	@FXML
	private TextField txtLongitud;
	
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
	 * instancia para lista de generos
	 */
	List <Genero> generos;
	
	/**
	 * campo para la fecha de coleccion
	 */
	@FXML
	private DatePicker txtColeccion;


	/**
	 * 
	 */
	@FXML
	private void initialize() {
		
	}
	
	
	/**
	 * permite cargar la informacion de una especie para realizar una edicion
	 * 
	 * @param especie a editar
	 */
	public void cargarEspecie(Especie especie) {

		txtNombre.setText(especie.getNombre());
		txtDescripcion.setText(especie.getObservaciones());
		txtLatitud.setText(especie.getLatitud());
		txtLongitud.setText(especie.getLongitud());
		txtColeccion.setValue(Utilidades.pasarALocalDate(especie.getFechaRecoleccion()));
		}
	
	
	/**
	 * Permite listar los generos activos en el comboBoxGenero
	 * @param g
	 */
	public void llenarComboBox(List<Genero> g) {
		if(comboBoxGenero.getItems().size()==0) {
			generos = g;
			for (Genero genero : generos) {
				if(genero.getActivo()) {
					comboBoxGenero.getItems().add(genero);
				}
			}
		}
	}

	/**
	 * permite registrar una especie en la base de datos
	 */
	@FXML
	public void registrarEspecie() {

		try{
		String nombre = txtNombre.getText();
		String descripcion = txtDescripcion.getText();
		Genero genero = comboBoxGenero.getValue();
		Divipola divipola = comboBoxDivipola.getValue();
		String latitud = txtLatitud.getText();
		String longitud = txtLongitud.getText();
		Date creacion = new Date();
		Date fechaRecoleccion = (Utilidades.pasarADate(txtColeccion.getValue()));
		
		Especie especie = new Especie(nombre, latitud, longitud, descripcion, fechaRecoleccion, genero, divipola, creacion);

		if (manejador.registrarEspecie(especie)) {

			manejador.agregarAListaEspecie(especie);
			Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
			escenarioEditar.close();
		} else {
			Utilidades.mostrarMensaje("Registro", "Error en registro!!");
		}
		}catch (Exception e) {
			Utilidades.mostrarMensaje("Error", e.getMessage());
		}
	}

	/**
	 * permite editar la informacion de una persona
	 */
	@FXML
	private void modificarEspecie() {

		String nombre = txtNombre.getText();
		String descripcion = txtDescripcion.getText();
		Genero genero = comboBoxGenero.getValue();
		Divipola divipola = comboBoxDivipola.getValue();
		String latitud = txtLatitud.getText();
		String longitud = txtLongitud.getText();
		Date creacion = new Date();
		Date fechaRecoleccion = (Utilidades.pasarADate(txtColeccion.getValue()));
		
		Especie especie = new Especie(nombre, latitud, longitud, descripcion, fechaRecoleccion, genero, divipola, creacion);

		if (manejador.registrarEspecie(especie)) {

			manejador.agregarAListaEspecie(especie);
			Utilidades.mostrarMensaje("Registro", "Registro modificado!!");
			escenarioEditar.close();
		} else {
			Utilidades.mostrarMensaje("Registro", "Error modificando el registro!!");
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

