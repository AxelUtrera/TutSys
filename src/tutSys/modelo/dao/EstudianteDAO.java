package tutSys.modelo.dao;

/**
 * Autor: Axel Utrera
 * fecha de creacion: 13 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import javafx.scene.control.CheckBox;
import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.Estudiante;
import tutSys.utilidades.CuadroDialogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstudianteDAO {

    public static ArrayList<Estudiante> recuperarEstudiantesTutorados(int idTutorAcademico){
        ArrayList<Estudiante> estudiantesRecuperados = new ArrayList();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            String consulta = "SELECT * FROM estudiante INNER JOIN tutoracademico WHERE estudiante.idTutorAcademico = ?" +
                    " AND tutoracademico.idTutorAcademico = ?";
            try {
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setInt(1, idTutorAcademico);
                prepararConsulta.setInt(2, idTutorAcademico);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    Estudiante estudianteObtenido = new Estudiante();
                    estudianteObtenido.setIdEstudiante(resultadoConsulta.getInt("idEstudiante"));
                    estudianteObtenido.setMatricula(resultadoConsulta.getString("matricula"));
                    estudianteObtenido.setNombre(resultadoConsulta.getString("nombre"));
                    estudiantesRecuperados.add(estudianteObtenido);
                }
                conexionBD.close();
            } catch (SQLException excepcion){
                excepcion.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        }
        return estudiantesRecuperados;
    }
}
