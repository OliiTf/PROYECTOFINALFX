package Recepcion.Documento;

import javafx.scene.control.DatePicker;

import java.sql.Date;

public class DocumentoInsert {
    int txtfolio,txtnodoc,cmbformato,cmbtipo,iddocumento;
    Date dpfechadoc,dpfechasrecep;

    public DocumentoInsert(int txtfolio, int txtnodoc, int cmbformato, int cmbtipo, int iddocumento, Date dpfechadoc, Date dpfechasrecep) {
        this.txtfolio = txtfolio;
        this.txtnodoc = txtnodoc;
        this.cmbformato = cmbformato;
        this.cmbtipo = cmbtipo;
        this.iddocumento = iddocumento;
        this.dpfechadoc = dpfechadoc;
        this.dpfechasrecep = dpfechasrecep;
    }

    public int getTxtfolio() {
        return txtfolio;
    }

    public void setTxtfolio(int txtfolio) {
        this.txtfolio = txtfolio;
    }

    public int getTxtnodoc() {
        return txtnodoc;
    }

    public void setTxtnodoc(int txtnodoc) {
        this.txtnodoc = txtnodoc;
    }

    public int getCmbformato() {
        return cmbformato;
    }

    public void setCmbformato(int cmbformato) {
        this.cmbformato = cmbformato;
    }

    public int getCmbtipo() {
        return cmbtipo;
    }

    public void setCmbtipo(int cmbtipo) {
        this.cmbtipo = cmbtipo;
    }

    public Date getDpfechadoc() {
        return dpfechadoc;
    }

    public void setDpfechadoc(Date dpfechadoc) {
        this.dpfechadoc = dpfechadoc;
    }

    public Date getDpfechasrecep() {
        return dpfechasrecep;
    }

    public void setDpfechasrecep(Date dpfechasrecep) {
        this.dpfechasrecep = dpfechasrecep;
    }

    public int getIddocumento() {
        return iddocumento;
    }

    public void getiddocumento(int iddocumento) {
        this.iddocumento = iddocumento;
    }
}
