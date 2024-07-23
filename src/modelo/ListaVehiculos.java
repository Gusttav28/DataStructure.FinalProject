package modelo;

import java.io.Serializable;
/**
 *
 * @author Mike T
 */
public class ListaVehiculos implements Serializable 
{
    private static final long serialVersionUID = 1L;

    private class Nodo implements Serializable 
    {
        private static final long serialVersionUID = 1L;
        Vehiculo vehiculo;
        Nodo siguiente;

        Nodo(Vehiculo v) 
        {
            this.vehiculo = v;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;

    public void agregarVehiculo(Vehiculo v) 
    {
        Nodo nuevoNodo = new Nodo(v);
        if (cabeza == null) 
        {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) 
            {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public Vehiculo buscarVehiculo(String placa) 
    {
        Nodo actual = cabeza;
        while (actual != null) 
        {
            if (actual.vehiculo.getPlaca().equals(placa)) 
            {
                return actual.vehiculo;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public void eliminarVehiculo(String placa) 
    {
        if (cabeza == null) return;
        if (cabeza.vehiculo.getPlaca().equals(placa)) 
        {
            cabeza = cabeza.siguiente;
            return;
        }
        Nodo actual = cabeza;
        Nodo anterior = null;
        while (actual != null && !actual.vehiculo.getPlaca().equals(placa)) 
        {
            anterior = actual;
            actual = actual.siguiente;
        }
        if (actual != null) 
        {
            anterior.siguiente = actual.siguiente;
        }
    }

    public String listarVehiculos() 
    {
        StringBuilder sb = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) 
        {
            sb.append(actual.vehiculo.toString()).append("\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    public String listarVehiculosDisponibles() 
    {
        StringBuilder sb = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) 
        {
            if (actual.vehiculo.isDisponible()) 
            {
                sb.append(actual.vehiculo.toString()).append("\n");
            }
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    public String[] obtenerPlacasVehiculos() 
    {
        MiLista<String> placas = new MiLista<>();
        Nodo actual = cabeza;
        while (actual != null) 
        {
            if (actual.vehiculo.isDisponible()) 
            {
                placas.add(actual.vehiculo.getPlaca());
            }
            actual = actual.siguiente;
        }
        return placas.toArray();
    }

    public String[] obtenerPlacasVehiculosAlquilados() 
    {
        MiLista<String> placas = new MiLista<>();
        Nodo actual = cabeza;
        while (actual != null) 
        {
            if (!actual.vehiculo.isDisponible()) 
            {
                placas.add(actual.vehiculo.getPlaca());
            }
            actual = actual.siguiente;
        }
        return placas.toArray();
    }

    private class MiLista<T> 
    {
        private class NodoLista 
        {
            T dato;
            NodoLista siguiente;

            NodoLista(T dato) 
            {
                this.dato = dato;
                this.siguiente = null;
            }
        }

        private NodoLista cabeza;
        private int size;

        MiLista() 
        {
            cabeza = null;
            size = 0;
        }

        void add(T dato) 
        {
            if (cabeza == null) 
            {
                cabeza = new NodoLista(dato);
            } 
            else 
            {
                NodoLista temp = cabeza;
                while (temp.siguiente != null) 
                {
                    temp = temp.siguiente;
                }
                temp.siguiente = new NodoLista(dato);
            }
            size++;
        }

        String[] toArray() 
        {
            String[] array = new String[size];
            NodoLista temp = cabeza;
            int i = 0;
            while (temp != null) 
            {
                array[i] = temp.dato.toString();
                temp = temp.siguiente;
                i++;
            }
            return array;
        }
    }
}
