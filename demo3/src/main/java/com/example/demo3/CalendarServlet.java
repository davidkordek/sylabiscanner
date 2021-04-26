package com.example.demo3;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

@WebServlet(name = "CalendarServlet", value = "/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
