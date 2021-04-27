package com.example.demo3;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Parser {

    private PDDocument doc;
    private Calendar calendar = new Calendar();
    private Calendar calendar2 = new Calendar();


    private int maxFileSize = 250 * 1024;
    private int maxMemSize = 20 * 1024;
    public Parser(String filePath)throws IOException{
        File file = new File(filePath);

    }

    public Parser() {

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
                    eventDescription = eventDescription.replace("Class \\d\\d" , "");
                    eventDescription = eventDescription.replace("(\\d\\d/\\d\\d)" , "");

                    System.out.println(eventDescription);

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
            fileWriter.print("]");

            fileWriter.close();
            System.out.println("file saved");
        }

        if (doc != null) {
            doc.close();
        }

    }
    public void insertNewEvent(String eventDate, String eventType, String eventDescription) throws FileNotFoundException {
        Event event = new Event(eventDate, eventType, eventDescription);


        Calendar cal = readJSON();


        cal.addEvent(event);

            PrintWriter fileWriter = new PrintWriter(new
                    FileOutputStream("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json",true));
            fileWriter.println("[");
            for(JSONObject obj : cal.getCalendar()){
                String jsonText = obj.toString();
                fileWriter.println( jsonText +",");
                fileWriter.println( "\n" );

            }
            fileWriter.print("]");

            fileWriter.close();
            System.out.println("file saved");

    }
    public void parseEventObject(JSONObject event, HttpServletResponse response) throws IOException {
        java.io.PrintWriter out = response.getWriter();

        out.println((String)event.get("eventDate"));
        out.println("</br>");
        System.out.println((String)event.get("eventDate"));
        out.println((String)event.get("eventType"));
        out.println("</br>");
        out.println((String)event.get("eventDescription"));
        out.println("</br>");
        out.println("</br>");

    }
    public void printSingleEventObject(JSONObject event, HttpServletResponse response) throws IOException{
        java.io.PrintWriter out = response.getWriter();

        out.println( "Date: "+(String)event.get("eventDate"));
        out.println("</br>");

        out.println("Type(s): "+(String)event.get("eventType"));
        out.println("</br>");
        out.println("Description: "+ (String)event.get("eventDescription"));
        out.println("</br>");
        out.println("</br>");
    }
    public boolean isEventMatch(JSONObject event, String matchingString) {

        if(event.get("eventDate").equals(matchingString)){


            return true;
        }

        return false;
    }
    public Calendar readJSON() {
        try (FileReader reader = new FileReader("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json"))
        {
            //Read JSON file
            Object json = new JSONTokener(reader).nextValue();
            System.out.println(json.toString());
            Parser parser = new Parser();



            JSONArray eventList = (JSONArray) json;
            System.out.println(eventList);

            //Iterate over  array
            eventList.forEach( event1 -> {
                Event temp = new Event( (String)((JSONObject)event1 ).get("eventDate"),    (String)((JSONObject)event1 ).get("eventType"), (String)((JSONObject)event1 ).get("eventDescription") );
                calendar2.addEvent (temp );
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File myObj = new File("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file..");
        }
        return calendar2;
    }
    public void removeEvent(String eventDate) throws FileNotFoundException {




        Calendar cal = readJSON();



        PrintWriter fileWriter = new PrintWriter(new
                FileOutputStream("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json",true));
        fileWriter.println("[");

        Object json = new JSONTokener(String.valueOf(cal.getCalendar())).nextValue();


        JSONArray eventList = (JSONArray) json;
        System.out.println(eventList);

        //Iterate over employee array
        eventList.forEach( event -> {

            boolean isMatch = isEventMatch( ((JSONObject) event ),  eventDate );
            if(!isMatch){

                String jsonText = event.toString();
                fileWriter.println( jsonText +",");
                fileWriter.println( "\n" );
            }
        });


        fileWriter.print("]");

        fileWriter.close();
        System.out.println("file saved");

    }

}