package tutSys.modelo.pojo;

/**
 * Autor: Axel Utrera
 * Fecha de creacion: 17/05/2022
 * Ultima modificacion: 22/05/2022
 * Nombre modificador: Axel Utrera
 */

public class Profesor {
    private int idProfesor;
    private String correoElectronio;
    private String nombre;
    private String numeroEmpleado;
    private String numeroTelefono;

    public Profesor() {
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getCorreoElectronio() {
        return correoElectronio;
    }

    public void setCorreoElectronio(String correoElectronio) {
        this.correoElectronio = correoElectronio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
