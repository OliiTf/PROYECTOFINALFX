package sample;

import java.sql.Date;

public class ConsultaDocumentos {
    String noFolio, procedencia, noDocumento, Observaciones;
    Date fechaRecepcion;

    public ConsultaDocumentos(String noFolio, Date fechaRecepcion, String procedencia, String noDocumento, String observaciones) {
        this.noFolio = noFolio;
        this.fechaRecepcion = fechaRecepcion;
        this.procedencia = procedencia;
        this.noDocumento = noDocumento;
        Observaciones = observaciones;
    }
}

