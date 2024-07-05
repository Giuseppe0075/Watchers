package servlets;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet(name="PdfGeneratorServlet", value = "/pdfGenerator-servlet")
public class PdfGeneratorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Hello World", font);
            document.add(chunk);
            document.close();

            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "attachment; filename=fattura.pdf");
            resp.setContentLength(baos.size());
            resp.getOutputStream().write(baos.toByteArray());
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }

}