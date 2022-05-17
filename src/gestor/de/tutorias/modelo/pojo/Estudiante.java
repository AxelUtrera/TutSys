package gestor.de.tutorias.modelo.pojo;

public class Estudiante {
    private String correoInstitucional;
    private String matricula;
    private String nombre;
    private String periodosCursados;
    private String programaEducativo;

    public Estudiante(String correoInstitucional, String matricula, String nombre, String periodosCursados, String programaEducativo) {
        this.correoInstitucional = correoInstitucional;
        this.matricula = matricula;
        this.nombre = nombre;
        this.periodosCursados = periodosCursados;
        this.programaEducativo = programaEducativo;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodosCursados() {
        return periodosCursados;
    }

    public void setPeriodosCursados(String periodosCursados) {
        this.periodosCursados = periodosCursados;
    }

    public String getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(String programaEducativo) {
        this.programaEducativo = programaEducativo;
    }
}
