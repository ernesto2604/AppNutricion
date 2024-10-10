import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Bdd {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/proyectofinal";
        String user = "root";
        String password = "root";
        String salida;
        ResultSet resultado;
        Statement st;
        String consulta = "select * from usuarios";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, user, password);
            st = conexion.createStatement();
            resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                System.out.println(resultado.getString("apellidos"));
            }
            st.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bdd.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not found.");
        } catch (SQLException e) {
            Logger.getLogger(Bdd.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}