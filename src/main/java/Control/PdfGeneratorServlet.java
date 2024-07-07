package Control;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Phrase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@WebServlet(name="PdfGeneratorServlet", value = "/pdfGenerator-servlet")
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

        Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
        List<Map<String, String>> data = gson.fromJson(jsonBuilder.toString(), listType);

        //Create the table
        PdfPTable table = createTable(data);

        //Generate PDF
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(table);
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

    private static PdfPTable createTable(List<Map<String, String>> data) {
        //Create the table
        final int headersSize = data.get(0).keySet().size();
        PdfPTable table = new PdfPTable(headersSize);

        //setting the headers
        data.get(0).keySet().forEach(key -> table.addCell(createCell(key)));

        //Adding the data
        data.forEach(row -> row.values().forEach(value -> {
            table.addCell(createCell(value));
        }));

        //create the total
        float total = 0f;
        for (Map<String, String> row : data) {
            total += Float.parseFloat(row.get("total").replace("€", ""));
        }

        PdfPCell totalCell = createCell(total +"€");
        for(int i = 0; i < headersSize - 1; i++) {
            PdfPCell cell = createCell("");
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
        }
        table.addCell(totalCell);

        return table;
    }

    private static PdfPCell createCell(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(4);
        cell.setPhrase(new Phrase(text));
        return cell;
    }

}