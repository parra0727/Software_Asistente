package Controllers;
import java.util.ArrayList;
import java.util.Scanner;
import Utils.Menu;
import Utils.MensajesUtil;
import Models.Usuarios;
public class ControllerUsuarios
{
    static Menu menu=new Menu(); // Instanciamos la clase menu para poder acceder a repetir menu
    static MensajesUtil mensajes = new MensajesUtil(); // Instanciamos la clase mensajes para una buena practica de desarrollo
    static ArrayList<Usuarios> usuarios= new ArrayList(); // Instanciamos e inicializamos una ArrayList de la clase usuariamos para poder guardar todos los usuarios
    static Scanner sc = new Scanner(System.in);//Instanciamos una clase scanner para leer por consola

    //Procedimiento para agregar usuarios
    public void Agregar_Usuarios()
    {
        mensajes.mostrarMensajes("|||||||||||| BIENVENID@ A AGREGAR USUARIO ||||||||||||");
        mensajes.mostrarMensajes("Ingresa los siguientes datos(por favor digita muy bien la informacion):");
        //Validacion para que el usuario ingrese datos correctos (basica)
        try
        {
            Usuarios u = new Usuarios(); // Instanciamos una nueva clase usuario para guardar en el ArrayList con todos los demas
            mensajes.mostrarMensajes("Ingrese el Nombre del Usuario:");
            u.setNombre(sc.nextLine());//Leemos y guardamos el nombre, usamos nextLine para evitar errores con los espacios en todos los casos!!!
            mensajes.mostrarMensajes("Ingrese el Numero de documento del Usuario:");
            u.setNumero_documento(sc.nextLine());//Leemos y guardamos el documento
            mensajes.mostrarMensajes("Ingrese el Correo del Usuario:");
            u.setEmail(sc.nextLine());// Leemos y guardamos el email
            mensajes.mostrarMensajes("Ingrese el telefono del Usuario:");
            u.setTelefono(sc.nextLine()); //Leemos y guardamos el telefono
            mensajes.mostrarMensajes("Ingrese por favor su fecha de nacimiento(dd/MM/yyyy):");
            u.setFecha_nacimiento(sc.nextLine()); //Leemos y guardarmos la fecha de nacimiento
            mensajes.mostrarMensajes("Ingrese por favor el tipo de documento del Usuario(Ti,Cc,Pasaporte):");
            u.setTipo_documento(sc.nextLine()); //Leemos y guardamos el tipo de documento(no tiene validaciones,ya que se hacia muy extenso todo y no teniamos mucho tiempo)
            usuarios.add(u);
            mensajes.mostrarMensajes("Agregando usuario...");
            menu.RepetirMenu();
        }
        catch (Exception e)
        {
            mensajes.mostrarMensajes("ha ocurrido un error:" + e.getMessage()); //Mostramos el error
            mensajes.mostrarMensajes("Revisa bien la informacion digitada!!!");
            menu.RepetirMenu(); // Preguntamos si quiere volver a repetir el menu
        }
    }
    //Procedimiento para eliminar usuarios
    public void Eliminar_Usuarios()
    {
        boolean confirmado=false; //Creamos una variable para confirmar que el usuario quiere eliminar el perfil
        mensajes.mostrarMensajes("|||||||||| BIENVENID@ A ELIMINAR USUARIO |||||||||||||");
        do //Utilizamos un bucle do-while para poder validar la informacion
        {
            mensajes.mostrarMensajes("¿Esta seguro de eliminar su usuario?(s/n)");
            String opcion = sc.nextLine().toLowerCase();
            switch (opcion)
            {
                case "s": // si el usuario digita "s" salimos del bucle, salimos del condicional y seguimos con la ejecucion
                    confirmado=true;
                    break;
                case "n": // si el usuario digita "n" salimos del procedimiento y preguntamos si quiere repetir el menu
                    mensajes.mostrarMensajes("Operacion cancelada");
                    menu.RepetirMenu();
                    return;
                default:// si el usuario no ingresa una opcion valida se repite el bucle
                    mensajes.mostrarMensajes("Ingrese una opcion valida por favor");
                    break;
            }
        }while(!confirmado);

        try //Usamos un try para poder manejar errores
        {
            mensajes.mostrarMensajes("Por favor ingresa el numero de documento del Usuario:");
            String numero_documento = sc.nextLine();
            if(Validar_Usuario(numero_documento)) // Usamos la funcion Validar_Usuario, si esta retorna true la condicion se cumple
            {
                mensajes.mostrarMensajes("Usuario encontrado...");
                mensajes.mostrarMensajes("Eliminando usuario...");
                usuarios.removeIf(u -> u.getNumero_documento().equals(numero_documento)); //Usamos el metodo removeIf, para eliminar el usuario que correspoda!
                mensajes.mostrarMensajes("Usuario eliminado correctamente!!!");
            }
            else
            {
                mensajes.mostrarMensajes("El usuario no existe!!!");
                menu.RepetirMenu();
            }

        }
        catch (Exception e)
        {
            mensajes.mostrarMensajes("ocurrio un error al eliminar el usuario:"+ e.getMessage());
            menu.RepetirMenu();
        }

    }
    //Procedimiento para modificar usuarios
    public void Modificar_Usuario()
    {
        mensajes.mostrarMensajes("||||||||||||BIENVENID@ A MODIFICAR USUARIO||||||||||||");
        try
        {
            mensajes.mostrarMensajes("Por favor ingresa el numero de documento del Usuario:");
            String numero_documento = sc.nextLine();
            if(Validar_Usuario(numero_documento)) //Validamos que el usuario exista
            {
                mensajes.mostrarMensajes("Usuario encontrado...");
                boolean confirmado=false;
                do// Usamos un do-while para preguntarle al usuario que quiere modificar
                {
                    mensajes.mostrarMensajes("¿Que quieres modificar?");
                    mensajes.mostrarMensajes("1.Email");
                    mensajes.mostrarMensajes("2.Telefono");
                    mensajes.mostrarMensajes("Digita tu opcion aca:");
                    int opcion = sc.nextInt();
                    switch(opcion) //Validamos la opcion del usuario
                    {
                        case 1:
                            mensajes.mostrarMensajes("Por favor ingresa el nuevo email:");
                            String email = sc.nextLine();
                            for (Usuarios u : usuarios)// Recorremos el ArrayList usuario
                            {
                                if(u.getNumero_documento().equals(numero_documento)) //Usamos otro if para modificar el usuario correctamente
                                {
                                    u.setEmail(email);//Reemplazamos por el nuevo email
                                    mensajes.mostrarMensajes("El email fue cambiado exitosamente!!!");
                                    mensajes.mostrarMensajes("si desea volver a editar informacion debe volver al menu pricipal!!!");
                                    confirmado=true;
                                    menu.RepetirMenu();//Aca decidimos hacerlo asi por falta de tiempo, pero deberia preguntar si quiere cambiar otro dato, por facilidad y tiempo retornamos el menu principal
                                    return;
                                }
                            }
                        case 2://Logica repetida pero con telefono
                            mensajes.mostrarMensajes("Por favor ingresa el nuevo telefono:");
                            String telefono = sc.nextLine();
                            for(Usuarios u : usuarios)
                            {
                                if(u.getNumero_documento().equals(numero_documento))
                                    {
                                    u.setTelefono(telefono);
                                    mensajes.mostrarMensajes("El telefono fue cambiado exitosamente!!!");
                                    mensajes.mostrarMensajes("si desea volver a editar informacion debe volver al menu pricipal!!!");
                                    menu.RepetirMenu();
                                    confirmado=true;
                                    return;
                                    }
                            }
                        default:
                            mensajes.mostrarMensajes("Opcion invalida...");
                            break;
                    }

                }while(!confirmado);
            }
            else
            {
                mensajes.mostrarMensajes("Usuario no encontrado...");
                menu.RepetirMenu();
            }
        }
        catch (Exception e)
        {

            mensajes.mostrarMensajes("Ha ocurrido un error:"+e.getMessage());
            menu.RepetirMenu();
        }
    }
    //Procedimiento para validar si un usuario existe en la ArrayList Usuarios o no
    public static boolean Validar_Usuario(String numero_documento)
    {
        for(Usuarios u : usuarios)//Usamos un bucle foreach para iterar todos los elementos de la ArrayList
        {
            if(u.getNumero_documento().equals(numero_documento)) //Validamos si el numero de documento del usuario que digitaron existe en la ArrayList
            {
                return true;
            }
        }
        return false;
    }

}
