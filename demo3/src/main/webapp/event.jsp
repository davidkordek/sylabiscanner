
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Event</title>
</head>
<body>
<form action="EventServlet" method="post">
    Add Event <br/>
    Date:<input type="text" name="eventDate"/><br/><br/>
    Event Type:<input type="text" name="eventType"/><br/><br/>
    Description:<input type="text" name="eventDescription"/><br/><br/>


    <br/><br/>
    <input type="submit" value="create event"/>

</form>


<form action="EventServlet" method="post">
    Remove Event <br/>
    Date:<input type="text" name="eventDateRemove"/><br/><br/>

    <br/><br/>
    <input type="submit" value="remove event"/>

</form>

<form action=/demo1_war_exploded/>

    <input type="submit" value="GO HOME"/>
</form>

</body>
</html>
