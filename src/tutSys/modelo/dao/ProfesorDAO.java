package tutSys.modelo.dao;
/**
 *
 * fecha de creacion: 8 / 06 / 2022
 * Ultima modificacion: 10  / 06 / 2022
 * autor: Axel Utrera
 *
 * */
import java.sql.*;
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
                    profesorRecuperado.setIdProfesor(resultadoConsulta.getInt("idProfesor"));
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

            try {
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                preparacionConsulta.setString(1, nombre);
                ResultSet resultadoConsulta = preparacionConsulta.executeQuery();
                if (resultadoConsulta.next()){
                    profesorRecuperado.setIdProfesor(resultadoConsulta.getInt("idProfesor"));
                    profesorRecuperado.setNombre(resultadoConsulta.getString("nombre"));
                    profesorRecuperado.setCorreoElectronio(resultadoConsulta.getString("correoElectronico"));
                    profesorRecuperado.setNumeroEmpleado(resultadoConsulta.getString("numeroEmpleado"));
                    profesorRecuperado.setNumeroTelefono(resultadoConsulta.getString("numeroTelefono"));
                }
                conexionBD.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return profesorRecuperado;
    }

    public static ArrayList<Profesor> recuperarProfesoresPorExperienciaEducativa(String nombreExperienciaEducativa){
        ArrayList<Profesor> profesoresRecuperador = new ArrayList<>();
        Connection conexionBD = new ConexionBD().abrirConexionBD();
        String consulta =  "SELECT profesor.idProfesor, profesor.nombre, numeroEmpleado, correoElectronico, numeroTelefono " +
                "from profesor inner join experienciaeducativa " +
                "on experienciaeducativa.idProfesor = profesor.idProfesor and experienciaeducativa.nombre = (?);";
        if (conexionBD != null){
            try{
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                preparacionConsulta.setString(1, nombreExperienciaEducativa);
                ResultSet resultadoConsulta  = preparacionConsulta.executeQuery();

                while(resultadoConsulta.next()){
                    Profesor profesorEncontrado = new Profesor();
                    profesorEncontrado.setIdProfesor(resultadoConsulta.getInt("idProfesor"));
                    profesorEncontrado.setNombre(resultadoConsulta.getString("nombre"));
                    profesorEncontrado.setNumeroEmpleado(resultadoConsulta.getString("numeroEmpleado"));
                    profesorEncontrado.setCorreoElectronio(resultadoConsulta.getString("correoElectronico"));
                    profesorEncontrado.setNumeroTelefono(resultadoConsulta.getString("numeroTelefono"));
                    profesoresRecuperador.add(profesorEncontrado);
                }
                conexionBD.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return profesoresRecuperador;
    }
}
