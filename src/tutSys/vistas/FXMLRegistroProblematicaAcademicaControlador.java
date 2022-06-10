package tutSys.vistas;


/**
*
* fecha de creacion: 9 / 06 / 2022
* autor: Axel Utrera
*
* */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tutSys.modelo.dao.ExperienciaEducativaDAO;
import tutSys.modelo.dao.ProfesorDAO;
import tutSys.modelo.pojo.ExperienciaEducativa;
import tutSys.modelo.pojo.Profesor;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FXMLRegistroProblematicaAcademicaControlador implements Initializable {

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
            Stage escenario = (Stage) comboBoxProfesor.getScene().getWindow();
            escenario.close();
    }

    @FXML
    void clicRegistrar(ActionEvent event) {
        validarCampos();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarComboboxSecionTutorias();
        llenarComboboxExperienciasEducativas();
        llenarComboboxProfesor();
        colocarFechaHoy();
    }

    public void llenarComboboxSecionTutorias(){
        String nombreSesiones[] = {"Primera sesión", "Segunda sesión", "Tercera sesión"};
        ObservableList<String> sesionesTutoria = FXCollections.observableArrayList();
        sesionesTutoria.addAll(nombreSesiones);
        comboBoxTutorias.setItems(sesionesTutoria);
    }

    public void llenarComboboxExperienciasEducativas(){
        ObservableList<String> experienciasEducativas = FXCollections.observableArrayList();
        ArrayList<ExperienciaEducativa> experienciasRecuperadas = ExperienciaEducativaDAO.recuperarTodasExperienciasEducativas();
        for (ExperienciaEducativa ee : experienciasRecuperadas){
            experienciasEducativas.add(ee.getNombre());
        }
        comboBoxExperienciaEducativa.setItems(experienciasEducativas);
    }

    public void llenarComboboxProfesor(){
        ObservableList<String> profesores = FXCollections.observableArrayList();
        ArrayList<Profesor> profesoresRecuperados = ProfesorDAO.recuperarTodosProfesores();

        for (Profesor profesor : profesoresRecuperados){
            profesores.add(profesor.getNombre());
        }
        comboBoxProfesor.setItems(profesores);
    }

    public Label colocarFechaHoy(){
        Date fechaHoy = new Date();
        labelFechaHoy.setText("Fecha de hoy: "+ new SimpleDateFormat("dd/MM/yyyy").format(fechaHoy));
        return labelFechaHoy;
    }

     public void validarCampos(){

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

         Pattern expresionRegular =  Pattern.compile("^[0-9]*$");
         Matcher validador = expresionRegular.matcher(textFieldNumeroReportes.getText());
         if(!(validador.matches()) || textoNumeroReportes.isEmpty()){
            labelErrorNumeroReportes.setVisible(true);
            esValido = false;
         }

         if(sesionTutoria == null){
             labelErrorTutoria.setVisible(true);
             esValido = false;
         }
         if(textoDescripcion.isEmpty()){
             labelErrorDescripcion.setVisible(true);
             esValido = false;
         }

         if(experienciaEducativaSeleccionada == null){
             labelErrorExperienciaEducativa.setVisible(true);
             esValido = false;
         }

         if (nombreProfesorSeleccionado == null){
             labelErrorProfesor.setVisible(true);
             esValido = false;
         }
     }


}

