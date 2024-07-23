package modelo;

import java.io.Serializable;
/**
 *
 * @author Mike T
 */
public class PilaHistorial implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private class Nodo implements Serializable
    {
        private static final long serialVersionUID = 1L;
        Alquiler alquiler;
        Nodo siguiente;

        Nodo(Alquiler a) 
        {
            this.alquiler = a;
            this.siguiente = null;
        }
    }

    private Nodo tope;

    public void apilar(Alquiler a) 
    {
        Nodo nuevoNodo = new Nodo(a);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
    }

    public Alquiler desapilar() 
    {
        if (tope == null) 
        {
            return null;
        }
        Alquiler alquiler = tope.alquiler;
        tope = tope.siguiente;
        return alquiler;
    }

    public boolean estaVacia() 
    {
        return tope == null;
    }
}