package com.example.demo3;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class Calendar {
    private ArrayList<JSONObject> calendar = new ArrayList<JSONObject>();
    public void addEvent(Event event){
        calendar.add(event.getEvent());
    }

    public ArrayList<JSONObject> getCalendar() {
        return calendar;
    }

    public void printCalendar(HttpServletResponse response) throws IOException {
       java.io.PrintWriter out = response.getWriter();


        StringWriter sw = new StringWriter();
        for(JSONObject obj: calendar) {
            String jsonText = obj.toString();
            out.println(jsonText);
            out.println("</br>");
            out.println("</br>");

        }
    }
}
