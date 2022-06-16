package tutSys.pruebas;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import tutSys.modelo.dao.EstudianteDAO;
import tutSys.modelo.pojo.Estudiante;

import java.util.ArrayList;



public class EstudianteDAOPrueba {

    @Test
    public void recuperarEstudiantesTutoradosPruebaExitosa(){
        ArrayList<Estudiante> estudiantesRecuperados = EstudianteDAO.recuperarEstudiantesTutorados(2);
        assertNotNull(estudiantesRecuperados);
    }

    @Test
    public void recuperarEstudiantesTutoradosPruebaFallida(){
        ArrayList<Estudiante> estudiantesRecuperados = EstudianteDAO.recuperarEstudiantesTutorados(23232323);
        assertNotNull(estudiantesRecuperados);
    }
}
