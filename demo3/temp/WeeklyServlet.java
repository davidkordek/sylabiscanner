package com.example.demo3;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "WeeklyServlet", value = "/WeeklyServlet")
public class WeeklyServlet extends HttpServlet {

    private ArrayList<String> sunday;   // ArrayList can be of type 'Event' in order to access/print each attribute associate with an event
    private ArrayList<String> monday;
    private ArrayList<String> tuesday;
    private ArrayList<String> wednesday;
    private ArrayList<String> thursday;
    private ArrayList<String> friday;
    private ArrayList<String> saturday;


    public void init()
    {
        sunday = new ArrayList<>();
        monday = new ArrayList<>();
        tuesday = new ArrayList<>();
        wednesday = new ArrayList<>();
        thursday = new ArrayList<>();
        friday = new ArrayList<>();
        saturday = new ArrayList<>();
        monday = new ArrayList<>();

        // Add events to each day
        monday.add("IT326 Sprint 2");
        monday.add("IT328 Presentation");
        wednesday.add("IT326 Assignment 4");
        friday.add("MAT175 Homework 6");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><table style ='width:100%' border ='1px solid black'>");
        out.print("<tr><th width=150> Sunday </th><th width=150> Monday </th><th width=150> Tuesday </th>");
        out.println("<th width=150> Wednesday </th><th width=150> Thursday </th><th width=150> Friday </th><th width=150> Saturday </th>");

        // Sunday Events
        out.print("</tr><tr height='600'><td width=150>");
        for (int i = 0; i < sunday.size(); i++)
        {
            out.println(sunday.get(i) + "<br>");
        }
        out.println("</td>");

        // Monday Events
        out.print("<td width=150>");
        for (int i = 0; i < monday.size(); i++)
        {
            out.println(monday.get(i) + "<br>");
        }
        out.println("</td>");

        // Tuesday Events
        out.print("<td width=150>");
        for (int i = 0; i < tuesday.size(); i++)
        {
            out.println(tuesday.get(i) + "<br>");
        }
        out.println("</td>");

        // Wednesday Events
        out.print("<td width=150>");
        for (int i = 0; i < wednesday.size(); i++)
        {
            out.println(wednesday.get(i) + "<br>");
        }
        out.println("</td>");

        // Thursday Events
        out.print("<td width=150>");
        for (int i = 0; i < thursday.size(); i++)
        {
            out.println(thursday.get(i) + "<br>");
        }
        out.println("</td>");

        // Friday Events
        out.print("<td width=150>");
        for (int i = 0; i < friday.size(); i++)
        {
            out.println(friday.get(i) + "<br>");
        }
        out.println("</td>");

        // Saturday Events
        out.print("<td width=150>");
        for (int i = 0; i < saturday.size(); i++)
        {
            out.println(saturday.get(i) + "<br>");
        }
        out.println("</td>");
        out.println("</tr><table></html>");
    }

    public void destroy()
    {
    }

}
