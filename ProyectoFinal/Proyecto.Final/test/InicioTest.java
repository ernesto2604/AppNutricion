import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class InicioTest {
    @Test
    public void testInicioCreation() {
        Inicio inicio = new Inicio();
        assertNotNull(inicio);
        assertNotNull(inicio.panelInicio);
        assertNotNull(inicio.iniciarSesionButton);
        assertNotNull(inicio.registrarseButton);
        assertNotNull(inicio.imagenLabel);
    }
}
