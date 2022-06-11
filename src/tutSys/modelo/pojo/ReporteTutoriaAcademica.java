package tutSys.modelo.pojo;

public class ReporteTutoriaAcademica {
    private int idReporteTutoriaAcademica;
    private int numeroSesionTutoria;
    private int numeroAlumnosAsistentes;
    private int numeroAlumnosRiesgo;
    private String programaEducativo;
    private String idPeriodoEscolar;

     public ReporteTutoriaAcademica(){

     }

    public ReporteTutoriaAcademica(int numeroAlumnosAsistentes, int numeroAlumnosRiesgo, String programaEducativo) {
        this.numeroAlumnosAsistentes = numeroAlumnosAsistentes;
        this.numeroAlumnosRiesgo = numeroAlumnosRiesgo;
        this.programaEducativo = programaEducativo;
    }

    public String getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    public void setIdPeriodoEscolar(String idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    public int getIdReporteTutoriaAcademica() {
        return idReporteTutoriaAcademica;
    }

    public void setIdReporteTutoriaAcademica(int idReporteTutoriaAcademica) {
        this.idReporteTutoriaAcademica = idReporteTutoriaAcademica;
    }

    public int getNumeroSesionTutoria() {
        return numeroSesionTutoria;
    }

    public void setNumeroSesionTutoria(int numeroSesionTutoria) {
        this.numeroSesionTutoria = numeroSesionTutoria;
    }

    public int getNumeroAlumnosAsistentes() {
        return numeroAlumnosAsistentes;
    }

    public void setNumeroAlumnosAsistentes(int numeroAlumnosAsistentes) {
        this.numeroAlumnosAsistentes = numeroAlumnosAsistentes;
    }

    public int getNumeroAlumnosRiesgo() {
        return numeroAlumnosRiesgo;
    }

    public void setNumeroAlumnosRiesgo(int numeroAlumnosRiesgo) {
        this.numeroAlumnosRiesgo = numeroAlumnosRiesgo;
    }

    public String getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(String programaEducativo) {
        this.programaEducativo = programaEducativo;
    }
}
