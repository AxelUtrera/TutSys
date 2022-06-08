package test;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import tutSys.modelo.ConexionBD;

import java.sql.Connection;

public class ConexionBDTest {

    @Test
    public void conexionBDTestExitoso(){
        Connection resultado = ConexionBD.abrirConexionBD();
        assertNotNull(resultado);
    }
}


