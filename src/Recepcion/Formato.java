package Recepcion;

public class Formato {
    int idFormato;
    String nombreFormato;

    public Formato(int idFormato, String nombreFormato) {
        this.idFormato = idFormato;
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
}
