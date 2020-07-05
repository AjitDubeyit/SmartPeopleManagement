<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>People management application</title>
</head>
<body>
	<form action="search" method="post">
	<center>
		<h1>People Management</h1>
        <h2>
        	<a href="new">Add New Person</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List of Person</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of People</h2></caption>
            <caption>serarch by name: <input type="text" id="name" name="name" size="25">  </caption>
           <caption> email : <input type="text" id="email" name="email" size="25"></caption>
           <caption> <input type="submit" value="Search"></caption>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email address</th>
                
            </tr>
            <c:forEach var="person" items="${listPeople}">
                <tr>
                    <td><c:out value="${person.id}" /></td>
                    <td><c:out value="${person.firstName}" /></td>
                    <td><c:out value="${person.lastName}" /></td>
                    <td><c:out value="${person.emailId}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${person.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${person.id}' />" onclick="myFunction()">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <script>
		function myFunction() {
		  confirm("Press a button!");
		}
	</script>	
	</form>
</body>
</html>
