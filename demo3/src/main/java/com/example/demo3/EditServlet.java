package com.example.demo3;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "EditServlet", value = "/EditServlet")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        getServletContext().getRequestDispatcher("/searchEditDate.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
            //already searched, show the form to edit


        if(request.getParameter("eventDescription") !=null){
            Parser parser = new Parser();

            try {
                parser.insertNewEvent(request.getParameter("eventDate"), request.getParameter("eventType"), request.getParameter("eventDescription"), false);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/eventEditedSuccess.jsp").forward(request,response);

        }
        //need to find
        else{
            Parser parser = new Parser();
            String date = request.getParameter("eventDate");


            JSONObject obj1 = null;
            boolean found = false;

            Calendar cal = null;
            try {
                cal = parser.readJSON(false);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for(JSONObject obj: cal.getCalendar()){
                found = false;
                found = parser.findByDate(obj, date);
                if(found){

                    obj1 = obj;
                    break;
                }
            }


            if(obj1 !=null){
                String date1 = obj1.get("eventDate") != null ? (String) obj1.get("eventDate") :  "NOT FOUND" ;
                String type1 =obj1.get("eventType") != null ? (String) obj1.get("eventType") :"NOT FOUND" ;
                String description1 =obj1.get("eventDescription") != null ? (String) obj1.get("eventDescription") : "NOT FOUND";

                try {
                    parser.removeEvent((String) obj1.get("eventDate"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                java.io.PrintWriter out = response.getWriter();
                request.setAttribute("eventDate", date1);
                request.setAttribute("eventType", type1);
                request.setAttribute("eventDescription", description1);
                getServletContext().getRequestDispatcher("/editEvent.jsp").forward(request,response);
            }else{
                getServletContext().getRequestDispatcher("/notFound.jsp").forward(request,response);

            }


        }



    }



}
