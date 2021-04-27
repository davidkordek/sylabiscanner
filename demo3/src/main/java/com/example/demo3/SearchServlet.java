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
import java.io.PrintWriter;

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


        String date =request.getParameter("eventDate");
        System.out.println("out" + date);

        Parser parser1 = new Parser();
        try (FileReader reader = new FileReader("c:\\Program Files\\apache-tomcat-9.0.45\\webapps\\data/filedata1.json"))
        {
            //Read JSON file
            Object json = new JSONTokener(reader).nextValue();
            System.out.println(json.toString());



            JSONArray eventList = (JSONArray) json;
            System.out.println(eventList);

            //Iterate over employee array
            eventList.forEach( event -> {

                boolean isMatch = parser1.isEventObject( ((JSONObject) event ),  date );
                if(isMatch){
                    try {
                        parser1.printSingleEventObject((JSONObject) event, response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
