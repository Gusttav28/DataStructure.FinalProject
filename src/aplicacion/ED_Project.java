package aplicacion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.SistemaRentACar;
import vista.VentanaPrincipal;
import javax.swing.*;

/**
 *
 * @author Mike T
 */

public class ED_Project {
    public static void main(String[] args) {
        SistemaRentACar sistema = SistemaRentACar.cargarDatos();
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(sistema);
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    sistema.guardarDatos();
                    System.exit(0);
                }
            });
            ventana.setVisible(true);
        });
    }
}