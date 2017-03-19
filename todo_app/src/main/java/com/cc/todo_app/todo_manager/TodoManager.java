package com.cc.todo_app.todo_manager;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.cc.todo_app.todo.Todo;
import com.cc.todo_app.todo_mapper.TodoMapper;


public class TodoManager {

	private SqlSession session;
	private TodoMapper todoMapper;

	public TodoManager(SqlSession sqlSession) {
		session = sqlSession;
		todoMapper = sqlSession.getMapper(TodoMapper.class);
	}

	public Todo selectTodoById(int id) {
		try {
			return todoMapper.selectTodoById(id);
		} finally {
			session.close();
		}
	}

	public Todo selectTodoByName(String name) {
		try {
			return todoMapper.selectTodoByName(name);
		} finally {
			session.close();
		}
	}

	public ArrayList<Todo> selectAllTodo() {
		try {
			return todoMapper.selectAllTodo();
		} finally {
			session.close();
		}
	}
	
	public void addNewTodo(Todo todo) {
		try {
			todoMapper.addNewTodo(todo);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public void modifyTodoSetNewName(int id, String name) {
		try {
			todoMapper.modifyTodoSetNewName(id, name);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public void modifyTodoSetNewDescription(int id, String description) {
		try {
			todoMapper.modifyTodoSetNewDescription(id, description);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public void removeTodo(int id) {
		try {
			todoMapper.removeTodo(id);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public Todo selectDoneById(int id) {
		try {
			return todoMapper.selectDoneById(id);
		} finally {
			session.close();
		}
	}
	
	public ArrayList<Todo> selectAllDone() {
		try {
			return todoMapper.selectAllDone();
		} finally {
			session.close();
		}
	}
	
	public void addToDone(Todo todo) {
		try {
			todoMapper.addToDone(todo);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public void removeFromDone(int id) {
		try {
			todoMapper.removeFromDone(id);
			session.commit();
		} finally {
			session.close();
		}
	}
	
}
