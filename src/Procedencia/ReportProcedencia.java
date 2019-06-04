package Procedencia;

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

public class ReportProcedencia {

    public static final String OP = "src/styles/OP.png";
    public void createPdfProcedencia(String dest, List<InstitucionProcedencia> procedencias) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);
        Image op = new Image(ImageDataFactory.create(OP));
        document.add(op);
        document.add(new Paragraph("REPORTE INSTITUCIONES DE PROCEDENCIA"));
        document.add(new Paragraph(""));

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(new float[]{4, 1});
        //table.setWidthPercent(100);

        process(table,null, bold, true);
        for(InstitucionProcedencia p : procedencias){
            process(table,p,font,false);
        }
        document.add(table);

        //Close document
        document.close();
    }

    public void process(Table table,InstitucionProcedencia institucionProcedencia, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("Id Institucion").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre Institucion").setFont(font)));
        } else {
            table.addCell(new Cell().add(new Paragraph(""+institucionProcedencia.getIdInstitucion()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(institucionProcedencia.getNombreInstitucion()).setFont(font)));


        }

    }
}
