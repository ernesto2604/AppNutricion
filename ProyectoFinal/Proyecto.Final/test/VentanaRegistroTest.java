import org.junit.Test;

import static org.junit.Assert.*;

public class VentanaRegistroTest {
    @Test
    public void testVentanaRegistroCreation() {
        VentanaRegistro ventanaRegistro = new VentanaRegistro();
        assertNotNull(ventanaRegistro);
        assertNotNull(ventanaRegistro.panelRegistro);
        assertNotNull(ventanaRegistro.textField1);
        assertNotNull(ventanaRegistro.textField2);
        assertNotNull(ventanaRegistro.textField3);
        assertNotNull(ventanaRegistro.textField4);
        assertNotNull(ventanaRegistro.textField5);
        assertNotNull(ventanaRegistro.textField6);
        assertNotNull(ventanaRegistro.textField7);
        assertNotNull(ventanaRegistro.textField8);
        assertNotNull(ventanaRegistro.registrarseButton);
    }

    @Test
    public void testAbrirPaginaPrincipal() {
        VentanaRegistro ventanaRegistro = new VentanaRegistro();
        ventanaRegistro.abrirPaginaPrincipal("Juanito");
        assertEquals("Registro", ventanaRegistro.getTitle());
    }
}
