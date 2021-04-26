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
<a href="EventServlet">Event Servlet</a><br/>
<a href="CoursesServlet">Courses Servlet</a><br/>
<a href="CalendarServlet">Calendar Servlet</a><br/>
</br>
</br>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action = "UploadServlet" method = "post" enctype = "multipart/form-data">
    <input type = "file" name = "file" size = "250" />
    <br />
    <input type = "submit" value = "Upload File" />
</form>

</body>
</html>