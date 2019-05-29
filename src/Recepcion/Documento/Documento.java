package Recepcion.Documento;

public class Documento {

    int numFolio,idUsuario,idDestinatario,idProcedencia;
    boolean adjuntar;

    public Documento(int numFolio) {
        this.numFolio = numFolio;
    }

    public Documento(int numFolio, int idUsuario, int idDestinatario, int idProcedencia, boolean adjuntar) {
        this.numFolio = numFolio;
        this.idUsuario = idUsuario;
        this.idDestinatario = idDestinatario;
        this.idProcedencia = idProcedencia;
        this.adjuntar = adjuntar;
    }

    public int getNumFolio() {
        return numFolio;
    }

    public void setNumFolio(int numFolio) {
        this.numFolio = numFolio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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


}
