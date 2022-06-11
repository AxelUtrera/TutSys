package tutSys.modelo.pojo;

public class ProblematicaAcademica {
    private int idProblematicaAcademica;
    private String descripcion;
    private int numeroResportes;
    private int idReporteTutoria;
    private int idExperienciaEducativa;

    public ProblematicaAcademica(){

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroResportes() {
        return numeroResportes;
    }

    public void setNumeroResportes(int numeroResportes) {
        this.numeroResportes = numeroResportes;
    }

    public int getIdProblematicaAcademica() {
        return idProblematicaAcademica;
    }

    public void setIdProblematicaAcademica(int idProblematicaAcademica) {
        this.idProblematicaAcademica = idProblematicaAcademica;
    }

    public int getIdReporteTutoria() {
        return idReporteTutoria;
    }

    public void setIdReporteTutoria(int idReporteTutoria) {
        this.idReporteTutoria = idReporteTutoria;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }
}
