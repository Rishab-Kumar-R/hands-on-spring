package com.example.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * This is a simple REST controller that returns a string when a GET request is made to /hello.
 * 
 * **Exploring Basic Authentication** (Not recommended for production use)
 * It uses Base 64 encoding to encode the username and password sent as a header in the request.
 * E.g., Authorization: Basic dXNlffsd784hjsd==
 * 
 * How to send a request with Basic Authentication in Postman:
 * 1. Click on Authorization tab
 * 2. Select Basic Auth from the dropdown
 * 3. Enter the username and password
 * 4. Click on Update Request
 * 5. Send the request
 * 
 * Flaws of Basic Authentication:
 * 1. It is not secure as the username and password are sent in the request header
 * 2. It is not recommended for production use
 * 3. Easy to decode the Base 64 encoded string
 * 4. It is not secure for sending sensitive information
 * 
 * **CSRF (Cross Site Request Forgery)**
 * It is a type of attack where a malicious website, email, or program causes a user to perform an unwanted action on a trusted site where the user is authenticated.
 * 
 * **Preventing CSRF**
 * 1. Synchronize Token Pattern (Create a token and send it with the request, and if you want to update the data, you need to send the token from the previous request)
 * 2. Double Submit Cookies (Send a cookie with the request and a hidden field with the same value)
 * 3. Custom Header (Send a custom header with the request)
 * 4. SameSite Cookie Attribute (Prevents the browser from sending the cookie along with cross-site requests)
 */

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
