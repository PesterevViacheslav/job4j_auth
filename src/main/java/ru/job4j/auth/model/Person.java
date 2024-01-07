package ru.job4j.auth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class Person - Персона. Решение задач уровня Middle.
 * Категория : 3.5. Spring / 3.5.6. Rest
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 29.11.2023
 * @version 1
 */
@Entity
@Table(name = "person")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @NotNull(message = "Fill the login")
    @Size(min = 6, max = 20, message = "Login should be between 6 and 20 characters")
    private String login;
    @NotNull(message = "Fill the password")
    @Size(min = 6, max = 64, message = "Password should be between 6 and 64 characters.")
    private String password;
}