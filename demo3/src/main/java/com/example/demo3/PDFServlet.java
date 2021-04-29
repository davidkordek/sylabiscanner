package com.example.demo3;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "PDFServlet", value = "/PDFServlet")
public class PDFServlet extends HttpServlet {

    private String filepath;
    private File file;
    private int maxMemSize = 20 * 1024;
    private int maxFileSize = 250 * 1024;
    private boolean isMultipart;
    public void init(){
        filepath = getServletContext().getInitParameter("C:\\temp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PDDocument document = new PDDocument();

        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        Parser parser = new Parser();
        Calendar cal = new Calendar();
        try {
           cal = parser.readJSON(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contentStream.setFont( PDType1Font.HELVETICA_BOLD	, 12 );


        for ( JSONObject obj: cal.getCalendar()) {
            StringBuilder sb = new StringBuilder();
            //System.out.println((String) obj.get("eventDate"));
            sb.append((String) obj.get("eventDate")+ " ");
            sb.append((String) obj.get("eventType") +" ");
            sb.append((String) obj.get("eventDescription")+ " \n");
            contentStream.newLine();
            contentStream.showText(sb.toString());
        }


        contentStream.endText();
        contentStream.close();
        document.addPage(page);
        document.save(new File("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/my_syllabus.pdf"));
        document.close();
        System.out.println("PDF created");

    }
}