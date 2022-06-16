package tutSys.vistas;

/**
 *Autor: Daniel Eduardo Anota Paxtian
 *Fecha de creacion: 09/06/2022
 *Ultima modificacion: 13/06/2022
 *Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tutSys.modelo.dao.EstudianteDAO;
import tutSys.modelo.dao.PeriodoEscolarDAO;
import tutSys.modelo.dao.ReporteTutoriaAcademicaDAO;
import tutSys.modelo.dao.TutoriaAcademicaDAO;
import tutSys.modelo.pojo.*;
import tutSys.utilidades.CuadroDialogo;

public class FXMLRegistroReporteTutoriaControlador implements Initializable {

    @FXML
    private Label labelPeriodoEscolar;
    @FXML
    private Label labelFecha;
    @FXML
    private ComboBox<TutoriaAcademica> comboBoxTutoriaAcademica;
    @FXML
    private TableView<Estudiante> tableViewAlumnos;
    @FXML
    private TableColumn tableColumnMatricula;
    @FXML
    private TableColumn tableColumnNombre;
    @FXML
    private TableColumn tableColumnAsistio;
    @FXML
    private TableColumn tableColumnRiesgo;
    @FXML
    private Label labelTutorAcademico;
    @FXML
    private Label labelFechaTutoria;
    @FXML
    private Label labelFechaCierreReporte;

    public static TutorAcademico tutorAcademico;
    private PeriodoEscolar periodoActual;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colocarFechaActual();
        colocarNombreTutor();
        colocarPeriodoEscolarActual();
        llenarComboBoxTutoriasAcademicas();
        observarCambiosTutorias();
    }    

    @FXML
    private void clicRegistrarReporteTutoria(ActionEvent event) {
        int registroExitoso = 0;
        Optional<ButtonType> botonPulsado;
        botonPulsado = CuadroDialogo.crearCuadroDialogoConfirmacion("Confimación", "¿Desea registrar el " +
                "reporte de tutoría académica?");
        if(botonPulsado.get() == ButtonType.OK){
            registroExitoso = registrarReporteTutoria();
            registroExitoso = registrarAsistenciaTutoria();
        }
        if(registroExitoso == 1){
            CuadroDialogo.crearCuadroDialogoInformacion("Registro exitoso", "Reporte registrado con éxito");
        }
        botonPulsado = CuadroDialogo.crearCuadroDialogoConfirmacion("Confirmación", "¿Desea registrar alguna problemática académica?");
        if(botonPulsado.get() == ButtonType.OK){
            enviarDatosReporte();
            invocarRegistroProblematica();
        } else {
            cerrarVentana();
        }

    }

    @FXML
    private void clicCancelarRegistroReporteTutoria(ActionEvent event) {
        Optional<ButtonType> botonPulsado;
        botonPulsado = CuadroDialogo.crearCuadroDialogoConfirmacion("Cancelar registro", "¿Desea cancelar el" +
                " registro del reporte de tutoría?");
        if(botonPulsado.get() == ButtonType.OK){
            cerrarVentana();
        }
    }

    private void colocarFechaActual(){
        Date fechaActual = new Date();
        labelFecha.setText("Hoy es: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
    }

    private void colocarNombreTutor(){
        labelTutorAcademico.setText("Tutor académico: " + tutorAcademico.getNombre());
    }

    private void colocarPeriodoEscolarActual(){
        long millis = System.currentTimeMillis();
        java.sql.Date fechaActual = new java.sql.Date(millis);
        periodoActual = PeriodoEscolarDAO.obtenerPeriodoEscolarActual(fechaActual);
        if(periodoActual != null){
            labelPeriodoEscolar.setText("Periodo escolar actual: " + periodoActual.getId());
        }
    }

    private void llenarComboBoxTutoriasAcademicas(){
        ArrayList<TutoriaAcademica> tutoriasRecuperadas = TutoriaAcademicaDAO.obtenerTutoriasAcademicas(periodoActual.getIdPeriodoEscolar());
        ObservableList<TutoriaAcademica> tutoriasAcademicas = FXCollections.observableArrayList();
        tutoriasAcademicas.addAll(tutoriasRecuperadas);
        comboBoxTutoriaAcademica.setItems(tutoriasAcademicas);
    }

    private void llenarDatosTutoria(TutoriaAcademica infoTutoria){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        labelFechaTutoria.setText("Fecha de tutoria: " + formatoFecha.format(infoTutoria.getFechaTutoria()));
        labelFechaCierreReporte.setText("Fecha de cierre de reporte: " + formatoFecha.format(infoTutoria.getFechaCierreReporte()));
    }

    private void observarCambiosTutorias(){
        comboBoxTutoriaAcademica.valueProperty().addListener(new ChangeListener<TutoriaAcademica>() {
            @Override
            public void changed(ObservableValue<? extends TutoriaAcademica> observable, TutoriaAcademica oldValue, TutoriaAcademica newValue) {
                validarFechasTutoria();
                llenarDatosTutoria(comboBoxTutoriaAcademica.getValue());
            }
        });
    }

    private void validarFechasTutoria(){
        long millis = System.currentTimeMillis();
        java.sql.Date fechaActual = new java.sql.Date(millis);
        TutoriaAcademica tutoriaSeleccionada = comboBoxTutoriaAcademica.getValue();
        if(fechaActual.after(tutoriaSeleccionada.getFechaTutoria()) && fechaActual.before(tutoriaSeleccionada.getFechaCierreReporte())){
            llenarTablaEstudiantes();
        } else if(fechaActual.before(tutoriaSeleccionada.getFechaTutoria())){
            CuadroDialogo.crearCuadroDialogoError("Registro prematuro", "La tutoría seleccionada " +
                    "aún no se realiza, paciencia, regresará al menú principal");
            cerrarVentana();
        } else if(fechaActual.after(tutoriaSeleccionada.getFechaCierreReporte())){
            CuadroDialogo.crearCuadroDialogoError("Registro tardío", "Lo sentimos, " +
                    "la fecha de registro ha concluido, regresará al menú principal");
            cerrarVentana();
        }
    }

    private void llenarTablaEstudiantes(){
        ArrayList<Estudiante> estudiantesRecuperados = EstudianteDAO.recuperarEstudiantesTutorados(tutorAcademico.getIdTutorAcademico());
        ObservableList<Estudiante> infoEstudiantes = FXCollections.observableArrayList();
        tableColumnMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tableColumnAsistio.setCellValueFactory(new PropertyValueFactory("asiste"));
        tableColumnRiesgo.setCellValueFactory(new PropertyValueFactory("riesgo"));
        if(estudiantesRecuperados != null){
            for(Estudiante estudiante : estudiantesRecuperados){
                estudiante.setAsiste(new CheckBox());
                estudiante.setRiesgo(new CheckBox());
                infoEstudiantes.add(estudiante);
            }
            tableViewAlumnos.setItems(infoEstudiantes);
        }
    }

    private void cerrarVentana(){
        Stage ventanaActual = (Stage) labelFecha.getScene().getWindow();
        ventanaActual.close();
    }

    private int registrarReporteTutoria(){
        int numeroAlumnosAsistentes = 0;
        int numeroAlumnosRiesgo = 0;
        int idTutorAcademico = tutorAcademico.getIdTutorAcademico();
        int idTutoriaAcademica = comboBoxTutoriaAcademica.getValue().getIdTutoriaAcademica();

        ObservableList<Estudiante> estudiantesTabla = tableViewAlumnos.getItems();
        ArrayList<Estudiante> estudiantesReporte = new ArrayList<>(estudiantesTabla);
        for(Estudiante infoEstudiantes : estudiantesReporte){
            if(infoEstudiantes.getAsiste().isSelected()){
                numeroAlumnosAsistentes = numeroAlumnosAsistentes + 1;
            }
            if(infoEstudiantes.getRiesgo().isSelected()){
                numeroAlumnosRiesgo = numeroAlumnosRiesgo + 1;
            }
        }

        int registroExitoso = ReporteTutoriaAcademicaDAO.registrarReporteTutoriaAcademica(numeroAlumnosAsistentes, numeroAlumnosRiesgo, idTutorAcademico, idTutoriaAcademica);

        return registroExitoso;
    }

    private int registrarAsistenciaTutoria(){
        int registroExitoso = 0;
        int idTutoriaAcademica = comboBoxTutoriaAcademica.getValue().getIdTutoriaAcademica();
        int idTutorAcademico = tutorAcademico.getIdTutorAcademico();
        int idEstudiante;
        ObservableList<Estudiante> estudiantesTabla = tableViewAlumnos.getItems();
        ArrayList<Estudiante> estudiantesReporte = new ArrayList<>(estudiantesTabla);
        for(Estudiante asistenciaEstudiante : estudiantesReporte){
            if(asistenciaEstudiante.getAsiste().isSelected()){
                idEstudiante = asistenciaEstudiante.getIdEstudiante();
                registroExitoso = TutoriaAcademicaDAO.registrarAsistenciasTutoria(idTutoriaAcademica, idEstudiante, idTutorAcademico);
            }
        }
        return registroExitoso;
    }

    private void enviarDatosReporte(){
        int idTutorAcademico = tutorAcademico.getIdTutorAcademico();
        int idTutoriaAcademica = comboBoxTutoriaAcademica.getValue().getIdTutoriaAcademica();
        int numeroTutoria = comboBoxTutoriaAcademica.getValue().getNumeroTutoria();
        int idReporteExtendido = ReporteTutoriaAcademicaDAO.obtenerReporteTutoriaUnico(idTutorAcademico, idTutoriaAcademica);
        ReporteTutoriaAcademica reporteExtendido = new ReporteTutoriaAcademica();

        reporteExtendido.setIdReporteTutoriaAcademica(idReporteExtendido);
        reporteExtendido.setNumeroSesionTutoria(numeroTutoria);
        reporteExtendido.setIdPeriodoEscolar(periodoActual.getId());

        System.out.println(reporteExtendido.getIdReporteTutoriaAcademica());
        FXMLRegistroProblematicaAcademicaControlador.reporteExtendido = reporteExtendido;
    }

    private void invocarRegistroProblematica(){
        try {
            Stage escenarioPrincipal = (Stage) labelFecha.getScene().getWindow();
            Scene menuTutSys = new Scene(FXMLLoader.load(getClass().getResource("RegistroProblematicaAcademicaVista.fxml")));
            escenarioPrincipal.setScene(menuTutSys);
            escenarioPrincipal.setTitle("TutSys - Registro problematica académica");
            escenarioPrincipal.show();
        } catch (IOException excepcion){
            excepcion.printStackTrace();
        }
    }
}
