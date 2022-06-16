package tutSys.modelo.dao;

/**
 * Autor: Axel Utrera
 * fecha de creacion: 08 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.ReporteTutoriaAcademica;
import tutSys.modelo.pojo.TutorAcademico;
import tutSys.utilidades.CuadroDialogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReporteTutoriaAcademicaDAO {

    public static ArrayList<ReporteTutoriaAcademica> consultarReportesTutoriaPorTutor(TutorAcademico tutorAcademico){
        ArrayList<ReporteTutoriaAcademica> listaReportesRecuperados = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        String consulta = "select idReporteTutoriaAcademica, tutoriaacademica.numeroTutoria, periodoescolar.identificador \n" +
                "from reportetutoriaacademica \n" +
                "inner join tutoracademico on reportetutoriaacademica.idTutorAcademico  = tutoracademico.idTutorAcademico \n" +
                "and tutoracademico.nombre = ?" +
                "inner join tutoriaacademica on reportetutoriaacademica.idTutoriaAcademica = tutoriaacademica.idTutoriaAcademica\n" +
                "inner join periodoescolar on reportetutoriaacademica.idTutoriaAcademica = tutoriaacademica.idTutoriaAcademica \n" +
                "and tutoriaacademica.idPeriodoEscolar = periodoescolar.idPeriodoEscolar;";

        if (conexionBD != null && tutorAcademico != null){
            try{
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                preparacionConsulta.setString(1, tutorAcademico.getNombre());
                ResultSet resultadoConsulta = preparacionConsulta.executeQuery();

                while (resultadoConsulta.next()){
                    ReporteTutoriaAcademica reporteTutoriaAcademica = new ReporteTutoriaAcademica();
                    reporteTutoriaAcademica.setIdReporteTutoriaAcademica(resultadoConsulta.getInt("idReporteTutoriaAcademica"));
                    reporteTutoriaAcademica.setNumeroSesionTutoria(resultadoConsulta.getInt("numeroTutoria"));
                    reporteTutoriaAcademica.setIdPeriodoEscolar(resultadoConsulta.getString("identificador"));
                    listaReportesRecuperados.add(reporteTutoriaAcademica);
                }
            }catch (SQLException ex){
                ex.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexion con BD",
                        "No se ha podido establecer conexión con la base de datos, inténtelo más tarde");
            }
        }

        return listaReportesRecuperados;
    }


    public static int registrarReporteTutoriaAcademica(int numeroAsistentes, int numeroRiesgo, int idTutor, int idTutoria){
        int registroExitoso = 0;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        String consulta = "INSERT INTO reportetutoriaacademica " +
                "(numeroAlumnosAsistentes, numeroAlumnosRiesgo, idTutorAcademico, idTutoriaAcademica) VALUES (?, ?, ?, ?);";
        if(conexionBD != null){
            try {
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setInt(1, numeroAsistentes);
                prepararConsulta.setInt(2, numeroRiesgo);
                prepararConsulta.setInt(3, idTutor);
                prepararConsulta.setInt(4, idTutoria);
                registroExitoso = prepararConsulta.executeUpdate();
            } catch (SQLException excepcion){
                excepcion.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        }
        return registroExitoso;
    }

    public static int obtenerReporteTutoriaUnico(int idTutorAcademico, int idTutoriaAcademica){
       Connection conexionBD = ConexionBD.abrirConexionBD();
       int idReporteTutoria = 0;
       String consulta = "SELECT * FROM reportetutoriaacademica WHERE idTutorAcademico = ? AND idTutoriaAcademica = ?";
       if(conexionBD != null){
           try{
               PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
               prepararConsulta.setInt(1, idTutorAcademico);
               prepararConsulta.setInt(2, idTutoriaAcademica);
               ResultSet resultadoConsulta = prepararConsulta.executeQuery();
               if (resultadoConsulta.next()){
                   idReporteTutoria = resultadoConsulta.getInt("idReporteTutoriaAcademica");
               }
           } catch (SQLException excepcion){
               excepcion.printStackTrace();
               CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                       " con la base de datos, intentelo mas tarde");
           }
       }
       return idReporteTutoria;
    }


    public static ReporteTutoriaAcademica obtenerReporteTutoriaAcademicaPorId(int idReporteTutoria){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        ReporteTutoriaAcademica reporteTutoriaAcademica = new ReporteTutoriaAcademica();
        String consulta = "select Distinct idReporteTutoriaAcademica, tutoriaacademica.numeroTutoria, periodoescolar.identificador " +
                "from tutsys.reportetutoriaacademica " +
                "inner join tutsys.tutoracademico on reportetutoriaacademica.idReporteTutoriaAcademica = ? "+
                "inner join tutsys.tutoriaacademica on reportetutoriaacademica.idTutoriaAcademica = tutoriaacademica.idTutoriaAcademica " +
                "inner join tutsys.periodoescolar on reportetutoriaacademica.idTutoriaAcademica = tutoriaacademica.idTutoriaAcademica " +
                "and tutoriaacademica.idPeriodoEscolar = periodoescolar.idPeriodoEscolar;";
        if (conexionBD != null) {
            try {
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                preparacionConsulta.setInt(1, idReporteTutoria);
                ResultSet resultadoConsulta = preparacionConsulta.executeQuery();

                if(resultadoConsulta.next()){
                    reporteTutoriaAcademica.setIdReporteTutoriaAcademica(resultadoConsulta.getInt("idReporteTutoriaAcademica"));
                    reporteTutoriaAcademica.setNumeroSesionTutoria(resultadoConsulta.getInt("numeroTutoria"));
                    reporteTutoriaAcademica.setIdPeriodoEscolar(resultadoConsulta.getString("identificador"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return reporteTutoriaAcademica;
    }

}
