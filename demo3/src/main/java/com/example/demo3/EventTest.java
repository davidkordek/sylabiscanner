package com.example.demo3;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private Object NullPointerException;

    @Test
    public void  getEvent(){
        Event event = new Event("date", "type", "desc");

        assertNotNull(event.getEvent());

   }
}