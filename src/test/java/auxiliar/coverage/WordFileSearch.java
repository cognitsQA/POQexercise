package auxiliar.coverage;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class WordFileSearch {
    static String raizTest = System.getProperty("user.dir") + "\\src\\test\\java\\test\\web";
    static String rutaTest = raizTest;
    static int sumaParametro1 = 0;
    static List<String> archivos;

    public static void contador(String parametro1){
        File carpetaT = new File(rutaTest);
        archivos = new ArrayList<>();
        List<String> clases = listarFicherosPorCarpeta(carpetaT);
        String aux = "";
        for (String clase : clases) {
            if (!clase.split("\\\\")[clase.split("\\\\").length - 2].equals(aux)) {
                //System.out.println("\n#####   PACKAGE " + (aux = clase.split("\\\\")[clase.split("\\\\").length - 2]) + "   #####");
            }
            int totalPorClase = contarCasosDePrueba(clase,parametro1);
            //System.out.println(clase.split("\\\\")[clase.split("\\\\").length - 1] + ", [" + parametro1.replace("(", "") + ": " + totalPorClase + "]");
        }
        System.out.println("| Total : " + sumaParametro1);
        sumaParametro1=0;
    }

    private static List<String> listarFicherosPorCarpeta(File carpeta) {
        for (File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                listarFicherosPorCarpeta(ficheroEntrada);
            } else {
                archivos.add(ficheroEntrada.getAbsolutePath());
            }
        }
        return archivos;
    }

    private static int contarCasosDePrueba(String absolutePath, String parametro1) {
        File clase = new File(absolutePath);
        int contador = 0;
        try {
            FileReader fileReader = new FileReader(clase);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.contains(parametro1)) {
                    contador++;
                    sumaParametro1++;
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Excepción leyendo fichero " + clase + ": ");
            Assert.fail(e.getMessage());
        }
        return contador;
    }

}
