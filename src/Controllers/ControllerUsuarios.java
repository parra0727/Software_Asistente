package Controllers; // Paquete donde se encuentra la clase

import Models.Usuarios; // Importa la clase ArrayList para listas dinámicas
import Utils.MensajesUtil; // Importa la clase Scanner para leer datos por consola
import Utils.Menu; // Importa la clase Menu desde el paquete Utils
import java.util.ArrayList; // Importa la clase MensajesUtil desde el paquete Utils
import java.util.Scanner; // Importa la clase Usuarios desde el paquete Models

public class ControllerUsuarios // Define la clase ControllerUsuarios
{
    static Menu menu=new Menu(); // Instancia la clase Menu para acceder a sus métodos
    static MensajesUtil mensajes = new MensajesUtil(); // Instancia la clase MensajesUtil para mostrar mensajes
    static ArrayList<Usuarios> usuarios= new ArrayList(); // Crea una lista para almacenar usuarios
    static Scanner sc = new Scanner(System.in);// Instancia Scanner para leer datos desde la consola

    // Procedimiento para agregar usuarios
    public void Agregar_Usuarios()
    {
        mensajes.mostrarMensajes("|||||||||||| BIENVENID@ A AGREGAR USUARIO ||||||||||||"); // Muestra mensaje de bienvenida
        mensajes.mostrarMensajes("Ingresa los siguientes datos(por favor digita muy bien la informacion):"); // Solicita datos al usuario
        // Validación para que el usuario ingrese datos correctos (básica)
        try
        {
            Usuarios u = new Usuarios(); // Crea un nuevo usuario
            mensajes.mostrarMensajes("Ingrese el Nombre del Usuario:"); // Solicita el nombre
            u.setNombre(sc.nextLine());// Lee y guarda el nombre
            mensajes.mostrarMensajes("Ingrese el Numero de documento del Usuario:"); // Solicita el número de documento
            u.setNumero_documento(sc.nextLine());// Lee y guarda el número de documento
            mensajes.mostrarMensajes("Ingrese el Correo del Usuario:"); // Solicita el correo
            u.setEmail(sc.nextLine());// Lee y guarda el correo
            mensajes.mostrarMensajes("Ingrese el telefono del Usuario:"); // Solicita el teléfono
            u.setTelefono(sc.nextLine()); // Lee y guarda el teléfono
            mensajes.mostrarMensajes("Ingrese por favor su fecha de nacimiento(dd/MM/yyyy):"); // Solicita la fecha de nacimiento
            u.setFecha_nacimiento(sc.nextLine()); // Lee y guarda la fecha de nacimiento
            mensajes.mostrarMensajes("Ingrese por favor el tipo de documento del Usuario(Ti,Cc,Pasaporte):"); // Solicita el tipo de documento
            u.setTipo_documento(sc.nextLine()); // Lee y guarda el tipo de documento
            usuarios.add(u); // Agrega el usuario a la lista
            mensajes.mostrarMensajes("Agregando usuario..."); // Muestra mensaje de proceso
            menu.RepetirMenu(); // Llama al menú para repetir la acción
        }
        catch (Exception e)
        {
            mensajes.mostrarMensajes("ha ocurrido un error:" + e.getMessage()); // Muestra el error ocurrido
            mensajes.mostrarMensajes("Revisa bien la informacion digitada!!!"); // Solicita revisar la información
            menu.RepetirMenu(); // Llama al menú para repetir la acción
        }
    }

    // Procedimiento para eliminar usuarios
    public void Eliminar_Usuarios()
    {
        boolean confirmado=false; // Variable para confirmar la eliminación
        mensajes.mostrarMensajes("|||||||||| BIENVENID@ A ELIMINAR USUARIO |||||||||||||"); // Mensaje de bienvenida
        do // Bucle para validar la confirmación
        {
            mensajes.mostrarMensajes("¿Esta seguro de eliminar su usuario?(s/n)"); // Solicita confirmación
            String opcion = sc.nextLine().toLowerCase(); // Lee la opción y la convierte a minúsculas
            switch (opcion)
            {
                case "s": // Si el usuario confirma
                    confirmado=true; // Cambia la variable a verdadero
                    break;
                case "n": // Si el usuario cancela
                    mensajes.mostrarMensajes("Operacion cancelada"); // Muestra mensaje de cancelación
                    menu.RepetirMenu(); // Llama al menú
                    return; // Sale del método
                default:// Si la opción no es válida
                    mensajes.mostrarMensajes("Ingrese una opcion valida por favor"); // Solicita opción válida
                    break;
            }
        }while(!confirmado); // Repite hasta que se confirme

        try // Manejo de errores
        {
            mensajes.mostrarMensajes("Por favor ingresa el numero de documento del Usuario:"); // Solicita el número de documento
            String numero_documento = sc.nextLine(); // Lee el número de documento
            if(Validar_Usuario(numero_documento)) // Verifica si el usuario existe
            {
                mensajes.mostrarMensajes("Usuario encontrado..."); // Muestra mensaje de usuario encontrado
                mensajes.mostrarMensajes("Eliminando usuario..."); // Muestra mensaje de proceso
                usuarios.removeIf(u -> u.getNumero_documento().equals(numero_documento)); // Elimina el usuario de la lista
                mensajes.mostrarMensajes("Usuario eliminado correctamente!!!"); // Muestra mensaje de éxito
            }
            else
            {
                mensajes.mostrarMensajes("El usuario no existe!!!"); // Muestra mensaje de usuario no encontrado
                menu.RepetirMenu(); // Llama al menú
            }

        }
        catch (Exception e)
        {
            mensajes.mostrarMensajes("ocurrio un error al eliminar el usuario:"+ e.getMessage()); // Muestra el error
            menu.RepetirMenu(); // Llama al menú
        }

    }

    // Procedimiento para modificar usuarios
    public void Modificar_Usuario()
    {
        mensajes.mostrarMensajes("||||||||||||BIENVENID@ A MODIFICAR USUARIO||||||||||||"); // Mensaje de bienvenida
        try
        {
            mensajes.mostrarMensajes("Por favor ingresa el numero de documento del Usuario:"); // Solicita el número de documento
            String numero_documento = sc.nextLine(); // Lee el número de documento
            if(Validar_Usuario(numero_documento)) // Verifica si el usuario existe
            {
                mensajes.mostrarMensajes("Usuario encontrado..."); // Muestra mensaje de usuario encontrado
                boolean confirmado=false; // Variable para confirmar modificación
                do// Bucle para modificar datos
                {
                    mensajes.mostrarMensajes("¿Que quieres modificar?"); // Solicita qué desea modificar
                    mensajes.mostrarMensajes("1.Email"); // Opción para modificar email
                    mensajes.mostrarMensajes("2.Telefono"); // Opción para modificar teléfono
                    mensajes.mostrarMensajes("Digita tu opcion aca:"); // Solicita opción
                    int opcion = sc.nextInt(); // Lee la opción
                    sc.nextLine(); // Limpia el buffer
                    switch(opcion) // Verifica la opción
                    {
                        case 1:
                            mensajes.mostrarMensajes("Por favor ingresa el nuevo email:"); // Solicita nuevo email
                            String email = sc.nextLine(); // Lee el nuevo email
                            for (Usuarios u : usuarios)// Recorre la lista de usuarios
                            {
                                if(u.getNumero_documento().equals(numero_documento)) // Busca el usuario por documento
                                {
                                    u.setEmail(email);// Modifica el email
                                    mensajes.mostrarMensajes("El email fue cambiado exitosamente!!!"); // Mensaje de éxito
                                    mensajes.mostrarMensajes("si desea volver a editar informacion debe volver al menu pricipal!!!"); // Mensaje informativo
                                    confirmado=true; // Confirma la modificación
                                    menu.RepetirMenu();// Llama al menú
                                    return; // Sale del método
                                }
                            }
                        case 2:// Lógica para modificar teléfono
                            mensajes.mostrarMensajes("Por favor ingresa el nuevo telefono:"); // Solicita nuevo teléfono
                            String telefono = sc.nextLine(); // Lee el nuevo teléfono
                            for(Usuarios u : usuarios) // Recorre la lista de usuarios
                            {
                                if(u.getNumero_documento().equals(numero_documento)) // Busca el usuario por documento
                                    {
                                    u.setTelefono(telefono); // Modifica el teléfono
                                    mensajes.mostrarMensajes("El telefono fue cambiado exitosamente!!!"); // Mensaje de éxito
                                    mensajes.mostrarMensajes("si desea volver a editar informacion debe volver al menu pricipal!!!"); // Mensaje informativo
                                    menu.RepetirMenu(); // Llama al menú
                                    confirmado=true; // Confirma la modificación
                                    return; // Sale del método
                                    }
                            }
                        default:
                            mensajes.mostrarMensajes("Opcion invalida..."); // Mensaje de opción inválida
                            break;
                    }

                }while(!confirmado); // Repite hasta que se confirme
            }
            else
            {
                mensajes.mostrarMensajes("Usuario no encontrado..."); // Mensaje de usuario no encontrado
                menu.RepetirMenu(); // Llama al menú
            }
        }
        catch (Exception e)
        {

            mensajes.mostrarMensajes("Ha ocurrido un error:"+e.getMessage()); // Muestra el error
            menu.RepetirMenu(); // Llama al menú
        }
    }

    // Procedimiento para validar si un usuario existe en la lista de usuarios
    public static boolean Validar_Usuario(String numero_documento)
    {
        for(Usuarios u : usuarios)// Recorre la lista de usuarios
        {
            if(u.getNumero_documento().equals(numero_documento)) // Verifica si el documento coincide
            {
                return true; // Retorna verdadero si existe
            }
        }
        return false; // Retorna falso si no existe
    }

}
