package Login;

public class Municipio {

    int idMunicipio;
    String nombreMunicipio;
    int idEstado;

    public Municipio(int idMunicipio, String nombreMunicipio, int idEstado) {
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
        this.idEstado = idEstado;
    }

    public Municipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }


    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return nombreMunicipio;
    }



}
