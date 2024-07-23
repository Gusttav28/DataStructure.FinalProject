package modelo;

import java.io.Serializable;
/**
 *
 * @author Mike T
 */
public class ArbolClientes implements Serializable 
{
    private static final long serialVersionUID = 1L;

    private class Nodo implements Serializable 
    {
        private static final long serialVersionUID = 1L;
        Cliente cliente;
        Nodo izquierda, derecha;

        Nodo(Cliente c) 
        {
            this.cliente = c;
            izquierda = derecha = null;
        }
    }

    private Nodo raiz;

    public void agregarCliente(Cliente c) 
    {
        raiz = agregarRecursivo(raiz, c);
    }

    private Nodo agregarRecursivo(Nodo actual, Cliente c) 
    {
        if (actual == null) 
        {
            return new Nodo(c);
        }
        if (c.getIdentificacion().compareTo(actual.cliente.getIdentificacion()) < 0) 
        {
            actual.izquierda = agregarRecursivo(actual.izquierda, c);
        } else if (c.getIdentificacion().compareTo(actual.cliente.getIdentificacion()) > 0) {
            actual.derecha = agregarRecursivo(actual.derecha, c);
        }
        return actual;
    }

    public Cliente buscarCliente(String identificacion) 
    {
        return buscarClienteRecursivo(raiz, identificacion);
    }

    private Cliente buscarClienteRecursivo(Nodo nodo, String identificacion) 
    {
        if (nodo == null) {
            return null;
        }
        int comparacion = identificacion.compareTo(nodo.cliente.getIdentificacion());
        if (comparacion == 0) {
            return nodo.cliente;
        } else if (comparacion < 0) {
            return buscarClienteRecursivo(nodo.izquierda, identificacion);
        } else {
            return buscarClienteRecursivo(nodo.derecha, identificacion);
        }
    }
    
    public void eliminarCliente(String identificacion) 
    {
        raiz = eliminarClienteRecursivo(raiz, identificacion);
    }

    private Nodo eliminarClienteRecursivo(Nodo nodo, String identificacion) 
    {
        if (nodo == null) 
        {
            return null;
        }

        int comparacion = identificacion.compareTo(nodo.cliente.getIdentificacion());
        if (comparacion < 0) 
        {
            nodo.izquierda = eliminarClienteRecursivo(nodo.izquierda, identificacion);
        } else if (comparacion > 0) {
            nodo.derecha = eliminarClienteRecursivo(nodo.derecha, identificacion);
        } else {
            // Nodo con un solo hijo o sin hijos
            if (nodo.izquierda == null) 
            {
                return nodo.derecha;
            } else if (nodo.derecha == null) {
                return nodo.izquierda;
            }

            // Nodo con dos hijos: obtener el sucesor en orden (el menor de el subárbol derecho)
            nodo.cliente = encontrarMinimo(nodo.derecha);

            // Elimina el sucesor en orden
            nodo.derecha = eliminarClienteRecursivo(nodo.derecha, nodo.cliente.getIdentificacion());
        }

        return nodo;
    }

    private Cliente encontrarMinimo(Nodo nodo) 
    {
        Cliente minimo = nodo.cliente;
        while (nodo.izquierda != null) 
        {
            minimo = nodo.izquierda.cliente;
            nodo = nodo.izquierda;
        }
        return minimo;
    }

    public String listarClientes() 
    {
        StringBuilder sb = new StringBuilder();
        listarClientesRecursivo(raiz, sb);
        return sb.toString();
    }

    private void listarClientesRecursivo(Nodo nodo, StringBuilder sb) 
    {
        if (nodo != null) 
        {
            listarClientesRecursivo(nodo.izquierda, sb);
            sb.append(nodo.cliente.toString()).append("\n");
            listarClientesRecursivo(nodo.derecha, sb);
        }
    }

    public String[] obtenerIdsClientes() 
    {
        MiLista<String> ids = new MiLista<>();
        obtenerIdsClientesRecursivo(raiz, ids);
        return ids.toArray();
    }
    
    private void obtenerIdsClientesRecursivo(Nodo nodo, MiLista<String> ids) 
    {
        if (nodo != null) 
        {
            obtenerIdsClientesRecursivo(nodo.izquierda, ids);
            ids.add(nodo.cliente.getIdentificacion());
            obtenerIdsClientesRecursivo(nodo.derecha, ids);
        }
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
