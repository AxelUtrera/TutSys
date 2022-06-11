/**
 *Autor: Daniel Eduardo Anota Paxtian
 *Fecha de creacion: 10/06/2022
 *Ultima modificacion: 10/06/2022
 *Nombre modificador: Daniel Eduardo Anota Paxtian
 */

package tutSys.modelo.dao;

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.TutorAcademico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorAcademicoDAO {
    public static TutorAcademico recuperarTutorAcademico(String usuario, String contrasenia){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        TutorAcademico tutorRecuperado = new TutorAcademico();
        if(conexionBD != null){
            try {
                String consulta = "SELECT * FROM tutoracademico WHERE usuario = ? AND contrasenia = ?";
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setString(1, usuario);
                prepararConsulta.setString(2, contrasenia);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                if(resultadoConsulta.next()){
                    tutorRecuperado.setNombre(resultadoConsulta.getString("nombre"));
                }else {
                    tutorRecuperado = null;
                }
                conexionBD.close();
            } catch (SQLException excepcion){
                excepcion.printStackTrace();
            }
        }
        return tutorRecuperado;
    }
}
