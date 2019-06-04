package Login;

public class Usuarios {

    int idUsuario;
    String nombre, contraseña;
    int idRol, idMunicipio;
    String tipoRol, nombreMunicupio;

    public Usuarios(int idUsuario, String nombre, String contraseña, int idRol, int idMunicipio) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.idRol = idRol;
        this.idMunicipio = idMunicipio;
    }

    public Usuarios(int idUsuario, String nombre, String tipoRol, String nombreMunicupio) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.tipoRol = tipoRol;
        this.nombreMunicupio = nombreMunicupio;
    }


    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public String getNombreMunicupio() {
        return nombreMunicupio;
    }

    public void setNombreMunicupio(String nombreMunicupio) {
        this.nombreMunicupio = nombreMunicupio;
    }

    public Usuarios(int idRol) {
        this.idRol = idRol;
    }



    public Usuarios() {
    }



    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public String toString() {
        return Integer.toString(idRol);
    }
}
