package tutSys.modelo.pojo;

/**
 * Autor: Axel Utrera
 * fecha de creacion: 08 / 06 /2022
 * Ultima modificacion: 15 / 06 / 2022
 * Nombre modificador: Daniel Eduardo Anota Paxtian
 */

import javafx.scene.control.CheckBox;

public class Estudiante {
    private int idEstudiante;
    private String correoInstitucional;
    private String matricula;
    private String nombre;
    private String periodosCursados;
    private String programaEducativo;
    private CheckBox asiste;
    private CheckBox riesgo;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String correoInstitucional, String matricula, String nombre, String periodosCursados, String programaEducativo) {
        this.idEstudiante = idEstudiante;
        this.correoInstitucional = correoInstitucional;
        this.matricula = matricula;
        this.nombre = nombre;
        this.periodosCursados = periodosCursados;
        this.programaEducativo = programaEducativo;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
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

    public CheckBox getAsiste() {
        return asiste;
    }

    public void setAsiste(CheckBox asiste) {
        this.asiste = asiste;
    }

    public CheckBox getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(CheckBox riesgo) {
        this.riesgo = riesgo;
    }
}
