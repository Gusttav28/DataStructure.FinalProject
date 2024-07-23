package modelo;

import java.io.Serializable;
/**
 *
 * @author Mike T
 */
public class Vehiculo implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String marca;
    private String modelo;
    private int año;
    private String placa;
    private double precioPorDia;
    private boolean disponible;
    private Cliente clienteActual;

    public Vehiculo(String marca, String modelo, int año, String placa, double precioPorDia, boolean disponible) 
    {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.placa = placa;
        this.precioPorDia = precioPorDia;
        this.disponible = disponible;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAño() { return año; }
    public void setAño(int año) { this.año = año; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public double getPrecioPorDia() { return precioPorDia; }
    public void setPrecioPorDia(double precioPorDia) { this.precioPorDia = precioPorDia; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setClienteActual(Cliente cliente) { this.clienteActual = cliente; }
    public Cliente getClienteActual() { return clienteActual; }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Marca: ").append(marca)
                .append(" - Modelo: ").append(modelo)
                .append(" - Año: ").append(año)
                .append(" - Placa: ").append(placa)
                .append(" - Precio por día: $").append(precioPorDia)
                .append(" - Estado: ").append(disponible ? "Disponible" : "No disponible");

        if (!disponible && clienteActual != null) 
        {
            sb.append(" - Alquilado por: ").append(clienteActual.getNombre())
                    .append(" ").append(clienteActual.getApellido())
                    .append(" (ID: ").append(clienteActual.getIdentificacion()).append(")");
        }

        return sb.toString();
    }
}
