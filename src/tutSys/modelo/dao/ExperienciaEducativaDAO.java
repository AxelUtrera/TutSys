package tutSys.modelo.dao;

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.ExperienciaEducativa;

/*
 *
 * fecha de creacion: 8 / 06 / 2022
 *
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
        try {
            PreparedStatement preparacionConsulta = conexionBD.prepareStatement(consulta);
            ResultSet resultadoConsulta = preparacionConsulta.executeQuery();

            while(resultadoConsulta.next()){
                ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                experienciaEducativa.setNombre(resultadoConsulta.getString("nombre"));
                experienciaEducativa.setProgramaEducativo(resultadoConsulta.getString("idProgramaEducativo"));
                experienciaEducativa.setCreditos(resultadoConsulta.getInt("creditos"));
                experienciaEducativa.setNrc(resultadoConsulta.getString("NRC"));
                listaExperienciasEducativas.add(experienciaEducativa);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return listaExperienciasEducativas;
    }


}
