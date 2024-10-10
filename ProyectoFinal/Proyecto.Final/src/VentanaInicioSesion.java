import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaInicioSesion extends JFrame {
    private JPanel panelInicioSesion;
    static String tipoTabla;
    static String tipoDieta;
    static String usuario;
    static JButton volverButton;

    public VentanaInicioSesion() {
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelInicioSesion = new JPanel();
        JLabel usuarioLabel = new JLabel("       Usuario:");
        JTextField usuarioField = new JTextField(20);
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        JPasswordField contraseñaField = new JPasswordField(20);
        JButton iniciarSesionButton = new JButton("Iniciar Sesión");
        volverButton = new JButton("Volver");

        panelInicioSesion.add(usuarioLabel);
        panelInicioSesion.add(usuarioField);
        panelInicioSesion.add(contraseñaLabel);
        panelInicioSesion.add(contraseñaField);
        panelInicioSesion.add(iniciarSesionButton);
        panelInicioSesion.add(volverButton);

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Inicio inicio = new Inicio();
                inicio.setTitle("NutriFit Coach");
                inicio.setContentPane(Inicio.panelInicio);
                inicio.setSize(300,300);
                inicio.setVisible(true);
                inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                inicio.setLocationRelativeTo(null);
            }
        });


        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                usuario = usuarioField.getText();
                String contraseña = new String(contraseñaField.getPassword());

                if (verificarInicioSesion(usuario, contraseña)) {
                    usuarioField.setText("");
                    contraseñaField.setText("");
                    abrirVentanaPrincipal(usuario);
                } else {
                    usuarioField.setText("");
                    contraseñaField.setText("");
                    JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Usuario o contraseña incorrectos");
                    VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
                    ventanaInicioSesion.setVisible(true);
                }
            }
        });

        setContentPane(panelInicioSesion);
        setLocationRelativeTo(null);
    }


    public boolean verificarInicioSesion(String usuario, String contraseña) {
        String url = "jdbc:mysql://localhost:3306/proyectofinal";
        String user = "root";
        String password = "root";

        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario);
            statement.setString(2, contraseña);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + ex.getMessage());
            return false;
        }
    }

    private void abrirVentanaPrincipal(String usuario) {
        JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
        SwingUtilities.invokeLater(() -> {
            tipoTabla = obtenerTipoTablaUsuarioDeBD(usuario);
            tipoDieta = obtenerTipoDietaUsuarioDeBD(usuario);
            PaginaPrincipal paginaPrincipal = new PaginaPrincipal(tipoTabla, tipoDieta);
            paginaPrincipal.setTitle("NutriFit Coach");
            paginaPrincipal.setVisible(true);
        });
    }

    private String obtenerTipoDietaUsuarioDeBD(String usuario) {
        String tipoDieta = null;

        String url = "jdbc:mysql://localhost:3306/proyectofinal";
        String user = "root";
        String password = "root";

        String sql = "SELECT tipoDieta FROM usuarios WHERE usuario = ?";


        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tipoDieta = resultSet.getString("tipoDieta");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tipoDieta;
    }

    private String obtenerTipoTablaUsuarioDeBD(String usuario) {
        String tipoTabla = null;

        String url = "jdbc:mysql://localhost:3306/proyectofinal";
        String user = "root";
        String password = "root";

        String sql = "SELECT tipoTabla FROM usuarios WHERE usuario = ?";


        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tipoTabla = resultSet.getString("tipoTabla");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return tipoTabla;
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
            ventanaInicioSesion.setVisible(true);
        });
    }
}