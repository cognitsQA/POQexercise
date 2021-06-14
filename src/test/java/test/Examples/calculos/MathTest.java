package test.Examples.calculos;

import auxiliar.tools.Calculate;
import org.testng.annotations.Test;

public class MathTest {
    @Test
    public void redondeo(){
        Double resultado=Calculate.round(2.55,1);
        System.out.println(resultado);

        resultado=Calculate.floor(2.49,1);
        System.out.println(resultado);
    }
}
