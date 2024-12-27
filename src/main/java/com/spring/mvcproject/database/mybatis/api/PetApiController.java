package com.spring.mvcproject.database.mybatis.api;


import com.spring.mvcproject.board.entity.PostStatus;
import com.spring.mvcproject.database.mybatis.dto.response.PetListResponse;
import com.spring.mvcproject.database.mybatis.dto.response.PetResponse;
import com.spring.mvcproject.database.mybatis.entity.Pet;
import com.spring.mvcproject.database.mybatis.service.PetService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pet")
public class PetApiController {

    private final PetService petService;

    //목록조회
    @GetMapping
    public ResponseEntity<?> findAll() {
        PetListResponse list = petService.getList();
        return ResponseEntity.ok().body(list);
    }

    //개별조회
    @GetMapping("/{id}")
    public ResponseEntity<?> fineOne(
            @PathVariable Long id
    ) {
        Pet pet = petService.getPet(id);

        return ResponseEntity.ok().body(pet);
    }
    //추가
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody Pet pet
    ) {
        boolean posted = petService.createPet(pet);
        return ResponseEntity.ok().body(posted);
    }

    //수정
    @PutMapping
    public ResponseEntity<?> update(
            Long id
            , @RequestBody Pet pet
    ) {
        boolean updated = petService.updatePet(id, pet);
        return ResponseEntity.ok().body(updated);
    }

    //삭제
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        boolean deleted = petService.deletePet(id);
        return ResponseEntity.ok().body(deleted);
    }

    //카운터
    @GetMapping("/count")
    public ResponseEntity<?> update() {
        int countPet = petService.countPet();
        return ResponseEntity.ok().body(countPet);
    }
}
