package com.example._05_Todo_App.todo;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

// @Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public String getAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername(getLoggedInUserName(model));
        model.put("todos", todos);
        return "listTodos";
    }

    @GetMapping("/add-todo")
    public String showTodoPage(ModelMap model) {
        Todo todo = new Todo();
        todo.setUsername(getLoggedInUserName(model));
        todo.setTargetDate(LocalDate.now().plusYears(1));
        model.put("todo", todo);
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        todoService.addTodo(getLoggedInUserName(model), todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:/todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }

    @GetMapping("/update-todo")
    public String updateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("/update-todo")
    public String updateTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        todo.setUsername((String) model.get("name"));
        todoService.updateTodo(todo);
        return "redirect:/todos";
    }

    private String getLoggedInUserName(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
