package com.cc.todo_app.todo_controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.todo_app.connection_factory.ConnectionFactory;
import com.cc.todo_app.todo.Todo;
import com.cc.todo_app.todo_manager.TodoManager;

/**
 * Handles requests for the todo pages.
 */
@Controller
public class TodoController {

	private TodoManager todoManager;

	/**
	 * Decides what view model attribute has to be set
	 */
	private void validateSelectedTodo(Todo selectedTodo, Model model) {
		if (selectedTodo != null) {
			model.addAttribute("todoId", selectedTodo.getTodoId());
			model.addAttribute("todoName", selectedTodo.getTodoName());
			model.addAttribute("todoDescription", selectedTodo.getTodoDescription());
		} else {
			model.addAttribute("err", "Selected todo not found!");
		}
	}

	/**
	 * Same as validateSelectedTodo but it sets the err attribute if there is an
	 * empty list as well
	 */
	private void validateTodoList(List<Todo> todoList, Model model) {
		if (todoList != null) {
			if (!todoList.isEmpty()) {
				model.addAttribute("todos", todoList);
			}
		} else {
			model.addAttribute("err", "No todos found!");
		}
	}

	/**
	 * Get a todo by id and return a JSP page
	 */
	@RequestMapping(value = "/todobyid", method = RequestMethod.GET)
	public String selectByIdReturnJSP(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedTodo = todoManager.selectTodoById(id);
		validateSelectedTodo(selectedTodo, model);
		return "todobyid";
	}

	/**
	 * Get a todo by id and return a JSON object
	 */
	@RequestMapping(value = "/todobyidJSON", method = RequestMethod.GET)
	public @ResponseBody Todo selectByIdReturnJSON(@RequestParam(value = "id", defaultValue = "1") int id,
			Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedTodo = todoManager.selectTodoById(id);
		return selectedTodo;
	}

	/**
	 * Get a todo by name and return a JSP page
	 */
	@RequestMapping(value = "/todobyname", method = RequestMethod.GET)
	public String selectByNameReturnJSP(@RequestParam(value = "name") String name, Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedTodo = todoManager.selectTodoByName(name);
		validateSelectedTodo(selectedTodo, model);
		return "todobyname";
	}

	/**
	 * Get a todo by name and return a JSON object
	 */
	@RequestMapping(value = "/todobynameJSON", method = RequestMethod.GET)
	public @ResponseBody Todo selectByNameReturnJSON(@RequestParam(value = "name") String name, Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedTodo = todoManager.selectTodoByName(name);
		return selectedTodo;
	}

	/**
	 * Get all todos and return a JSP page
	 */
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public String selectAllReturnJSP(Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		List<Todo> todoList = todoManager.selectAllTodo();
		validateTodoList(todoList, model);
		return "todos";
	}

	/**
	 * Get all todos and return a JSON object
	 */
	@RequestMapping(value = "/todosJSON", method = RequestMethod.GET)
	public @ResponseBody List<Todo> selectAllReturnJSON(Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		List<Todo> todoList = todoManager.selectAllTodo();
		return todoList;
	}

	/**
	 * Database modifier methods
	 */
	@RequestMapping(value = "/addnewtodo", method = RequestMethod.POST)
	public void addNewTodo(
			@RequestParam(value = "id") int id, 
			@RequestParam(value = "name") String name,
			@RequestParam(value = "description") String description) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo todo = new Todo(id, name, description);
		todoManager.addNewTodo(todo);
	}
	
	@RequestMapping(value = "/updatetodoname", method = RequestMethod.POST)
	public void modifyTodoSetNewName(
			@RequestParam(value = "id") int id, 
			@RequestParam(value = "name") String name) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		todoManager.modifyTodoSetNewName(id, name);
	}
	
	@RequestMapping(value = "/updatetododescription", method = RequestMethod.POST)
	public void modifyTodoSetNewDescription(
			@RequestParam(value = "id") int id, 
			@RequestParam(value = "description") String description) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		todoManager.modifyTodoSetNewDescription(id, description);
	}
	
	@RequestMapping(value = "/removetodo", method = RequestMethod.POST)
	public void removeTodo(
			@RequestParam(value = "id") int id) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		todoManager.removeTodo(id);
	}
	
}
