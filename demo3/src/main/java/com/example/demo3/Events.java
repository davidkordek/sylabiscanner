package com.example.demo3;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;

public class Events {

    private JSONObject event = new JSONObject();
    private String eventDate, eventType, eventDesciprtion;
    public Events(String eventDate, String eventType, String eventDescription) throws IOException {
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDesciprtion = eventDescription;
        createEvent();
    }

    public JSONObject getEvent(){

        return event;
    }
    private void createEvent() throws IOException {

        event.put("eventDate",eventDate);
        event.put("eventType",eventType);
        event.put("eventDescription",eventDesciprtion);
        StringWriter out = new StringWriter();
        event.writeJSONString(out);

        String jsonText = out.toString();
        System.out.print(jsonText);
    }

}
