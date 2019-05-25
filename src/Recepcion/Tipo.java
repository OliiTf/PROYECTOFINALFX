package Recepcion;

public class Tipo {
    int idTipoDocumento;
    String nombreTipoDoc;


    public Tipo(int idTipoDocumento, String nombreTipoDoc) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombreTipoDoc = nombreTipoDoc;
    }



    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreTipoDoc() {
        return nombreTipoDoc;
    }

    public void setNombreTipoDoc(String nombreTipoDoc) {
        this.nombreTipoDoc = nombreTipoDoc;
    }
}
