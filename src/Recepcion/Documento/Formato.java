package Recepcion.Documento;

public class Formato {
    int idFormato;
    String nombreFormato;

    public Formato(int idFormato, String nombreFormato) {
        this.idFormato = idFormato;
        this.nombreFormato = nombreFormato;

    }


    public Formato(int idFormato) {
        this.idFormato = idFormato;
    }

    public Formato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    public int getIdFomato() {
        return idFormato;
    }

    public void setIdFormato(int idFormato) {
        this.idFormato = idFormato;
    }

    public String getNombreFormato() {
        return nombreFormato;
    }

    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    @Override
    public String toString() {
        return nombreFormato;
    }
}
