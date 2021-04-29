package com.example.demo3;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Parser {

    private PDDocument doc;


    private int maxFileSize = 250 * 1024;
    private int maxMemSize = 20 * 1024;
    public Parser(String filePath)throws IOException{
        File file = new File(filePath);

    }

    public Parser() {

    }

    public void showEntireCalendar(HttpServletResponse response, String day) throws IOException, ParseException {
        Calendar calendar = readJSON(false);

        for(JSONObject obj: calendar.getCalendar()){
            String eDate = (String) obj.get("eventDate");
            if (eDate.contains(day))
            {
                printSingleEventObject(obj,  response);
                return;
            }
        }
    }
    public boolean findByDescription(JSONObject event, String matchingString){
        if((event.get("eventDescription").toString().contains(matchingString))){

            return true;
        }

        return false;
    }
    public void parseFile(File file, HttpServletResponse response) throws Exception {
        doc = PDDocument.load(file);
        //converts the PDF into text
        java.io.PrintWriter out = response.getWriter( );
        PDFTextStripper stripper = new PDFTextStripper();

        // StringBuilder to store the extracted text
        StringBuilder sb = new StringBuilder();
        Calendar calendar = new Calendar();
        // Add text to the StringBuilder from the PDF
        sb.append(stripper.getText(doc));

        boolean inEvent = false;

        Pattern p = Pattern.compile("(\\d\\d/\\d\\d)");
        Matcher m = p.matcher("") ;

        String eventDate = "MM/dd";
        String eventTypes = "General";
        String eventDescription = "";

        StringBuilder tempSB = new StringBuilder();

        //Pattern used to find text in file
        //This pattern finds the pattern (DD/MM) in the syllabus
        String[] lines = sb.toString().split("\\n");
        for(String s: lines){


            if(s.contains("Class")){
                if(inEvent){
                    eventDescription = eventDescription.replace("Class \\d\\d" , "");
                    eventDescription = eventDescription.replace("(\\d\\d/\\d\\d)" , "");

                    eventDescription = eventDescription.replace("\n", "").replace("\r", "");
                    eventTypes = eventTypes.replace("\n", "").replace("\r", "");


                    Event event  = new Event(eventDate, eventTypes, eventDescription);
                    //Add to calendar below
                    calendar.addEvent(event);
                }
                eventDate =  "MM/dd";
                eventTypes ="General, ";
                eventDescription= "";
                tempSB = new StringBuilder();
                sb.append(s);
                m = p.matcher(sb);
                while (m.find()){//prints out the matches

                   eventDate =(m.group());
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
            writeToFile(calendar);
        }

        if (doc != null) {
            doc.close();
        }

    }
    private void writeToFile(Calendar calendar) throws FileNotFoundException {
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
    public void insertNewEvent(String eventDate, String eventType, String eventDescription, boolean isEdit) throws FileNotFoundException, ParseException {
        Event event = new Event(eventDate, eventType, eventDescription);

        Calendar cal = readJSON(!isEdit);

        cal.addEvent(event);

        writeToFile(cal);

    }
    public void deleteOldJSON(){
        File myObj = new File("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file..");
        }
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
    public boolean findByDate(JSONObject event, String matchingString) {

        if(event.get("eventDate").equals(matchingString)){


            return true;
        }

        return false;
    }
    public Calendar readJSON(boolean delete) throws ParseException {
        Calendar calendarReturn = new Calendar();
        try (FileReader reader = new FileReader("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json"))
        {
            //Read JSON file
            Object json = new JSONTokener(reader).nextValue();

            JSONArray eventList = (JSONArray) json;

            //Iterate over  array
            for (Object event1 : eventList) {

                Event temp = new Event( (String)((JSONObject)event1 ).get("eventDate"),    (String)((JSONObject)event1 ).get("eventType"), (String)((JSONObject)event1 ).get("eventDescription") );
                calendarReturn.addEvent(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(delete){
            deleteOldJSON();
        }

        calendarReturn = sortCalendar(calendarReturn);
        return calendarReturn;
    }
    public Calendar sortCalendar(Calendar cal) throws ParseException {
        DateFormat date1 =new SimpleDateFormat("MM/dd");;
        DateFormat  date2 =new SimpleDateFormat("MM/dd");;


        Collections.sort(cal.getCalendar(), (s1, s2) -> {
            int temp = 0;
            try {
                temp = date1.parse((String) s1.get("eventDate")).compareTo(date2.parse((String) s2.get("eventDate")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return temp;
        });


        return cal;
    }
    public Date stringToDate(String date) throws ParseException {
        Date convertedDate ;
        DateFormat date1 =new SimpleDateFormat("MM/dd");;
        convertedDate = date1.parse(date);

        System.out.println("SDS" +convertedDate.toString());
        return convertedDate;
    }
    public void removeEvent(String eventDate) throws FileNotFoundException, ParseException {




        Calendar cal = readJSON(true);



        PrintWriter fileWriter = new PrintWriter(new
                FileOutputStream("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json",true));
        fileWriter.println("[");

        Object json = new JSONTokener(String.valueOf(cal.getCalendar())).nextValue();


        JSONArray eventList = (JSONArray) json;
        System.out.println(eventList);

        //Iterate over event array
        eventList.forEach( event -> {

            boolean isMatch = findByDate( ((JSONObject) event ),  eventDate );
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