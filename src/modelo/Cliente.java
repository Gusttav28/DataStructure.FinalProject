package modelo;

import java.io.Serializable;
/**
 *
 * @author Mike T
 */
public class Cliente implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String direccion;
    private String telefono;

    public Cliente(String nombre, String apellido, String identificacion, String direccion, String telefono) 
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    @Override
    public String toString() 
    {
        return "Nombre: " + nombre
                + " - Apellido: " + apellido
                + " - ID: " + identificacion
                + " - Dirección: " + direccion
                + " - Teléfono: " + telefono;
    }
}
