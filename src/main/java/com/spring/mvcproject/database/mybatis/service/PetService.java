package com.spring.mvcproject.database.mybatis.service;


import com.spring.mvcproject.board.entity.PostStatus;
import com.spring.mvcproject.database.mybatis.dto.request.PetSaveRequest;
import com.spring.mvcproject.database.mybatis.dto.response.PetDetailResponse;
import com.spring.mvcproject.database.mybatis.dto.response.PetListResponse;
import com.spring.mvcproject.database.mybatis.dto.response.PetResponse;
import com.spring.mvcproject.database.mybatis.entity.Pet;
import com.spring.mvcproject.database.mybatis.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 클라이언트의 요청과 응답 사이를 중간처리
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    //목록조회
    public PetListResponse getList() {
        List<Pet> petList = petRepository.findAll();

//        List<PetResponse> responseList = new ArrayList<>();
//
//        for (Pet pet : petList) {
//            responseList.add(PetResponse.from(pet));
//        }

        List<PetResponse> responseList = petList.stream()
                .map(PetResponse::from)
                .collect(Collectors.toList());

        PetListResponse petListResponse = new PetListResponse(petRepository.petCount(),responseList);

        return petListResponse;
    }

    //개별조회
    public PetDetailResponse getPet(Long id) {
        Pet pet = petRepository.findById(id);

        return PetDetailResponse.from(pet);
    }

    //추가
    public boolean createPet(PetSaveRequest pet) {
        boolean savedPet = petRepository.save(pet.toEntity());
        return savedPet;
    }

    //수정
    public boolean updatePet(Long id, Pet pet) {
        boolean savedPet = petRepository.updatePet(pet);
        return savedPet;
    }

    //삭제
    public boolean deletePet(Long id) {
        boolean deletedPet = petRepository.deleteById(id, PostStatus.DELETED);
        return deletedPet;
    }

    //카운터
    public int countPet() {
        int count = petRepository.petCount();
        return count;
    }
}
