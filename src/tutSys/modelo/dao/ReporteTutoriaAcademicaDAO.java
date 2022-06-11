package tutSys.modelo.dao;
/**
 *
 * fecha de creacion: 8 / 06 / 2022
 * ultima modificacion : 10 / 06 / 2022
 * autor: Axel Utrera
 *
 * */

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.ReporteTutoriaAcademica;
import tutSys.modelo.pojo.TutorAcademico;

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
            }
        }

        return listaReportesRecuperados;
    }
}
