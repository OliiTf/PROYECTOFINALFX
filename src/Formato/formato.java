package Formato;

public class formato {

    int idFormato;
    String nombreFormato;

    public formato(int idFormato, String nombreFormato) {
        this.idFormato = idFormato;
        this.nombreFormato = nombreFormato;
    }

    public int getIdFormato() {return idFormato; }

    public void setIdFormato(int idFormato) { this.idFormato = idFormato; }

    public String getNombreFormato() {return nombreFormato; }

    public void setNombreFormato(String nombreFormato) {this.nombreFormato = nombreFormato; }
}
