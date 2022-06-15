package tutSys.modelo.dao;

/**
 * Autor: Daniel Eduardo Anota Paxtian
 * fecha de creacion: 13 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import tutSys.modelo.ConexionBD;
import tutSys.modelo.pojo.PeriodoEscolar;
import tutSys.utilidades.CuadroDialogo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PeriodoEscolarDAO {

    public static PeriodoEscolar obtenerPeriodoEscolarActual(Date fechaActual){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        PeriodoEscolar periodoActual = new PeriodoEscolar();
        if (conexionBD != null){
            String consulta = "SELECT * FROM periodoescolar WHERE ? BETWEEN fechaInicio AND fechaFin";
            try{
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setDate(1, fechaActual);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                if(resultadoConsulta.next()){
                    periodoActual.setIdPeriodoEscolar(resultadoConsulta.getInt("idPeriodoEscolar"));
                    periodoActual.setFechaInicio(resultadoConsulta.getDate("fechaInicio"));
                    periodoActual.setFechaFin(resultadoConsulta.getDate("fechaFin"));
                    periodoActual.setId(resultadoConsulta.getString("identificador"));
                    conexionBD.close();
                } else {
                    periodoActual = null;
                }
            } catch (SQLException excepcion){
                excepcion.printStackTrace();
                CuadroDialogo.crearCuadroDialogoError("Sin conexión con la base de datos", "No hay conexión" +
                        " con la base de datos, intentelo mas tarde");
            }
        }
        return periodoActual;
    }
}
