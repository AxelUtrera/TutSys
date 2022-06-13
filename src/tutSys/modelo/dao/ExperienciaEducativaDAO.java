package tutSys.modelo.dao;

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.ExperienciaEducativa;

/**
 *
 * fecha de creacion: 8 / 06 / 2022
 * ultima modificacion : 10 / 06 / 2022
 * autor: Axel Utrera
 *
 * */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExperienciaEducativaDAO {

    public static ArrayList<ExperienciaEducativa> recuperarTodasExperienciasEducativas(){
        ArrayList<ExperienciaEducativa> listaExperienciasEducativas = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        String consulta = "SELECT * FROM experienciaEducativa";
        if(conexionBD != null) {
            try {
                PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = preparacionConsulta.executeQuery();

                while (resultadoConsulta.next()) {
                    ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                    experienciaEducativa.setIdExperienciaEducativa(resultadoConsulta.getInt("idExperienciaEducativa"));
                    experienciaEducativa.setNombre(resultadoConsulta.getString("nombre"));
                    experienciaEducativa.setProgramaEducativo(resultadoConsulta.getString("idProgramaEducativo"));
                    experienciaEducativa.setCreditos(resultadoConsulta.getInt("creditos"));
                    experienciaEducativa.setNrc(resultadoConsulta.getString("NRC"));
                    listaExperienciasEducativas.add(experienciaEducativa);
                }
                conexionBD.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaExperienciasEducativas;
    }

    public static ExperienciaEducativa recuperarExperienciaEducativa(int idProfesor, String experienciaEducativa){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        String consulta =  "SELECT * from experienciaeducativa where experienciaeducativa.idProfesor = ? and experienciaeducativa.nombre = ?";
        ExperienciaEducativa experienciaEducativaRecuperada = new ExperienciaEducativa();
        try{
            PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
            preparacionConsulta.setInt(1, idProfesor);
            preparacionConsulta.setString(2, experienciaEducativa);
            ResultSet resultadoConsulta = preparacionConsulta.executeQuery();

            if (resultadoConsulta.next()){
                experienciaEducativaRecuperada.setIdExperienciaEducativa(resultadoConsulta.getInt("idExperienciaEducativa"));
                experienciaEducativaRecuperada.setNombre(resultadoConsulta.getString("nombre"));
                experienciaEducativaRecuperada.setNrc(resultadoConsulta.getString("NRC"));
                experienciaEducativaRecuperada.setCreditos(resultadoConsulta.getInt("creditos"));
                experienciaEducativaRecuperada.setIdProfesor(resultadoConsulta.getInt("idProfesor"));
                experienciaEducativaRecuperada.setIdProgramaEducativo(resultadoConsulta.getInt("idProgramaEducativo"));
                experienciaEducativaRecuperada.setIdPeriodoEscolar(resultadoConsulta.getInt("idPeriodoEscolar"));
            }
            conexionBD.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return experienciaEducativaRecuperada;
    }
}
