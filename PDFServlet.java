package com.example.demo3;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Parser parser = new Parser();
        Calendar cal = new Calendar();
        try {
            cal = parser.readJSON(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PDDocument document = new PDDocument();

        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.newLineAtOffset(25, 700);
        contentStream.setLeading(14.5f);
        contentStream.setFont( PDType1Font.TIMES_ROMAN	, 12 );

        for ( JSONObject obj: cal.getCalendar()) {
            ArrayList<String> text;
            StringBuilder sb = new StringBuilder();
            //System.out.println((String) obj.get("eventDate"));
            sb.append("Date: "+(String) obj.get("eventDate")+ " ");
            //sb.append((String) obj.get("eventType") +" ");
            sb.append("Description: "+(String) obj.get("eventDescription")+ " ");
            String t = sb.toString().replace("\n", " ").replace("\r", " ").replace(","," ").replace("-","");
            t = t.replaceAll("\\(\\d\\d/\\d\\d\\)"," ");
            //text will stored into smaller arrays of specified size
            //t can be modified to your liking, i got rid of redundent symbols and dates
            text = splitEqually(t,110);
            //you can look text adding it to content stream. This next line will print to stream
            contentStream.showText(text.get(0));
            //after adding to stream we should add a new line.
            contentStream.newLine();
            //I can add code here to stop stream once the length is past the pdf limit.
            //I would close stream and reopen the stream with parameters similar to like 59-62.
        }

        contentStream.endText();
        contentStream.close();
        //document.addPage(page);
        document.save(new File("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/my_syllabus.pdf"));
        document.close();
        System.out.println("PDF created");

    }
    public static ArrayList<String> splitEqually(String text, int size) {
        // Give the list the right capacity to start with. You could use an array
        // instead if you wanted.
        ArrayList<String> t = new ArrayList<String>((text.length() + size - 1) / size);

        for (int start = 0; start < text.length(); start += size) {
            t.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return t;
    }
}