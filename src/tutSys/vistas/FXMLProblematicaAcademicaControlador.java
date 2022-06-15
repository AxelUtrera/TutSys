package tutSys.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tutSys.modelo.dao.ProblematicaAcademicaDAO;
import tutSys.modelo.pojo.ProblematicaAcademicaAux;
import tutSys.modelo.pojo.TutorAcademico;
import tutSys.utilidades.CuadroDialogo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

/**
 *
 * @author Axel Utrera
 */
public class FXMLProblematicaAcademicaControlador {

    @FXML
    private ComboBox<String> comboBoxBusqueda;

    @FXML
    private Label labelFechaHoy;

    @FXML
    private TableColumn tableColumnDescripcion;

    @FXML
    private TableColumn tableColumnExperienciaEducativa;

    @FXML
    private TableColumn tableColumnNumeroReportes;

    @FXML
    private TableColumn tableColumnProfesor;

    @FXML
    private TableView<ProblematicaAcademicaAux> tableViewProblematicas;

    @FXML
    private TextField textFieldBusqueda;

    public static TutorAcademico tutorAcademico;
    ObservableList<ProblematicaAcademicaAux> infoProblematica;


    @FXML
    void initialize() {
        colocarFechaActual();
        configurarComboBoxBusqueda();
        llenarTablaProblematica();
    }

    @FXML
    void clicBuscar(ActionEvent event) {
        String terminoBusqueda = textFieldBusqueda.getText();
        String tipoBusqueda = comboBoxBusqueda.getValue();

        if(tipoBusqueda.equals("Buscar por: EE")){
            buscarProblematicasPorEE(terminoBusqueda);
        } else if(tipoBusqueda.equals("Buscar por: Profesor")){
            buscarProblematicasPorProfesor(terminoBusqueda);
        }
    }

    @FXML
    void clicEliminar(ActionEvent event) {

    }

    @FXML
    void clicModificar(ActionEvent event) {

    }

    @FXML
    void clicSalir(ActionEvent event) {
        Optional<ButtonType> confimarSalida = CuadroDialogo.crearCuadroDialogoConfirmacion("Confirmar salida", "¿Desea salir?");
        if(confimarSalida.get() == ButtonType.OK){
            cerrarVentana();
        }
    }


    private void llenarTablaProblematica() {
        ArrayList<ProblematicaAcademicaAux> problematicasObtenidas = ProblematicaAcademicaDAO.obtenerProblematicasAcademicas(tutorAcademico.getIdTutorAcademico());
        infoProblematica = FXCollections.observableArrayList();
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tableColumnNumeroReportes.setCellValueFactory(new PropertyValueFactory("numeroReportes"));
        tableColumnExperienciaEducativa.setCellValueFactory(new PropertyValueFactory("nombreExperienciaEducativa"));
        tableColumnProfesor.setCellValueFactory(new PropertyValueFactory("nombreProfesor"));
        infoProblematica.addAll(problematicasObtenidas);
        tableViewProblematicas.setItems(infoProblematica);
    }

    private void colocarFechaActual(){
        Date fechaActual = new Date();
        labelFechaHoy.setText("Hoy es: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
    }

    private void configurarComboBoxBusqueda(){
        ObservableList<String> tiposBusqueda = FXCollections.observableArrayList();
        tiposBusqueda.addAll("Buscar por: EE", "Buscar por: Profesor");
        comboBoxBusqueda.setItems(tiposBusqueda);
        comboBoxBusqueda.getSelectionModel().select(0);
    }

    private void cerrarVentana(){
        Stage ventanaActual = (Stage) labelFechaHoy.getScene().getWindow();
        ventanaActual.close();
    }

    private void buscarProblematicasPorEE(String terminoBusqueda) {
        if(infoProblematica.size() > 0){
            FilteredList<ProblematicaAcademicaAux> listaFiltrada = new FilteredList<>(infoProblematica, p -> true);
            listaFiltrada.setPredicate(busqueda ->{
                if(busqueda.getNombreExperienciaEducativa().toLowerCase().contains(terminoBusqueda)){
                    return true;
                }
                return false;
            });
            SortedList<ProblematicaAcademicaAux> ordenamientoProblematica = new SortedList<>(listaFiltrada);
            ordenamientoProblematica.comparatorProperty().bind(tableViewProblematicas.comparatorProperty());
            tableViewProblematicas.setItems(ordenamientoProblematica);
        }
    }

    private void buscarProblematicasPorProfesor(String terminoBusqueda) {
        if(infoProblematica.size() > 0){
            FilteredList<ProblematicaAcademicaAux> listaFiltrada = new FilteredList<>(infoProblematica, p -> true);
            listaFiltrada.setPredicate(busqueda ->{
                if(busqueda.getNombreProfesor().toLowerCase().contains(terminoBusqueda)){
                    return true;
                }
                return false;
            });
            SortedList<ProblematicaAcademicaAux> ordenamientoProblematica = new SortedList<>(listaFiltrada);
            ordenamientoProblematica.comparatorProperty().bind(tableViewProblematicas.comparatorProperty());
            tableViewProblematicas.setItems(ordenamientoProblematica);
        }
    }
}

