package gestor.de.tutorias.modelo.pojo;

public class ProblematicaAcademica {
    private String descripcion;
    private int numeroResportes;

    public ProblematicaAcademica(String descripcion, int numeroResportes) {
        this.descripcion = descripcion;
        this.numeroResportes = numeroResportes;
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
}
