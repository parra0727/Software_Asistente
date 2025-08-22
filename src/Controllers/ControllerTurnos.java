package Controllers; // Paquete Controllers
import Models.Turnos; // Importa la clase Menu
import Models.Usuarios; // Importa la clase Turnos
import Utils.MensajesUtil; // Importa la clase Usuarios
import Utils.Menu; // Importa la clase MensajesUtil
import java.util.ArrayList; // Importa la clase Scanner para leer datos por consola
import java.util.List; // Importa la clase ArrayList para listas dinámicas
import java.util.Random; // Importa la clase Random para generar números aleatorios
import java.util.Scanner; // Importa la interfaz List

public class ControllerTurnos // Define la clase ControllerTurnos
{
    static Menu menu= new Menu(); // Instancia de la clase Menu
    static ArrayList<Turnos> turnos= new ArrayList(); // Lista de turnos registrados
    static MensajesUtil mensajes = new MensajesUtil(); // Instancia para mostrar mensajes
    static Scanner sc = new Scanner(System.in); // Scanner para leer datos del usuario
    static Random aleatorio = new Random(); // Instancia para generar números aleatorios

    public void Agendar_Turnos() // Método para agendar nuevos turnos
    {
        Turnos turno= new Turnos(); // Crea un nuevo objeto Turnos
        try
        {
            mensajes.mostrarMensajes("Por favor ingrese el numero de documento"); // Solicita el número de documento
            String numero_documento= sc.nextLine(); // Lee el número de documento ingresado
            for(Usuarios u: ControllerUsuarios.usuarios) // Recorre la lista de usuarios
            {
                if(ControllerUsuarios.Validar_Usuario(numero_documento)) // Valida si el usuario existe
                {
                    mensajes.mostrarMensajes("||||||||||||BIENVENID@ A AGENDAR TURNOS"); // Mensaje de bienvenida
                    mensajes.mostrarMensajes("Usuario encontrado..."); // Informa que el usuario fue encontrado
                    turno.setUsuario(u); // Asocia el usuario al turno
                    mensajes.mostrarMensajes("Por favor sigue los siguientes pasos para agendar un turno!"); // Indica los pasos a seguir
                    mensajes.mostrarMensajes("Por favor ingresa la fecha para agendar tu turno(dd/MM/yyyy):"); // Solicita la fecha del turno
                    turno.setFecha(sc.nextLine()); // Guarda la fecha ingresada
                    mensajes.mostrarMensajes("Por favor ingresa la hora para agendar tu turno(HH:mm):"); // Solicita la hora del turno
                    turno.setHora(sc.nextLine()); // Guarda la hora ingresada
                    turno.setNumero_Turno(aleatorio.nextInt(9000)); // Asigna un número de turno aleatorio
                    mensajes.mostrarMensajes("Turno agendado con exito!"); // Informa que el turno fue agendado
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||"); // Separador visual
                    mensajes.mostrarMensajes("Usuario:"+turno.getUsuario().getNombre()); // Muestra el nombre del usuario
                    mensajes.mostrarMensajes("Documento:" + turno.getUsuario().getNumero_documento()); // Muestra el documento del usuario
                    mensajes.mostrarMensajes("Fecha:" + turno.getFecha()); // Muestra la fecha del turno
                    mensajes.mostrarMensajes("Hora:" + turno.getHora()); // Muestra la hora del turno
                    mensajes.mostrarMensajes("turno:"+ turno.getNumero_Turno()); // Muestra el número de turno
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||"); // Separador visual
                    turnos.add(turno); // Agrega el turno a la lista de turnos
                    menu.RepetirMenu(); // Vuelve al menú principal
                }
                else
                {
                    mensajes.mostrarMensajes("Usuario no encotrado..."); // Informa que el usuario no fue encontrado
                    menu.RepetirMenu(); // Vuelve al menú principal
                }
            }
        }
        catch(Exception e)
        {
            mensajes.mostrarMensajes("Ha ocurrido un error:"+e.getMessage()); // Muestra el mensaje de error
            mensajes.mostrarMensajes("Revisa bien la informacion digitada!!!"); // Recomienda revisar la información
            menu.RepetirMenu(); // Vuelve al menú principal
        }
    }

    public void Mostrar_Turnos() // Método para mostrar los turnos de un usuario
    {
        mensajes.mostrarMensajes("|||||||||||||||BIENVENID@ A MOSTRAR TURNOS"); // Mensaje de bienvenida
        mensajes.mostrarMensajes("Por favor ingrese el numero de documento"); // Solicita el número de documento
        String numero_documento= sc.nextLine(); // Lee el número de documento
        if(ControllerUsuarios.Validar_Usuario(numero_documento)) // Valida si el usuario existe
        {
            mensajes.mostrarMensajes("Usuario encontrado..."); // Informa que el usuario fue encontrado
            int contador=0; // Inicializa el contador de turnos
            for(Turnos t: turnos) // Recorre la lista de turnos
            {
                if(Validar_Turnos(numero_documento)) // Valida si el usuario tiene turnos
                {
                    contador++; // Incrementa el contador
                    mensajes.mostrarMensajes("Se encontraron turnos registrados para el usuario..."); // Informa que se encontraron turnos
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||||"); // Separador visual
                    mensajes.mostrarMensajes("Turnos #"+contador); // Muestra el número de turno
                    mensajes.mostrarMensajes("Nombre:"+t.getUsuario().getNombre()); // Muestra el nombre del usuario
                    mensajes.mostrarMensajes("Numero de documento:"+t.getUsuario().getNumero_documento()); // Muestra el documento
                    mensajes.mostrarMensajes("Fecha:"+t.getFecha()); // Muestra la fecha del turno
                    mensajes.mostrarMensajes("Hora:"+t.getHora()); // Muestra la hora del turno
                    mensajes.mostrarMensajes("turno:"+t.getNumero_Turno()); // Muestra el número de turno
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||||"); // Separador visual
                }
            }
            if(contador==0) // Si no se encontraron turnos
            {
                mensajes.mostrarMensajes("El usuario no tiene turnos encontrados"); // Informa que no hay turnos
                menu.RepetirMenu(); // Vuelve al menú principal
            }
            else
            {
                mensajes.mostrarMensajes("Numero de turnos encontrados:"+contador); // Muestra la cantidad de turnos encontrados
                menu.RepetirMenu(); // Vuelve al menú principal
            }
        }
        else
        {
            mensajes.mostrarMensajes("Usuario no encontrado..."); // Informa que el usuario no fue encontrado
            menu.RepetirMenu(); // Vuelve al menú principal
        }
    }

    public void Modificar_Turno() // Método para modificar un turno existente
    {
        mensajes.mostrarMensajes("|||||||||||||BIENVENID@ A MODIFICAR TURNOS|||||||||||||"); // Mensaje de bienvenida
        mensajes.mostrarMensajes("Por favor ingrese el numero de documento"); // Solicita el número de documento
        String numero_documento= sc.nextLine(); // Lee el número de documento

        if(ControllerUsuarios.Validar_Usuario(numero_documento)) // Valida si el usuario existe
        {
            mensajes.mostrarMensajes("Usuario encontrado..."); // Informa que el usuario fue encontrado

            List<Turnos> turnosUsuario = new ArrayList<>(); // Lista para almacenar los turnos del usuario
            for(Turnos t : turnos) { // Recorre la lista de turnos
                if(t.getUsuario().getNumero_documento().equals(numero_documento)) { // Si el turno pertenece al usuario
                    turnosUsuario.add(t); // Agrega el turno a la lista
                }
            }

            if(turnosUsuario.isEmpty()) { // Si no hay turnos para el usuario
                mensajes.mostrarMensajes("No se encontraron turnos registrados para este usuario..."); // Informa que no hay turnos
                menu.RepetirMenu(); // Vuelve al menú principal
                return; // Sale del método
            }
            mensajes.mostrarMensajes("cantidad de turnos:" + turnosUsuario.size()); // Muestra la cantidad de turnos
            mensajes.mostrarMensajes("Se encontraron los siguientes turnos:"); // Informa que se encontraron turnos
            for(int i=0; i<turnosUsuario.size(); i++) { // Recorre la lista de turnos del usuario
                Turnos t = turnosUsuario.get(i); // Obtiene el turno
                mensajes.mostrarMensajes((i+1) + ". Fecha: " + t.getFecha() + " | Hora: " + t.getHora()); // Muestra la fecha y hora del turno
            }

            mensajes.mostrarMensajes("Digite el número del turno que desea modificar:"); // Solicita el número del turno a modificar
            int indice = sc.nextInt(); // Lee el número ingresado
            sc.nextLine(); // Limpia el buffer

            if(indice < 1 || indice > turnosUsuario.size()) { // Valida si la opción es válida
                mensajes.mostrarMensajes("Opción inválida..."); // Informa que la opción es inválida
                menu.RepetirMenu(); // Vuelve al menú principal
                return; // Sale del método
            }

            Turnos turnoSeleccionado = turnosUsuario.get(indice - 1); // Obtiene el turno seleccionado

            boolean confirmado=false; // Variable para controlar la confirmación
            do {
                mensajes.mostrarMensajes("¿Qué quieres modificar del turno?"); // Pregunta qué desea modificar
                mensajes.mostrarMensajes("1. Fecha"); // Opción para modificar la fecha
                mensajes.mostrarMensajes("2. Hora"); // Opción para modificar la hora
                mensajes.mostrarMensajes("Digita tu opción acá:"); // Solicita la opción
                int opcion=sc.nextInt(); // Lee la opción
                sc.nextLine(); // Limpia el buffer

                switch(opcion) { // Evalúa la opción seleccionada
                    case 1:
                        mensajes.mostrarMensajes("Por favor ingresa la nueva fecha:"); // Solicita la nueva fecha
                        String fecha = sc.nextLine(); // Lee la nueva fecha
                        turnoSeleccionado.setFecha(fecha); // Modifica la fecha del turno
                        mensajes.mostrarMensajes("La fecha se modificó con éxito!!!"); // Informa que la fecha fue modificada
                        confirmado=true; // Confirma la modificación
                        break;

                    case 2:
                        mensajes.mostrarMensajes("Por favor ingresa la nueva hora para el turno:"); // Solicita la nueva hora
                        String hora= sc.nextLine(); // Lee la nueva hora
                        turnoSeleccionado.setHora(hora); // Modifica la hora del turno
                        mensajes.mostrarMensajes("La hora fue cambiada exitosamente!!!"); // Informa que la hora fue modificada
                        confirmado=true; // Confirma la modificación
                        break;

                    default:
                        mensajes.mostrarMensajes("Opción inválida..."); // Informa que la opción es inválida
                        break;
                }
            } while(!confirmado); // Repite hasta que se confirme la modificación

            mensajes.mostrarMensajes("Si desea volver a editar información debe volver al menú principal!!!"); // Informa que debe volver al menú para editar más
            menu.RepetirMenu(); // Vuelve al menú principal
        }
        else
        {
            mensajes.mostrarMensajes("Usuario no encontrado..."); // Informa que el usuario no fue encontrado
            menu.RepetirMenu(); // Vuelve al menú principal
        }
    }

    public void Eliminar_Turnos() { // Método para eliminar turnos de un usuario
        mensajes.mostrarMensajes("||||||||||||||||BIENVENID@ A ELIMINAR TURNOS|||||||||||||||"); // Mensaje de bienvenida

        boolean confirmado = false; // Variable para confirmar la eliminación
        do {
            mensajes.mostrarMensajes("¿Está seguro de eliminar un turno? (s/n)"); // Solicita confirmación
            String opcion = sc.nextLine().toLowerCase(); // Lee la opción y la convierte a minúsculas
            switch (opcion) {
                case "s":
                    confirmado = true; // Confirma la eliminación
                    break;
                case "n":
                    mensajes.mostrarMensajes("Operación cancelada"); // Informa que se canceló la operación
                    menu.RepetirMenu(); // Vuelve al menú principal
                    return; // Sale del método
                default:
                    mensajes.mostrarMensajes("Ingrese una opción válida por favor"); // Solicita una opción válida
                    break;
            }
        } while (!confirmado); // Repite hasta que se confirme

        try {
            mensajes.mostrarMensajes("Por favor ingrese el número de documento del Usuario:"); // Solicita el número de documento
            String numero_documento = sc.nextLine(); // Lee el número de documento

            List<Turnos> turnosUsuario = new ArrayList<>(); // Lista para almacenar los turnos del usuario
            for (Turnos t : turnos) { // Recorre la lista de turnos
                if (t.getUsuario().getNumero_documento().equals(numero_documento)) { // Si el turno pertenece al usuario
                    turnosUsuario.add(t); // Agrega el turno a la lista
                }
            }

            if (turnosUsuario.isEmpty()) { // Si no hay turnos para el usuario
                mensajes.mostrarMensajes("El usuario no tiene turnos para eliminar!!!"); // Informa que no hay turnos
                menu.RepetirMenu(); // Vuelve al menú principal
                return; // Sale del método
            }

            boolean continuar = true; // Variable para controlar si se sigue eliminando
            while (continuar && !turnosUsuario.isEmpty()) { // Mientras haya turnos y se quiera continuar
                mensajes.mostrarMensajes("Turnos del usuario:"); // Muestra los turnos del usuario
                for (int i = 0; i < turnosUsuario.size(); i++) { // Recorre la lista de turnos
                    Turnos t = turnosUsuario.get(i); // Obtiene el turno
                    mensajes.mostrarMensajes((i + 1) + ". Fecha: " + t.getFecha() + " | Hora: " + t.getHora()); // Muestra la fecha y hora
                }

                mensajes.mostrarMensajes("Seleccione el número del turno a eliminar:"); // Solicita el número del turno a eliminar
                int opcionTurno = Integer.parseInt(sc.nextLine()); // Lee la opción ingresada

                if (opcionTurno > 0 && opcionTurno <= turnosUsuario.size()) { // Valida si la opción es válida
                    Turnos turnoEliminar = turnosUsuario.get(opcionTurno - 1); // Obtiene el turno a eliminar
                    turnos.remove(turnoEliminar); // Elimina el turno de la lista principal
                    turnosUsuario.remove(turnoEliminar); // Elimina el turno de la lista del usuario

                    mensajes.mostrarMensajes("Turno eliminado correctamente!!!"); // Informa que el turno fue eliminado
                } else {
                    mensajes.mostrarMensajes("Opción inválida."); // Informa que la opción es inválida
                }

                if (!turnosUsuario.isEmpty()) { // Si aún quedan turnos
                    mensajes.mostrarMensajes("¿Desea eliminar otro turno? (s/n)"); // Pregunta si desea eliminar otro turno
                    String otra = sc.nextLine().toLowerCase(); // Lee la opción
                    continuar = otra.equals("s"); // Continúa si la opción es 's'
                } else {
                    mensajes.mostrarMensajes("Ya no quedan turnos para este usuario."); // Informa que no quedan turnos
                    continuar = false; // Termina el ciclo
                }
            }

        } catch (Exception e) {
            mensajes.mostrarMensajes("Ocurrió un error al eliminar el turno: " + e.getMessage()); // Muestra el mensaje de error
            menu.RepetirMenu(); // Vuelve al menú principal
        }
    }

    public static boolean Validar_Turnos(String numero_documento) // Método para validar si un usuario tiene turnos
    {
        for(Turnos t: turnos) // Recorre la lista de turnos
        {
            if(t.getUsuario().getNumero_documento().equals(numero_documento)) // Si el turno pertenece al usuario
            {
                return true; // Retorna verdadero si encuentra un turno
            }
        }
        return false; // Retorna falso si no encuentra turnos
    }
}
