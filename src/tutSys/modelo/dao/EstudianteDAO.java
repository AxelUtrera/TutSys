package tutSys.modelo.dao;

import javafx.scene.control.CheckBox;
import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.Estudiante;

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
            }
        }
        return estudiantesRecuperados;
    }
}
