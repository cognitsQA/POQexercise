package test.Examples.fechas;

import auxiliar.tools.FechasCalendario;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FechasCalendarioTest {

    @Test
    public void obtenerFecha(){
        String fecha=FechasCalendario.obtenerFecha();
        System.out.println(fecha);
    }
    @Test
    public void obtenerFechaDiagonalISO(){
        String fecha=FechasCalendario.obtenerFecha();

        fecha=FechasCalendario.cambioISOAFormatoDiagonal(fecha);
        System.out.println(fecha);
        fecha=FechasCalendario.cambioDiagonalAFormatoISO(fecha);
        System.out.println(fecha);
    }
    @Test
    public void definirPatronesFechas(){
        FechasCalendario.definirPatron("yyyy-MM-dd hh:mm:ss");
        System.out.println(FechasCalendario.obtenerFecha());

        FechasCalendario.definirPatronDiagonal();
        System.out.println(FechasCalendario.obtenerFecha());

        FechasCalendario.definirPatronISO();
        System.out.println(FechasCalendario.obtenerFecha());
    }
    @Test
    public void ordenarFechasAscendente(){
        List<String> listaFechas=new ArrayList<>();
        listaFechas.add("2021-05-12");
        listaFechas.add("2021-05-15");
        listaFechas.add("2021-05-13");
        List<String> listaOrdenada=FechasCalendario.ordenarFechasAscendente(listaFechas);
        System.out.println(listaOrdenada);
    }
    @Test
    public void sumarDiaMesHoraFecha(){
        String fecha=FechasCalendario.obtenerFecha();
        String fechaMasUnDia=FechasCalendario.sumarRestar(fecha,"day",1);
        System.out.println(fechaMasUnDia);
    }
    @Test
    public void obtenerMesHoraDia(){
        String mesActual=FechasCalendario.obtener("month");
        System.out.println(mesActual);
    }
    @Test
    public void diferenciaDeDiasEntreDosFechas(){
        String fechaInicial="2021-05-12";
        String fechaFinal="2021-05-15";
        int diferenciaDias = FechasCalendario.numeroDiasEntreDosFechas(fechaInicial,fechaFinal);
        System.out.println(diferenciaDias);
    }
    @Test
    public void diferenciaDeTiempoEntreFechas(){
        String fechaInicial="2021-05-12";
        String fechaFinal="2021-05-15";
        int diferenciaDias = FechasCalendario.obtenerDiferenciaDeTiempoEntreFechas(fechaInicial,fechaFinal,"hour");
        System.out.println(diferenciaDias);
    }
    @Test
    public void perteneceAlRango(){
        String fechaInicial="2021-05-12";
        String fechaFinal="2021-05-15";
        String fechaEnMedioDelRango="2021-05-13";
        boolean pertenece = FechasCalendario.perteneceAlRango(fechaEnMedioDelRango,fechaInicial,fechaFinal);
        System.out.println(pertenece);
    }
    @Test
    public void cambiarPatronFecha(){
        String fechaInicial="2021-05-12 12:30:30";
        String fechaNuevoPatron = FechasCalendario.cambiarFormatoFecha(fechaInicial,"yyyy-MM-dd hh:mm:ss","yyyy-MM-dd");
        System.out.println(fechaNuevoPatron);
    }
    @Test
    public void generarTodasLasFechasRango(){
        String fechaInicial="2021-05-12";
        String fechaFinal="2021-05-17";
        List<String> listaFechasRango = FechasCalendario.generarTodasLasFechasRango(fechaInicial,fechaFinal);
        System.out.println(listaFechasRango);
    }
    @Test
    public void obtenerFechaAleatoriaRango(){
        String fechaInicial="2021-05-12";
        String fechaFinal="2021-05-17";
        String fechaAleatoriaRango = FechasCalendario.obtenerFechaAleatoriaRango(fechaInicial,fechaFinal);
        System.out.println(fechaAleatoriaRango);
    }
    @Test
    public void primerUltimoDiaMes(){
        String fechaActual=FechasCalendario.obtenerFecha();
        String primerDia = FechasCalendario.obtenerPrimerFechaDelMes(fechaActual);
        System.out.println(primerDia);
        String ultimoDia = FechasCalendario.obtenerUltimaFechaDelMes(fechaActual);
        System.out.println(ultimoDia);
    }

}
