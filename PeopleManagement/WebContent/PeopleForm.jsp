<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>People Management Application</title>
</head>
<body>
	<center>
		<h1>People Management</h1>
        <h2>
        	<a href="new">Add New Person</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Person</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${people != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${people == null}">
			<form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${people != null}">
            			Edit People
            		</c:if>
            		<c:if test="${people == null}">
            			Add New People
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${people != null}">
        			<input type="hidden" name="id" value="<c:out value='${people.id}' />" />
        		</c:if>            
            <tr>
                <th>First Name: </th>
                <td>
                	<input type="text" name="firstName" size="45"
                			value="<c:out value='${people.firstName}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                	<input type="text" name="lastName" size="45"
                			value="<c:out value='${people.lastName}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Email Id: </th>
                <td>
                	<input type="text" name="emailId" size="45"
                			value="<c:out value='${people.emailId}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
