package tutSys.utilidades;

/**
 * Autor: Daniel Eduardo Anota Paxtian
 * fecha de creacion: 09 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CuadroDialogo {

    public static void crearCuadroDialogoError(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static Optional<ButtonType> crearCuadroDialogoConfirmacion(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        return alerta.showAndWait();
    }

    public static void crearCuadroDialogoAdvetencia(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static boolean crearCuadroDialogoInformacion(String titulo, String mensaje){
        boolean confirmacion = false;
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
        confirmacion = true;
        return confirmacion;
    }


}
