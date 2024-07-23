package modelo;

import java.io.Serializable;
import java.io.*;
/**
 *
 * @author Mike T
 */
public class SistemaRentACar implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private ListaVehiculos vehiculos;
    private ArbolClientes clientes;
    private ColaAlquileres alquileresActivos;
    private PilaHistorial historialAlquileres;

    public SistemaRentACar() 
    {
        vehiculos = new ListaVehiculos();
        clientes = new ArbolClientes();
        alquileresActivos = new ColaAlquileres();
        historialAlquileres = new PilaHistorial();
    }

    public boolean agregarVehiculo(Vehiculo v) 
    {
        if (vehiculos.buscarVehiculo(v.getPlaca()) != null) 
        {
            return false; // Ya existe un vehículo con esa placa
        }
        vehiculos.agregarVehiculo(v);
        return true;
    }

    public boolean agregarCliente(Cliente c) 
    {
        if (clientes.buscarCliente(c.getIdentificacion()) != null) 
        {
            return false; // Ya existe un cliente con esa identificación
        }
        clientes.agregarCliente(c);
        return true;
    }

    public void alquilarVehiculo(String idCliente, String placa, String fechaInicio, String fechaFin) 
    {
        Cliente c = clientes.buscarCliente(idCliente);
        Vehiculo v = vehiculos.buscarVehiculo(placa);
        if (c != null && v != null && v.isDisponible()) 
        {
            Alquiler alquiler = new Alquiler(c, v, fechaInicio, fechaFin);
            v.setDisponible(false);
            v.setClienteActual(c);
            alquileresActivos.encolar(alquiler);
        }
    }

    public void devolverVehiculo(String placa) 
    {
        ColaAlquileres colaTemp = new ColaAlquileres();
        Alquiler alquilerDevuelto = null;
        while (!alquileresActivos.estaVacia()) 
        {
            Alquiler a = alquileresActivos.desencolar();
            if (a.getVehiculo().getPlaca().equals(placa)) 
            {
                alquilerDevuelto = a;
                a.getVehiculo().setDisponible(true);
                a.getVehiculo().setClienteActual(null);
            } else {
                colaTemp.encolar(a);
            }
        }
        while (!colaTemp.estaVacia()) 
        {
            alquileresActivos.encolar(colaTemp.desencolar());
        }
        if (alquilerDevuelto != null) 
        {
            historialAlquileres.apilar(alquilerDevuelto);
        }
    }

    public String obtenerListaVehiculos() 
    {
        return vehiculos.listarVehiculos();
    }

    public String listarVehiculosDisponibles() 
    {
        return vehiculos.listarVehiculosDisponibles();
    }

    public String listarClientes() 
    {
        return clientes.listarClientes();
    }

    public String listarAlquileresActivos() 
    {
        StringBuilder sb = new StringBuilder();
        ColaAlquileres colaTemp = new ColaAlquileres();
        while (!alquileresActivos.estaVacia()) {
            Alquiler a = alquileresActivos.desencolar();
            sb.append("Placa: ").append(a.getVehiculo().getPlaca())
              .append(" - Cliente: ").append(a.getCliente().getIdentificacion())
              .append(" - Fecha inicio: ").append(a.getFechaInicio())
              .append(" - Fecha fin: ").append(a.getFechaFin())
              .append("\n");
            colaTemp.encolar(a);
        }
        while (!colaTemp.estaVacia()) 
        {
            alquileresActivos.encolar(colaTemp.desencolar());
        }
        return sb.toString();
    }

    public Cliente buscarCliente(String identificacion) 
    {
        return clientes.buscarCliente(identificacion);
    }

    public Vehiculo buscarVehiculo(String placa)
    {
        return vehiculos.buscarVehiculo(placa);
    }

    public String[] obtenerIdsClientes() 
    {
        return clientes.obtenerIdsClientes();
    }

    public String[] obtenerPlacasVehiculos() 
    {
        return vehiculos.obtenerPlacasVehiculos();
    }

    public String[] obtenerPlacasVehiculosAlquilados() 
    {
        return vehiculos.obtenerPlacasVehiculosAlquilados();
    }
    
    public boolean eliminarVehiculo(String placa) 
    {
        Vehiculo v = vehiculos.buscarVehiculo(placa);
        if (v != null && v.isDisponible()) 
        {
            vehiculos.eliminarVehiculo(placa);
            return true;
        }
        return false;
    }

    public boolean eliminarCliente(String identificacion) 
    {
        Cliente c = clientes.buscarCliente(identificacion);
        if (c != null) {
            // Verifica si el cliente tiene alquileres activos
            if (!tieneAlquileresActivos(c)) 
            {
                clientes.eliminarCliente(identificacion);
                return true;
            }
        }
        return false;
    }

    private boolean tieneAlquileresActivos(Cliente c) 
    {
        ColaAlquileres colaTemp = new ColaAlquileres();
        boolean tieneAlquileres = false;
        while (!alquileresActivos.estaVacia()) 
        {
            Alquiler a = alquileresActivos.desencolar();
            if (a.getCliente().getIdentificacion().equals(c.getIdentificacion())) 
            {
                tieneAlquileres = true;
            }
            colaTemp.encolar(a);
        }
        while (!colaTemp.estaVacia()) 
        {
            alquileresActivos.encolar(colaTemp.desencolar());
        }
        return tieneAlquileres;
    }


    public void guardarDatos() 
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("datos_rentacar.ser"))) 
        {
            out.writeObject(this);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static SistemaRentACar cargarDatos() 
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("datos_rentacar.ser"))) 
        {
            return (SistemaRentACar) in.readObject();
        } catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("No se encontraron datos guardados o hubo un error al cargarlos. Iniciando nuevo sistema.");
            return new SistemaRentACar();
        }
    }

    public static void borrarDatos() 
    {
        File archivo = new File("datos_rentacar.ser");
        if (archivo.delete()) 
        {
            System.out.println("Datos borrados exitosamente.");
        } else {
            System.out.println("No se pudieron borrar los datos o no existían.");
        }
    }
}