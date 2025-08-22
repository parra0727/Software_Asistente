package Controllers; // Paquete Controllers
import Models.Citas; // Importa la clase Citas
import Models.Usuarios; // Importa la clase Usuarios
import Utils.MensajesUtil; // Importa la clase MensajesUtil
import Utils.Menu; // Importa la clase Menu
import java.util.ArrayList; // Importa la clase Scanner para leer datos por consola
import java.util.List; // Importa ArrayList para manejar listas dinámicas
import java.util.Scanner; // Importa la interfaz List

public class ControllerCitas // Define la clase ControllerCitas
{
    static Menu menu= new Menu(); // Instancia el menú principal
    static ArrayList<Citas> citas= new ArrayList<>(); // Lista para almacenar todas las citas
    static MensajesUtil mensajes = new MensajesUtil(); // Instancia para mostrar mensajes al usuario
    static Scanner sc = new Scanner(System.in); // Scanner para leer la entrada del usuario

    public void Agendar_Citas() // Método para agendar una nueva cita
    {
        Citas cita= new Citas(); // Crea una nueva instancia de cita
        try // Manejo de excepciones
        {
            mensajes.mostrarMensajes("Por favor ingrese el numero de documento"); // Solicita el número de documento
            String numero_documento= sc.nextLine(); // Lee el número de documento ingresado

            for(Usuarios u: ControllerUsuarios.usuarios) // Recorre la lista de usuarios
            {
                if(ControllerUsuarios.Validar_Usuario(numero_documento)) // Valida si el usuario existe
                {
                    mensajes.mostrarMensajes("Usuario encontrado..."); // Informa que el usuario fue encontrado
                    cita.setUsuario(u); // Asocia el usuario a la cita
                    mensajes.mostrarMensajes("Por favor sigue los siguientes pasos para agendar una cita!"); // Mensaje de guía
                    mensajes.mostrarMensajes("Por favor ingresa la fecha para agendar la cita(dd/MM/yyyy):"); // Solicita la fecha
                    cita.setFecha(sc.nextLine()); // Guarda la fecha ingresada
                    mensajes.mostrarMensajes("Por favor ingresa la hora para agendar la cita(HH:mm):"); // Solicita la hora
                    cita.setHora(sc.nextLine()); // Guarda la hora ingresada
                    mensajes.mostrarMensajes("Cita agendada con exito!"); // Confirma que la cita fue agendada
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||"); // Separador visual
                    mensajes.mostrarMensajes("Usuario:"+u.getNombre()); // Muestra el nombre del usuario
                    mensajes.mostrarMensajes("Documento:" + u.getNumero_documento()); // Muestra el documento
                    mensajes.mostrarMensajes("Fecha:" + cita.getFecha()); // Muestra la fecha de la cita
                    mensajes.mostrarMensajes("Hora:" + cita.getHora()); // Muestra la hora de la cita
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||"); // Separador visual
                    citas.add(cita); // Agrega la cita a la lista de citas
                    menu.RepetirMenu(); // Vuelve al menú principal
                }
                else // Si el usuario no existe
                {
                    mensajes.mostrarMensajes("Usuario no encotrado..."); // Informa que el usuario no fue encontrado
                    menu.RepetirMenu(); // Vuelve al menú principal
                }
            }
        }
        catch(Exception e) // Captura cualquier excepción
        {
            mensajes.mostrarMensajes("Ha ocurrido un error:"+e.getMessage()); // Muestra el mensaje de error
            mensajes.mostrarMensajes("Revisa bien la informacion digitada!!!"); // Recomienda revisar la información
            menu.RepetirMenu(); // Vuelve al menú principal
        }
    }

    public void Mostrar_Citas() // Método para mostrar las citas de un usuario
    {
        mensajes.mostrarMensajes("Por favor ingrese el numero de documento"); // Solicita el número de documento
        String numero_documento= sc.nextLine(); // Lee el número de documento

        if(ControllerUsuarios.Validar_Usuario(numero_documento)) // Valida si el usuario existe
        {
            mensajes.mostrarMensajes("Usuario encontrado..."); // Informa que el usuario fue encontrado
            int contador=0; // Inicializa el contador de citas

            for(Citas c: citas) // Recorre la lista de citas
            {
                if(c.getUsuario().getNumero_documento().equals(numero_documento)) // Verifica si la cita pertenece al usuario
                {
                    contador++; // Incrementa el contador
                    mensajes.mostrarMensajes("Se encontraron citas registradas para el usuario..."); // Mensaje informativo
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||||"); // Separador visual
                    mensajes.mostrarMensajes("Cita #"+contador); // Muestra el número de la cita
                    mensajes.mostrarMensajes("Nombre:"+c.getUsuario().getNombre()); // Muestra el nombre del usuario
                    mensajes.mostrarMensajes("Numero de documento:"+c.getUsuario().getNumero_documento()); // Muestra el documento
                    mensajes.mostrarMensajes("Fecha:"+c.getFecha()); // Muestra la fecha de la cita
                    mensajes.mostrarMensajes("Hora:"+c.getHora()); // Muestra la hora de la cita
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||||"); // Separador visual
                }
            }
            if(contador==0) // Si no se encontraron citas
            {
                mensajes.mostrarMensajes("El usuario no tiene citas encontradas"); // Informa que no hay citas
                menu.RepetirMenu(); // Vuelve al menú principal
            }
            else // Si se encontraron citas
            {
                mensajes.mostrarMensajes("Numero de citas encontradas:"+contador); // Muestra el número de citas encontradas
                menu.RepetirMenu(); // Vuelve al menú principal
            }
        }
        else // Si el usuario no existe
        {
            mensajes.mostrarMensajes("Usuario no encontrado..."); // Informa que el usuario no fue encontrado
            menu.RepetirMenu(); // Vuelve al menú principal
        }
    }

    public void Modificar_Cita() { // Método para modificar una cita existente
        mensajes.mostrarMensajes("||||||||||||||||BIENVENID@ A MODIFICAR CITAS|||||||||||||||"); // Mensaje de bienvenida
        mensajes.mostrarMensajes("Por favor ingrese el numero de documento"); // Solicita el número de documento
        String numero_documento = sc.nextLine(); // Lee el número de documento

        if (ControllerUsuarios.Validar_Usuario(numero_documento)) { // Valida si el usuario existe
            mensajes.mostrarMensajes("Usuario encontrado..."); // Informa que el usuario fue encontrado

            List<Citas> citasUsuario = new ArrayList<>(); // Lista para almacenar las citas del usuario
            for (Citas c : citas) { // Recorre la lista de citas
                if (c.getUsuario().getNumero_documento().equals(numero_documento)) { // Verifica si la cita pertenece al usuario
                    citasUsuario.add(c); // Agrega la cita a la lista del usuario
                }
            }

            if (citasUsuario.isEmpty()) { // Si no hay citas para el usuario
                mensajes.mostrarMensajes("El usuario no tiene citas registradas!!!"); // Informa que no hay citas
                menu.RepetirMenu(); // Vuelve al menú principal
                return; // Sale del método
            }

            mensajes.mostrarMensajes("Se encontraron citas registradas para el usuario..."); // Informa que hay citas
            for (int i = 0; i < citasUsuario.size(); i++) { // Muestra las citas encontradas
                Citas c = citasUsuario.get(i); // Obtiene la cita
                mensajes.mostrarMensajes((i + 1) + ". Fecha: " + c.getFecha() + " | Hora: " + c.getHora()); // Muestra la fecha y hora
            }

            mensajes.mostrarMensajes("Por favor ingrese el número de la cita que desea modificar:"); // Solicita el número de cita
            int seleccion = sc.nextInt(); // Lee la selección del usuario
            sc.nextLine(); // Limpia el buffer

            if (seleccion < 1 || seleccion > citasUsuario.size()) { // Verifica si la selección es válida
                mensajes.mostrarMensajes("Selección inválida..."); // Informa que la selección es inválida
                menu.RepetirMenu(); // Vuelve al menú principal
                return; // Sale del método
            }

            Citas citaSeleccionada = citasUsuario.get(seleccion - 1); // Obtiene la cita seleccionada

            boolean confirmado = false; // Variable para controlar la confirmación
            do {
                mensajes.mostrarMensajes("¿Qué quieres modificar de la cita?"); // Pregunta qué desea modificar
                mensajes.mostrarMensajes("1. Fecha"); // Opción para modificar la fecha
                mensajes.mostrarMensajes("2. Hora"); // Opción para modificar la hora
                mensajes.mostrarMensajes("Digita tu opción acá:"); // Solicita la opción
                int opcion = sc.nextInt(); // Lee la opción
                sc.nextLine(); // Limpia el buffer

                switch (opcion) { // Evalúa la opción seleccionada
                    case 1:
                        mensajes.mostrarMensajes("Por favor ingresa la nueva fecha:"); // Solicita la nueva fecha
                        String nuevaFecha = sc.nextLine(); // Lee la nueva fecha
                        citaSeleccionada.setFecha(nuevaFecha); // Actualiza la fecha de la cita
                        mensajes.mostrarMensajes("La fecha se modificó con éxito!!!"); // Confirma la modificación
                        confirmado = true; // Marca como confirmado
                        break;

                    case 2:
                        mensajes.mostrarMensajes("Por favor ingresa la nueva hora:"); // Solicita la nueva hora
                        String nuevaHora = sc.nextLine(); // Lee la nueva hora
                        citaSeleccionada.setHora(nuevaHora); // Actualiza la hora de la cita
                        mensajes.mostrarMensajes("La hora se modificó con éxito!!!"); // Confirma la modificación
                        confirmado = true; // Marca como confirmado
                        break;

                    default:
                        mensajes.mostrarMensajes("Opción inválida..."); // Informa que la opción es inválida
                        break;
                }
            } while (!confirmado); // Repite hasta que se confirme la modificación

            mensajes.mostrarMensajes("Si desea volver a editar información debe regresar al menú principal."); // Mensaje informativo
            menu.RepetirMenu(); // Vuelve al menú principal

        } else { // Si el usuario no existe
            mensajes.mostrarMensajes("Usuario no encontrado..."); // Informa que el usuario no fue encontrado
            menu.RepetirMenu(); // Vuelve al menú principal
        }
    }

    public void Eliminar_Cita() { // Método para eliminar una cita
        mensajes.mostrarMensajes("||||||||||||||||BIENVENID@ A ELIMINAR CITAS|||||||||||||||"); // Mensaje de bienvenida
        boolean confirmado = false; // Variable para confirmar la eliminación
        do {
            mensajes.mostrarMensajes("¿Está seguro de eliminar una cita? (s/n)"); // Solicita confirmación
            String opcion = sc.nextLine().toLowerCase(); // Lee la opción y la convierte a minúscula
            switch (opcion) { // Evalúa la opción
                case "s": confirmado = true; break; // Si es sí, confirma
                case "n":
                    mensajes.mostrarMensajes("Operación cancelada"); // Si es no, cancela la operación
                    menu.RepetirMenu(); // Vuelve al menú principal
                    return; // Sale del método
                default:
                    mensajes.mostrarMensajes("Ingrese una opción válida por favor"); // Si es inválida, solicita nuevamente
            }
        } while (!confirmado); // Repite hasta que se confirme

        try { // Manejo de excepciones
            mensajes.mostrarMensajes("Por favor ingrese el número de documento del Usuario:"); // Solicita el número de documento
            String numero_documento = sc.nextLine(); // Lee el número de documento

            List<Citas> citasUsuario = new ArrayList<>(); // Lista para almacenar las citas del usuario
            for (Citas c : citas) { // Recorre la lista de citas
                if (c.getUsuario().getNumero_documento().equals(numero_documento)) { // Verifica si la cita pertenece al usuario
                    citasUsuario.add(c); // Agrega la cita a la lista del usuario
                }
            }

            if (citasUsuario.isEmpty()) { // Si no hay citas para el usuario
                mensajes.mostrarMensajes("El usuario no tiene citas para eliminar."); // Informa que no hay citas
                menu.RepetirMenu(); // Vuelve al menú principal
                return; // Sale del método
            }

            boolean continuar = true; // Variable para controlar si se desea eliminar más citas
            while (continuar && !citasUsuario.isEmpty()) { // Mientras haya citas y se desee continuar

                mensajes.mostrarMensajes("Citas del usuario:"); // Muestra las citas del usuario
                for (int i = 0; i < citasUsuario.size(); i++) { // Recorre las citas
                    Citas c = citasUsuario.get(i); // Obtiene la cita
                    mensajes.mostrarMensajes((i + 1) + ". Fecha: " + c.getFecha() + " | Hora: " + c.getHora()); // Muestra la fecha y hora
                }

                mensajes.mostrarMensajes("Seleccione el número de la cita a eliminar:"); // Solicita la cita a eliminar
                int opcionIndice;
                try {
                    opcionIndice = Integer.parseInt(sc.nextLine()); // Lee el número de cita a eliminar
                } catch (NumberFormatException e) { // Si la entrada no es un número
                    mensajes.mostrarMensajes("Entrada inválida. Debe ser un número."); // Informa que la entrada es inválida
                    continue; // Vuelve a solicitar la entrada
                }

                if (opcionIndice < 1 || opcionIndice > citasUsuario.size()) { // Verifica si la opción está en rango
                    mensajes.mostrarMensajes("Opción fuera de rango."); // Informa que la opción está fuera de rango
                    continue; // Vuelve a solicitar la entrada
                }

                // Eliminar solo la cita seleccionada
                Citas citaEliminar = citasUsuario.remove(opcionIndice - 1); // Elimina la cita de la lista del usuario
                citas.remove(citaEliminar); // Elimina la cita de la lista general
                mensajes.mostrarMensajes("Cita eliminada correctamente."); // Confirma la eliminación

                if (!citasUsuario.isEmpty()) { // Si quedan más citas
                    mensajes.mostrarMensajes("¿Desea eliminar otra cita? (s/n)"); // Pregunta si desea eliminar otra cita
                    continuar = sc.nextLine().trim().toLowerCase().equals("s"); // Actualiza la variable continuar
                } else { // Si no quedan más citas
                    mensajes.mostrarMensajes("Ya no quedan más citas para este usuario."); // Informa que no hay más citas
                    continuar = false; // Termina el ciclo
                }
            }

            menu.RepetirMenu(); // Vuelve al menú principal

        } catch (Exception e) { // Captura cualquier excepción
            mensajes.mostrarMensajes("Ocurrió un error al eliminar la cita: " + e.getMessage()); // Muestra el mensaje de error
            menu.RepetirMenu(); // Vuelve al menú principal
        }
    }

    public static boolean Validar_Citas(String numero_documento) // Método para validar si un usuario tiene citas
    {
        for(Citas c: citas) // Recorre la lista de citas
        {
            if(c.getUsuario().getNumero_documento().equals(numero_documento)) // Verifica si la cita pertenece al usuario
            {
                return true; // Retorna verdadero si encuentra una cita
            }
        }
        return false; // Retorna falso si no encuentra ninguna cita
    }
}
