<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Showing all todos</title>
</head>

<body>
	<h1>Todos:</h1>

	<br>
	<form action="home" method="GET">
		<input type="submit" value="Back to home" />
	</form>
	<br>
	
	<c:forEach items="${todos}" var="todo">
		<table border="1">
			<tr align="left">
				<th>Todo id: ${todo.todoId}</th>
			</tr>
			<tr align="left">
				<th>Todo name: ${todo.todoName}</th>
			</tr>
			<tr align="left">
				<th>Todo description: ${todo.todoDescription}</th>
			</tr>
		</table>
		<br>
	</c:forEach>
	${err}

	<form action="home" method="GET">
		<input type="submit" value="Back to home" />
	</form>
</body>
</html>
