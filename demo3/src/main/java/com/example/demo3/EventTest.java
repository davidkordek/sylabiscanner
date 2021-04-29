package com.example.demo3;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private Object NullPointerException;

    @Test
    //black box
    public void  getEvent() throws ParseException {
        Date convertedDate;
        Parser parser = new Parser();


        Event event = new Event("00/00", "type", "desc");

        assertNotNull(event.getEvent());

   }
}