package auxiliar.tools;

import auxiliar.Data;
import org.testng.Assert;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.util.Properties;

public class GmailReader {

    static Properties properties;
    static Session session;
    static Store store;

    final static String USUARIO = Data.emailTesting.user;
    final static String CONTRASENA = Data.emailTesting.password;

    final static String HOST = "smtp.gmail.com";
    final static String PROTOCOLO = "imaps";
    final static String INBOX = "INBOX";

    public final static int DE = 0;
    public final static int ASUNTO = 1;
    public final static int CUERPO_CORREO = 2;

    private static void establecerConexion() throws MessagingException {
        properties = System.getProperties();
        session = Session.getInstance(properties);
        store = session.getStore(PROTOCOLO);
        System.out.println(HOST);
        store.connect(HOST, USUARIO, CONTRASENA);
    }

    public static int obtenerTotalDeCorreosEnLaBandejaDeEntrada() throws MessagingException {
        establecerConexion();
        int totalDeCorreosEnLaBandejaDeEntrada = store.getFolder(INBOX).getMessageCount();
        store.close();
        return totalDeCorreosEnLaBandejaDeEntrada;
    }

    public static String[] obtenerCorreosConAsunto(String asunto) throws MessagingException {
        System.out.println("Buscando correos con asunto: '" + asunto + "'.");

        establecerConexion();
        String[] mensajeCodificado;
        Folder bandejaDeEntrada = store.getFolder(INBOX);
        bandejaDeEntrada.open(Folder.READ_ONLY);
        Message[] correosEncontrados = bandejaDeEntrada.search(criterioDeBusqueda(asunto));

        System.out.println("Se encontraron " + correosEncontrados.length + " correos con ese asunto, revisando el último.");

        int idUltimoCorreo = correosEncontrados.length - 1;
        Message ultimoCorreo = correosEncontrados[idUltimoCorreo];
        mensajeCodificado = parseMessage(ultimoCorreo);
        bandejaDeEntrada.close();
        store.close();
        return mensajeCodificado;
    }

    public static String[][] obtenerMultiplesCorreosConAsunto(String asunto) throws MessagingException {
        System.out.println("Buscando correos con asunto: '" + asunto + "'.");

        establecerConexion();
        Folder bandejaDeEntrada = store.getFolder(INBOX);
        bandejaDeEntrada.open(Folder.READ_ONLY);
        Message[] correosEncontrados = bandejaDeEntrada.search(criterioDeBusqueda(asunto));

        String[][] mensajesCodificados = new String[correosEncontrados.length][3];

        System.out.println("Se encontraron " + correosEncontrados.length + " correos con ese asunto, revisando el último.");

        for (int i = 0; i < mensajesCodificados.length; i++) {
            Message correo_i = correosEncontrados[i];
            mensajesCodificados[i] = parseMessage(correo_i);
        }
        bandejaDeEntrada.close();
        store.close();
        return mensajesCodificados;
    }

    public static int obtenerTotalDeCorreosConElMismoAsunto(String asunto) throws MessagingException {
        establecerConexion();
        Folder bandejaDeEntrada = store.getFolder(INBOX);
        bandejaDeEntrada.open(Folder.READ_ONLY);
        int totalDeCorreosConElMismoAsunto = bandejaDeEntrada.search(criterioDeBusqueda(asunto)).length;
        store.close();
        return totalDeCorreosConElMismoAsunto;
    }

    public static boolean revisarEmail(String asunto, String palabraClave1, String palabraClave2) throws MessagingException {
        String[] correo = obtenerCorreosConAsunto(asunto);
        correo = htmlToString(correo);
        String contenido = correo[CUERPO_CORREO];
        return contenido.contains(palabraClave1) && contenido.contains(palabraClave2);
    }

    public static boolean revisarEmails(String asunto, String palabraClave1, String palabraClave2) throws MessagingException {
        String[][] correos = obtenerMultiplesCorreosConAsunto(asunto);
        for(String[] correo : correos) {
            correo = htmlToString(correo);
            String contenido = correo[CUERPO_CORREO];
            if (contenido.contains(palabraClave1) && contenido.contains(palabraClave2)) {
                return true;
            }
        }
        return false;
    }

    public static void borrarTodosLosCorreosDeLaBandejaDeEntrada() throws MessagingException {
        establecerConexion();
        Folder bandejaDeEntrada = store.getFolder(INBOX);
        bandejaDeEntrada.open(Folder.READ_WRITE);
        Message[] mensajes = bandejaDeEntrada.getMessages();
        for (Message mensaje : mensajes)
            mensaje.setFlag(Flags.Flag.DELETED, true);
        bandejaDeEntrada.close(true);
        store.close();
    }

    public static boolean esperarCorreoConAsunto(String asunto, int intentos) throws MessagingException {
        boolean llegoElCorreo = false;
        for (int i = 1; i <= intentos; i++) {
            System.out.println("Esperando correo... intento: " + i);
            if (obtenerTotalDeCorreosConElMismoAsunto(asunto) != 0) {
                llegoElCorreo = true;
                break;
            }
        }
        return llegoElCorreo;
    }

    /* NO BORRAR */
    private static SearchTerm criterioDeBusqueda(String asunto) {
        return new SearchTerm() {
            public boolean match(Message message) {
                try {
                    return message.getSubject().contains(asunto);
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
    }

    public static String[] htmlToString(String[] email) {
        String[] returnArray = new String[3];
        returnArray[DE] = reemplazarCaracteresEspeciales(email[DE]);
        returnArray[ASUNTO] = reemplazarCaracteresEspeciales(email[ASUNTO]);
        returnArray[CUERPO_CORREO] = email[CUERPO_CORREO].replaceAll("</td><td>", " ");
        returnArray[CUERPO_CORREO] = returnArray[CUERPO_CORREO].replaceAll("</tr><tr>", " ");
        returnArray[CUERPO_CORREO] = returnArray[CUERPO_CORREO].replaceAll("\\<.*?>", "");
        returnArray[CUERPO_CORREO] = returnArray[CUERPO_CORREO].replaceAll("\\s+", " ");
        returnArray[CUERPO_CORREO] = reemplazarCaracteresEspeciales(returnArray[CUERPO_CORREO].replaceAll("&nbsp;", "\n"));
        return returnArray;
    }

    private static String reemplazarCaracteresEspeciales(String caracterEspecial) {
        caracterEspecial = caracterEspecial.replaceAll("&aacute;", "á");
        caracterEspecial = caracterEspecial.replaceAll("&eacute;", "é");
        caracterEspecial = caracterEspecial.replaceAll("&iacute;", "í");
        caracterEspecial = caracterEspecial.replaceAll("&oacute;", "ó");
        caracterEspecial = caracterEspecial.replaceAll("&uacute;", "ú");
        caracterEspecial = caracterEspecial.replaceAll("&Aacute;", "Á");
        caracterEspecial = caracterEspecial.replaceAll("&Eacute;", "É");
        caracterEspecial = caracterEspecial.replaceAll("&Iacute;", "Í");
        caracterEspecial = caracterEspecial.replaceAll("&Oacute;", "Ó");
        caracterEspecial = caracterEspecial.replaceAll("&Uacute;", "Ú");
        caracterEspecial = caracterEspecial.replaceAll("&ntilde;", "ñ");
        caracterEspecial = caracterEspecial.replaceAll("&Ntilde;", "Ñ");
        return caracterEspecial;
    }

    private static String[] parseMessage(Message message) {
        String[] messageParsed = new String[3];
        String stringContent;
        try {
            messageParsed[DE] = message.getFrom()[0].toString();
            messageParsed[ASUNTO] = message.getSubject();
            Object content = message.getContent();
            if (!(content instanceof Multipart)) {
                stringContent = message.getContent().toString();
            } else {
                Multipart multipart = (Multipart) content;
                stringContent = "MULTIPART;";
                if (multipart.getCount() > 2)
                    Assert.fail("Unknown Mail Format.");
                for (int j = 0; j < multipart.getCount(); j++) {
                    BodyPart bodyPart = multipart.getBodyPart(j);
                    String disposition = bodyPart.getDisposition();
                    if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                        DataHandler handler = bodyPart.getDataHandler();
                        stringContent += "ATTACHMENT: " + handler.getName() + ";";
                    } else {
                        stringContent += "BODY: " + bodyPart.getContent().toString() + ";";
                    }
                }
            }
            messageParsed[CUERPO_CORREO] = stringContent;
        } catch (Exception e) {
            System.err.println("MessagingException: " + e.getMessage());
            Assert.fail("Couldn't parse mail.");
        }
        return messageParsed;
    }
}