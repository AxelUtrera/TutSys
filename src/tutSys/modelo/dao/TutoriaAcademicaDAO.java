package tutSys.modelo.dao;

/**
 * Autor: Daniel Eduardo Anota Paxtian
 * fecha de creacion: 09 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.TutoriaAcademica;
import tutSys.utilidades.CuadroDialogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TutoriaAcademicaDAO {

    public static ArrayList<TutoriaAcademica> obtenerTutoriasAcademicas(int idPeriodoEscolar){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        ArrayList<TutoriaAcademica> listaTutorias = new ArrayList<>();
        if(conexionBD != null){
            String consulta = "SELECT * FROM tutoriaacademica INNER JOIN periodoescolar " +
                    "WHERE tutoriaacademica.idPeriodoEscolar = ? AND periodoescolar.idperiodoEscolar = ?";
            try {
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setInt(1, idPeriodoEscolar);
                prepararConsulta.setInt(2, idPeriodoEscolar);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    TutoriaAcademica tutoriaRecuperada = new TutoriaAcademica();
                    tutoriaRecuperada.setIdTutoriaAcademica(resultadoConsulta.getInt("idTutoriaAcademica"));
                    tutoriaRecuperada.setNumeroTutoria(resultadoConsulta.getInt("numeroTutoria"));
                    tutoriaRecuperada.setFechaTutoria(resultadoConsulta.getDate("fechaTutoria"));
                    tutoriaRecuperada.setFechaCierreReporte(resultadoConsulta.getDate("fechaCierreReporte"));
                    listaTutorias.add(tutoriaRecuperada);
                }
                conexionBD.close();
            } catch (SQLException excepcion) {
                excepcion.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        }
        return listaTutorias;
    }

    public static int registrarAsistenciasTutoria(int idTutoriaAcademica, int idEstudiante, int idTutorAcademico){
        int registroExitoso = 0;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        String consulta = "INSERT INTO asiste (idTutoriaAcademica, idEstudiante, idTutorAcademico) VALUES (?, ?, ?);";
        if(conexionBD != null){
            try{
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setInt(1, idTutoriaAcademica);
                prepararConsulta.setInt(2, idEstudiante);
                prepararConsulta.setInt(3, idTutorAcademico);
                registroExitoso = prepararConsulta.executeUpdate();
                conexionBD.close();
            }catch (SQLException excepcion){
                registroExitoso = 0;
                excepcion.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        } else {
            registroExitoso = 0;
        }
        return registroExitoso;
    }
}
