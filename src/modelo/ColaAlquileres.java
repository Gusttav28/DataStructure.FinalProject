package modelo;

import java.io.Serializable;
/**
 *
 * @author Mike T
 */
public class ColaAlquileres implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private class Nodo implements Serializable{
        private static final long serialVersionUID = 1L;
        Alquiler alquiler;
        Nodo siguiente;

        Nodo(Alquiler a) 
        {
            this.alquiler = a;
            this.siguiente = null;
        }
    }

    private Nodo frente;
    private Nodo finale;

    public void encolar(Alquiler a) 
    {
        Nodo nuevoNodo = new Nodo(a);
        if (frente == null) 
        {
            frente = finale = nuevoNodo;
        } else {
            finale.siguiente = nuevoNodo;
            finale = nuevoNodo;
        }
    }

    public Alquiler desencolar() 
    {
        if (frente == null) 
        {
            return null;
        }
        Alquiler alquiler = frente.alquiler;
        frente = frente.siguiente;
        if (frente == null) 
        {
            finale = null;
        }
        return alquiler;
    }

    public boolean estaVacia() 
    {
        return frente == null;
    }
}