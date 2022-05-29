package tutSys.modelo.pojo;

public class TutorAcademico {
    private String correoElectronico;
    private String nombre;
    private String programaEducativo;
    private String telefono;

    public TutorAcademico(String correoElectronico, String nombre, String programaEducativo, String telefono) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.programaEducativo = programaEducativo;
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(String programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
