package Models;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Citas
{
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private Usuarios usuario;
    private LocalDate fecha;
    private LocalTime hora;

    public Citas()
    {

    }
    public Citas(Usuarios usuario, LocalDate fecha, LocalTime hora)
    {
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Usuarios getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuarios usuario)
    {
        this.usuario = usuario;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha =LocalDate.parse( fecha,formatter);
    }

    public LocalTime getHora()
    {
        return hora;
    }

    public void setHora(String hora)
    {
        this.hora = LocalTime.parse(hora, timeFormatter);
    }
}
