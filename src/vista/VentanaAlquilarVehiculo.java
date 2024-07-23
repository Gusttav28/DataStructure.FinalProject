package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Mike T
 */
public class VentanaAlquilarVehiculo extends JFrame 
{
    private SistemaRentACar sistema;
    private JComboBox<String> comboClientes;
    private JComboBox<String> comboVehiculos;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;

    public VentanaAlquilarVehiculo(SistemaRentACar sistema) 
    {
        this.sistema = sistema;
        setTitle("Alquilar Vehículo");
        setSize(350, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        comboClientes = new JComboBox<>(sistema.obtenerIdsClientes());
        comboVehiculos = new JComboBox<>(sistema.obtenerPlacasVehiculos());
        txtFechaInicio = new JTextField();
        txtFechaFin = new JTextField();

        panel.add(new JLabel("ID Cliente:"));
        panel.add(comboClientes);
        panel.add(new JLabel("Placa Vehículo:"));
        panel.add(comboVehiculos);
        panel.add(new JLabel("Fecha Inicio (DD/MM/YYYY):"));
        panel.add(txtFechaInicio);
        panel.add(new JLabel("Fecha Fin (DD/MM/YYYY):"));
        panel.add(txtFechaFin);

        JButton btnAlquilar = new JButton("Alquilar");
        btnAlquilar.addActionListener(e -> alquilarVehiculo());
        panel.add(btnAlquilar);

        add(panel);
        setVisible(true);
    }

    private void alquilarVehiculo() 
    {
        String idCliente = (String) comboClientes.getSelectedItem();
        String placa = (String) comboVehiculos.getSelectedItem();
        String fechaInicio = txtFechaInicio.getText().trim();
        String fechaFin = txtFechaFin.getText().trim();

        if (idCliente == null || placa == null || fechaInicio.isEmpty() || fechaFin.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try 
        {
            LocalDate inicio = LocalDate.parse(fechaInicio, formatter);
            LocalDate fin = LocalDate.parse(fechaFin, formatter);
            
            if (inicio.isAfter(fin)) 
            {
                JOptionPane.showMessageDialog(this, "La fecha de inicio debe ser anterior a la fecha de fin", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            sistema.alquilarVehiculo(idCliente, placa, fechaInicio, fechaFin);
            JOptionPane.showMessageDialog(this, "Vehículo alquilado con éxito");
            dispose();
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/YYYY", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}