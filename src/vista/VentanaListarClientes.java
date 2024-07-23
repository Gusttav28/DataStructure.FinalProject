package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Mike T
 */


public class VentanaListarClientes extends JFrame 
{
    private SistemaRentACar sistema;

    public VentanaListarClientes(SistemaRentACar sistema) 
    {
        this.sistema = sistema;
        setTitle("Listar Clientes");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JTextArea txtAreaClientes = new JTextArea();
        txtAreaClientes.setEditable(false);

        txtAreaClientes.setText(sistema.listarClientes());

        JScrollPane scrollPane = new JScrollPane(txtAreaClientes);
        add(scrollPane);

        setVisible(true);
    }
}