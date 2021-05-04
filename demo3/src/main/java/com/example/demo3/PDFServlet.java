package com.example.demo3;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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

        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.newLineAtOffset(25, 800);
        contentStream.setLeading(14.5f);
        contentStream.setFont( PDType1Font.TIMES_ROMAN	, 11 );

        for ( JSONObject obj: cal.getCalendar()) {
            ArrayList<String> text;
            StringBuilder sb = new StringBuilder();
            //System.out.println((String) obj.get("eventDate"));
            sb.append("Date: "+(String) obj.get("eventDate")+ " ");
            //sb.append((String) obj.get("eventType") +" ");
            sb.append("Description: "+(String) obj.get("eventDescription")+ " ");

            //this replaces letters in the string to make it look nicer and we remove new line characters
            //because the program will crash if we dont.
            String t = sb.toString().replace("\n", " ").replace("\r", " ").replace(","," ").replace("-","");
            t = t.replaceAll("\\(\\d\\d/\\d\\d\\)"," ");


            //this line of code removes duplicate words in the string.
            String a = Arrays.stream( t.split("\\s+")).distinct().collect(Collectors.joining(" ") );
            contentStream.showText(a);
            contentStream.newLine();

        }
        //the syllabus will be short enough that one page will be enough.
        contentStream.endText();
        contentStream.close();
        //document.addPage(page);
        document.save(new File("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/my_syllabus.pdf"));
        document.close();
        System.out.println("PDF created");
        getServletContext().getRequestDispatcher("/pdfCreatedSuccess.jsp").forward(request,response);

    }

}