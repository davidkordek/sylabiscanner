package com.example.demo3;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MonthlyServlet", value = "/MonthlyServlet")
public class MonthlyServlet extends HttpServlet {


    public void init()
    {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><table style ='width:100%' border ='1px solid black'>");
        out.print("<tr><th width=150> Sunday </th><th width=150> Monday </th><th width=150> Tuesday </th>");
        out.println("<th width=150> Wednesday </th><th width=150> Thursday </th><th width=150> Friday </th><th width=150> Saturday </th>");

        out.println("<tr height=150> <td width=150></td> <td width=150> </td> <td width=150></td> <td width=150></td> <td width=150></td> <td width=150></td> <td width=150></td> </tr>");
        out.println("<tr height=150><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td></tr>");
        out.println("<tr height=150><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td></tr>");
        out.println("<tr height=150><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td></tr>");
        out.println("<tr height=150><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td><td width=150></td></tr>");
        out.println("<table></html>");
    }

    public void destroy()
    {
    }

}
