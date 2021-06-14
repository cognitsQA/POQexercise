package test.Examples.documents;

import auxiliar.tools.ExcelTesting;
import org.testng.annotations.Test;

import java.util.List;

public class ExcelTestingTest {

    @Test
    public void imprimirHojaExcel(){
        String path="src/test/java/test/Examples/documents";
        ExcelTesting.readExcel(path,"Hoja de cálculo sin título.xlsx");

        ExcelTesting.imprimirHoja("Hoja 1");
        System.out.println("-----------------------------------------------------------");
    }

    @Test
    public void cantidadDefilas(){
        String path="src/test/java/test/Examples/documents";
        ExcelTesting.readExcel(path,"Hoja de cálculo sin título.xlsx");

        int cantidadFilas=ExcelTesting.cantidadDeFilas("Hoja 1");
        System.out.println("tiene :"+cantidadFilas+" filas.");
        System.out.println("-----------------------------------------------------------");
    }
    @Test
    public void verificarSiexisteCelda(){
        String path="src/test/java/test/Examples/documents";
        ExcelTesting.readExcel(path,"Hoja de cálculo sin título.xlsx");

        System.out.println("Existe la celda: "+ExcelTesting.existeCelda("Hoja 1","siga con su vida"));
        System.out.println("-----------------------------------------------------------");
    }
    @Test
    public void obtenerColumnaIgnorandoCasillasVacias(){
        String path="src/test/java/test/Examples/documents";
        ExcelTesting.readExcel(path,"Hoja de cálculo sin título.xlsx");

        List<String> columnaUnoSinNull=ExcelTesting.obtenerColumna("Hoja 1",0);
        System.out.println("columna Uno Sin Null");
        System.out.println(columnaUnoSinNull);
        System.out.println("-----------------------------------------------------------");
    }
    @Test
    public void obtenerColumnaConCasillasVacias(){
        String path="src/test/java/test/Examples/documents";
        ExcelTesting.readExcel(path,"Hoja de cálculo sin título.xlsx");

        List<String> columnaUnoConNull=ExcelTesting.obtenerColumnaConNull("Hoja 1",0);
        System.out.println("columna Uno Con Null");
        System.out.println(columnaUnoConNull);
        System.out.println("-----------------------------------------------------------");
    }
    @Test
    public void obtenerFila(){
        String path="src/test/java/test/Examples/documents";
        ExcelTesting.readExcel(path,"Hoja de cálculo sin título.xlsx");

        List<String> filaDos=ExcelTesting.obtenerFila("Hoja 1",1);
        System.out.println("fila Dos");
        System.out.println(filaDos);
    }

}
