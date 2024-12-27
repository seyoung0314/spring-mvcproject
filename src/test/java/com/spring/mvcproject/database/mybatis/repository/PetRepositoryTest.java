package com.spring.mvcproject.database.mybatis.repository;

import com.spring.mvcproject.board.entity.PostStatus;
import com.spring.mvcproject.database.mybatis.entity.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Test
    void savePet(){
        Pet pet = Pet.builder()
                .petName("햄")
                .petAge(1)
                .injection(false)
                .build();

        petRepository.save(pet);
    }

    @Test
    void findByIdTest(){
        Pet foundPet = petRepository.findById(1L);

        System.out.println("foundPet = " + foundPet);
    }

    @Test
    void findAllTest(){
        List<Pet> petList =  petRepository.findAll();
        System.out.println("petList = " + petList);
    }

    @Test
    void deleteTest(){
        boolean b = petRepository.deleteById(1L, PostStatus.DELETED);
        System.out.println("b = " + b +"===================");
    }

    @Test
    void countTest(){
        int count = petRepository.petCount();
        System.out.println("count = " + count+"--------------------------");
    }
}