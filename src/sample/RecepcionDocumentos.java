package sample;

import java.sql.Date;

public class RecepcionDocumentos {
    private int no_folio;
    private Date no_doc;
    private String formato, tipo;
    private Date fecha_doc, fecharecep;

    public RecepcionDocumentos(int no_folio, Date no_doc, String formato, String tipo, Date fecha_doc, Date fecharecep) {
        this.no_folio = no_folio;
        this.no_doc = no_doc;
        this.formato = formato;
        this.tipo = tipo;
        this.fecha_doc = fecha_doc;
        this.fecharecep = fecharecep;
    }

    public int getNo_folio() {
        return no_folio;
    }

    public void setNo_folio(int no_folio) {
        this.no_folio = no_folio;
    }

    public Date getNo_doc() {
        return no_doc;
    }

    public void setNo_doc(Date no_doc) {
        this.no_doc = no_doc;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipodoc(String tipodoc) {
        this.tipo = tipodoc;
    }

    public Date getFecha_doc() {
        return fecha_doc;
    }

    public void setFecha_doc(Date fecha_doc) {
        this.fecha_doc = fecha_doc;
    }

    public Date getFecharecep() {
        return fecharecep;
    }

    public void setFecharecep(Date fecharecep) {
        this.fecharecep = fecharecep;
    }

}
