
/**
 *
 * @author Jose Luis Segura Fernandez, Jorge Espejo Martínez
 */
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Mensaje {
    //Atributos

    private static final String APIPA = "169.254.0.1";
    private String usuario;
    private GregorianCalendar fecha;
    private String ip;
    private String texto;

    //Constructores
    public Mensaje() {
        //Entorno
        //Algoritmo
        this.usuario = "System";
        this.fecha = new GregorianCalendar();
        this.ip = APIPA;
        this.texto = encripta("Por defecto".toUpperCase());/**/
    }

    public Mensaje(String usuario, String ip, String texto) {
        //Entorno
        //Algoritmo
        this.usuario = usuario;
        this.fecha = new GregorianCalendar();

        if (esValida(ip)) {
            this.ip = ip;
        } else {
            this.ip = APIPA;
        }//Fin Si

        this.texto = encripta(texto.toUpperCase());
    }

    //Métodos
    public String getUsuario() {
        //Entorno
        //Algoritmo
        return this.usuario;
    }

    public String getIp() {
        //Entorno
        //Algoritmo
        return this.ip;
    }

    public String getFecha() {
        //Entorno
        byte d, m;
        short a;
        //Algoritmo
        d = (byte) this.fecha.get(Calendar.DAY_OF_MONTH);
        m = (byte) (this.fecha.get(Calendar.MONTH) + 1);
        a = (short) this.fecha.get(Calendar.YEAR);
        return d + "/" + m + "/" + a;
    }

    public String getTexto() {
        //Entorno
        //Algoritmo
        return desencripta(this.texto);
    }

    private static boolean esValida(String ip) {
        //Entorno
        boolean valida;
        String[] octetos;
        int i, valor;
        //Algoritmo
        valida = true;
        if (ip == null || ip.isEmpty()) {
            valida = false;
        } else {
            octetos = ip.split("\\.");
            if (octetos.length != 4) {
                valida = false;
            } else {
                try {
                    for (i = 0; i < 4; i++) {
                        valor = Integer.parseInt(octetos[i]);
                        if (valor < 1 || valor > 254) {
                            valida = false;
                        }//Fin Si
                    }//Fin Para
                } catch (NumberFormatException nfe) {
                    valida = false;
                }//Fin try-catch
            }//Fin Si
        }//Fin Si
        return valida;
    }

    private static String encripta(String msg) {
        //Entorno
        String invertido, encriptado;
        int i;
        char c, nuevo;
        //Algoritmo
        invertido = reverse(msg);
        encriptado = "";
        for (i = 0; i < invertido.length(); i++) {
            c = invertido.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                nuevo =(char) ((c - 'A' + 3) % 26 + 'A');
                encriptado = encriptado + nuevo;
            } else {
                    encriptado = encriptado + c;
                }//Fin Si
        }//Fin Para
        return encriptado;
    }

    private static String desencripta(String msg) {
        //Entorno
        String desplazado;
        int i;
        char c, nuevo;
        //Algoritmo
        desplazado = "";
        for (i = 0; i < msg.length(); i++) {
            c = msg.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                nuevo =(char) ((c - 'A' - 3 + 26) % 26 + 'A');
                desplazado = desplazado + nuevo;
            } else {
                    desplazado = desplazado + c;
                }//Fin Si
        }//Fin Para
        return reverse(desplazado);
    }

    private static String reverse(String cad) {
        //Entorno
        String[] cadena;
        String resultado, palabra, pInvert;
        int i, j;
        //Algoritmo
        cadena = cad.split(" ");
        resultado = "";
        for (i = 0; i < cadena.length; i++) {
            palabra = cadena[i];
            pInvert = "";
            for (j = palabra.length() - 1; j >= 0; j--) {
                pInvert = pInvert + palabra.charAt(j);
            }//Fin Para
            resultado = resultado + pInvert;
            if (i < cadena.length - 1) {
                resultado = resultado + " ";
            }//Fin Si
        }//Fin Para
        return resultado;
    }
}
