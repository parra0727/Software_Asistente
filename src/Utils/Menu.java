package Utils; // Define el paquete donde se encuentra la clase

import Controllers.ControllerCitas; // Importa la clase ControllerUsuarios
import Controllers.ControllerTurnos; // Importa la clase ControllerCitas
import Controllers.ControllerUsuarios; // Importa la clase ControllerTurnos
import java.util.Scanner; // Importa la clase Scanner para leer datos por consola

public class Menu // Define la clase Menu
{
    static MensajesUtil mensajes=new MensajesUtil(); // Crea una instancia de MensajesUtil para mostrar mensajes
    static Scanner sc = new Scanner(System.in); // Crea un objeto Scanner para leer la entrada del usuario
    static ControllerUsuarios controllerUsuarios= new ControllerUsuarios(); // Instancia el controlador de usuarios
    static ControllerCitas controllerCitas= new ControllerCitas(); // Instancia el controlador de citas
    static ControllerTurnos controllerTurnos= new ControllerTurnos(); // Instancia el controlador de turnos

    public void Iniciar() // Método para iniciar el menú principal
    {
        mensajes.mostrarMensajes("||||||BIENVENID@ SOY TU ASISTENTE VIRTUAL||||||||||||"); // Muestra mensaje de bienvenida
        mensajes.mostrarMensajes("Estoy preparado para ayudarte con todo lo que necesites!!!"); // Muestra mensaje de ayuda
        mensajes.mostrarMensajes("A continuacion te mostrare todo lo que puedo hacer..."); // Muestra mensaje de opciones
        Menu(); // Llama al método Menu para mostrar las opciones
    }

    public void Menu() // Método que muestra el menú y gestiona las opciones
    {
        int opcion=0; // Variable para almacenar la opción seleccionada por el usuario
        do {
            mensajes.mostrarMensajes("1. Agregar usuario"); // Opción para agregar usuario
            mensajes.mostrarMensajes("2. Eliminar Usuario"); // Opción para eliminar usuario
            mensajes.mostrarMensajes("3. Modidificar Usuario"); // Opción para modificar usuario
            mensajes.mostrarMensajes("4. Agendar Citas"); // Opción para agendar citas
            mensajes.mostrarMensajes("5. Mostrar Citas"); // Opción para mostrar citas
            mensajes.mostrarMensajes("6. Modificar Citas"); // Opción para modificar citas
            mensajes.mostrarMensajes("7. Eliminar Citas"); // Opción para eliminar citas
            mensajes.mostrarMensajes("8. Agendar Turnos"); // Opción para agendar turnos
            mensajes.mostrarMensajes("9. Mostrar Turnos"); // Opción para mostrar turnos
            mensajes.mostrarMensajes("10. Modificar Turnos"); // Opción para modificar turnos
            mensajes.mostrarMensajes("11. Eliminar Turnos"); // Opción para eliminar turnos
            mensajes.mostrarMensajes("0. Salir"); // Opción para salir

            mensajes.mostrarMensajes("Por favor ingresa tu opcion aca:"); // Solicita al usuario que ingrese una opción
            opcion = sc.nextInt(); // Lee la opción ingresada por el usuario

            switch (opcion) { // Evalúa la opción seleccionada
                case 1:
                    controllerUsuarios.Agregar_Usuarios(); // Llama al método para agregar usuarios
                    return; // Sale del método después de ejecutar la opción
                case 2:
                    controllerUsuarios.Eliminar_Usuarios(); // Llama al método para eliminar usuarios
                    return;
                case 3:
                    controllerUsuarios.Modificar_Usuario(); // Llama al método para modificar usuarios
                    return;
                case 4:
                    controllerCitas.Agendar_Citas(); // Llama al método para agendar citas
                    return;
                case 5:
                    controllerCitas.Mostrar_Citas(); // Llama al método para mostrar citas
                    return;
                case 6:
                    controllerCitas.Modificar_Cita(); // Llama al método para modificar citas
                    return;
                case 7:
                    controllerCitas.Eliminar_Cita(); // Llama al método para eliminar citas
                    return;
                case 8:
                    controllerTurnos.Agendar_Turnos(); // Llama al método para agendar turnos
                    return;
                case 9:
                    controllerTurnos.Mostrar_Turnos(); // Llama al método para mostrar turnos
                    return;
                case 10:
                    controllerTurnos.Modificar_Turno(); // Llama al método para modificar turnos
                    return;
                case 11:
                    controllerTurnos.Eliminar_Turnos(); // Llama al método para eliminar turnos
                    return;
                case 0:
                    return; // Sale del menú si el usuario selecciona 0
                default:
                    Menu(); // Si la opción no es válida, vuelve a mostrar el menú
            }
        }while(opcion!=0); // Repite el menú mientras la opción no sea 0

    }

    public void RepetirMenu() // Método para preguntar si se desea repetir el menú principal
    {
        String opcion=""; // Variable para almacenar la respuesta del usuario
        do
        {
            mensajes.mostrarMensajes("¿Desea volver a repetir el menu principal?(s/n)"); // Pregunta si desea repetir el menú
            mensajes.mostrarMensajes("Ingrese su opcion aca:"); // Solicita la respuesta
            opcion = sc.next().toLowerCase(); // Lee la respuesta y la convierte a minúsculas
            switch (opcion)
            {
                case "s":
                    Menu(); // Si la respuesta es 's', muestra el menú principal nuevamente
                    break;
                case "n":
                    mensajes.mostrarMensajes("Saliendo del programa..."); // Si la respuesta es 'n', muestra mensaje de salida
                    return; // Sale del método
                default:
                    mensajes.mostrarMensajes("Se ha generado un error..."); // Si la respuesta no es válida, muestra mensaje de error
                    break;
            }
        }while(true); // Repite hasta que el usuario decida salir
    }
}
