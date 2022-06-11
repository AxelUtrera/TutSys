/*
 *Autor: Daniel Eduardo Anota Paxtian
 *Fecha de creacion: 10/06/2022
 *Ultima modificacion: 10/06/2022
 *Nombre modificador: Daniel Eduardo Anota Paxtian
 */

package tutSys.vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tutSys.modelo.dao.TutorAcademicoDAO;
import tutSys.modelo.pojo.TutorAcademico;
import tutSys.utilidades.CuadroDialogo;

import java.io.IOException;

public class FXMLInicioSesionControlador {
    @FXML
    private Label labelContraseniaError;
    @FXML
    private Label labelUsuarioError;
    @FXML
    private PasswordField passwordFieldContraseña;
    @FXML
    private TextField textFieldUsuario;


    @FXML
    void clicIniciarSesion(ActionEvent event) {
        if(validarCamposTexto()){
            String usuario = textFieldUsuario.getText();
            String contrasenia = passwordFieldContraseña.getText();

            TutorAcademico usuarioSesion = TutorAcademicoDAO.recuperarTutorAcademico(usuario, contrasenia);

            if(usuarioSesion != null){
                CuadroDialogo.crearCuadroDialogoInformacion("Bienvenida/o!", "Bienvenida/o " + usuarioSesion.getNombre());
                //Se inicializa la variable estatica para tener acceso a los datos desde la otra ventana.
                FXMLMenuTutSysControlador.tutorAcademico = usuarioSesion;
                FXMLRegistroProblematicaAcademicaControlador.tutorAcademico = usuarioSesion;
                invocarMenuPrincipal();
            } else {
                CuadroDialogo.crearCuadroDialogoError("Usuario no encontrado", "El usuario o la contraseña ingresada no existen");
            }
        }
    }

    private boolean validarCamposTexto(){
        boolean camposValidos = true;
        labelUsuarioError.setVisible(false);
        labelContraseniaError.setVisible(false);

        if(textFieldUsuario.getText().isEmpty()){
            labelUsuarioError.setVisible(true);
            camposValidos = false;
        }

        if(passwordFieldContraseña.getText().isEmpty()){
            labelContraseniaError.setVisible(true);
            camposValidos = false;
        }
        return camposValidos;
    }

    private void invocarMenuPrincipal(){
        try {
            Stage escenarioPrincipal = (Stage) textFieldUsuario.getScene().getWindow();
            Scene menuTutSys = new Scene(FXMLLoader.load(getClass().getResource("MenuTutSysVista.fxml")));
            escenarioPrincipal.setScene(menuTutSys);
            escenarioPrincipal.setTitle("TutSys - Menú Principal");
            escenarioPrincipal.show();
        } catch (IOException excepcion){
            excepcion.printStackTrace();
        }
    }

}