package sample;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.IOException;
import java.util.List;

public class ReporteConsultaDocumentos {

    public static final String OP = "src/styles/OP.png";

    public void PDFdocuments(String dest, List<ReporteConsultas> doc,List<ReporteConsultas> desti,List<ReporteConsultas> proc) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Image op = new Image(ImageDataFactory.create(OP));
        document.add(op);
        //DOCUMENTO
        document.add(new Paragraph("INFORMACIÓN DOCUMENTO"));
        Table table = new Table(new float[]{4, 1, 3, 3, 3, 3});
        //table.setWidthPercent(100);

       process1(table, null, bold, true);
        for (ReporteConsultas dep: doc)
        {
            process1(table,dep, font, false);
        }
        document.add(table);
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));


        //DESTINATARIO
        document.add(new Paragraph("INFORMACIÓN DESTINATARIO"));
        document.add(new Paragraph(""));
        Table table1 = new Table(new float[]{4, 1, 3, 3, 3, 3});
        //table.setWidthPercent(100);

        process2(table1, null, bold, true);
        for (ReporteConsultas des: desti)
        {
            process2(table1,des, font, false);
        }
        document.add(table1);
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));

        //PROCEDENCIA
        document.add(new Paragraph("INFORMACIÓN DE PROCEDENCIA"));
        document.add(new Paragraph(""));
        Table table2 = new Table(new float[]{4, 1, 3, 3, 3, 3});
        //table.setWidthPercent(100);

        process3(table2, null, bold, true);
        for (ReporteConsultas dep: proc)
        {
            process3(table2,dep, font, false);
        }
        document.add(table2);
        document.add(new Paragraph(""));

        //Close document
        document.close();
    }

    public void process1(Table table,ReporteConsultas rc, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("No Folio").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("No Documento").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Documento").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Recepcion").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre Formato").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre Tipo Documento").setFont(font)));

        } else {
            table.addCell(new Cell().add(new Paragraph(""+rc.getNumFolio()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+rc.getNumDocumento()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+rc.getFechaDocumento()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+rc.getFechaRecepcion()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getNombreFormato()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getNombreTipoDoc()).setFont(font)));
        }

    }

    public void process2(Table table,ReporteConsultas rc, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("Quien Recibe").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre Area").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Prioridad").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Instruccion").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Entrega").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Limite").setFont(font)));

        } else {
            table.addCell(new Cell().add(new Paragraph(rc.getQuienRecibe()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getNombreArea()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getDescPrioridad()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getDescInstruccion()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+ rc.getFechaEntrega()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+rc.getFechaLimite()).setFont(font)));
        }

    }

    public void process3(Table table,ReporteConsultas rc, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre Institucion").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Quien Firma").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Puesto").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Dirigida A").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Asunto").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Observaciones").setFont(font)));

        } else {
            table.addCell(new Cell().add(new Paragraph(rc.getNombreInstitucion()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getQuienFirma()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getPuesto()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getDirigidaA()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getAsunto()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(rc.getObservaciones()).setFont(font)));
        }

    }
}
