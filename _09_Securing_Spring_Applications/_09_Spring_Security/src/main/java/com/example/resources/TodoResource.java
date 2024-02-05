package com.example.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

record Todo(String username, String description) {
}

@RestController
public class TodoResource {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final List<Todo> TODOS = List.of(
            new Todo("user", "Learn Spring Boot"),
            new Todo("user", "Learn Docker"),
            new Todo("admin", "Learn Kubernetes"));

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return TODOS;
    }

    @GetMapping("/todos/{user}")
    @PreAuthorize("hasRole('USER') and #user == authentication.name")
    @PostAuthorize("returnObject.isEmpty() == false")
    @RolesAllowed({ "ADMIN", "USER" })
    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    public List<Todo> getAllTodosForUser(@PathVariable String user) {
        return TODOS.stream().filter(todo -> todo.username().equals(user)).toList();
    }

    // Remember to update or post the request we need a CSRF token
    @PostMapping("/todos/{user}")
    public void addTodo(@PathVariable String user, @RequestBody Todo todo) {
        logger.info("Adding todo for user: " + user + " with description: " + todo.description());
    }
}
