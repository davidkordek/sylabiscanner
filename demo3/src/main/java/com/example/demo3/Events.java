package com.example.demo3;

import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;

public class Events {

    private JSONObject event;
    private String eventDate, eventType, eventDesciprtion;
    public Events(String eventDate, String eventType, String eventDescription) {
        this.event = new JSONObject();
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDesciprtion = eventDescription;
        createEvent();
    }

    public JSONObject getEvent(){

        return event;
    }
    private void createEvent()  {

        event.put("eventDate",eventDate);
        event.put("eventType",eventType);
        event.put("eventDescription",eventDesciprtion);
        StringWriter out = new StringWriter();


        String jsonText = out.toString();
        System.out.print(jsonText);
    }

}
