package gestor.de.tutorias.modelo.pojo;

import java.sql.Date;

public class PeriodoEscolar {
    private Date fechaFin;
    private Date fechaInicio;
    private String id;

    public PeriodoEscolar(Date fechaFin, Date fechaInicio, String id) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.id = id;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
