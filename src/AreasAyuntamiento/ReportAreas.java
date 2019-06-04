package AreasAyuntamiento;

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

public class ReportAreas {

    public static final String OP = "src/styles/OP.png";

    public void createPdfAreas(String dest, List<AreasAyuntamiento> areas) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);
        Image op = new Image(ImageDataFactory.create(OP));
        document.add(op);
        document.add(new Paragraph("REPORTE AREAS DE AYUNTAMIENTO"));
        document.add(new Paragraph(""));

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(new float[]{4, 1});
        //table.setWidthPercent(100);

        process(table,null, bold, true);
        for(AreasAyuntamiento a : areas){
            process(table,a,font,false);
        }
        document.add(table);

        //Close document
        document.close();
    }

    public void process(Table table,AreasAyuntamiento areas, PdfFont font, boolean isHeader) {

        if (isHeader) {
            table.addHeaderCell(new Cell().add(new Paragraph("Id Areas de Ayuntamiento").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre de Area").setFont(font)));
        } else {
            table.addCell(new Cell().add(new Paragraph(""+areas.getIdArea()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(areas.getNombreArea()).setFont(font)));


        }

    }
}
