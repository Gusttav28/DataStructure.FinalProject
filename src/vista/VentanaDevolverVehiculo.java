package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Mike T
 */
public class VentanaDevolverVehiculo extends JFrame 
{
    private SistemaRentACar sistema;
    private JComboBox<String> comboVehiculos;

    public VentanaDevolverVehiculo(SistemaRentACar sistema) 
    {
        this.sistema = sistema;
        setTitle("Devolver Vehículo");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        comboVehiculos = new JComboBox<>(sistema.obtenerPlacasVehiculosAlquilados());

        panel.add(new JLabel("Placa Vehículo:"));
        panel.add(comboVehiculos);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.addActionListener(e -> devolverVehiculo());
        panel.add(btnDevolver);

        add(panel);
        setVisible(true);
    }

    private void devolverVehiculo() 
    {
        String placa = (String) comboVehiculos.getSelectedItem();

        if (placa == null) 
        {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un vehículo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sistema.devolverVehiculo(placa);
        JOptionPane.showMessageDialog(this, "Vehículo devuelto con éxito");
        dispose();
    }
}