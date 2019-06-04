package sample;
import Procedencia.InstitucionProcedencia;
import Reportes.Reportes;
import Reportes.ReportesDAO;
import Reportes.ReporteEntregaD;
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class GeneracionReporteProcedencia {

    public static final String Logo = "src/Logos/Logo.png";


    public void createPdf(String dest) throws IOException {
        ProcedenciaDAO procedenciaDAO= new ProcedenciaDAO(MySQLConnection.getConnection());
        ReportesDAO reportesDAO= new ReportesDAO(MySQLConnection.getConnection());
        List<InstitucionProcedencia> institucionProcedencias =procedenciaDAO.findAll();

        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);


        Image logo = new Image(ImageDataFactory.create(Logo));
        document.add(logo);
for (InstitucionProcedencia rep : institucionProcedencias ) {

    document.add(new Paragraph(rep.getNombreInstitucion()));
    Table table = new Table(new float[]{2, 4, 3,3,4,4,4,4});
    process(table, null, bold, true);

    List<ReporteEntregaD> list = reportesDAO.fetchAll2(rep.getNombreInstitucion());

    for (ReporteEntregaD e : list) {

        process(table, e, font, false);

    }

    document.add(table);
    document.add(new Paragraph(""));

}
        document.close();
    }

    public void process(Table table,ReporteEntregaD employee, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("Numero de folio").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre de la institucion").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Quien Recibe").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Tipo Documento").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Prioridad").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Formato").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Observaciones").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha de Recepcion").setFont(font)));




        } else {
            table.addCell(new Cell().add(new Paragraph(""+employee.getNumfolio()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getNombreInstitucion()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getQuienRecibe()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getNombreTipoDoc()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getDescPrioridad()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getNombreFormato()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getObservaciones()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getFechaRecepcion()).setFont(font)));
        }

    }
}
