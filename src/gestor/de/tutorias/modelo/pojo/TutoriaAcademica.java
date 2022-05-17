package gestor.de.tutorias.modelo.pojo;

import java.sql.Date;

public class TutoriaAcademica {
    private Date fechaCierreReporte;
    private Date fechaTutoria;
    private int numeroTutoria;

    public TutoriaAcademica(Date fechaCierreReporte, Date fechaTutoria, int numeroTutoria) {
        this.fechaCierreReporte = fechaCierreReporte;
        this.fechaTutoria = fechaTutoria;
        this.numeroTutoria = numeroTutoria;
    }

    public Date getFechaCierreReporte() {
        return fechaCierreReporte;
    }

    public void setFechaCierreReporte(Date fechaCierreReporte) {
        this.fechaCierreReporte = fechaCierreReporte;
    }

    public Date getFechaTutoria() {
        return fechaTutoria;
    }

    public void setFechaTutoria(Date fechaTutoria) {
        this.fechaTutoria = fechaTutoria;
    }

    public int getNumeroTutoria() {
        return numeroTutoria;
    }

    public void setNumeroTutoria(int numeroTutoria) {
        this.numeroTutoria = numeroTutoria;
    }
}
