package AreasAyuntamiento;

public class AreasAyuntamiento {

    int idArea;
    String nombreArea;

    public AreasAyuntamiento(int idArea, String nombreArea) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
    }

    public AreasAyuntamiento(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public AreasAyuntamiento() {
    }

    public AreasAyuntamiento(int idArea) {
        this.idArea = idArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    @Override
    public String toString() {
        return nombreArea;
    }
}
