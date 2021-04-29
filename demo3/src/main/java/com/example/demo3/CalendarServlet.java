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


        response.setContentType("text/html");
        Parser parser = new Parser();
        String newDate = null;
        newDate = request.getParameter("date");

        //   ----- PRINTING THE HEADERS -----
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);    // Sunday-1 thru Saturday-7
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");
        SimpleDateFormat sDay = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("YYYY");
        PrintWriter out = response.getWriter();

        if(newDate == null)
        {
            //cal.add(Calendar.MONTH,-1);
            date = cal.getTime();
        }
        else if (newDate != null)
        {
            int m = Integer.parseInt(newDate.substring(0,2));
            int d = Integer.parseInt(newDate.substring(3));
            int currentM = Integer.parseInt(sMonth.format(date));
            int currentD = Integer.parseInt(sDay.format(date));

            m = currentM - m;   // if currentM > m then m is positive
            // if m > currentM then m is negative

            d = currentD - d;   // if currentD > d then d is positive
            // if d > currentD then d is negative

            cal.add(Calendar.MONTH, -m);
            cal.add(Calendar.DATE, -d);
        }


//        Date weekStart = new Date();
//        Date weekEnd = new Date();
//        cal.setTime(weekStart);
//        cal.add(Calendar.DATE,+7);
//        weekEnd = cal.getTime();
        out.println("<html>");
        out.println("<head>");
        out.print("<br><a href=\"/demo1_war_exploded/\"><button type=\"button\">GO HOME</button></a>");
        out.print("<br><a href=\"/demo1_war_exploded/MonthlyServlet\"><button type=\"button\">GO TO MONTHLY CALENDAR</button></a>");
        out.println("<title>Weekly Calender</title>");
        out.println("</head>");
        out.println("<body><h2>Weekly Calendar<h2><h1> Week of " + sdf1.format(date) + " - ");
        cal.add(Calendar.DATE, +6);
        date = cal.getTime();
        out.println(sdf1.format(date) + "</h1>");
        out.println("<table style ='width:100%' border ='1px solid black'>");

        cal.add(Calendar.DATE, -6);
        date = cal.getTime();

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
        try {
            parser.showEntireCalendar(response, day1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // out.println(newDate);
        out.println("</td>");

        // Monday Events
        out.print("<td width=150>");
        try {
            parser.showEntireCalendar(response, day2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        out.println("</td>");

        // Tuesday Events
        out.print("<td width=150>");
        try {
            parser.showEntireCalendar(response, day3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        out.println("</td>");

        // Wednesday Events
        out.print("<td width=150>");
        try {
            parser.showEntireCalendar(response, day4);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        out.println("</td>");

        // Thursday Events
        out.print("<td width=150>");
        try {
            parser.showEntireCalendar(response, day5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        out.println("</td>");

        // Friday Events
        out.print("<td width=150>");
        try {
            parser.showEntireCalendar(response, day6);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        out.println("</td>");

        // Saturday Events
        out.print("<td width=150>");
        try {
            parser.showEntireCalendar(response, day7);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        out.println("</td>");
        out.println("</tr><table></html>");


        out.print("<a href='MonthlyServlet'>View Monthly Calendar</a><br/>");
        out.print("<form action = \"CalendarServlet\" method = \"GET\">\n" +
                "         View Week Of (MM/dd): <input type = \"text\" name = \"date\">\n" +
                "         <input type = 'submit' value = 'GO' />" +
                "      </form>");


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