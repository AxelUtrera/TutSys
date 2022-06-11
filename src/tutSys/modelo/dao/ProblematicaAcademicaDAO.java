package tutSys.modelo.dao;
/**
 *
 * fecha de creacion: 9 / 06 / 2022
 * Ultima modificacion: 10  / 06 / 2022
 * autor: Axel Utrera
 *
 * */
import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.ProblematicaAcademica;

import java.sql.*;

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
            }
        }
        return respuesta;
    }

    //public static ProblematicaAcademica consultarProblematicaAcademica(String idProblematica){

    //}
}
