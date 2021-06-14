package auxiliar.tools;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhonatan Flores
 * @modified Rafael Mazariegos
 */

public class ExcelTesting {

    static Workbook cognitsWorkbook;

    public static void readExcel(String filePath,String fileName){

        System.out.println("Abriendo archivo " + filePath  + fileName);
        File file = new File(filePath + "\\" + fileName);
        try (FileInputStream inputStream = new FileInputStream(file);){
            cognitsWorkbook = null;
            String fileExtensionName = fileName.substring(fileName.indexOf("."));

            if (fileExtensionName.equals(".xlsx")) {
                cognitsWorkbook = new XSSFWorkbook(inputStream);

            } else if (fileExtensionName.equals(".xls")) {
                cognitsWorkbook = new HSSFWorkbook(inputStream);
            }
        }catch (Exception e){
            Assert.fail("El archivo: "+fileName+" no existe en la carpeta: "+filePath);
        }

    }

    public static int cantidadDeFilas(String nombreHoja) {
        Sheet hoja = cognitsWorkbook.getSheet(nombreHoja);
        return hoja.getLastRowNum() - hoja.getFirstRowNum();
    }

    public static void imprimirHoja(String hojaName){
        Sheet hoja = cognitsWorkbook.getSheet(hojaName);

        try {
            System.out.println("Se obtuvo la hoja " + hojaName + " correctamente. La última fila es la " + hoja.getLastRowNum() + " y la primera es la " + hoja.getFirstRowNum());
        } catch (Exception e) {
            System.out.println("No fue posible imprimir. Mensaje de error: '" + e.getMessage() + "'");
        }

        int rowCount = hoja.getLastRowNum()-hoja.getFirstRowNum();
        String contenidoCelda;

        for (int numeroFila = 0; numeroFila < rowCount+1; numeroFila++) {
            Row row = hoja.getRow(numeroFila);
            if(row==null){
                System.out.println("Casilla en fila "+numeroFila+" esta vacia || ");
            }else{
                for (int numeroColumna = 0; numeroColumna < row.getLastCellNum(); numeroColumna++) {
                    if(row.getCell(numeroColumna).getCellType()==1){
                        contenidoCelda=row.getCell(numeroColumna).getStringCellValue();
                        System.out.print(contenidoCelda+"|| ");
                    }else{
                        contenidoCelda=row.getCell(numeroColumna).getNumericCellValue()+"";
                        System.out.print(contenidoCelda+"|| ");
                    }

                }
                System.out.println();
                System.out.println();
            }
        }
    }

    public static boolean existeCelda(String hojaName,String palabra){
        Sheet hoja = cognitsWorkbook.getSheet(hojaName);
        int rowCount = hoja.getLastRowNum()-hoja.getFirstRowNum();
        String contenidoCelda;
        boolean existePalabra=false;

        for (int numeroFila = 0; numeroFila < rowCount+1; numeroFila++) {
            Row row = hoja.getRow(numeroFila);
            if(row==null){
                //System.out.println("Advertencia: la fila "+numeroFila+" esta vacia");
            }else {
                for (int numeroColumna = 0; numeroColumna < row.getLastCellNum(); numeroColumna++) {
                    if (row.getCell(numeroColumna).getCellType() == 1) {
                        contenidoCelda = row.getCell(numeroColumna).getStringCellValue();
                        if (contenidoCelda.equals(palabra)) {
                            System.out.println("String ("+palabra+") encontrado en la Fila: "+numeroFila+",Columna: "+numeroColumna);
                            existePalabra = true;
                        }
                    } else {
                        contenidoCelda = row.getCell(numeroColumna).getNumericCellValue() + "";
                        if (contenidoCelda.equals(palabra)) {
                            System.out.println("String ("+palabra+") encontrado en la Fila: "+numeroFila+",Columna: "+numeroColumna);
                            existePalabra = true;
                        }
                    }
                }
            }
        }
        if(existePalabra==false){
            System.out.println("No existe la palabra");
        }
        return existePalabra;
    }

    public static List<String> obtenerColumna(String hojaName,int numeroColumnaBuscado){
        Sheet hoja = cognitsWorkbook.getSheet(hojaName);
        int rowCount = hoja.getLastRowNum()-hoja.getFirstRowNum();
        List<String> columnaBuscada= new ArrayList<>();
        for (int numeroFila = 0; numeroFila < rowCount+1; numeroFila++) {
            Row row = hoja.getRow(numeroFila);
            if(row==null){
                //System.out.println("Advertencia: la fila "+numeroFila+" esta vacia");
            }else {
                for (int numeroColumna = 0; numeroColumna < row.getLastCellNum(); numeroColumna++) {
                    if(row.getCell(numeroColumna)!=null){
                        if(row.getCell(numeroColumna).getCellType()==1){
                            if(numeroColumna==numeroColumnaBuscado){
                                columnaBuscada.add(row.getCell(numeroColumna).getStringCellValue());
                            }
                        }else{
                            if(numeroColumna==numeroColumnaBuscado){
                                Object celdaNumerica=row.getCell(numeroColumna).getNumericCellValue();
                                columnaBuscada.add(new BigDecimal(celdaNumerica.toString()).toPlainString());
                            }
                        }
                    }
                }
            }
        }
        if(columnaBuscada.size()==0){
            Assert.fail("No existe esa columna");
        }
        return columnaBuscada;
    }

    public static List<String> obtenerColumnaConNull(String hojaName,int numeroColumnaBuscado){
        Sheet hoja = cognitsWorkbook.getSheet(hojaName);
        int rowCount = hoja.getLastRowNum()-hoja.getFirstRowNum();
        List<String> columnaBuscada= new ArrayList<>();
        for (int numeroFila = 0; numeroFila < rowCount+1; numeroFila++) {
            Row row = hoja.getRow(numeroFila);
            if(row==null){
                columnaBuscada.add("Casilla Nula");
            }else {
                for (int numeroColumna = 0; numeroColumna < row.getLastCellNum(); numeroColumna++) {
                    if(row.getCell(numeroColumna)!=null){
                        if(row.getCell(numeroColumna).getCellType()==1){
                            if(numeroColumna==numeroColumnaBuscado){
                                columnaBuscada.add(row.getCell(numeroColumna).getStringCellValue());
                            }
                        }else{
                            if(numeroColumna==numeroColumnaBuscado){
                                Object celdaNumerica=row.getCell(numeroColumna).getNumericCellValue();
                                columnaBuscada.add(new BigDecimal(celdaNumerica.toString()).toPlainString());
                            }
                        }
                    }else{
                        columnaBuscada.add("Casilla Nula");
                    }
                }
            }
        }
        if(columnaBuscada.size()==0){
            Assert.fail("No existe esa columna");
        }
        return columnaBuscada;
    }

    public static List<String> obtenerFila(String hojaName,int numeroFila){
        Sheet hoja = cognitsWorkbook.getSheet(hojaName);
        List<String> filaBuscada= new ArrayList<>();
        Row row = hoja.getRow(numeroFila);
        if(row==null){
            //System.out.println("Advertencia: la fila "+numeroFila+" esta vacia");
        }else {
            for (int numeroColumna = 0; numeroColumna < row.getLastCellNum(); numeroColumna++) {
                if(row.getCell(numeroColumna)!=null){
                    if(row.getCell(numeroColumna).getCellType()==1){
                        filaBuscada.add(row.getCell(numeroColumna).getStringCellValue());
                    }else{
                        Object celdaNumerica=row.getCell(numeroColumna).getNumericCellValue();
                        filaBuscada.add(new BigDecimal(celdaNumerica.toString()).toPlainString());
                    }
                }
            }

        }
        if(filaBuscada.size()==0){
            Assert.fail("No existe esa fila");
        }
        return filaBuscada;
    }

    public static String obtenerValorDeCeldaString(Row fila, int numeroCelda) {
        Cell cell = fila.getCell(numeroCelda);

        if(cell == null) {
            return "";
        } else {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    return (cell.getRichStringCellValue().getString());
                case Cell.CELL_TYPE_NUMERIC:
                    return (Integer.toString((int) ((cell.getNumericCellValue()))));
                default:
                    return "";
            }
        }
    }
}