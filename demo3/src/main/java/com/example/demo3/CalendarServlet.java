package com.example.demo3;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.text.ParseException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "CalendarServlet", value = "/CalendarServlet")
public class CalendarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        Parser parser = new Parser();
        try (FileReader reader = new FileReader("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json"))
        {
            //Read JSON file
            Object json = new JSONTokener(reader).nextValue();
            System.out.println(json.toString());



            JSONArray eventList = (JSONArray) json;
            System.out.println(eventList);

            //Iterate over employee array
            eventList.forEach( event -> {
                try {
                    parser.parseEventObject( ((JSONObject) event ),  response );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        PrintWriter out = response.getWriter();
//        try {
//            BufferedReader objReader = new BufferedReader(new FileReader("c:\\temp/filedata1.json"));
//
//            String json = objReader.readLine();
//            JSONTokener tokener = new JSONTokener(json);
//
//            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
//            JSONArray finalResult = new JSONArray(tokener);
//            // A JSON array. JSONObject supports java.util.List interface.
//            //JSONArray companyList = (JSONArray) jsonObject.get("Company List");
//            while (finalResult.iterator().hasNext()) {
//                System.out.println(finalResult.iterator().next());
//                out.println(finalResult.iterator().next().toString());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        out.println("<html><table style ='width:100%' border ='1px solid black'>");
//        out.print("<tr><th width=150> Sunday </th><th width=150> Monday </th><th width=150> Tuesday </th>");
//        out.println("<th width=150> Wednesday </th><th width=150> Thursday </th><th width=150> Friday </th><th width=150> Saturday </th>");
//
//        out.println("<tr height=150> <td width=150></td> <td width=150> </td> <td width=150></td> <td width=150></td> <td width=150></td> <td width=150></td> <td width=150></td> </tr>");
//        out.println("<tr height=150><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td></tr>");
//        out.println("<tr height=150><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td></tr>");
//        out.println("<tr height=150><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td></tr>");
//        out.println("<tr height=150><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td></tr>");
//        out.println("<table></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
