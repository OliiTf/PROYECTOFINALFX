package sample;

public class Institucion {
    int idInstitucion;
    String nombreInstitucion;

    public Institucion(int idInstitucion, String nombreInstitucion) {
        this.idInstitucion = idInstitucion;
        this.nombreInstitucion = nombreInstitucion;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }
}
