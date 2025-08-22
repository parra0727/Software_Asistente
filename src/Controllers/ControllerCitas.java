package Controllers;
import Models.Citas;
import Models.Usuarios;
import Utils.MensajesUtil;
import Utils.Menu;
import java.util.Scanner;
import java.util.ArrayList;

public class ControllerCitas
{
    static Menu menu= new Menu();
    static ArrayList<Citas> citas= new ArrayList<>();
    static MensajesUtil mensajes = new MensajesUtil();
    static Scanner sc = new Scanner(System.in);
    public void Agendar_Citas()
    {
        Citas citas= new Citas();
        try
        {
            mensajes.mostrarMensajes("Por favor ingrese el numero de documento");
            String numero_documento= sc.nextLine();
            for(Usuarios u: ControllerUsuarios.usuarios)
            {
                if(ControllerUsuarios.Validar_Usuario(numero_documento))
                {
                    mensajes.mostrarMensajes("Usuario encontrado...");
                    citas.setUsuario(u);
                    mensajes.mostrarMensajes("Por favor sigue los siguientes pasos para agendar una cita!");
                    mensajes.mostrarMensajes("Por favor ingresa la fecha para agendar la cita(dd/MM/yyyy):");
                    citas.setFecha(sc.nextLine());
                    mensajes.mostrarMensajes("Por favor ingresa la hora para agendar la cita(HH:mm):");
                    citas.setHora(sc.nextLine());
                    mensajes.mostrarMensajes("Cita agendada con exito!");
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||");
                    mensajes.mostrarMensajes("Usuario:"+u.getNombre());
                    mensajes.mostrarMensajes("Documento:" + u.getNumero_documento());
                    mensajes.mostrarMensajes("Fecha:" + citas.getFecha());
                    mensajes.mostrarMensajes("Hora:" + citas.getHora());
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||");
                    menu.RepetirMenu();
                }
                else
                {
                    mensajes.mostrarMensajes("Usuario no encotrado...");
                    menu.RepetirMenu();
                }
            }
        }
        catch(Exception e)
        {
            mensajes.mostrarMensajes("Ha ocurrido un error:"+e.getMessage());
            mensajes.mostrarMensajes("Revisa bien la informacion digitada!!!");
            menu.RepetirMenu();
        }
    }
    public void Mostrar_Citas()
    {
        mensajes.mostrarMensajes("Por favor ingrese el numero de documento");
        String numero_documento= sc.nextLine();
        if(ControllerUsuarios.Validar_Usuario(numero_documento))
        {
            mensajes.mostrarMensajes("Usuario encontrado...");
            int contador=0;
            for(Citas c: citas)
            {
                if(Validar_Citas(numero_documento))
                {
                    contador++;
                    mensajes.mostrarMensajes("Se encontraron citas registradas para el usuario...");
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||||");
                    mensajes.mostrarMensajes("Cita #"+contador);
                    mensajes.mostrarMensajes("Nombre:"+c.getUsuario().getNombre());
                    mensajes.mostrarMensajes("Numero de documento:"+c.getUsuario().getNumero_documento());
                    mensajes.mostrarMensajes("Fecha:"+c.getFecha());
                    mensajes.mostrarMensajes("Hora:"+c.getHora());
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||||");
                }
            }
            if(contador==0)
            {
                mensajes.mostrarMensajes("El usuario no tiene citas encontradas");
                menu.RepetirMenu();
            }
            else
            {
                mensajes.mostrarMensajes("Numero de citas encontradas:"+contador);
                menu.RepetirMenu();
            }
        }
        else
        {
            mensajes.mostrarMensajes("Usuario no encontrado...");
            menu.RepetirMenu();
        }
    }
    public void Modificar_Cita()
    {
        mensajes.mostrarMensajes("Por favor ingrese el numero de documento");
        String numero_documento= sc.nextLine();
        if(ControllerUsuarios.Validar_Usuario(numero_documento))
        {
            mensajes.mostrarMensajes("Usuario encontrado...");
            for(Citas c: citas)
            {
                if(Validar_Citas(numero_documento))
                {
                    mensajes.mostrarMensajes("Se encontraron citas registradas para el usuario...");
                    boolean confirmado=false;
                    do
                    {
                        mensajes.mostrarMensajes("¿Que quieres modificar de la cita?");
                        mensajes.mostrarMensajes("1.Fecha");
                        mensajes.mostrarMensajes("2.Hora");
                        mensajes.mostrarMensajes("Digita tu opcion aca:");
                        int opcion=sc.nextInt();
                        switch(opcion)
                        {
                            case 1:
                                mensajes.mostrarMensajes("Por favor ingresa la nueva fecha:");
                                String fecha = sc.nextLine();
                                if(c.getUsuario().getNumero_documento().equals(numero_documento))
                                {
                                    c.setFecha(fecha);
                                    mensajes.mostrarMensajes("La fecha se modifico con exito!!!");
                                    mensajes.mostrarMensajes("si desea volver a editar informacion debe volver al menu pricipal!!!");
                                    confirmado=true;
                                    menu.RepetirMenu();
                                    return;
                                }

                            case 2:
                                mensajes.mostrarMensajes("Por favor ingresa la nueva hora de la cita:");
                                String hora= sc.nextLine();
                                if(c.getUsuario().getNumero_documento().equals(numero_documento))
                                {
                                    c.setHora(hora);
                                    mensajes.mostrarMensajes("La hora fue cambiada exitosamente!!!");
                                    mensajes.mostrarMensajes("si desea volver a editar informacion debe volver al menu pricipal!!!");
                                    menu.RepetirMenu();
                                    confirmado=true;
                                    return;
                                }
                            default:
                                mensajes.mostrarMensajes("Opcion invalida...");
                                break;
                        }
                    }while(!confirmado);
                }
            }
        }
        else
        {
            mensajes.mostrarMensajes("Usuario no encontrado...");
            menu.RepetirMenu();
        }

    }
    public void Eliminar_Cita()
    {
        boolean confirmado=false;
        do
        {
            mensajes.mostrarMensajes("¿Esta seguro de eliminar su cita?(s/n)");
            String opcion = sc.nextLine().toLowerCase();
            switch (opcion)
            {
                case "s":
                    confirmado=true;
                    break;
                case "n":
                    mensajes.mostrarMensajes("Operacion cancelada");
                    menu.RepetirMenu();
                    return;
                default:
                    mensajes.mostrarMensajes("Ingrese una opcion valida por favor");
                    break;
            }
        }while(!confirmado);
        try
        {
            mensajes.mostrarMensajes("Por favor ingresa el numero de documento del Usuario:");
            String numero_documento = sc.nextLine();
            if(Validar_Citas(numero_documento))
            {
                mensajes.mostrarMensajes("Usuario encontrado...");
                mensajes.mostrarMensajes("Eliminando cita...");
                citas.removeIf(c -> c.getUsuario().getNumero_documento().equals(numero_documento));
                mensajes.mostrarMensajes("Cita eliminada correctamente!!!");
            }
            else
            {
                mensajes.mostrarMensajes("El usuario no tiene citas para eliminar!!!");
                menu.RepetirMenu();
            }

        }
        catch (Exception e)
        {
            mensajes.mostrarMensajes("ocurrio un error al eliminar la cita:"+ e.getMessage());
            menu.RepetirMenu();
        }
    }
    public static boolean Validar_Citas(String numero_documento)
    {
        for(Citas c: citas)
        {
            if(c.getUsuario().getNumero_documento().equals(numero_documento))
            {
                return true;
            }
        }
        return false;
    }
}
