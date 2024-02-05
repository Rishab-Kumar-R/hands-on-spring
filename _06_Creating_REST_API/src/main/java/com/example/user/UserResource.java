package com.example.user;

import com.example.exception.Response;

import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class UserResource {
    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    /*
     * HATEOAS (Hypermedia as the Engine of Application State) is a constraint of
     * the REST application architecture that keeps the RESTful style architecture
     * unique from most others network application architectures.
     *
     * The term “hypermedia” refers to any content that contains links to other
     * forms of media such as images, movies, and text.
     *
     * How to add HATEOAS to your RESTful Web Service?
     *
     * First, add the following dependency to your pom.xml file:
     * Then make wrap your return object with EntityModel
     * And to add links to your return object, we can make use of WebMvcLinkBuilder class.
     */

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) {
        User user = service.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkToUsers.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.ok(new Response(LocalDateTime.now(), "User deleted", "User with id " + id + " deleted"));
    }
}
