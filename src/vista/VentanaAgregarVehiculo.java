package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.time.Year;
/**
 *
 * @author Mike T
 */
public class VentanaAgregarVehiculo extends JFrame 
{
    private SistemaRentACar sistema;
    private JTextField txtMarca, txtModelo, txtAño, txtPlaca, txtPrecio;

    public VentanaAgregarVehiculo(SistemaRentACar sistema) 
    {
        this.sistema = sistema;
        setTitle("Agregar Vehículo");
        setSize(350, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtMarca = new JTextField();
        txtModelo = new JTextField();
        txtAño = new JTextField();
        txtPlaca = new JTextField();
        txtPrecio = new JTextField();

        panel.add(new JLabel("Marca:"));
        panel.add(txtMarca);
        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Año:"));
        panel.add(txtAño);
        panel.add(new JLabel("Placa:"));
        panel.add(txtPlaca);
        panel.add(new JLabel("Precio por día:"));
        panel.add(txtPrecio);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarVehiculo());
        panel.add(btnAgregar);

        add(panel);
        setVisible(true);
    }

    private void agregarVehiculo() 
    {
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String añoStr = txtAño.getText().trim();
        String placa = txtPlaca.getText().trim();
        String precioStr = txtPrecio.getText().trim();

        if (marca.isEmpty() || modelo.isEmpty() || añoStr.isEmpty() || placa.isEmpty() || precioStr.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
        {
            int año = Integer.parseInt(añoStr);
            int currentYear = Year.now().getValue();
            if (año < 1900 || año > currentYear) 
            {
                JOptionPane.showMessageDialog(this, "Año inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double precio = Double.parseDouble(precioStr);
            if (precio <= 0) 
            {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor que cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vehiculo v = new Vehiculo(marca, modelo, año, placa, precio, true);
            if (sistema.agregarVehiculo(v)) 
            {
                JOptionPane.showMessageDialog(this, "Vehículo agregado con éxito");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe un vehículo con esa placa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Año y precio deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}