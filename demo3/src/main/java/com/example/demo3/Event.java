package com.example.demo3;

import org.json.JSONObject;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Event {

    private JSONObject event;
    private String eventType, eventDesciprtion, eventDate;

    public Event(String eventDate, String eventType, String eventDescription) {
        this.event = new JSONObject();
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDesciprtion = eventDescription;

        createEvent();
    }

    public Event() {

    }

    public JSONObject getEvent(){

        return event;
    }
    public Date getDateTime() throws ParseException {
        Parser parser = new Parser();
        return parser.stringToDate(this.eventDate);
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
