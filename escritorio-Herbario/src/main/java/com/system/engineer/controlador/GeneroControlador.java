package com.system.engineer.controlador;

import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import com.system.engineer.modelo.GeneroObservable;
import com.system.engineer.util.Utilidades;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class GeneroControlador {

	/**
	 * table donde se almacena la informacion de los generos
	 */
	@FXML
	private TableView<GeneroObservable> tablaGeneros;
	/**
	 * hace referencia a la columna con los id's
	 */
	@FXML
	private TableColumn<GeneroObservable, String> idColumna;
	/**
	 * hace referencia a la columna de los nombres de los generos
	 */
	@FXML
	private TableColumn<GeneroObservable, String> nombreColumna;
	/**
	 * hace referencia a la columna de las familias de los generos
	 */
	@FXML
	private TableColumn<GeneroObservable, String> familiaColumna;
	/**
	 * etiqueta del id
	 */
	@FXML
	private Label txtId;
	/**
	 * etiqueta del nombre
	 */
	@FXML
	private Label txtNombre;
	/**
	 * etiqueta de la descripcion
	 */
	@FXML
	private Label txtDescripcion;
	/**
	 * etiqueta del estado
	 */
	@FXML
	private Label txtEstado;
	/**
	 * etiqueta de la familia
	 */
	@FXML
	private Label txtFamilia;
	/**
	 * etiqueta de la creacion
	 */
	@FXML
	private Label txtCreacion;
	
	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditarGenero;
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios manejador;

	private GeneroObservable generoObservable;

	public GeneroControlador() {
	}

	/**
	 * permite carga la informacion en las tables y escuchar las operaciones que se
	 * realizan con la tabla
	 */
	@FXML
	private void initialize() {

		idColumna.setCellValueFactory(generoCelda -> generoCelda.getValue().getId());
		nombreColumna.setCellValueFactory(generoCelda -> generoCelda.getValue().getNombre());
		familiaColumna.setCellValueFactory(generoCelda -> generoCelda.getValue().getFamilia());
	
		mostrarDetalleGenero(null);

		tablaGeneros.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleGenero(newValue));

	}

	/**
	 * permite mostrar la informacion del genero seleccionado
	 * 
	 * @param genero al que se le desea mostrar el detalle
	 */
	public void mostrarDetalleGenero(GeneroObservable genero) {

		if (genero != null) {
			generoObservable = genero;
			txtId.setText(genero.getId().getValue());
			txtNombre.setText(genero.getNombre().getValue());
			txtDescripcion.setText(genero.getDescripcion().getValue());
			txtEstado.setText(genero.getActivo().getValue()+"");
			txtFamilia.setText(genero.getFamilia().getValue());
			txtCreacion.setText(genero.getCreacion().getValue().toString());
		} else {
			txtId.setText("");
			txtNombre.setText("");
			txtDescripcion.setText("");
			txtEstado.setText("");
			txtFamilia.setText("");
			txtCreacion.setText("");
		}

	}

	/**
	 * permite mostrar la ventana de agregar genero
	 */
	@FXML
	public void agregarGenero() {
		int i = manejador.getFamilias().size();
		if(i==0) {
			Utilidades.mostrarMensaje("Error", "Aún no hay familias registradas");
		}else {
			manejador.cargarEscenarioCrearGenero();
			tablaGeneros.refresh();
		}
	}
	
	/**
	 * permite cargar la ventana de editar genero
	 */
	@FXML
	public void editarGenero() {
		int indice = tablaGeneros.getSelectionModel().getSelectedIndex();
		if (indice != -1) {

			Genero genero = tablaGeneros.getItems().get(indice).getGenero();			

			manejador.cargarEscenarioEditarGenero(genero);
			tablaGeneros.refresh();
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar un género");
		}
	}

	/**
	 * permite desactivar un genero seleccionado
	 */
	@FXML
	public void desactivarGenero() {
		int indice = tablaGeneros.getSelectionModel().getSelectedIndex();

		if (indice != -1) {

			Genero genero = tablaGeneros.getItems().get(indice).getGenero();

			if (manejador.eliminarGenero(genero)) {
				
				tablaGeneros.refresh();
				Utilidades.mostrarMensaje("Borrar", "El género ha sido desactivado");
			} else {
				Utilidades.mostrarMensaje("Error", "El género no pudo ser desactivado");
			}
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar un género");
		}
	}

	/**
	 * permite cargar el manejador de escenarios
	 * 
	 * @param manejador
	 */
	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
		tablaGeneros.setItems(manejador.getGenerosObservables());
	}

	/**
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditarGenero(Stage escenarioEditarGenero) {
		this.escenarioEditarGenero = escenarioEditarGenero;
	}

}

