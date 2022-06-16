package tutSys.pruebas;

import org.junit.Assert;
import org.junit.Test;
import tutSys.modelo.dao.PeriodoEscolarDAO;
import tutSys.modelo.pojo.PeriodoEscolar;

import java.sql.Date;

public class PeriodoEscolarDAOPrueba {


    @Test
    public void obtenerPeriodoEscolarActualTestExitoso(){
        Long millis = System.currentTimeMillis();
        Date fechaActual = new Date(millis);
        String periodoEsperado = "Feb-Jul2022";
        PeriodoEscolar periodoObtenido = PeriodoEscolarDAO.obtenerPeriodoEscolarActual(fechaActual);
        Assert.assertEquals(periodoEsperado, periodoObtenido.getId());
    }

    @Test
    public void obtenerPeriodoEscolarActualTestFallido(){
        Date fechaActual = Date.valueOf("2022-08-15");
        String periodoEsperado = "Feb-Jul2022";
        PeriodoEscolar periodoObtenido = PeriodoEscolarDAO.obtenerPeriodoEscolarActual(fechaActual);
        Assert.assertNotEquals(periodoEsperado, periodoObtenido.getId());
    }
}
