package com.example.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    // http://localhost:8080/v1/person
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Benjamin Taylor");
    }

    // http://localhost:8080/v2/person
    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Benjamin", "Taylor"));
    }

    // http://localhost:8080/person?version=2
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter() {
        return new PersonV1("Benjamin Taylor");
    }

    // http://localhost:8080/person?version=2
    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParam() {
        return new PersonV2(new Name("Benjamin", "Taylor"));
    }

    // http://localhost:8080/person/header X-API-VERSION=1(header)
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader() {
        return new PersonV1("Benjamin Taylor");
    }

    // http://localhost:8080/person/header X-API-VERSION=2(header)
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {
        return new PersonV2(new Name("Benjamin", "Taylor"));
    }

    // http://localhost:8080/person/produces Accept=application/vnd.company.app-v1+json(header)
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Benjamin Taylor");
    }

    // http://localhost:8080/person/produces Accept=application/vnd.company.app-v2+json(header)
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Benjamin", "Taylor"));
    }
}

/*
 * Factors to consider when choosing a versioning strategy:
 *
 * URI Pollution - The URI is polluted with version information.
 * Headers - Misuse of HTTP Headers.
 * Caching - Caching becomes difficult.
 * Can we execute the request on the browser? - Some of the versioning strategies do not work on the browser.
 * API Documentation - API documentation becomes difficult to maintain.
 */
