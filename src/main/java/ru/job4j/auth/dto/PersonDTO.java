package ru.job4j.auth.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PersonDTO {
    @NotNull(message = "Fill the password")
    @Min(value = 6, message = "Password should be more than 6 characters")
    private String password;
}