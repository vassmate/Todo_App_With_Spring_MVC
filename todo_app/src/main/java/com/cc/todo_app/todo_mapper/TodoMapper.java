package com.cc.todo_app.todo_mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.cc.todo_app.todo.Todo;

public interface TodoMapper {
	
	public Todo selectTodoById(int id);
	
	public Todo selectTodoByName(String name);
	
	public ArrayList<Todo> selectAllTodo();
	
	public void addNewTodo(@Param("todo") Todo todo);
	
	public void modifyTodoSetNewName(@Param("id") int id, @Param("name") String name);

	public void modifyTodoSetNewDescription(@Param("id") int id, @Param("description") String description);
	
	public void removeTodo(@Param("id") int id);
	
	public Todo selectDoneById(int id);
	
	public ArrayList<Todo> selectAllDone();
	
	public void addToDone(@Param("todo") Todo todo);

	public void removeFromDone(@Param("id") int id);

}
