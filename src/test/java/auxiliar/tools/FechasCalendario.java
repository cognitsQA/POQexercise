package auxiliar.tools;

import auxiliar.Data;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class FechasCalendario {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger( FechasCalendario.class.getName() );
    private static String patron = "yyyy-MM-dd";
    private static String timeZone = Data.time.timeZone;

    private static Date revisarPatron(String fechaString){
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        formato.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date fechaRevisada = null;
        try{
            fechaRevisada = formato.parse(fechaString);
        }catch (Exception ParseException) {
            Assert.fail(ParseException.getMessage());
        }
        return  fechaRevisada;
    }

    public static int numeroDiasEntreDosFechas(String fechaIncial, String fechaFinal){
        Date fechaD1 = revisarPatron(fechaIncial);
        Date fechaD2 = revisarPatron(fechaFinal);

        if(fechaD1!=null && fechaD2!=null){
            long startTime = fechaD1.getTime();
            long endTime = fechaD2.getTime();
            long diffTime = endTime - startTime;
            long diffDays = diffTime / (1000 * 60 * 60 * 24);
            return (int)diffDays;
        }else{
            Assert.fail("La diferencia es null");
            return Integer.parseInt(null);
        }
    }

    public static int obtenerDiferenciaDeTiempoEntreFechas(String stringFechaInicial, String stringFechaFinal, String unidadDeTiempo) {
        Date fechaInicial = revisarPatron(stringFechaInicial);
        Date fechaFinal = revisarPatron(stringFechaFinal);

        if(fechaInicial!=null && fechaFinal!=null){
            long tiempoInicial = fechaInicial.getTime();
            long tiempoFinal = fechaFinal.getTime();
            long tiempoDiferencia = tiempoFinal - tiempoInicial;
            switch(unidadDeTiempo) {
                case "second":{
                    tiempoDiferencia = tiempoDiferencia/1000;
                    break;
                }
                case "minute":{
                    tiempoDiferencia = tiempoDiferencia/(1000*60);
                    break;
                }
                case "hour":{
                    tiempoDiferencia = tiempoDiferencia/(1000*60*60);
                    break;
                }
                case "day":{
                    tiempoDiferencia = tiempoDiferencia/(1000*60*60*24);
                    break;
                }
            }
            return (int) tiempoDiferencia;
        }else{
            return Integer.parseInt(null);
        }
    }

    public static boolean perteneceAlRango(String fechaAComparar, String fechaInicial, String fechaFinal) {
        return (obtenerDiferenciaDeTiempoEntreFechas(fechaAComparar, fechaInicial, "dias") <= 0) && (obtenerDiferenciaDeTiempoEntreFechas(fechaFinal, fechaAComparar, "dias") <= 0);
    }

    public static String obtener(String opcion){
        Date d = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);

        String dia, mes, annio, hora, minuto, segundo, seleccion;

        dia = Integer.toString(calendar.get(Calendar.DATE));
        mes = Integer.toString(calendar.get(Calendar.MONTH)+1);
        annio = Integer.toString(calendar.get(Calendar.YEAR));
        hora = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        minuto = Integer.toString(calendar.get(Calendar.MINUTE));
        segundo = Integer.toString(calendar.get(Calendar.SECOND));

        if(dia.length()==1){
            dia=0+dia;
        }
        if(mes.length()==1){
            mes=0+mes;
        }
        if(hora.length()==1){
            hora=0+hora;
        }
        if(minuto.length()==1){
            minuto=0+minuto;
        }


        opcion=opcion.toLowerCase();
        switch (opcion) {
            case "day": seleccion=dia;break;
            case "month": seleccion=mes;break;
            case "year": seleccion=annio;break;
            case "hour": seleccion=hora;break;
            case "minute": seleccion=minuto;break;
            case "second": seleccion=segundo;break;
            default: seleccion = "Invalid month";break;
        }

        return seleccion;
    }

    //ISO 8601 sirve en calendarios.
    public static String obtenerFecha(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patron);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date date = new Date();
        String fechaFormato = simpleDateFormat.format(date);
        LOGGER.log(Level.INFO,"obtenerFecha:"+fechaFormato);
        return fechaFormato;
    }

    public static String sumarRestar(String fecha, String unidadDeTiempo, int cantidad) {
        String fechaBuena=fecha;
        if(patron.contains("-")){
            if(fecha.contains("/")){
                fechaBuena = FechasCalendario.cambioDiagonalAFormatoISO(fecha);
            }
        }
        Date temporal = revisarPatron(fechaBuena);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(temporal);

        switch(unidadDeTiempo) {

            case "minute":{
                calendar.add(Calendar.MINUTE, cantidad);
                break;
            }

            case "hour":{
                calendar.add(Calendar.HOUR, cantidad);
                break;
            }

            case "day":{
                calendar.add(Calendar.DAY_OF_YEAR, cantidad);
                break;
            }

            case "month":{
                calendar.add(Calendar.MONTH, cantidad);
                break;
            }

            case "year":{
                calendar.add(Calendar.YEAR , cantidad);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + unidadDeTiempo);
        }

        temporal = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(temporal);
    }

    private static Date dateFecha(String fechaCambio){
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        formato.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date fecha = null;
        try{
            fecha = formato.parse(fechaCambio);
        }catch (Exception ParseException){
            Assert.fail(ParseException.getMessage());
        }
        return fecha;
    }

    //dd/MM/yyyy to yyyy-MM-dd
    public static String cambioDiagonalAFormatoISO(String fechaSinFormato){
        String[] fechaDividida = fechaSinFormato.split("/");
        return fechaDividida[2]+"-"+fechaDividida[1]+"-"+fechaDividida[0];
    }

    public static String cambioISOAFormatoDiagonal(String fechaSinFormato){
        String[] fechaDividida = fechaSinFormato.split("-");
        return fechaDividida[2]+"/"+fechaDividida[1]+"/"+fechaDividida[0];
    }

    public static List<String> ordenarFechasAscendente(List<String> listaFechas){
        List<Date> fechas=new ArrayList<>();
        for(String fechaStr:listaFechas){
            fechas.add(FechasCalendario.dateFecha(fechaStr));
        }
        for(int i=0;i<fechas.size();i++){
            for(int j=0;j<fechas.size();j++){
                if(fechas.get(i).before(fechas.get(j))){
                    Date aux=fechas.get(i);
                    fechas.set(i, fechas.get(j));
                    fechas.set(j, aux);
                }
            }
        }
        List<String> fechasOrdenadas=new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        for(Date fechaDate:fechas){
            String fechaFormato = simpleDateFormat.format(fechaDate);
            fechasOrdenadas.add(fechaFormato);
        }
        return fechasOrdenadas;
    }

    public static String cambiarFormatoFecha(String fecha, String patronEntrada, String patronSalida) {
        Date temporal = dateFechaConFormato(fecha, patronEntrada);
        SimpleDateFormat sdf = new SimpleDateFormat(patronSalida);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(temporal);
    }
    private static Date dateFechaConFormato(String fechaEntrada, String patronSalida) {
        SimpleDateFormat formato = new SimpleDateFormat(patronSalida);
        formato.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date fecha = null;
        try {
            fecha = formato.parse(fechaEntrada);
        } catch (Exception ParseException) {
            Assert.fail(ParseException.getMessage());
        }
        return fecha;
    }

    public static List<String> generarTodasLasFechasRango(String fechaInicialInclusiva, String fechaFinalInclusiva) {
        List<String> fechasDelRango = new ArrayList<>();
        String fechaTemporal = fechaInicialInclusiva;
        String fechaTemporalFinal = fechaFinalInclusiva;
        if(patron.contains("-")){
            if(fechaInicialInclusiva.contains("/")){
                fechaTemporal = FechasCalendario.cambioDiagonalAFormatoISO(fechaInicialInclusiva);
            }
            if(fechaFinalInclusiva.contains("/")){
                fechaTemporalFinal = FechasCalendario.cambioDiagonalAFormatoISO(fechaFinalInclusiva);
            }
        }

        while(obtenerDiferenciaDeTiempoEntreFechas(fechaTemporal, fechaTemporalFinal, "días") >= 0) {
            fechasDelRango.add(fechaTemporal);
            fechaTemporal = sumarRestar(fechaTemporal, "day", 1);
        }

        System.out.println("Diferencia entre fechas: " + obtenerDiferenciaDeTiempoEntreFechas(fechaTemporalFinal, fechaTemporal, "días"));

        return fechasDelRango;
    }

    public static String obtenerPrimerFechaDelMes(String fecha) {
        int diaMinimo;
        String fechaMinima;
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date dateTemporal = revisarPatron(fecha);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTemporal);

        diaMinimo = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, diaMinimo);

        dateTemporal = cal.getTime();

        fechaMinima = sdf.format(dateTemporal);

        return fechaMinima;
    }

    public static String obtenerUltimaFechaDelMes(String fecha) {
        int diaMaximo;
        String fechaMaxima;
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date dateTemporal = revisarPatron(fecha);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTemporal);
        diaMaximo = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, diaMaximo);
        dateTemporal = cal.getTime();
        fechaMaxima = sdf.format(dateTemporal);

        return fechaMaxima;
    }

    public static String obtenerFechaAleatoriaRango(String fechaInicial, String fechaFinal) {
        Date fechaInicialTemporal = revisarPatron(fechaInicial);
        Date fechaFinalTemporal = revisarPatron(fechaFinal);
        Date fechaSalida;
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        fechaSalida = new Date(ThreadLocalRandom.current().nextLong(fechaInicialTemporal.getTime(), fechaFinalTemporal.getTime()));
        return sdf.format(fechaSalida);
    }

    public static void definirPatron(String patronNuevo) {
        patron = patronNuevo;
    }
    public static void definirPatronISO() {
        patron = "yyyy-MM-dd";
    }
    public static void definirPatronDiagonal() {
        patron = "dd/MM/yyyy";
    }
}
