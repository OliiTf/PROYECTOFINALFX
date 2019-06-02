package sample;

import Reportes.Reportes;
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

public class GeneracionReporte {
    public static final String DATA = "src/resources/united_states.csv";
    public static final String Logo = "src/Logos/Logo.png";

    /*public static final String DEST = "results/chapter01/united_states.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E04_UnitedStates().createPdf(DEST);
    }*/

    public void createPdf(String dest, List<Reportes> employees) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(new float[]{4, 1, 3, 4, 3});
        //table.setWidthPercent(100);
        Image logo = new Image(ImageDataFactory.create(Logo));


        process(table,null, bold, true);
        for(Reportes e : employees){
            process(table,e,font,false);

        }
        document.add(logo);
        document.add(table);


        //Close document
        document.close();
    }

    public void process(Table table,Reportes employee, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("Numero de folio").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre de la institucion").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Quien Recibe").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha de Recepcion").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Firma de Persona").setFont(font)));



        } else {
            table.addCell(new Cell().add(new Paragraph(""+employee.getNumfolio()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getNombreInstitucion()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getQuienRecibe()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+employee.getFechaRecepcion()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph("")));


        }

    }
}
