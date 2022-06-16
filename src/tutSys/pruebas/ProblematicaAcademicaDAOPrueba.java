package tutSys.pruebas;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import tutSys.modelo.dao.ProblematicaAcademicaDAO;
import tutSys.modelo.pojo.ProblematicaAcademica;
import tutSys.modelo.pojo.ProblematicaAcademicaAux;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class ProblematicaAcademicaDAOPrueba {

    @Test
    public void agregarProblematicaAcademicaTestExitoso(){
        String descripcion = "Problematica Junit";
        ProblematicaAcademica problematicaPrueba = new ProblematicaAcademica(descripcion, 1, 31, 1);
        int respuesta = ProblematicaAcademicaDAO.agregarProblematicaAcademica(problematicaPrueba);
        Assert.assertEquals(1, respuesta);
    }

    //Prueba mal hecha
    @Test (expected = ExceptionInInitializerError.class)
    public void agregarProblematicaAcademicaTestFallido(){
        String descripcion = "Problematica Junit";
        ProblematicaAcademica problematicaPrueba = new ProblematicaAcademica(descripcion, 1, 31, 1);
        ProblematicaAcademicaDAO.agregarProblematicaAcademica(problematicaPrueba);
    }

    @Test
    public void obtenerProblematicasAcademicasTestExitoso(){
        ArrayList<ProblematicaAcademicaAux> problematicasObtenidas = ProblematicaAcademicaDAO.obtenerProblematicasAcademicas(1);
        Assert.assertNotNull(problematicasObtenidas);
    }

    @Test
    public void obtenerProblematicasAcademicasTestFallido(){
        ArrayList<ProblematicaAcademicaAux> valorEsperado = new ArrayList<>();
        ArrayList<ProblematicaAcademicaAux> problematicasObtenidas = ProblematicaAcademicaDAO.obtenerProblematicasAcademicas(99);
        Assert.assertEquals(valorEsperado, problematicasObtenidas);
    }

    @Test
    public void eliminarProblematicaAcademicaTestExitoso(){
        int valorEsperado = 1;
        int valorObtenido = ProblematicaAcademicaDAO.eliminarProblematicaAcademica(29);
        Assert.assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void eliminarProblematicaAcademicaTestFallido(){
        int valorEsperado = 0;
        int valorObtenido = ProblematicaAcademicaDAO.eliminarProblematicaAcademica(29);
        Assert.assertEquals(valorEsperado, valorObtenido);
    }
}
