package tutSys.vistas;

/**
 * Autor: Axel Utrera
 * fecha de creacion: 09 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Axel Utrera
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tutSys.modelo.dao.ExperienciaEducativaDAO;
import tutSys.modelo.dao.ProblematicaAcademicaDAO;
import tutSys.modelo.dao.ProfesorDAO;
import tutSys.modelo.dao.ReporteTutoriaAcademicaDAO;
import tutSys.modelo.pojo.*;
import tutSys.utilidades.CuadroDialogo;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FXMLEdicionProblematicaAcademicaControlador implements Initializable {

    public static TutorAcademico tutorAcademico;
    public static ProblematicaAcademicaAux problematicaAEditar;

    @FXML
    private ComboBox<String> comboBoxExperienciaEducativa;

    @FXML
    private ComboBox<String> comboBoxProfesor;

    @FXML
    private ComboBox<String> comboBoxTutorias;

    @FXML
    private Label labelFechaHoy;

    @FXML
    private TextArea textAreaDescripcion;

    @FXML
    private TextField textFieldNumeroReportes;

    @FXML
    private Label labelErrorTutoria;

    @FXML
    private Label labelErrorDescripcion;

    @FXML
    private Label labelErrorNumeroReportes;

    @FXML
    private Label labelErrorExperienciaEducativa;

    @FXML
    private Label labelErrorProfesor;

    @FXML
    void clicCancelar(ActionEvent event) {
        Optional<ButtonType> confirmarCancelacion = CuadroDialogo.crearCuadroDialogoConfirmacion("Confirmar", "¿Desea cancelar la modificacion?");
        if(confirmarCancelacion.get() == ButtonType.OK) {
            Stage escenario = (Stage) comboBoxProfesor.getScene().getWindow();
            escenario.close();
        }
    }

    @FXML
    void clicRegistrar(ActionEvent event) {

        if(validarCampos()) {
            //Recuperacion de profesor para obtener su id y asi recuperar el id de la Experiencia Educativa relacionada.
            Profesor profesorRecuperado = ProfesorDAO.recuperarProfesor(comboBoxProfesor.getValue());
            ExperienciaEducativa experienciaEducativaRecuperada = ExperienciaEducativaDAO.recuperarExperienciaEducativa(
                    profesorRecuperado.getIdProfesor(), comboBoxExperienciaEducativa.getValue());

            ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
            problematicaAcademica.setIdProblematicaAcademica(problematicaAEditar.getIdProblematicaAcademica());
            problematicaAcademica.setNumeroResportes(Integer.valueOf(textFieldNumeroReportes.getText()));
            problematicaAcademica.setDescripcion(textAreaDescripcion.getText());
            problematicaAcademica.setIdExperienciaEducativa(experienciaEducativaRecuperada.getIdExperienciaEducativa());


            Optional<ButtonType> confirmarRegistro = CuadroDialogo.crearCuadroDialogoConfirmacion("Actualizar", "¿Desea actualizar la problematica academica?");
            if (confirmarRegistro.get() == ButtonType.OK) {
                ProblematicaAcademicaDAO.actualizarProblematicaAcademica(problematicaAcademica);
                CuadroDialogo.crearCuadroDialogoInformacion("Actualizado con exito!", "¡Problematica academica actualizada con éxito!");
                Stage escenario = (Stage) textFieldNumeroReportes.getScene().getWindow();
                escenario.close();
                
            }
        }
    }

    ReporteTutoriaAcademica datosRecuperadosParaSeleccionCombobox = ReporteTutoriaAcademicaDAO.obtenerReporteTutoriaAcademicaPorId(
            problematicaAEditar.getIdReporteTutoriaAcademica()
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colocarValoresDeEdicion();
        llenarComboboxExperienciasEducativas();
        obsevarCambiosComboboxExperienciaEducativa();
        colocarFechaActual();
    }

    private void colocarValoresDeEdicion(){
        comboBoxTutorias.setValue("ID: "+ problematicaAEditar.getIdProblematicaAcademica()+
                " Sesion " +datosRecuperadosParaSeleccionCombobox.getNumeroSesionTutoria()+
                " " + datosRecuperadosParaSeleccionCombobox.getIdPeriodoEscolar()
        );
        textAreaDescripcion.setText(problematicaAEditar.getDescripcion());
        textFieldNumeroReportes.setText(String.valueOf(problematicaAEditar.getNumeroReportes()));
        comboBoxExperienciaEducativa.setValue(problematicaAEditar.getNombreExperienciaEducativa());
        comboBoxProfesor.setValue(problematicaAEditar.getNombreProfesor());
    }



    private void llenarComboboxExperienciasEducativas() {
        ObservableList<String> experienciasEducativas = FXCollections.observableArrayList();
        ArrayList<ExperienciaEducativa> experienciasRecuperadas = ExperienciaEducativaDAO.recuperarTodasExperienciasEducativas();
        for (ExperienciaEducativa ee : experienciasRecuperadas) {
            experienciasEducativas.add(ee.getNombre());
        }
        comboBoxExperienciaEducativa.setItems(limpiarExperienciasEducativasRepetidas(experienciasEducativas));
    }

    private ObservableList<String> limpiarExperienciasEducativasRepetidas(ObservableList<String> experienciasEducativas) {
        ObservableList<String> listaSinRepetidos = FXCollections.observableArrayList();

        for (String e : experienciasEducativas) {
            if (!listaSinRepetidos.contains(e)) {
                listaSinRepetidos.add(e);
            }
        }
        return listaSinRepetidos;
    }

    private void obsevarCambiosComboboxExperienciaEducativa() {
        comboBoxExperienciaEducativa.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                llenarComboboxProfesor(comboBoxExperienciaEducativa.getValue());
            }
        });
    }

    private void llenarComboboxProfesor(String experienciaEducatia) {
        ObservableList<String> profesores = FXCollections.observableArrayList();
        ArrayList<Profesor> profesoresRecuperados = ProfesorDAO.recuperarProfesoresPorExperienciaEducativa(experienciaEducatia);

        for (Profesor profesor : profesoresRecuperados) {
            profesores.add(profesor.getNombre());
        }
        comboBoxProfesor.setItems(profesores);
    }

    private Label colocarFechaActual() {
        Date fechaHoy = new Date();
        labelFechaHoy.setText("Fecha de hoy: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaHoy));
        return labelFechaHoy;
    }


    private boolean validarCampos() {

        boolean esValido = true;

        labelErrorTutoria.setVisible(false);
        labelErrorDescripcion.setVisible(false);
        labelErrorNumeroReportes.setVisible(false);
        labelErrorExperienciaEducativa.setVisible(false);
        labelErrorProfesor.setVisible(false);

        String sesionTutoria = comboBoxTutorias.getValue();
        String textoDescripcion = textAreaDescripcion.getText();
        String textoNumeroReportes = textFieldNumeroReportes.getText();
        String experienciaEducativaSeleccionada = comboBoxExperienciaEducativa.getValue();
        String nombreProfesorSeleccionado = comboBoxProfesor.getValue();

        Pattern expresionRegular = Pattern.compile("^[0-9]*$");
        Matcher validador = expresionRegular.matcher(textFieldNumeroReportes.getText());
        if (!(validador.matches()) || textoNumeroReportes.isEmpty()) {
            labelErrorNumeroReportes.setVisible(true);
            esValido = false;
        }

        if (sesionTutoria == null) {
            labelErrorTutoria.setVisible(true);
            esValido = false;
        }
        if (textoDescripcion.isEmpty()) {
            labelErrorDescripcion.setVisible(true);
            esValido = false;
        }

        if (experienciaEducativaSeleccionada == null) {
            labelErrorExperienciaEducativa.setVisible(true);
            esValido = false;
        }

        if (nombreProfesorSeleccionado == null) {
            labelErrorProfesor.setVisible(true);
            esValido = false;
        }

        return esValido;
    }



}

