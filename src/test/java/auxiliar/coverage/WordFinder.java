package auxiliar.coverage;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFinder {

    static String raizTest = System.getProperty("user.dir") + "\\src\\test\\resources";
    static List<String> archivos;
    static List<String> keywords;

    public static List<String> parser(String parametro, String rout) {
        int i = 0;
        File carpetaT = new File(rout);
        archivos = new ArrayList<>();
        keywords = new ArrayList<>();
        List<String> clases = listarFicherosPorCarpeta(carpetaT);
        String aux = "";
        List<String> totalPorClase = new ArrayList<>();
        for (String clase : clases) {
            if (!clase.split("\\\\")[clase.split("\\\\").length - 2].equals(aux)) {
                //System.out.println("\n#####   PACKAGE " + (aux = clase.split("\\\\")[clase.split("\\\\").length - 2]) + "   #####");
            }
            totalPorClase = extractValues(clase, parametro);
            for (String total : totalPorClase) {
                keywords.add(total);
            }
            keywords.add(clase.split("\\\\")[clase.split("\\\\").length-1]);
            //System.out.println(clase.split("\\\\")[clase.split("\\\\").length - 1] + ", [" + parametro1.replace("(", "") + ": " + totalPorClase + "]");
        }
        return keywords;
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

    static List<String> extractValues(String absolutePath, String parametro1) {
        File clase = new File(absolutePath);
        List<String> temp;
        List<String> values;
        List<String> importantValues;
        importantValues = new ArrayList<>();
        values = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(clase);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.contains(parametro1)) {
                    temp = Arrays.asList(linea.split(parametro1+"="));
                    values.add(temp.get(1));
                }
            }
            Pattern p = Pattern.compile("\"([^\"]*)\"");
            int j = 0;
            while (j < values.size()) {
                Matcher m = p.matcher(values.get(j));
                while (m.find()) {
                    importantValues.add(m.group(0));
                }
                j++;
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Excepción leyendo fichero " + clase + ": ");
            Assert.fail(e.getMessage());
        }
        //System.out.println(importantValues);
        return importantValues;
    }
}
