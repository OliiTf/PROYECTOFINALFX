package tipoDocumento;

public class tipoDocumento {

    int idTipoDocumento;
    String nombreTipoDoc;

    public tipoDocumento(int idTipoDocumento, String nombreTipoDoc) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombreTipoDoc = nombreTipoDoc;
    }

    public tipoDocumento() {
    }

    public tipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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

    @Override
    public String toString() {
        return Integer.toString(idTipoDocumento);
    }
}
