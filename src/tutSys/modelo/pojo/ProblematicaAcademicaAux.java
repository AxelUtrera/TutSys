package tutSys.modelo.pojo;

/**
 * Autor: Daniel Eduardo Anota Paxtian
 * fecha de creacion: 14 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

public class ProblematicaAcademicaAux {
    int idReporteTutoriaAcademica;
    int idProblematicaAcademica;
    String descripcion;
    int numeroReportes;
    int idExperienciaEducativa;
    String nombreExperienciaEducativa;
    int idProfesor;
    String nombreProfesor;

    public ProblematicaAcademicaAux() {
    }

    public ProblematicaAcademicaAux(int idReporteTutoriaAcademica, int idProblematicaAcademica, String descripcion, int numeroReportes, int idExperienciaEducativa, String nombreExperienciaEducativa, int idProfesor, String nombreProfesor) {
        this.idReporteTutoriaAcademica = idReporteTutoriaAcademica;
        this.idProblematicaAcademica = idProblematicaAcademica;
        this.descripcion = descripcion;
        this.numeroReportes = numeroReportes;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
    }

    public int getIdProblematicaAcademica() {
        return idProblematicaAcademica;
    }

    public int getIdReporteTutoriaAcademica() {
        return idReporteTutoriaAcademica;
    }

    public void setIdReporteTutoriaAcademica(int idReporteTutoriaAcademica) {
        this.idReporteTutoriaAcademica = idReporteTutoriaAcademica;
    }

    public void setIdProblematicaAcademica(int idProblematicaAcademica) {
        this.idProblematicaAcademica = idProblematicaAcademica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroReportes() {
        return numeroReportes;
    }

    public void setNumeroReportes(int numeroReportes) {
        this.numeroReportes = numeroReportes;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public String getNombreExperienciaEducativa() {
        return nombreExperienciaEducativa;
    }

    public void setNombreExperienciaEducativa(String nombreExperienciaEducativa) {
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
}
