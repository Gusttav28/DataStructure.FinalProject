package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Mike T
 */
public class Alquiler implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Alquiler(Cliente cliente, Vehiculo vehiculo, String fechaInicio, String fechaFin) 
    {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = LocalDate.parse(fechaInicio, formatter);
        this.fechaFin = LocalDate.parse(fechaFin, formatter);
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
    public String getFechaInicio() { return fechaInicio.format(formatter); }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = LocalDate.parse(fechaInicio, formatter); }
    public String getFechaFin() { return fechaFin.format(formatter); }
    public void setFechaFin(String fechaFin) { this.fechaFin = LocalDate.parse(fechaFin, formatter); }

    @Override
    public String toString() 
    {
        return "Cliente: " + cliente.getNombre() + " " + cliente.getApellido() +
               ", Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo() +
               ", Fecha Inicio: " + getFechaInicio() +
               ", Fecha Fin: " + getFechaFin();
    }
}