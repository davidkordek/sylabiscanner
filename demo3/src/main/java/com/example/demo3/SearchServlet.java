package com.example.demo3;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        getServletContext().getRequestDispatcher("/search.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();



        out.println("Results: ");

        out.println("</br>");
        out.println("</br>");


        Parser parser = new Parser();
        Calendar cal = null;
        try {
            cal = parser.readJSON(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String date = request.getParameter("eventDate");

        String description =request.getParameter("eventDescription");

        boolean found = false;
        boolean hasFoundSomething = false;
        if(description != null){
            for(JSONObject obj: cal.getCalendar()){
                found = false;
                 found = parser.findByDescription(obj, description);
                if(found){
                    hasFoundSomething = true;
                    parser.printSingleEventObject(obj, response);
                }
            }

        }else{
            for(JSONObject obj: cal.getCalendar()){
                found = false;
               found = parser.findByDate(obj, date);
                if(found){
                    hasFoundSomething = true;
                    parser.printSingleEventObject(obj, response);
                    break;
                }
            }

        }
        if(!hasFoundSomething){
            getServletContext().getRequestDispatcher("/notFound.jsp").forward(request,response);

        }else{
            out.print("<br><a href=\"/demo1_war_exploded/\"><button type=\"button\">GO HOME</button></a>");
            out.println("</br>");
            out.print("<br><a href=\"/demo1_war_exploded/SearchServlet\"><button type=\"button\">GO SEARCH</button></a>");
            out.println("</br>");
        }



    }
}
