package auxiliar.tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseTool {

    static String USER ="#USER";
    static String PASS ="#PASS";
    //"jdbc:oracle:thin:@ipaddress:portnumber/sidOname"
    static String URL="jdbc:oracle:thin:@ipaddress:portnumber/sidOname";


    static Connection connection;
    static ResultSet rs;

    private static void startConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    private static void endConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String sendQuery(String query, int fila, int columna) {
        String respuesta="vacia";
        startConnection();
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            for(int i=0;i<fila;i++) {
                rs.next();
                if(i==fila-1){
                    respuesta= rs.getString(columna);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            endConnection();
        }
        return respuesta;
    }
    public static void sendQuery(String query) {
        startConnection();
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            endConnection();
        }
    }

    public static List<String> sendQuery(String query, int columna) {
        List<String> respuesta=new ArrayList<>();
        startConnection();
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                respuesta.add(rs.getString(columna));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            endConnection();
        }
        return respuesta;
    }

    public static List<String> sendQuery(String query, String columna) {
        List<String> respuesta=new ArrayList<>();
        startConnection();
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                respuesta.add(rs.getString(columna));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            endConnection();
        }
        return respuesta;
    }

    public static void imprimirQuery(String query) {
        startConnection();
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            endConnection();
        }
    }

    public static int numeroDeFilas(String query){
        startConnection();
        int contador=0;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                contador++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            endConnection();
        }
        return contador;
    }
}