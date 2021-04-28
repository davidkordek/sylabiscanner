
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Event</title>
</head>
<body>
<form action="EditServlet" method="post">
    Edit Event (Please click submit event if you wish to not make any change, DO NOT PRESS BACK BUTTON) <br/>
    Date:<input type="text" name="eventDate" value="${requestScope.get("eventDate")}"/><br/><br/>
    Event Type:<input type="text" name="eventType" value="${requestScope.get("eventType")}"/><br/><br/>
    Description:<input type="text" name="eventDescription" value="${requestScope.get("eventDescription")}"/><br/><br/>


    <br/><br/>
    <input type="submit" value="submit event"/>

</form>



</body>
</html>
