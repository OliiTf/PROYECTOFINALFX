package Login;

public class Configuracion {

    String nombreJefe, direccion,telefono, horarioInicio, horarioSalida;
    int idMunicipio;
    String nombreEstado;

    public Configuracion(String nombreJefe, String direccion, String telefono, String horarioInicio, String horarioSalida, int idMunicipio,String nombreEstado) {
        this.nombreJefe = nombreJefe;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horarioInicio = horarioInicio;
        this.horarioSalida = horarioSalida;
        this.idMunicipio = idMunicipio;
        this.nombreEstado = nombreEstado;
    }

    public Configuracion(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Configuracion() {
    }

    public Configuracion(String nombreJefe, String direccion, String telefono, String horarioInicio, String horarioSalida, String nombreEstado) {
        this.nombreJefe = nombreJefe;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horarioInicio = horarioInicio;
        this.horarioSalida = horarioSalida;
        this.nombreEstado = nombreEstado;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public String toString() {
        String conf = "Nombre Jefe: " + nombreJefe + "\n\nTel: " + telefono +
                "\n\nDireccion: "+direccion+"\n\nHorario Inicio: " +horarioInicio +"\n\nHorario Salida: " +horarioSalida +
                "\n\nEstado: " + nombreEstado;
        return  conf;
    }


}
