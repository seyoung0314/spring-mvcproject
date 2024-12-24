package com.spring.mvcproject.database.jdbc.repository;

import com.spring.mvcproject.database.jdbc.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void saveTest(){
        Person p = new Person(3L,"뽀로로",10);
        personRepository.save(p);
    }

    @Test
    void updateTest(){
        Person p = new Person(3L,"오로라핑",4);
        personRepository.update(p);
    }

    @Test
    void deleteTest(){
        Long id = 1L;
        personRepository.delete(id);
    }
}