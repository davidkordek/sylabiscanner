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
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.*;
import java.lang.String;

@WebServlet(name = "CalendarServlet", value = "/CalendarServlet")
public class CalendarServlet extends HttpServlet {


    String sunday;
    String monday;
    String tuesday;
    String wednesday;
    String thursday;
    String friday;
    String saturday;
    String day1;
    String day2;
    String day3;
    String day4;
    String day5;
    String day6;
    String day7;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // getServletContext().getRequestDispatcher("/calDisplay.jsp");//.forward(request,response);
        response.setContentType("text/html");
        Parser parser = new Parser();

   //   ----- PRINTING THE HEADERS -----
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);    // Sunday-1 thru Saturday-7
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");
        PrintWriter out = response.getWriter();
        cal.add(Calendar.MONTH,-1);
        date = cal.getTime();

//        Date weekStart = new Date();
//        Date weekEnd = new Date();
//        cal.setTime(weekStart);
//        cal.add(Calendar.DATE,+7);
//        weekEnd = cal.getTime();

        out.println("<html><table style ='width:100%' border ='1px solid black'>");

   //  ---- CURRENT DAY ----

        day1 = sdf1.format(date);
        System.out.println(day1);
        out.print("<tr><th width=150>" + sdf1.format(date) + "<br>" + sdf.format(date) + "</th>");
        cal.add(Calendar.DATE,+1);
        date = cal.getTime();

  //   ---- NEXT DAY ----
        day2 = sdf1.format(date);
        out.print("<th width=150>" + sdf1.format(date) + "<br>" + sdf.format(date) + "</th>");
        cal.add(Calendar.DATE,+1);
        date = cal.getTime();

        day3 = sdf1.format(date);
        out.print("<th width=150>" + sdf1.format(date) + "<br>" + sdf.format(date) + "</th>");
        cal.add(Calendar.DATE,+1);
        date = cal.getTime();

        day4 = sdf1.format(date);
        out.print("<th width=150>" + sdf1.format(date) + "<br>" + sdf.format(date) + "</th>");
        cal.add(Calendar.DATE,+1);
        date = cal.getTime();

        day5 = sdf1.format(date);
        out.print("<th width=150>" + sdf1.format(date) + "<br>" + sdf.format(date) + "</th>");
        cal.add(Calendar.DATE,+1);
        date = cal.getTime();

        day6 = sdf1.format(date);
        out.print("<th width=150>" + sdf1.format(date) + "<br>" + sdf.format(date) + "</th>");
        cal.add(Calendar.DATE,+1);
        date = cal.getTime();

        day7 = sdf1.format(date);
        out.print("<th width=150>" + sdf1.format(date) + "<br>" + sdf.format(date) + "</th>");


        // Sunday Events
        out.print("</tr><tr height='600'><td width=150>");
        parser.showEntireCalendar(response, day1);
        out.println("</td>");

        // Monday Events
        out.print("<td width=150>");
        parser.showEntireCalendar(response, day2);
        out.println("</td>");

        // Tuesday Events
        out.print("<td width=150>");
        parser.showEntireCalendar(response, day3);
        out.println("</td>");

        // Wednesday Events
        out.print("<td width=150>");
        parser.showEntireCalendar(response, day4);
        out.println("</td>");

        // Thursday Events
        out.print("<td width=150>");
        parser.showEntireCalendar(response, day5);
        out.println("</td>");

        // Friday Events
        out.print("<td width=150>");
        parser.showEntireCalendar(response, day6);
        out.println("</td>");

        // Saturday Events
        out.print("<td width=150>");
        parser.showEntireCalendar(response, day7);
        out.println("</td>");
        out.println("</tr><table></html>");



//        out.print("<form action = 'UploadServlet' method = post enctype = 'multipart/form-data'/>");
//        out.print("<input type = 'String' name = " + day7 + "/> <br />  input type = 'submit' value = 'Next Week' /></form>");



        //getServletContext().getRequestDispatcher("/calDisplay.jsp");//.forward(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }



//    public void parseEventObject(JSONObject event, HttpServletResponse response) throws IOException {
//        java.io.PrintWriter out = response.getWriter();
//
//
//        out.println((String)event.get("eventDate"));
//        out.println("</br>");
//        System.out.println((String)event.get("eventDate"));
//        out.println((String)event.get("eventType"));
//        out.println("</br>");
//        out.println((String)event.get("eventDescription"));
//        out.println("</br>");
//        out.println("</br>");
//
//    }
}
