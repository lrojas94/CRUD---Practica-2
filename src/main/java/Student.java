/**
 * Created by luis on 5/29/16.
 */
public class Student {
    private int matricula;
    private String nombre;
    private String apellido;
    private String telefono;

    public Student(){}

    public int getMatricula() {
        return matricula;
    }

    public String getNombreCompleto(){
        return this.getNombre() + " " + this.getApellido();
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // GETTERS AND SETTERS:

}
