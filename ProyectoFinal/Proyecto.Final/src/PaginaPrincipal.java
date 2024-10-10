import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PaginaPrincipal extends JFrame {
    public JPanel panelPrincipal,panelProgreso;
    public JTabbedPane panel;
    public JLabel etiquetaImagenTabla, etiquetaImagenDieta, etiquetaGrafico;
    public JButton cerrarSesion, guardarPesos;
    public JLabel labelSemana1, labelSemana2, labelSemana3, labelSemana4, titulo1, titulo2;
    public JTextField textFieldSemana1, textFieldSemana2, textFieldSemana3, textFieldSemana4;



    public PaginaPrincipal(String tipoTabla, String tipoDieta) {
        panel = new JTabbedPane();
        panelProgreso = new JPanel();
        panelPrincipal = new JPanel();
        etiquetaImagenTabla = new JLabel();
        etiquetaImagenDieta = new JLabel();
        cerrarSesion = new JButton("Cerrar Sesión");
        JLabel titulo = new JLabel("NutriFit Coach");
        JLabel titulo2 = new JLabel("NutriFit Coach");
        guardarPesos = new JButton("Guardar");
        etiquetaGrafico = new JLabel();


        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(etiquetaImagenTabla, BorderLayout.EAST);
        panelPrincipal.add(etiquetaImagenDieta, BorderLayout.WEST);
        panelPrincipal.add(cerrarSesion, BorderLayout.SOUTH);
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        titulo.setFont(new Font("Agency FB", Font.BOLD, 75));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.setBackground(Color.pink);


        panelProgreso = new JPanel(new GridBagLayout());
        panelProgreso.setBackground(Color.pink);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        titulo2.setFont(new Font("Agency FB", Font.BOLD, 75));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelProgreso.add(titulo2, gbc);


        titulo1 = new JLabel("Peso (Kg)");
        titulo1.setFont(new Font("Times New Roman", Font.BOLD, 32));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelProgreso.add(titulo1, gbc);

        labelSemana1 = new JLabel("Semana 1:");
        labelSemana1.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        textFieldSemana1 = new JTextField(10);
        textFieldSemana1.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelProgreso.add(labelSemana1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelProgreso.add(textFieldSemana1, gbc);

        labelSemana2 = new JLabel("Semana 2:");
        labelSemana2.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        textFieldSemana2 = new JTextField(10);
        textFieldSemana2.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelProgreso.add(labelSemana2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelProgreso.add(textFieldSemana2, gbc);

        labelSemana3 = new JLabel("Semana 3:");
        labelSemana3.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        textFieldSemana3 = new JTextField(10);
        textFieldSemana3.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelProgreso.add(labelSemana3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelProgreso.add(textFieldSemana3, gbc);

        labelSemana4 = new JLabel("Semana 4:");
        labelSemana4.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        textFieldSemana4 = new JTextField(10);
        textFieldSemana4.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelProgreso.add(labelSemana4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelProgreso.add(textFieldSemana4, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        JLabel imagenProgreso = new JLabel(new ImageIcon("grafico (1).jpg"));
        panelProgreso.add(imagenProgreso, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 1;
        gbc.gridy = 6;
        panelProgreso.add(guardarPesos, gbc);


        panel.addTab("Inicio", panelPrincipal);
        panel.addTab("Progreso", panelProgreso);


        if (tipoTabla.equals("Fácil")) {
            ImageIcon imagenFacil = new ImageIcon("tablaFacil.jpg");
            etiquetaImagenTabla.setIcon(imagenFacil);
        } else if (tipoTabla.equals("Intermedio")) {
            ImageIcon imagenIntermedio = new ImageIcon("tablaIntermedia.jpg");
            etiquetaImagenTabla.setIcon(imagenIntermedio);
        } else if (tipoTabla.equals("Dificil")) {
            ImageIcon imagenProfesional = new ImageIcon("tablaProfesional.jpg");
            etiquetaImagenTabla.setIcon(imagenProfesional);
        }

        if (tipoDieta.equals("Dieta 1")) {
            ImageIcon imagenDieta1 = new ImageIcon("dieta1.jpg");
            etiquetaImagenDieta.setIcon(imagenDieta1);
        } else if (tipoDieta.equals("Dieta 2")) {
            ImageIcon imagenDieta2 = new ImageIcon("dieta2.jpg");
            etiquetaImagenDieta.setIcon(imagenDieta2);
        } else if (tipoDieta.equals("Dieta 3")) {
            ImageIcon imagenDieta3 = new ImageIcon("dieta3.jpg");
            etiquetaImagenDieta.setIcon(imagenDieta3);
        }

        setTitle("Página Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        cerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        guardarPesos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = VentanaInicioSesion.usuario;

                if (!textFieldSemana1.getText().isEmpty()) {
                    guardarPeso(usuario, textFieldSemana1.getText(), "semana1");
                }

                if (!textFieldSemana2.getText().isEmpty()) {
                    guardarPeso(usuario, textFieldSemana2.getText(), "semana2");
                }

                if (!textFieldSemana3.getText().isEmpty()) {
                    guardarPeso(usuario, textFieldSemana3.getText(), "semana3");
                }

                if (!textFieldSemana4.getText().isEmpty()) {
                    guardarPeso(usuario, textFieldSemana4.getText(), "semana4");
                }
            }

            private void guardarPeso(String usuario, String peso, String semana) {
                String url = "jdbc:mysql://localhost:3306/proyectofinal";
                String user = "root";
                String password = "root";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    String sql = "UPDATE usuarios SET " + semana + " = ? WHERE usuario = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, peso);
                    statement.setString(2, usuario);
                    int filasAfectadas = statement.executeUpdate();

                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "El peso de la semana " + semana.substring(6) + " se ha guardado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar el peso de la semana " + semana.substring(6) + ".");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        PaginaPrincipal paginaPrincipal = new PaginaPrincipal("Fácil", "Dieta 1");
        paginaPrincipal.setVisible(true);
    }
}