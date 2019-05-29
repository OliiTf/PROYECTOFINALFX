package Recepcion.Destinatario;

public class Instruccion {
    int idInstruccion;
    String descInstruccion;

    public Instruccion(int idInstruccion, String descInstruccion) {
        this.idInstruccion = idInstruccion;
        this.descInstruccion = descInstruccion;
    }

    public Instruccion() {
    }

    public Instruccion(int idInstruccion) {
        this.idInstruccion = idInstruccion;
    }

    public Instruccion(String descInstruccion) {
        this.descInstruccion = descInstruccion;
    }

    public int getIdInstruccion() {
        return idInstruccion;
    }

    public void setIdInstruccion(int idInstruccion) {
        this.idInstruccion = idInstruccion;
    }

    public String getDescInstruccion() {
        return descInstruccion;
    }

    public void setDescInstruccion(String descInstruccion) {
        this.descInstruccion = descInstruccion;
    }
    @Override
    public String toString() {
        return descInstruccion;
    }
}
