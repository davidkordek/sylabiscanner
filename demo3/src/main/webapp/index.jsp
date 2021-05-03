<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Syllabi Scanner</title>
</head>
<body>
<h1><%= "Syllabi Scanner" %>
</h1>
<br/>

<form action=/demo1_war_exploded/EventServlet><input type='submit' value='Add/Remove Events'/></form></br>
<form action=/demo1_war_exploded/CoursesServlet><input type='submit' value='Courses (redacted)'/></form></br>
<form action=/demo1_war_exploded/CalendarServlet><input type='submit' value='Weekly Calendar Display'/></form></br>
<form action=/demo1_war_exploded/MonthlyServlet><input type='submit' value='Monthly Calendar Display'/></form></br>
<form action=/demo1_war_exploded/SearchServlet><input type='submit' value='Search'/></form></br>
<form action=/demo1_war_exploded/EditServlet><input type='submit' value='Edit'/></form></br>

<%--<a href="EventServlet">Add/Remove Events</a><br/>--%>
<%--<a href="CoursesServlet">Courses (Redacted)</a><br/>--%>
<%--<a href="CalendarServlet">Calendar</a><br/>--%>
<%--<a href="SearchServlet">Search</a><br/>--%>
<%--<a href="EditServlet">Edit</a><br/>--%>

</br>
</br>
<h3>File Upload:</h3>
Select syllabus from root folder /syllabusHERE/syllabus.pdf: <br />
<form action = "UploadServlet" method = "post" enctype = "multipart/form-data">
    <input type = "file" name = "file" size = "250" />
    <br />
    <input type = "submit" value = "Upload File" />
</form>
<form action = "PDFServlet" method="post" enctype = "multipart/form-data">
    <input type="submit" disabled="disabled" value="Save Calendar as PDF"/>
</form>
</body>
</html>
