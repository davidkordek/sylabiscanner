package com.example.demo3;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Parser parser = new Parser();

    @Test
    void showEntireCalendar() {
    }

    @Test
    //black box
    void findByDescription() {



        Event event = new Event("00/00", "type", "desc22ww");

        assertEquals((parser.findByDescription(event.getEvent(),"desc22ww")), true);

        Event event2 = new Event("00/00", "type", "descwe22ww");

        assertEquals((parser.findByDescription(event2.getEvent(),"desc22ww")), false);
    }

    @Test
    void parseFile() {
    }

    @Test
    void insertNewEvent() {
    }

    @Test
    void deleteOldJSON() {
    }

    @Test
    void printSingleEventObject() {
    }

    @Test
    //black box
    void findByDate() {


        Event event = new Event("05/03", "type", "desc22ww");

        assertEquals((parser.findByDate(event.getEvent(),"05/03")), true);

        Event event2 = new Event("02/03", "type", "descwe22ww");

        assertEquals((parser.findByDate(event2.getEvent(),"02/05")), false);
    }

    @Test
    void readJSON() {
        Event event = new Event("05/03", "type", "desc22ww");
        Event event2 = new Event("02/03", "type", "descwe22ww");
        Calendar cal = new Calendar();
        cal.addEvent(event);
        cal.addEvent(event2);
    }

    @Test
    //whitebox
    void sortCalendar() throws ParseException {
        Event event = new Event("05/03", "type", "desc22ww");
        Event event2 = new Event("02/03", "type", "descwe22ww");
        Calendar cal = new Calendar();
        cal.addEvent(event);
        cal.addEvent(event2);

        Calendar cal2Sorted = new Calendar();
        cal.addEvent(event);
        cal.addEvent(event2);


        assertNotEquals((parser.sortCalendar(cal)), cal2Sorted);

    }

    @Test
    //whitebox
    void stringToDate() throws ParseException {

        assertEquals((parser.stringToDate("03/08")).toString(), "Sun Mar 08 00:00:00 CST 1970");

        assertNotEquals((parser.stringToDate("02/05")).toString(), "Sun Mar 02 00:00:00 CST 1970");
    }

    @Test
    void removeEvent() {

    }
}