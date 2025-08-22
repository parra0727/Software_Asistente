package Models;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Usuarios
{
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String nombre;
    private String numero_documento;
    private String email;
    private String telefono;
    private LocalDate fecha_nacimiento;
    private String Tipo_documento;

    public Usuarios()
    {

    }

    public Usuarios(String nombre,String numero_documento,String email,String telefono,LocalDate fecha_nacimiento,String Tipo_documento,String Descripcion)
    {
        this.nombre=nombre;
        this.numero_documento=numero_documento;
        this.email=email;
        this.telefono=telefono;
        this.fecha_nacimiento=fecha_nacimiento;
        this.Tipo_documento=Tipo_documento;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNumero_documento()
    {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento)
    {
        this.numero_documento = numero_documento;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public LocalDate getFecha_nacimiento()
    {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento)
    {
        this.fecha_nacimiento = LocalDate.parse(fecha_nacimiento,formatter);
    }

    public String getTipo_documento()
    {
        return Tipo_documento;
    }

    public void setTipo_documento(String tipo_documento)
    {
        Tipo_documento = tipo_documento;
    }
}
