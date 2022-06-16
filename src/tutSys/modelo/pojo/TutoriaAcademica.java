package tutSys.modelo.pojo;

/**
 * Autor: Axel Utrera
 * fecha de creacion: 08 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import java.sql.Date;

public class TutoriaAcademica {
    private int idTutoriaAcademica;
    private Date fechaCierreReporte;
    private Date fechaTutoria;
    private int numeroTutoria;

    public TutoriaAcademica() {
    }

    public TutoriaAcademica(int idTutoriaAcademica, Date fechaCierreReporte, Date fechaTutoria, int numeroTutoria) {
        this.idTutoriaAcademica = idTutoriaAcademica;
        this.fechaCierreReporte = fechaCierreReporte;
        this.fechaTutoria = fechaTutoria;
        this.numeroTutoria = numeroTutoria;
    }

    public int getIdTutoriaAcademica() {
        return idTutoriaAcademica;
    }

    public void setIdTutoriaAcademica(int idTutoriaAcademica) {
        this.idTutoriaAcademica = idTutoriaAcademica;
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

    @Override
    public String toString() {
        return "Tutoria " + getNumeroTutoria();
    }
}
