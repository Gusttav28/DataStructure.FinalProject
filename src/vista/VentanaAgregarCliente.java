package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Mike T
 */
public class VentanaAgregarCliente extends JFrame 
{
    private SistemaRentACar sistema;
    private JTextField txtNombre, txtApellido, txtIdentificacion, txtDireccion, txtTelefono;

    public VentanaAgregarCliente(SistemaRentACar sistema) 
    {
        this.sistema = sistema;
        setTitle("Agregar Cliente");
        setSize(350, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtIdentificacion = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Identificación:"));
        panel.add(txtIdentificacion);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarCliente());
        panel.add(btnAgregar);

        add(panel);
        setVisible(true);
    }

    private void agregarCliente() 
    {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String identificacion = txtIdentificacion.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || identificacion.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!identificacion.matches("\\d+")) 
        {
            JOptionPane.showMessageDialog(this, "La identificación debe contener solo números", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!telefono.matches("\\d{7,15}")) 
        {
            JOptionPane.showMessageDialog(this, "El teléfono debe contener entre 7 y 15 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente c = new Cliente(nombre, apellido, identificacion, direccion, telefono);
        if (sistema.agregarCliente(c)) 
        {
            JOptionPane.showMessageDialog(this, "Cliente agregado con éxito");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Ya existe un cliente con esa identificación", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}