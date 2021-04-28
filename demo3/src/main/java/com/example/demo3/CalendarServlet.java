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
        java.io.PrintWriter out = response.getWriter();

        out.print("<br><a href=\"/demo1_war_exploded/\"><button type=\"button\">GO HOME</button></a>");
        out.println("</br>");
        out.println("</br>");
        out.println("CALENDAR: ");
        out.println("</br>");

        Parser parser = new Parser();
        parser.showEntireCalendar(response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
