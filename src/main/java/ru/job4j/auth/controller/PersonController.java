package ru.job4j.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.service.PersonService;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Class PersonController - Контроллер работы с персонами. Решение задач уровня Middle.
 * Категория : 3.5. Spring / 3.5.6. Rest
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 15.08.2023
 * @version 1
 */

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService persons;
    private final PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> findAll() {
        var personList = this.persons.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .header("findAllHeaderName", "job4j_auth")
                .contentType(MediaType.APPLICATION_JSON)
                .body(personList);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable int id) {
        return this.persons.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Person not found."
        ));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        if (person.getLogin() == null || person.getPassword() == null) {
            throw new NullPointerException("Login and password mustn't be empty");
        }
        if (person.getPassword().length() < 6) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Optional<Person> personCreated = this.persons.create(person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personCreated.get());
    }

    @PutMapping("/")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        if (person.getLogin() == null || person.getPassword() == null) {
            throw new NullPointerException("Login and password mustn't be empty");
        }
        return ResponseEntity
                .status(persons.update(person) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        return new ResponseEntity<>(persons.delete(person) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public void exceptionHandler(Exception exception, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "User can't be created, username is exists...");
            put("type", exception.getClass());
        }}));
        log.error(exception.getLocalizedMessage(), exception);
    }
}