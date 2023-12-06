package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

/**
 * Class PersonService - Сервис работы с персонами. Решение задач уровня Middle.
 * Категория : 3.5. Spring / 3.5.6. Rest
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 15.08.2023
 * @version 1
 */

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    private static final Logger LOG = LogManager.getLogger(PersonService.class.getName());

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> create(Person person) {
        try {
            return Optional.of(personRepository.save(person));
        } catch (Exception e) {
            LOG.error("PersonService.create", e);
        }
        return Optional.empty();
    }

    public boolean delete(Person person) {
        if (personRepository.findById(person.getId()).isPresent()) {
            personRepository.delete(person);
            return true;
        }
        return false;
    }

    public boolean update(Person person) {
        if (personRepository.findById(person.getId()).isPresent()) {
            personRepository.save(person);
            return true;
        }
        return false;
    }
}