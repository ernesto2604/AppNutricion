import org.junit.Test;
import static org.junit.Assert.*;

public class VentanaInicioSesionTest {

    @Test
    public void testVerificarInicioSesionCredencialesValidas() {
        VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
        assertTrue(ventanaInicioSesion.verificarInicioSesion("usuarioValido", "contraseñaValida"));
    }

    @Test
    public void testVerificarInicioSesionCredencialesInvalidas() {
        VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
        assertFalse(ventanaInicioSesion.verificarInicioSesion("usuarioInvalido", "contraseñaInvalida"));
    }
}
