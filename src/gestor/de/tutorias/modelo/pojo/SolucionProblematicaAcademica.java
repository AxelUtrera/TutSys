package gestor.de.tutorias.modelo.pojo;

public class SolucionProblematicaAcademica {
    private String descripcion;
    private String titulo;

    public SolucionProblematicaAcademica(String descripcion, String titulo) {
        this.descripcion = descripcion;
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
