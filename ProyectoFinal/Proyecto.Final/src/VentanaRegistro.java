import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class VentanaRegistro extends JFrame {
    static String tipoTabla;
    static String tipoDieta;
    JTextField textField1;
    JTextField textField2;
    JTextField textField3;
    JTextField textField4;
    JTextField textField5;
    JTextField textField6;
    JTextField textField7;
    JTextField textField8;
    JPanel panelRegistro;
    JButton registrarseButton, volverButton;
    static String usuario;

    public VentanaRegistro() {
        panelRegistro = new JPanel();

        textField1 = new JTextField(20);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(20);
        textField6 = new JTextField(20);
        textField7 = new JTextField(20);
        textField8 = new JTextField(20);
        registrarseButton = new JButton("Registrarse");
        volverButton = new JButton("Volver");

        panelRegistro.add(new JLabel("Nombre:    "));
        panelRegistro.add(textField1);
        panelRegistro.add(new JLabel("Apellidos: "));
        panelRegistro.add(textField2);
        panelRegistro.add(new JLabel("Correo:    "));
        panelRegistro.add(textField3);
        panelRegistro.add(new JLabel("Teléfono:  "));
        panelRegistro.add(textField4);
        panelRegistro.add(new JLabel("Altura:    "));
        panelRegistro.add(textField5);
        panelRegistro.add(new JLabel("Peso:      "));
        panelRegistro.add(textField6);
        panelRegistro.add(new JLabel("Usuario:   "));
        panelRegistro.add(textField7);
        panelRegistro.add(new JLabel("Contraseña:"));
        panelRegistro.add(textField8);
        panelRegistro.add(registrarseButton);
        panelRegistro.add(volverButton);

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

        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarDatos();
            }
        });

        setTitle("Registro");
        setSize(250, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelRegistro);
        setLocationRelativeTo(null);
    }

    void registrarDatos() {
        String nombre = textField1.getText();
        String apellidos = textField2.getText();
        String correo = textField3.getText();
        String telefono = textField4.getText();
        double altura = Double.parseDouble(textField5.getText());
        double peso = Double.parseDouble(textField6.getText());
        usuario = textField7.getText();
        String contraseña = textField8.getText();

        String url = "jdbc:mysql://localhost:3306/proyectofinal";
        String user = "root";
        String password = "root";

        String sqlUsuario = "INSERT INTO usuarios (nombre, apellidos, correo, telefono, altura, peso, usuario, contraseña, semana1) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statementUsuario = connection.prepareStatement(sqlUsuario)) {
            statementUsuario.setString(1, nombre);
            statementUsuario.setString(2, apellidos);
            statementUsuario.setString(3, correo);
            statementUsuario.setString(4, telefono);
            statementUsuario.setDouble(5, altura);
            statementUsuario.setDouble(6, peso);
            statementUsuario.setString(7, usuario);
            statementUsuario.setString(8, contraseña);
            statementUsuario.setDouble(9, peso);

            int rowsInsertedUsuario = statementUsuario.executeUpdate();

            if (rowsInsertedUsuario > 0) {
                JOptionPane.showMessageDialog(this, "Registro exitoso.");
                usuario = textField1.getText();
                borrarDatos();
                dispose();
            }
        } catch (SQLException ex) {
            borrarDatos();
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + ex.getMessage());
            registrarDatos();
        }

        abrirVentanaSeleccionTablas();
    }

    private void borrarDatos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");
    }

    private void abrirVentanaSeleccionTablas() {
        JFrame ventanaSeleccionTablas = new JFrame("Selección de Tablas de Ejercicios");
        ventanaSeleccionTablas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaSeleccionTablas.setSize(300, 200);
        ventanaSeleccionTablas.setLocationRelativeTo(null);

        JPanel panelSeleccionTablas = new JPanel();

        JButton botonFacil = new JButton("Tabla Fácil");
        JButton botonIntermedio = new JButton("Tabla Intermedio");
        JButton botonProfesional = new JButton("Tabla Profesional");

        botonFacil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoTabla("Fácil");
                mostrarTablaFacil();
            }
        });

        botonIntermedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoTabla("Intermedio");
                mostrarTablaIntermedio();
            }
        });

        botonProfesional.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoTabla("Profesional");
                mostrarTablaDificil();
            }
        });

        panelSeleccionTablas.add(botonFacil);
        panelSeleccionTablas.add(botonIntermedio);
        panelSeleccionTablas.add(botonProfesional);

        ventanaSeleccionTablas.add(panelSeleccionTablas);
        ventanaSeleccionTablas.setVisible(true);
    }

    private void guardarTipoTabla(String tipoTabla) {

        String url = "jdbc:mysql://localhost:3306/proyectofinal";
        String user = "root";
        String password = "root";

        String sqlUpdate = "UPDATE usuarios SET tipoTabla = ? WHERE nombre = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)) {
            statement.setString(1, tipoTabla);
            statement.setString(2, usuario);
            statement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la base de datos: " + ex.getMessage());
        }
    }

    private void mostrarTablaIntermedio() {
        ArrayList<String> tablaIntermedio = new ArrayList<>();
        tablaIntermedio.add("1. Calentamiento: Saltar la cuerda");
        tablaIntermedio.add("2. Correr 5 km");
        tablaIntermedio.add("3. Flexiones");
        tablaIntermedio.add("4. Plancha");
        tablaIntermedio.add("5. Squats");

        JFrame ventanaTablaIntermedio = new JFrame("Tabla Intermedio");
        ventanaTablaIntermedio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaTablaIntermedio.setSize(300, 300);
        ventanaTablaIntermedio.setLocationRelativeTo(null);

        JPanel panelTablaIntermedio = new JPanel();
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        for (String ejercicio : tablaIntermedio) {
            textArea.append(ejercicio + "\n");
        }
        panelTablaIntermedio.add(new JScrollPane(textArea));

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoTabla("Intermedio");
                tipoTabla = "Intermedio";
                ventanaTablaIntermedio.dispose();
                abrirVentanaSeleccionDieta();
            }
        });

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaTablaIntermedio.dispose();
                abrirVentanaSeleccionTablas();

            }
        });

        panelTablaIntermedio.add(botonSeleccionar);
        panelTablaIntermedio.add(botonVolver);

        ventanaTablaIntermedio.add(panelTablaIntermedio);
        ventanaTablaIntermedio.setVisible(true);
    }

    private void mostrarTablaDificil() {
        ArrayList<String> tablaDificil = new ArrayList<>();
        tablaDificil.add("1. Calentamiento: Correr 10 minutos");
        tablaDificil.add("2. Entrenamiento de intervalos de alta intensidad (HIIT)");
        tablaDificil.add("3. Levantamiento de pesas");
        tablaDificil.add("4. Ejercicios de plyometrics");
        tablaDificil.add("5. Entrenamiento de resistencia");

        JFrame ventanaTablaDificil = new JFrame("Tabla Dificil");
        ventanaTablaDificil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaTablaDificil.setSize(300, 300);
        ventanaTablaDificil.setLocationRelativeTo(null);

        JPanel panelTablaDificil = new JPanel();
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        for (String ejercicio : tablaDificil) {
            textArea.append(ejercicio + "\n");
        }
        panelTablaDificil.add(new JScrollPane(textArea));

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoTabla("Dificil");
                tipoTabla = "Dificil";
                ventanaTablaDificil.dispose();
                abrirVentanaSeleccionDieta();
            }
        });

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaTablaDificil.dispose();
                abrirVentanaSeleccionTablas();
            }
        });

        panelTablaDificil.add(botonSeleccionar);
        panelTablaDificil.add(botonVolver);

        ventanaTablaDificil.add(panelTablaDificil);
        ventanaTablaDificil.setVisible(true);

    }

    private void mostrarTablaFacil() {
        ArrayList<String> tablaFacil = new ArrayList<>();
        tablaFacil.add("1. Estiramientos");
        tablaFacil.add("2. Caminar 30 minutos");
        tablaFacil.add("3. Flexiones de rodillas");
        tablaFacil.add("4. Abdominales básicos");

        JFrame ventanaTablaFacil = new JFrame("Tabla Fácil");
        ventanaTablaFacil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaTablaFacil.setSize(300, 300);
        ventanaTablaFacil.setLocationRelativeTo(null);

        JPanel panelTablaFacil = new JPanel();
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        for (String ejercicio : tablaFacil) {
            textArea.append(ejercicio + "\n");
        }
        panelTablaFacil.add(new JScrollPane(textArea));

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoTabla("Fácil");
                tipoTabla = "Fácil";
                ventanaTablaFacil.dispose();
                abrirVentanaSeleccionDieta();
            }
        });

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaTablaFacil.dispose();
                abrirVentanaSeleccionTablas();
            }
        });

        panelTablaFacil.add(botonSeleccionar);
        panelTablaFacil.add(botonVolver);

        ventanaTablaFacil.add(panelTablaFacil);
        ventanaTablaFacil.setVisible(true);


    }

    private void abrirVentanaSeleccionDieta() {
        JFrame ventanaSeleccionTablas = new JFrame("Selección de Dieta");
        ventanaSeleccionTablas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaSeleccionTablas.setSize(300, 200);
        ventanaSeleccionTablas.setLocationRelativeTo(null);

        JPanel panelSeleccionTablas = new JPanel();

        JButton botonDieta1 = new JButton("Dieta 1");
        JButton botonDieta2 = new JButton("Dieta 2");
        JButton botonDieta3 = new JButton("Dieta 3");

        botonDieta1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoDieta("Dieta 1");
                mostrarDieta1();
            }
        });

        botonDieta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoDieta("Dieta 2");
                mostrarDieta2();
            }
        });

        botonDieta3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoDieta("Dieta 3");
                mostrarDieta3();
            }
        });

        panelSeleccionTablas.add(botonDieta1);
        panelSeleccionTablas.add(botonDieta2);
        panelSeleccionTablas.add(botonDieta3);

        ventanaSeleccionTablas.add(panelSeleccionTablas);
        ventanaSeleccionTablas.setVisible(true);

    }

    private void mostrarDieta3() {
        ArrayList<String> dieta3 = new ArrayList<>();
        dieta3.add("Ensalada de quinoa con garbanzos, pepino, tomate cherry y aderezo de limón y cilantro");
        dieta3.add("Pechuga de pollo al horno con batata asada y espárragos");
        dieta3.add("Wrap integral con atún enlatado, aguacate, lechuga, tomate y mayonesa ligera");
        dieta3.add("Salmón al horno con ensalada de espinacas, fresas y nueces");
        dieta3.add("Arroz integral con tofu salteado, brócoli y zanahorias");
        dieta3.add("Ensalada de garbanzos con pepino, tomate, pimiento rojo, cebolla roja y vinagreta balsámica");
        dieta3.add("Sopa de verduras casera con fideos integrales");
        dieta3.add("Tacos de pollo a la parrilla con tortillas de maíz, repollo morado, salsa de yogur y lima");
        dieta3.add("Pasta integral con salsa de tomate casera, espinacas y albóndigas de carne magra");
        dieta3.add("Filete de pescado a la plancha con espárragos y puré de patatas");

        JFrame ventanaDieta3 = new JFrame("Dieta 3");
        ventanaDieta3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaDieta3.setSize(300, 300);
        ventanaDieta3.setLocationRelativeTo(null);

        JPanel panelDieta3 = new JPanel();
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        for (String ejercicio : dieta3) {
            textArea.append(ejercicio + "\n");
        }
        panelDieta3.add(new JScrollPane(textArea));

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoDieta("Dieta 3");
                tipoDieta = "Dieta 3";
                ventanaDieta3.dispose();
                abrirPaginaPrincipal(usuario);
            }
        });

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaDieta3.dispose();
                abrirVentanaSeleccionDieta();
            }
        });

        panelDieta3.add(botonSeleccionar);
        panelDieta3.add(botonVolver);

        ventanaDieta3.add(panelDieta3);
        ventanaDieta3.setVisible(true);

    }

    private void mostrarDieta2() {
        ArrayList<String> dieta2 = new ArrayList<>();
        dieta2.add("Ensalada de garbanzos con tomate, pepino, cebolla roja, cilantro y aderezo de limón y hierbas");
        dieta2.add("Filete de ternera a la plancha con espárragos y quinoa");
        dieta2.add("Wrap integral con pollo a la parrilla, lechuga, tomate, aguacate y salsa de yogur");
        dieta2.add("Pescado al horno con patatas asadas y brócoli al vapor");
        dieta2.add("Sopa de lentejas con verduras mixtas");
        dieta2.add("Ensalada de salmón ahumado con espinacas, manzana, nueces y vinagreta de miel y mostaza");
        dieta2.add("Pollo al curry con arroz basmati y vegetales salteados");
        dieta2.add("Rollitos de lechuga con carne molida, fideos de arroz y salsa de cacahuate");
        dieta2.add("Tortilla de claras de huevo con espinacas, champiñones y tomate");
        dieta2.add("Brochetas de camarones a la parrilla con verduras mixtas asadas y quinoa");

        JFrame ventanaDieta2 = new JFrame("Dieta 2");
        ventanaDieta2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaDieta2.setSize(300, 300);
        ventanaDieta2.setLocationRelativeTo(null);

        JPanel panelDieta2 = new JPanel();
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        for (String ejercicio : dieta2) {
            textArea.append(ejercicio + "\n");
        }
        panelDieta2.add(new JScrollPane(textArea));

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoDieta("Dieta 2");
                tipoDieta = "Dieta 2";
                ventanaDieta2.dispose();
                abrirPaginaPrincipal(usuario);
            }
        });

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaDieta2.dispose();
                abrirVentanaSeleccionDieta();
            }
        });

        panelDieta2.add(botonSeleccionar);
        panelDieta2.add(botonVolver);

        ventanaDieta2.add(panelDieta2);
        ventanaDieta2.setVisible(true);

    }

    private void mostrarDieta1() {
        ArrayList<String> dieta1 = new ArrayList<>();
        dieta1.add("Ensalada de pollo a la parrilla con verduras mixtas, aguacate y vinagreta de limón");
        dieta1.add("Salmón al horno con espárragos y quinoa");
        dieta1.add("Sándwich integral con pechuga de pavo, espinacas, tomate y mostaza");
        dieta1.add("Tacos de pescado con tortillas de maíz, repollo morado, salsa de yogur y lima");
        dieta1.add("Arroz integral con pollo salteado, brócoli y zanahorias");
        dieta1.add("Ensalada de garbanzos con tomate, pepino, cebolla roja, cilantro y aderezo de vinagreta balsámica");
        dieta1.add("Pechuga de pollo a la plancha con batata asada y espárragos");
        dieta1.add("Pimientos rellenos de quinoa, espinacas, champiñones y queso feta");
        dieta1.add("Pasta integral con salsa de tomate casera, espinacas y albóndigas de pavo");
        dieta1.add("Salmón a la parrilla con espárragos y puré de patatas");

        JFrame ventanaDieta1 = new JFrame("Dieta 1");
        ventanaDieta1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaDieta1.setSize(300, 300);
        ventanaDieta1.setLocationRelativeTo(null);

        JPanel panelDieta1 = new JPanel();
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        for (String ejercicio : dieta1) {
            textArea.append(ejercicio + "\n");
        }
        panelDieta1.add(new JScrollPane(textArea));

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTipoDieta("Dieta 1");
                ventanaDieta1.dispose();
                tipoDieta = "Dieta 1";
                abrirPaginaPrincipal(usuario);
            }
        });

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaDieta1.dispose();
                abrirVentanaSeleccionDieta();
            }
        });

        panelDieta1.add(botonSeleccionar);
        panelDieta1.add(botonVolver);

        ventanaDieta1.add(panelDieta1);
        ventanaDieta1.setVisible(true);

    }

    void abrirPaginaPrincipal(String usuario) {
        JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
        SwingUtilities.invokeLater(() -> {
            PaginaPrincipal paginaPrincipal = new PaginaPrincipal(tipoTabla, tipoDieta);
            paginaPrincipal.setTitle("NutriFit Coach");
            paginaPrincipal.setVisible(true);
        });
    }



    private void guardarTipoDieta(String tipoDieta) {
        String url = "jdbc:mysql://localhost:3306/proyectofinal";
        String user = "root";
        String password = "root";

        String sqlUpdate = "UPDATE usuarios SET tipoDieta = ? WHERE nombre = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)) {
            statement.setString(1, tipoDieta);
            statement.setString(2, usuario);
            statement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la base de datos: " + ex.getMessage());
        }


    }


    String obtenerTipoDietaUsuarioDeBD(String usuario) {
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

    String obtenerTipoTablaUsuarioDeBD(String usuario) {
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
            VentanaRegistro ventanaRegistro = new VentanaRegistro();
            ventanaRegistro.setVisible(true);
        });
    }
}
