package com.example.demo3;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.GregorianCalendar;
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

@WebServlet(name = "MonthlyServlet", value = "/MonthlyServlet")
public class MonthlyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Parser parser = new Parser();
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);    // Sunday-1 thru Saturday-7
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");
        SimpleDateFormat sDay = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("YYYY");
        SimpleDateFormat sMonthText = new SimpleDateFormat("MMMM");
        String thisDay;
        String newDate = null;
        newDate = request.getParameter("date");

        if(newDate == null)
        {
            cal.add(Calendar.MONTH,-1);
            date = cal.getTime();   // find the current date in order to display the current month

        }
        else if (newDate != null)
        {
            int m = Integer.parseInt(newDate);
            int currentM = Integer.parseInt(sMonth.format(date));
            m = currentM - m;
            cal.add(Calendar.MONTH, -m);
        }

        int totalDays = findMonthSize(sYear.format(date), sMonth.format(date), sDay.format(date));  // find total days in the current month
        int currentDay = Integer.parseInt(sDay.format(date)) - 1;

        cal.add(Calendar.DATE,-currentDay);
        date = cal.getTime();


        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Monthly Calender</title>");
            out.println("</head>");
            out.println("<body><h2>Monthly Calendar<h2><h1>" + sMonthText.format(date) + "</h1>");

            //  ---- HOME BUTTON ----
            out.print("<form action=/demo1_war_exploded/><input type='submit' value='GO HOME'/></form>");

            //  ---- VIEW MONTHLY CALENDAR BUTTON ----
            out.print("<form action=/demo1_war_exploded/CalendarServlet><input type='submit' value='View Weekly Display'/></form>");

            //  ---- ADD EVENT BUTTON ----
            out.print("<form action=/demo1_war_exploded/EventServlet><input type='submit' value='Add/Remove Events'/></form></br>");

            //  ---- VIEW NEW MONTH BUTTON ----
            out.print("<form action=/demo1_war_exploded/MonthlyServlet method='GET'> View Month Of (mm): <input type='text' name='date'><input type='submit' value='GO'/></form>");



            //  ---- DISPLAYS HEADERS (Sunday-Monday) STARTING WITH 1st OF THE MONTH
            out.println("<table style='width:100%' border ='1px solid black'><tr>");
            out.println("<th>" + sdf.format(date) + "</th>");
            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("<th>" + sdf.format(date) + "</th>");
            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("<th>" + sdf.format(date) + "</th>");
            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("<th>" + sdf.format(date) + "</th>");
            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("<th>" + sdf.format(date) + "</th>");
            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("<th>" + sdf.format(date) + "</th>");
            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("<th>" + sdf.format(date) + "</th></tr>");

            // ---- Reset Date back to 1st of month ----
            cal.add(Calendar.DATE,-6);
            date = cal.getTime();

            //  ----   Week 1   ----
            out.println("<tr width=70>");
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100 width=200>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100 width=200>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100 width=200>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100 width=200>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100 width=200>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100 width=200>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100 width=200>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("</tr>");

            //  ----   Week 2   ----
            out.println("<tr width=70>");
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("</tr>");

            //  ----   Week 3   ----
            out.println("<tr width=70>");
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("</tr>");

            //  ----   Week 4   ----
            out.println("<tr width=70>");
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("</tr>");

            //  ----   Week 5   ----
            out.println("<tr width=70>");
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            thisDay = sdf1.format(date);
            out.println("<td style='vertical-align:text-top' height=100>" + sDay.format(date) + "</br></br>");
            try {
                parser.showEntireCalendar(response, thisDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            out.println("</td>");

            cal.add(Calendar.DATE,+1);
            date = cal.getTime();
            out.println("</tr>");


            out.println("</table></body></html>");

        }


    }



    public int findMonthSize(String year, String month, String day)
    {
        // Calendar mycal = new Calender();
        GregorianCalendar mycal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


}
