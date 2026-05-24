
/**
 *
 * @author Jose Luis Segura Fernandez, Jorge Espejo Martinez
 */
public class Mensajeria {

    public static void main(String[] args) {
        //Entorno
        Cola colaMensajes;
        boolean salir;
        byte opcion;
        short aux;
        String usuario, ip, texto;
        Mensaje mensajeActual;
        char confirmacion;

        //Algoritmo
        colaMensajes = new Cola();
        salir = false;

        do {
            System.out.println("1. Envío instantáneo.");
            System.out.println("2. Enviar mensaje.");
            System.out.println("3. Recibir mensaje.");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            aux = Leer.datoShort();
        } while (aux < 1 || aux > 4);
        opcion = (byte) aux;
        switch (opcion) {
            case 1:
                mensajeActual = new Mensaje();
                colaMensajes.meterEnCola(mensajeActual);
                System.out.println("Se ha agregado mensaje instantaneo a la cola");
                break;

            case 2:
                System.out.println("Usuario: ");
                usuario = Leer.dato();

                System.out.println("IP: ");
                ip = Leer.dato();

                System.out.println("Texto: ");
                texto = Leer.dato();

                if (!usuario.equals("") && !ip.equals("") && !texto.equals("")) {
                    mensajeActual = new Mensaje(usuario, ip, texto);
                    colaMensajes.meterEnCola(mensajeActual);
                    System.out.println("Mensaje encolado.");
                } else {
                    System.out.println("Error: el usuario, la ip y el texto deben estar rellenados");
                }//Fin Si
                break;

            case 3:
                if (colaMensajes.esColaVacia()) {
                    System.out.println("No hay más mensajes");
                } else {
                    mensajeActual = (Mensaje) colaMensajes.sacarDeCola();
                    System.out.print("Usuario: " + mensajeActual.getUsuario());
                    System.out.print("Fecha: " + mensajeActual.getFecha());
                    System.out.print("IP: " + mensajeActual.getIp());
                    System.out.println("Texto: " + mensajeActual.getTexto());
                }//Fin Si
                break;

            default:
                confirmacion = 'c';
                do {
                    if (colaMensajes.esColaVacia() == false) {
                        System.out.println("Hay mensajes pendientes de recibir ¿Salir de todas formas? (S/N): ");
                        confirmacion = Leer.datoChar();
                        if (confirmacion == 's' || confirmacion == 'S') {
                            salir = true;
                        } else {
                            salir = false;
                        }//Fin Si
                    } else {
                        salir = true;
                    }//Fin Si
                } while (confirmacion != 'n' && confirmacion != 'N' && confirmacion != 's' && confirmacion != 'S');
        }//Fin Segun Sea
    }//Fin Programa
}
