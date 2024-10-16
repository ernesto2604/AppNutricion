import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame {
    static JPanel panelInicio;
    JButton iniciarSesionButton;
    JButton registrarseButton;
    JLabel imagenLabel;

    public Inicio() {
        panelInicio = new JPanel(new GridLayout(2, 1));

        imagenLabel = new JLabel();
        ImageIcon imagen = new ImageIcon("logo.jpg");
        imagenLabel.setIcon(imagen);
        imagenLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel panelImagen = new JPanel();
        panelImagen.add(imagenLabel);
        panelInicio.add(panelImagen);

        iniciarSesionButton = new JButton("Iniciar Sesión");
        registrarseButton = new JButton("Registrarse");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(iniciarSesionButton);
        panelBotones.add(registrarseButton);
        panelInicio.add(panelBotones);

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
                ventanaInicioSesion.setTitle("NutriFit Coach");
                ventanaInicioSesion.setVisible(true);
            }
        });

        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VentanaRegistro ventanaRegistro = new VentanaRegistro();
                ventanaRegistro.setTitle("NutriFit Coach");
                ventanaRegistro.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inicio t = new Inicio();
            t.setTitle("NutriFit Coach");
            t.setContentPane(t.panelInicio);
            t.setSize(300, 300);
            t.setVisible(true);
            t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            t.setLocationRelativeTo(null);
        });
    }

    @Override
    public void pack() {
        super.pack();
        ImageIcon icono = (ImageIcon) imagenLabel.getIcon();
        Image imagen = icono.getImage().getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagen));
    }
}
