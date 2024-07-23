package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Mike T
 */
public class VentanaPrincipal extends JFrame 
{
    private SistemaRentACar sistema;

    public VentanaPrincipal(SistemaRentACar sistema) 
    {
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema Rent a Car");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnAgregarVehiculo = new JButton("Agregar Vehículo");
        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        JButton btnAlquilarVehiculo = new JButton("Alquilar Vehículo");
        JButton btnDevolverVehiculo = new JButton("Devolver Vehículo");
        JButton btnListarVehiculos = new JButton("Listar Vehículos");
        JButton btnListarClientes = new JButton("Listar Clientes");
        JButton btnEliminarVehiculo = new JButton("Eliminar Vehículo");
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        JButton btnGuardarDatos = new JButton("Guardar Datos");
        JButton btnBorrarDatos = new JButton("Borrar Datos");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnAgregarVehiculo);
        panel.add(btnAgregarCliente);
        panel.add(btnAlquilarVehiculo);
        panel.add(btnDevolverVehiculo);
        panel.add(btnListarVehiculos);
        panel.add(btnListarClientes);
        panel.add(btnEliminarVehiculo);
        panel.add(btnEliminarCliente);
        panel.add(btnGuardarDatos);
        panel.add(btnBorrarDatos);
        panel.add(btnSalir);

        btnAgregarVehiculo.addActionListener(e -> new VentanaAgregarVehiculo(sistema));
        btnAgregarCliente.addActionListener(e -> new VentanaAgregarCliente(sistema));
        btnAlquilarVehiculo.addActionListener(e -> new VentanaAlquilarVehiculo(sistema));
        btnDevolverVehiculo.addActionListener(e -> new VentanaDevolverVehiculo(sistema));
        btnListarVehiculos.addActionListener(e -> new VentanaListarVehiculos(sistema));
        btnListarClientes.addActionListener(e -> new VentanaListarClientes(sistema));
        btnEliminarVehiculo.addActionListener(e -> eliminarVehiculo());
        btnEliminarCliente.addActionListener(e -> eliminarCliente());
        btnGuardarDatos.addActionListener(e -> 
        {
            sistema.guardarDatos();
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente.");
        });
        btnBorrarDatos.addActionListener(e -> 
        {
            int opcion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea borrar todos los datos? Esta acción no se puede deshacer.", 
                "Confirmar borrado de datos", 
                JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) 
            {
                SistemaRentACar.borrarDatos();
                this.sistema = new SistemaRentACar();
                JOptionPane.showMessageDialog(this, "Datos borrados exitosamente. Se reiniciará el sistema.");
                dispose();
                new VentanaPrincipal(this.sistema).setVisible(true);
            }
        });
        btnSalir.addActionListener(e -> 
        {
            sistema.guardarDatos();
            System.exit(0);
        });

        add(panel);
    }
    
    private void eliminarVehiculo() 
    {
        String[] placas = sistema.obtenerPlacasVehiculos();
        if (placas.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay vehículos para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox<String> comboPlacas = new JComboBox<>(placas);
        int result = JOptionPane.showConfirmDialog(this, comboPlacas, "Seleccione la placa del vehículo a eliminar", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) 
        {
            String placa = (String) comboPlacas.getSelectedItem();
            if (sistema.eliminarVehiculo(placa)) 
            {
                JOptionPane.showMessageDialog(this, "Vehículo eliminado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el vehículo. Asegúrese de que no esté alquilado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarCliente() 
    {
        String[] ids = sistema.obtenerIdsClientes();
        if (ids.length == 0) 
        {
            JOptionPane.showMessageDialog(this, "No hay clientes para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox<String> comboIds = new JComboBox<>(ids);
        int result = JOptionPane.showConfirmDialog(this, comboIds, "Seleccione la identificación del cliente a eliminar", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) 
        {
            String identificacion = (String) comboIds.getSelectedItem();
            if (sistema.eliminarCliente(identificacion)) 
            {
                JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el cliente. Asegúrese de que no tenga alquileres activos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}