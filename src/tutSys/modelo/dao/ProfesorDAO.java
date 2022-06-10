package tutSys.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.Profesor;

public class ProfesorDAO {

    public static ArrayList<Profesor> recuperarTodosProfesores(){
        ArrayList<Profesor> listaProfesoresBD = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if (conexionBD != null){
            String consulta = "SELECT * FROM profesor";
            try {
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = preparacionConsulta.executeQuery();

                while (resultadoConsulta.next()){
                    Profesor profesorRecuperado = new Profesor();
                    profesorRecuperado.setNombre(resultadoConsulta.getString("nombre"));
                    profesorRecuperado.setCorreoElectronio(resultadoConsulta.getString("correoElectronico"));
                    profesorRecuperado.setNumeroEmpleado(resultadoConsulta.getString("numeroEmpleado"));
                    profesorRecuperado.setNumeroTelefono(resultadoConsulta.getString("numeroTelefono"));
                    listaProfesoresBD.add(profesorRecuperado);
                }

                conexionBD.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return listaProfesoresBD;
    }

    public static Profesor recuperarProfesor(String nombre){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        Profesor profesorRecuperado = new Profesor();
        if(conexionBD != null){
            String consulta = "SELECT * FROM profesor WHERE nombre = ?";

            try{
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                preparacionConsulta.setString(1, nombre);
                ResultSet resultadoConsulta = preparacionConsulta.executeQuery();
                profesorRecuperado.setNombre(resultadoConsulta.getString("nombre"));
                profesorRecuperado.setCorreoElectronio(resultadoConsulta.getString("correoElectronico"));
                profesorRecuperado.setNumeroEmpleado(resultadoConsulta.getString("numeroEmpleado"));
                profesorRecuperado.setNumeroTelefono(resultadoConsulta.getString("numeroTelefono"));
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return profesorRecuperado;
    }
}
