package com.example.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "user", "Get AWS Certified",
                LocalDate.now().plusYears(10), false));
        todos.add(new Todo(++todosCount, "user", "Learn DevOps",
                LocalDate.now().plusYears(11), false));
        todos.add(new Todo(++todosCount, "user", "Learn Full Stack Development",
                LocalDate.now().plusYears(12), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos.stream()
                .filter(todo -> todo.getUsername()
                        .equalsIgnoreCase(username))
                .toList();
    }

    public Todo addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, isDone);
        todos.add(todo);
        return todo;
    }

    public void deleteTodoById(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

    public Todo findById(int id) {
        return todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .get();
    }

    public void updateTodo(Todo todo) {
        deleteTodoById(todo.getId());
        todos.add(todo);
    }
}
