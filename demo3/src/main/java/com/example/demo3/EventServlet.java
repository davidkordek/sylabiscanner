package com.example.demo3;

import java.io.*;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "EventServlet", value = "/EventServlet")
public class EventServlet extends HttpServlet {
    private String message;
    private Event event;
    public void init() {
        message = "This is the Event Servlet!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        getServletContext().getRequestDispatcher("/event.jsp").forward(request,response);
        // Hello

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        getServletContext().getRequestDispatcher("/eventDisplay.jsp").forward(request,response);

        Parser parser = new Parser();
        String date = request.getParameter("eventDate") != null ?  request.getParameter("eventDate")  :  "00/00" ;
        String type =request.getParameter("eventType") != null ? request.getParameter("eventType") :"Type N/A" ;
        String description =request.getParameter("eventDescription") != null ? request.getParameter("eventDescription") : "Description N/A";

        String eventRemove = request.getParameter("eventDateRemove");

        if(eventRemove != null){
            try {
                parser.removeEvent(eventRemove);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            try {
                parser.insertNewEvent(date, type, description, false);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }




    }

    public void destroy() {
    }
}