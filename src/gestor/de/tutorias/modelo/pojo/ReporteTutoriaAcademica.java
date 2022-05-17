package gestor.de.tutorias.modelo.pojo;

public class ReporteTutoriaAcademica {
    private int numeroAlumnosAsistentes;
    private int numeroAlumnosRiesgo;
    private String programaEducativo;

    public ReporteTutoriaAcademica(int numeroAlumnosAsistentes, int numeroAlumnosRiesgo, String programaEducativo) {
        this.numeroAlumnosAsistentes = numeroAlumnosAsistentes;
        this.numeroAlumnosRiesgo = numeroAlumnosRiesgo;
        this.programaEducativo = programaEducativo;
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
