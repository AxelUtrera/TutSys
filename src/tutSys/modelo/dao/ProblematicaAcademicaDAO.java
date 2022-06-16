package tutSys.modelo.dao;

/**
 * Autor: Axel Utrera
 * fecha de creacion: 09 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.ProblematicaAcademica;
import tutSys.modelo.pojo.ProblematicaAcademicaAux;
import tutSys.utilidades.CuadroDialogo;

import java.sql.*;
import java.util.ArrayList;

public class ProblematicaAcademicaDAO {

    public static int agregarProblematicaAcademica(ProblematicaAcademica problematica){
        int respuesta  = 0;
        String consulta = "INSERT INTO problematicaacademica(numeroReportes, descripcion, idReporteTutoria, idExperienciaEducativa) VALUES (?, ?, ?, ?)";
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null) {
            try {
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                preparacionConsulta.setInt(1, problematica.getNumeroResportes());
                preparacionConsulta.setString(2, problematica.getDescripcion());
                preparacionConsulta.setInt(3, problematica.getIdReporteTutoria());
                preparacionConsulta.setInt(4, problematica.getIdExperienciaEducativa());
                respuesta = preparacionConsulta.executeUpdate();
                conexionBD.close();
            }catch (SQLException ex){
                ex.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        }
        return respuesta;
    }

    public static ArrayList<ProblematicaAcademicaAux> obtenerProblematicasAcademicas(int idTutorAcademico){
        ArrayList<ProblematicaAcademicaAux> problematicasRecuperadas = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        String consulta = "select reportetutoriaacademica.idReporteTutoriaAcademica, problematicaacademica.idProblematicaAcademica ,problematicaacademica.descripcion, problematicaacademica.numeroReportes,\n" +
                " experienciaeducativa.idExperienciaEducativa, experienciaeducativa.nombre as nombreEE, profesor.idProfesor, profesor.nombre as nombreProf from reportetutoriaacademica inner join problematicaacademica on \n" +
                " reportetutoriaacademica.idReporteTutoriaAcademica = problematicaacademica.idReporteTutoria and reportetutoriaacademica.idTutorAcademico = ? inner join experienciaeducativa on \n" +
                " problematicaacademica.idExperienciaEducativa = experienciaeducativa.idExperienciaEducativa inner join profesor on experienciaeducativa.idProfesor = profesor.idProfesor;";
        if(conexionBD != null){
            try{
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setInt(1, idTutorAcademico);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    ProblematicaAcademicaAux problematicaRecuperada = new ProblematicaAcademicaAux();
                    problematicaRecuperada.setIdReporteTutoriaAcademica(resultadoConsulta.getInt("idReporteTutoriaAcademica"));
                    problematicaRecuperada.setIdProblematicaAcademica(resultadoConsulta.getInt("idProblematicaAcademica"));
                    problematicaRecuperada.setDescripcion(resultadoConsulta.getString("descripcion"));
                    problematicaRecuperada.setNumeroReportes(resultadoConsulta.getInt("numeroReportes"));
                    problematicaRecuperada.setIdExperienciaEducativa(resultadoConsulta.getInt("idExperienciaEducativa"));
                    problematicaRecuperada.setNombreExperienciaEducativa(resultadoConsulta.getString("nombreEE"));
                    problematicaRecuperada.setIdProfesor(resultadoConsulta.getInt("idProfesor"));
                    problematicaRecuperada.setNombreProfesor(resultadoConsulta.getString("nombreProf"));
                    problematicasRecuperadas.add(problematicaRecuperada);
                }
                conexionBD.close();
            } catch (SQLException excepcion){
                excepcion.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        }
        return problematicasRecuperadas;
    }

    public static int eliminarProblematicaAcademica(int idProblematicaAcademica){
        int confirmarEliminacion = 0;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        String consulta = "DELETE FROM problematicaacademica WHERE idProblematicaAcademica = ?";
        if(conexionBD != null){
            try {
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setInt(1, idProblematicaAcademica);
                confirmarEliminacion = prepararConsulta.executeUpdate();
                conexionBD.close();
            } catch (SQLException excepcion) {
                excepcion.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        }
        return confirmarEliminacion;
    }


    public static int  actualizarProblematicaAcademica(ProblematicaAcademica problematicaAcademica){
        int confirmacionConsulta = 0;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        String consulta = "UPDATE problematicaacademica SET numeroReportes = ?, descripcion = ?, idExperienciaEducativa = ? " +
                          "WHERE idProblematicaAcademica = ?";

        if(conexionBD != null){
            try{
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                preparacionConsulta.setInt(1,problematicaAcademica.getNumeroResportes());
                preparacionConsulta.setString(2,problematicaAcademica.getDescripcion());
                preparacionConsulta.setInt(3, problematicaAcademica.getIdExperienciaEducativa());
                preparacionConsulta.setInt(4,problematicaAcademica.getIdProblematicaAcademica());
                confirmacionConsulta = preparacionConsulta.executeUpdate();
                conexionBD.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        }
        return confirmacionConsulta;
    }
}
