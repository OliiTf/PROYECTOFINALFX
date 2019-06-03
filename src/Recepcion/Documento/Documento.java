package Recepcion.Documento;

public class Documento {

    int numFolio,idRol,idDestinatario,idProcedencia;
    boolean adjuntar, entregado;

    public Documento(int numFolio) {
        this.numFolio = numFolio;
    }

    public Documento(int numFolio, int idRol, int idDestinatario, int idProcedencia, boolean adjuntar, boolean entregado) {
        this.numFolio = numFolio;
        this.idRol = idRol;
        this.idDestinatario = idDestinatario;
        this.idProcedencia = idProcedencia;
        this.adjuntar = adjuntar;
        this.entregado =entregado;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public int getNumFolio() {
        return numFolio;
    }

    public void setNumFolio(int numFolio) {
        this.numFolio = numFolio;
    }

    public int getIdRol() {
        return idRol;
    }


    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public int getIdProcedencia() {
        return idProcedencia;
    }

    public void setIdProcedencia(int idProcedencia) {
        this.idProcedencia = idProcedencia;
    }

    public boolean isAdjuntar() {
        return adjuntar;
    }

    public void setAdjuntar(boolean adjuntar) {
        this.adjuntar = adjuntar;
    }

    @Override
    public String toString() {
        return Integer.toString(numFolio);
    }
}
