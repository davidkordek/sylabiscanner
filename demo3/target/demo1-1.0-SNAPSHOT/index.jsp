<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="EventServlet">Add/Remove Events</a><br/>
<a href="CoursesServlet">Courses (Redacted)</a><br/>
<a href="CalendarServlet">Calendar</a><br/>
<a href="SearchServlet">Search</a><br/>
<a href="EditServlet">Edit</a><br/>

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
    <input type="submit" value="Save Calendar as PDF"/>
</form>
</body>
</html>