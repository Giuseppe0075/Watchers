package Control;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name="PdfGeneratorServlet", value = "/pdfGenerator-servlet")
@SuppressWarnings("unchecked")
public class PdfGeneratorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get data from request
        Gson gson = new Gson();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;

        try(BufferedReader reader = req.getReader()) {
            while((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        Type dataType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> data = gson.fromJson(jsonBuilder.toString(), dataType);

        // Extract table data and additional data
        List<Map<String, String>> tableData = (List<Map<String, String>>) data.get("tableData");
        Map<String, String> clientData = (Map<String, String>) data.get("clientData");


        //Generate PDF
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add invoice header
            addInvoiceHeader(document);

            // Add invoice details
            addInvoiceDetails(document, clientData);

            addOrderTable(document, tableData);

            // Add invoice summary
            addInvoiceSummary(document);

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        resp.setContentLength(baos.size());
        try(OutputStream out = resp.getOutputStream()) {
            baos.writeTo(out);
            out.flush();
        }

    }
    private void addInvoiceHeader(Document document) throws DocumentException {

        // Custom color for text
        BaseColor customColor = new BaseColor(3, 66, 78); // #03424e

        // Create a table with 2 columns
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new int[]{3, 1}); // The column ratio is 3:1

        // Name of the company
        Font companyFont = new Font(Font.FontFamily.HELVETICA, 36, Font.BOLD, customColor);
        PdfPCell companyNameCell = new PdfPCell(new Phrase("Watchers", companyFont));
        companyNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        companyNameCell.setBorder(Rectangle.NO_BORDER);
        headerTable.addCell(companyNameCell);

        // Logo of the company
        try {
            String logoPath = getServletContext().getRealPath("/homepage/LOGO.png");
            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(150, 150); // Resize the logo
            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(logoCell);
        } catch (IOException e) {
            PdfPCell placeholderCell = new PdfPCell(new Phrase(""));
            placeholderCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(placeholderCell);
        }

        // Add the table to the document
        document.add(headerTable);

        // Company details
        Font detailsFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, customColor);
        Paragraph companyDetails = new Paragraph("Indirizzo\nP.IVA/C.F.: 86334519757\n\n", detailsFont);
        document.add(companyDetails);
    }

    private void addInvoiceDetails(Document document, Map<String, String> clientData) throws DocumentException {
        // Invoice Header
        Paragraph invoiceHeader = new Paragraph("Fattura a:\n" + clientData.get("userName") + " " + clientData.get("userSurname") +"\n"
                + clientData.get("userAddress") + "\n"
                + "P.IVA/C.F.: 12345678910\n\n",
                new Font(Font.FontFamily.HELVETICA, 10));
        document.add(invoiceHeader);

        // Invoice number and date
        Paragraph invoiceDetails = new Paragraph("Fattura n. X\nData fattura: " + clientData.get("date") + "\n\n", new Font(Font.FontFamily.HELVETICA, 10));
        invoiceDetails.setAlignment(Element.ALIGN_RIGHT);
        document.add(invoiceDetails);
    }

    private void addInvoiceSummary(Document document) throws DocumentException {
        // Bank details
        Paragraph bankDetails = new Paragraph("Banca degli Orologiai\nIT 12 A 12345 12345 123456789012\nSWIFT/BIC: AAAA BB CC [DDD]\n", new Font(Font.FontFamily.HELVETICA, 10));
        document.add(bankDetails);
    }

    private void addOrderTable(Document document, List<Map<String, String>> data) throws DocumentException {
        //Create the table
        final int headersSize = data.get(0).keySet().size();
        PdfPTable table = new PdfPTable(headersSize);
        table.setWidthPercentage(100);
        int[] widths = new int[headersSize];
        Arrays.fill(widths, 1);
        table.setWidths(widths);

        //setting the headers
        data.get(0).keySet().forEach(key -> {
            PdfPCell cell = createCell(key);
            cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT | PdfPCell.BOTTOM);
            table.addCell(cell);
        });

        //Adding the data
        data.forEach(row -> row.values().forEach(value -> table.addCell(createCell(value))));

        //create the total
        float total = 0f;
        for (Map<String, String> row : data) {
            total += Float.parseFloat(row.get("total").replace("€", ""));
        }

        PdfPCell totalCell = createCell(total +"€");

        //Add some empty cells to fill the row
        for(int i = 0; i < headersSize - 1; i++) {
            table.addCell(createCell(""));
        }
        table.addCell(totalCell);

        document.add(table);
    }

    private PdfPCell createCell(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(4);
        cell.setPhrase(new Phrase(text));
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(PdfPCell.RIGHT | PdfPCell.LEFT);
        return cell;
    }

}