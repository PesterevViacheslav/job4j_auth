package ru.job4j.auth.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Create Person Error")
public class PersonCreateException extends RuntimeException {
}