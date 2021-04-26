package com.example.demo3;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private PDDocument doc;

    public Parser(String filePath)throws IOException{
        File file = new File(filePath);

    }

    public void parseFile(File file, HttpServletResponse response)throws IOException{
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
            System.out.println("Content = " + s);

            if(s.contains("Class")){
                if(inEvent){

                    Events event  = new Events(eventDate, eventTypes, eventDescription);
                    //Add to calendar below
                    calendar.addEvent(event);
                }
                eventDate = "MM/DD";
                eventTypes ="General";
                eventDescription= "";
                tempSB = new StringBuilder();
                sb.append(s);
                m = p.matcher(tempSB);
                while (m.find()){//prints out the matches
                   eventDate = m.group();
                }
                eventDate =m.group();
                eventDescription.concat(s.substring(16) +", ");
                inEvent = true;
            }
            if(inEvent){
                if(s.contains("Due")){
                    eventTypes.concat("Due, ");
                }
                if(s.contains("Assigned")){
                    eventTypes.concat("Assigned, ");
                }
                eventDescription.concat(s+", ");
            }



        }
        calendar.printCalendar(response);

        Pattern p1 = Pattern.compile("(\\d\\d/\\d\\d)");
        Matcher m2 = p.matcher(sb);
        while (m.find()){//prints out the matches
            out.println(m.group());
            System.out.println(m.group());
            out.println("</br>");
        }
        if (doc != null) {
            doc.close();
        }

    }
}