package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Mike T
 */
public class VentanaListarVehiculos extends JFrame 
{
    private SistemaRentACar sistema;

    public VentanaListarVehiculos(SistemaRentACar sistema) 
    {
        this.sistema = sistema;
        setTitle("Listar Vehículos");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JTextArea txtAreaVehiculos = new JTextArea();
        txtAreaVehiculos.setEditable(false);

        String listaVehiculos = sistema.obtenerListaVehiculos();
        txtAreaVehiculos.setText(listaVehiculos);

        JScrollPane scrollPane = new JScrollPane(txtAreaVehiculos);
        add(scrollPane);

        setVisible(true);
    }
}
