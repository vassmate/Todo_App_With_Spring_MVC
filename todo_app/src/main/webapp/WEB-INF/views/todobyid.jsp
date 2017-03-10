<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selected todo by id</title>
</head>
<body>
	<h1>Todo by id:</h1>
	<table border="1">
		<tr align="left">
			<th>Todo id: ${todoId}</th>
		</tr>
		<tr align="left">
			<th>Todo name: ${todoName}</th>
		</tr>
		<tr align="left">
			<th>Todo description: ${todoDescription}</th>
		</tr>
	</table>
	${err}

	<br>
	<form action="home" method="GET">
		<input type="submit" value="Back to home" />
	</form>
</body>
</html>