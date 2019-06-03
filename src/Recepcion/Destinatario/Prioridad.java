package Recepcion.Destinatario;

public class Prioridad {
   int idPrioridad;
   String descPrioridad;

    public Prioridad(int idPrioridad, String descPrioridad) {
        this.idPrioridad = idPrioridad;
        this.descPrioridad = descPrioridad;
    }

    public Prioridad() {
    }

    public Prioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public Prioridad(String descPrioridad) {
        this.descPrioridad = descPrioridad;
    }

    public int getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getDescPrioridad() {
        return descPrioridad;
    }

    public void setDescPrioridad(String descPrioridad) {
        this.descPrioridad = descPrioridad;
    }
    @Override
    public String toString() {
        return descPrioridad;
    }
}
