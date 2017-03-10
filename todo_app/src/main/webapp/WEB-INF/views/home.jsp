<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Todo app home page</title>
</head>
<body>
	<h1>Hello user!</h1>
	<h2>Find yourself a todo!</h2>
	<p>The time on the server is ${serverTime}.</p>
	
	<br>
	<h3>Select a todo by name</h3>
	<form action="todobyname" method="GET">
		Todo name:<input type="text" name="name">
		<br>
		<br>
		<input type="submit"value="Show" />
	</form>
	
	<br>
	<h3>Select a todo by id</h3>
	<form action="todobyid" method="GET">	
		Todo id:<input type="number" name="id" value="1"/>
		<br>
		<br>
		<input type="submit"value="Show" />
	</form>	
	
	<br>
	<h3>Select all todos</h3>
	<form action="todos" method="GET">	
		<input type="submit"value="Show all" />
	</form>
	
	<br>
	<h2>Add or modify a todo!</h2>
	
	<br>
	<h3>Add new todo</h3>
	<form action="addnewtodo" method="POST">	
		Todo id:<input type="number" name="id"/>
		<br>
		Todo name:<input type="text" name="name"/>
		<br>
		Todo description:<input type="text" name="description"/>
		<br>
		<br>
		<input type="submit"value="Add" />
	</form>
	
	<br>
	<h3>Modify a todo's name</h3>
	<form action="updatetodoname" method="POST">	
		Todo id:<input type="number" name="id"/>
		<br>
		Todo name:<input type="text" name="name"/>
		<br>
		<br>
		<input type="submit"value="Update" />
	</form>
	
	<br>
	<h3>Modify a todo's description</h3>
	<form action="updatetododescription" method="POST">	
		Todo id:<input type="number" name="id"/>
		<br>
		Todo description:<input type="text" name="description"/>
		<br>
		<br>
		<input type="submit"value="Update" />
	</form>
	
	<br>
	<h3>Remove a todo</h3>
	<form action="removetodo" method="POST">	
		Todo id:<input type="number" name="id"/>
		<br>
		<br>
		<input type="submit"value="Remove" />
	</form>
</body>
</html>
