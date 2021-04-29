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

        //getServletContext().getRequestDispatcher("/monthlyCal.jsp").forward(request, response);
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

        //PrintWriter out = response.getWriter();

        if(newDate == null)
        {
            cal.add(Calendar.MONTH,-1);
            date = cal.getTime();   // find the current date in order to display the current month

        }
        else if (newDate != null)
        {
            int m = Integer.parseInt(newDate);
            // int d = Integer.parseInt(newDate.substring(3));
            int currentM = Integer.parseInt(sMonth.format(date));
            //int currentD = Integer.parseInt(sDay.format(date));

            m = currentM - m;   // if currentM > m then m is positive
            // if m > currentM then m is negative

            // d = currentD - d;   // if currentD > d then d is positive
            // if d > currentD then d is negative

            cal.add(Calendar.MONTH, -m);
            //cal.add(Calendar.DATE, -d);
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
            out.print("<br><a href=\"/demo1_war_exploded/\"><button type=\"button\">GO HOME</button></a>");
            out.print("<br><a href=\"/demo1_war_exploded/CalendarServlet\"><button type=\"button\">GO TO WEEKLY CALENDAR</button></a>");
            out.println("<title>Monthly Calender</title>");
            out.println("</head>");
            out.println("<body><h2>Monthly Calendar<h2><h1>" + sMonthText.format(date) + "</h1>");


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

            out.print("<form action = \"MonthlyServlet\" method = \"GET\">\n" +
                    "         View Month (MM): <input type = \"text\" name = \"date\">\n" +
                    "         <input type = 'submit' value = 'GO' />" +
                    "      </form>");
        }


    }

    public int findMonthSize(String year, String month, String day)
    {
        // Calendar mycal = new Calender();
        GregorianCalendar mycal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


}
