package test.Examples.email;

import auxiliar.tools.EnviarCorreo;
import org.testng.annotations.Test;

public class EnviarCorreoTest {
    @Test
    public void enviarCorreo(){
        String asuntoCorreo="Correo con asusto de prueba";
        String cuerpoCorreo="contenido de prueba";
        EnviarCorreo.enviarCorreo(asuntoCorreo, cuerpoCorreo);
    }
}
