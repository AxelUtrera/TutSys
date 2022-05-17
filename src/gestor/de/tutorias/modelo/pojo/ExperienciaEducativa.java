package gestor.de.tutorias.modelo.pojo;

public class ExperienciaEducativa {
    private int creditos;
    private String nombre;
    private String nrc;
    private String programaEducativo;

    public ExperienciaEducativa(int creditos, String nombre, String nrc, String programaEducativo) {
        this.creditos = creditos;
        this.nombre = nombre;
        this.nrc = nrc;
        this.programaEducativo = programaEducativo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(String programaEducativo) {
        this.programaEducativo = programaEducativo;
    }
}
