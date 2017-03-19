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
	 * Helper methods for the todo controllers. These are only used with the
	 * methods which are returning a .jsp page.
	 */

	/*
	 * Decides which view model attribute(s) has to be set.
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

	/*
	 * Same as validateSelectedTodo but it sets the "err" attribute if there is
	 * an empty list as well.
	 */
	private void validateTodoList(List<Todo> todoList, Model model) {
		if (todoList != null) {
			if (!todoList.isEmpty()) {
				model.addAttribute("todos", todoList);
			} else {
				model.addAttribute("err", "No todos found!");
			}
		} else {
			model.addAttribute("err", "No todos found!");
		}
	}

	/**
	 * Controllers for todos. These methods are using the "todo" table.
	 */

	/*
	 * Get a todo by id and return a .jsp page.
	 */
	@RequestMapping(value = "/todobyid", method = RequestMethod.GET)
	public String selectTodoByIdReturnJspView(@RequestParam(value = "id", defaultValue = "1") int id, Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedTodo = todoManager.selectTodoById(id);
		validateSelectedTodo(selectedTodo, model);
		return "todobyid";
	}

	/*
	 * Get a todo by id and return a JSON view.
	 */
	@RequestMapping(value = "/todobyidasjson", method = RequestMethod.GET)
	public @ResponseBody Todo selectTodoByIdReturnJsonView(@RequestParam(value = "id", defaultValue = "1") int id) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedTodo = todoManager.selectTodoById(id);
		return selectedTodo;
	}

	/*
	 * Get a todo by name and return a .jsp page.
	 */
	@RequestMapping(value = "/todobyname", method = RequestMethod.GET)
	public String selectTodoByNameReturnJspView(@RequestParam(value = "name") String name, Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedTodo = todoManager.selectTodoByName(name);
		validateSelectedTodo(selectedTodo, model);
		return "todobyname";
	}

	/*
	 * Get a todo by name and return a JSON view.
	 */
	@RequestMapping(value = "/todobynameasjson", method = RequestMethod.GET)
	public @ResponseBody Todo selectTodoByNameReturnJsonView(@RequestParam(value = "name") String name) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedTodo = todoManager.selectTodoByName(name);
		return selectedTodo;
	}

	/*
	 * Get all todos and return a .jsp page.
	 */
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public String selectAllTodoReturnJspView(Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		List<Todo> todoList = todoManager.selectAllTodo();
		validateTodoList(todoList, model);
		return "todos";
	}

	/*
	 * Get all todos and return a JSON view.
	 */
	@RequestMapping(value = "/todosasjson", method = RequestMethod.GET)
	public @ResponseBody List<Todo> selectAllTodoReturnJsonView() {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		List<Todo> todoList = todoManager.selectAllTodo();
		return todoList;
	}

	/*
	 * Add new todo to the database.
	 */
	@RequestMapping(value = "/addnewtodo", method = RequestMethod.POST)
	public void addNewTodo(@RequestParam(value = "name") String name,
			@RequestParam(value = "description") String description) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo todo = new Todo(name, description);
		todoManager.addNewTodo(todo);
	}

	/*
	 * Add an existing todo to the todo table.
	 */
	@RequestMapping(value = "/addtotodo", method = RequestMethod.POST)
	public void addTodoFromDone(@RequestParam(value = "id") int id) {
		TodoManager selectManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		TodoManager addDoneManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		TodoManager removeManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());

		Todo doneTodo = selectManager.selectDoneById(id);
		addDoneManager.addNewTodo(doneTodo);
		removeManager.removeFromDone(id);
	}

	/*
	 * Set a new name for an existing todo.
	 */
	@RequestMapping(value = "/updatetodoname", method = RequestMethod.POST)
	public void setNewTodoName(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		todoManager.modifyTodoSetNewName(id, name);
	}

	/*
	 * Set a new description for an existing todo.
	 */
	@RequestMapping(value = "/updatetododescription", method = RequestMethod.POST)
	public void setNewTodoDescription(@RequestParam(value = "id") int id,
			@RequestParam(value = "description") String description) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		todoManager.modifyTodoSetNewDescription(id, description);
	}

	/*
	 * Remove a todo from the database.
	 */
	@RequestMapping(value = "/removetodo", method = RequestMethod.POST)
	public void removeFromTodo(@RequestParam(value = "id") int id) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		todoManager.removeTodo(id);
	}

	/**
	 * Controllers for the done todos. These methods are using the "done" table.
	 */

	/*
	 * Select a done todo by it's id.
	 */
	@RequestMapping(value = "/donebyidasjson", method = RequestMethod.GET)
	public @ResponseBody Todo selectDoneByIdReturnJsonView(@RequestParam(value = "id", defaultValue = "1") int id,
			Model model) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		Todo selectedDoneTodo = todoManager.selectDoneById(id);
		return selectedDoneTodo;
	}

	/*
	 * Select all done todos.
	 */
	@RequestMapping(value = "/donetodosasjson", method = RequestMethod.GET)
	public @ResponseBody List<Todo> selectAllDoneReturnJsonView() {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		List<Todo> doneTodoList = todoManager.selectAllDone();
		return doneTodoList;
	}

	/*
	 * Add an existing todo to the done table.
	 */
	@RequestMapping(value = "/addtodone", method = RequestMethod.POST)
	public void addTodoToDone(@RequestParam(value = "id") int id) {
		TodoManager selectManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		TodoManager addDoneManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		TodoManager removeManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());

		Todo doneTodo = selectManager.selectTodoById(id);
		addDoneManager.addToDone(doneTodo);
		removeManager.removeTodo(id);
	}

	/*
	 * Remove the done todo from the database.
	 */
	@RequestMapping(value = "/removefromdone", method = RequestMethod.POST)
	public void removeFromDone(@RequestParam(value = "id") int id) {
		todoManager = new TodoManager(ConnectionFactory.getSqlSessionFactory().openSession());
		todoManager.removeFromDone(id);
	}
}
