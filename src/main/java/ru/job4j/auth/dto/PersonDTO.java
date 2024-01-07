package ru.job4j.auth.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PersonDTO {
    @NotNull(message = "Fill the password")
    @Size(min = 6, max = 64, message = "Password should be between 6 and 64 characters")
    private String password;
}