package Controllers;
import Utils.Menu;
import Models.Turnos;
import Models.Usuarios;
import Utils.MensajesUtil;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class ControllerTurnos
{
    static Menu menu= new Menu();
    static ArrayList<Turnos> turnos= new ArrayList();
    static MensajesUtil mensajes = new MensajesUtil();
    static Scanner sc = new Scanner(System.in);
    static Random aleatorio = new Random();
    public void Agendar_Turnos()
    {
        Turnos turnos= new Turnos();
        try
        {
            mensajes.mostrarMensajes("Por favor ingrese el numero de documento");
            String numero_documento= sc.nextLine();
            for(Usuarios u: ControllerUsuarios.usuarios)
            {
                if(ControllerUsuarios.Validar_Usuario(numero_documento))
                {
                    mensajes.mostrarMensajes("||||||||||||BIENVENID@ A AGENDAR TURNOS");
                    mensajes.mostrarMensajes("Usuario encontrado...");
                    turnos.setUsuario(u);
                    mensajes.mostrarMensajes("Por favor sigue los siguientes pasos para agendar un turno!");
                    mensajes.mostrarMensajes("Por favor ingresa la fecha para agendar tu turno(dd/MM/yyyy):");
                    turnos.setFecha(sc.nextLine());
                    mensajes.mostrarMensajes("Por favor ingresa la hora para agendar tu turno(HH:mm):");
                    turnos.setHora(sc.nextLine());
                    turnos.setNumero_Turno(aleatorio.nextInt(9000));
                    mensajes.mostrarMensajes("Turno agendado con exito!");
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||");
                    mensajes.mostrarMensajes("Usuario:"+turnos.getUsuario().getNombre());
                    mensajes.mostrarMensajes("Documento:" + turnos.getUsuario().getNumero_documento());
                    mensajes.mostrarMensajes("Fecha:" + turnos.getFecha());
                    mensajes.mostrarMensajes("Hora:" + turnos.getHora());
                    mensajes.mostrarMensajes("turno:"+ turnos.getNumero_Turno());
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
    public void Mostrar_Turnos()
    {
        mensajes.mostrarMensajes("|||||||||||||||BIENVENID@ A MOSTRAR TURNOS");
        mensajes.mostrarMensajes("Por favor ingrese el numero de documento");
        String numero_documento= sc.nextLine();
        if(ControllerUsuarios.Validar_Usuario(numero_documento))
        {
            mensajes.mostrarMensajes("Usuario encontrado...");
            int contador=0;
            for(Turnos t: turnos)
            {
                if(Validar_Turnos(numero_documento))
                {
                    contador++;
                    mensajes.mostrarMensajes("Se encontraron turnos registrados para el usuario...");
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||||");
                    mensajes.mostrarMensajes("Turnos #"+contador);
                    mensajes.mostrarMensajes("Nombre:"+t.getUsuario().getNombre());
                    mensajes.mostrarMensajes("Numero de documento:"+t.getUsuario().getNumero_documento());
                    mensajes.mostrarMensajes("Fecha:"+t.getFecha());
                    mensajes.mostrarMensajes("Hora:"+t.getHora());
                    mensajes.mostrarMensajes("turno:"+t.getNumero_Turno());
                    mensajes.mostrarMensajes("|||||||||||||||||||||||||||||||||||||");
                }
            }
            if(contador==0)
            {
                mensajes.mostrarMensajes("El usuario no tiene turnos encontrados");
                menu.RepetirMenu();
            }
            else
            {
                mensajes.mostrarMensajes("Numero de turnos encontrados:"+contador);
                menu.RepetirMenu();
            }
        }
        else
        {
            mensajes.mostrarMensajes("Usuario no encontrado...");
            menu.RepetirMenu();
        }
    }
    public void Modificar_Turno()
    {
        mensajes.mostrarMensajes("|||||||||||||BIENVENID@ A MODIFICAR TURNOS|||||||||||||");
        mensajes.mostrarMensajes("Por favor ingrese el numero de documento");
        String numero_documento= sc.nextLine();
        if(ControllerUsuarios.Validar_Usuario(numero_documento))
        {
            mensajes.mostrarMensajes("Usuario encontrado...");
            for(Turnos t: turnos)
            {
                if(Validar_Turnos(numero_documento))
                {
                    mensajes.mostrarMensajes("Se encontraron turnos registrados para el usuario...");
                    boolean confirmado=false;
                    do
                    {
                        mensajes.mostrarMensajes("¿Que quieres modificar del turno?");
                        mensajes.mostrarMensajes("1.Fecha");
                        mensajes.mostrarMensajes("2.Hora");
                        mensajes.mostrarMensajes("Digita tu opcion aca:");
                        int opcion=sc.nextInt();
                        switch(opcion)
                        {
                            case 1:
                                mensajes.mostrarMensajes("Por favor ingresa la nueva fecha:");
                                String fecha = sc.nextLine();
                                if(t.getUsuario().getNumero_documento().equals(numero_documento))
                                {
                                    t.setFecha(fecha);
                                    mensajes.mostrarMensajes("La fecha se modifico con exito!!!");
                                    mensajes.mostrarMensajes("si desea volver a editar informacion debe volver al menu pricipal!!!");
                                    confirmado=true;
                                    menu.RepetirMenu();
                                    return;
                                }

                            case 2:
                                mensajes.mostrarMensajes("Por favor ingresa la nueva hora para el turno:");
                                String hora= sc.nextLine();
                                if(t.getUsuario().getNumero_documento().equals(numero_documento))
                                {
                                    t.setHora(hora);
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
    public void Eliminar_Turnos()
    {
        mensajes.mostrarMensajes("||||||||||||||||BIENVENID@ A ELIMINAR TURNOS|||||||||||||||");
        boolean confirmado=false;
        do
        {
            mensajes.mostrarMensajes("¿Esta seguro de eliminar su turno?(s/n)");
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
            if(Validar_Turnos(numero_documento))
            {
                mensajes.mostrarMensajes("Usuario encontrado...");
                mensajes.mostrarMensajes("Eliminando turno...");
                turnos.removeIf(t -> t.getUsuario().getNumero_documento().equals(numero_documento));
                mensajes.mostrarMensajes("Turno eliminado correctamente!!!");
            }
            else
            {
                mensajes.mostrarMensajes("El usuario no tiene turnos para eliminar!!!");
                menu.RepetirMenu();
            }

        }
        catch (Exception e)
        {
            mensajes.mostrarMensajes("ocurrio un error al eliminar el turno:"+ e.getMessage());
            menu.RepetirMenu();
        }
    }
    public static boolean Validar_Turnos(String numero_documento)
    {
        for(Turnos t: turnos)
        {
            if(t.getUsuario().getNumero_documento().equals(numero_documento))
            {
                return true;
            }
        }
        return false;
    }
}
