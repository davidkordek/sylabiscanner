package com.example.demo3;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Calendar {
    private ArrayList<JSONObject> calendar = new ArrayList<JSONObject>();
    public void addEvent(Events event){
        calendar.add(event.getEvent());
    }
    public void printCalendar(HttpServletResponse response) throws IOException {
        java.io.PrintWriter out = response.getWriter();


        for(int i = 0; i < calendar.size(); i++) {

            String jsonText = calendar.get(i).toString();
            System.out.print(jsonText);
            out.println(jsonText);
            out.println("</br>");
        }      }
}
