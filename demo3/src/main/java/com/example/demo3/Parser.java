package com.example.demo3;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

public class Parser {

    private PDDocument doc;
    private int maxFileSize = 250 * 1024;
    private int maxMemSize = 20 * 1024;
    public Parser(String filePath)throws IOException{
        File file = new File(filePath);

    }

    public void parseFile(File file, HttpServletResponse response) throws Exception {
        doc = PDDocument.load(file);
        //converts the PDF into text
        java.io.PrintWriter out = response.getWriter( );
        PDFTextStripper stripper = new PDFTextStripper();

        // StringBuilder to store the extracted text
        StringBuilder sb = new StringBuilder();

        // Add text to the StringBuilder from the PDF
        sb.append(stripper.getText(doc));

        boolean inEvent = false;
        Calendar calendar = new Calendar();
        String eventDate = "MM/DD";
        String eventTypes = "General";
        String eventDescription = "";
        StringBuilder tempSB = new StringBuilder();
        Pattern p = Pattern.compile("(\\d\\d/\\d\\d)");
        Matcher m = p.matcher("") ;
        //Pattern used to find text in file
        //This pattern finds the pattern (DD/MM) in the syllabus
        String[] lines = sb.toString().split("\\n");
        for(String s: lines){


            if(s.contains("Class")){
                if(inEvent){

                    Event event  = new Event(eventDate, eventTypes, eventDescription);
                    //Add to calendar below
                    calendar.addEvent(event);
                }
                eventDate = "MM/DD";
                eventTypes ="General, ";
                eventDescription= "";
                tempSB = new StringBuilder();
                sb.append(s);
                m = p.matcher(sb);
                while (m.find()){//prints out the matches
                   eventDate = m.group();
                }

                eventDescription = eventDescription.concat(s.substring(16) +", ");
                inEvent = true;
            }
            if(inEvent){
                if(s.contains("Due")){
                   eventTypes = eventTypes.concat("Due, ");
                }
                if(s.contains("Assigned")){
                    eventTypes = eventTypes.concat("Assigned, ");
                }
                eventDescription =  eventDescription.concat(s+", ");
            }



        }
        calendar.printCalendar(response);

        boolean found = false;

        FileReader fileReader = new FileReader(file);

        if (!found){
            PrintWriter fileWriter = new PrintWriter(new
                    FileOutputStream("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json",true));
            fileWriter.println("[");
            for(JSONObject obj : calendar.getCalendar()){
                String jsonText = obj.toString();
                fileWriter.println( jsonText +",");
                fileWriter.println( "\n" );

            }
            fileWriter.println("]");

            fileWriter.close();
            System.out.println("file saved");
        }

        if (doc != null) {
            doc.close();
        }

    }
}