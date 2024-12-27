package com.spring.mvcproject.database.mybatis.repository;

import com.spring.mvcproject.board.entity.PostStatus;
import com.spring.mvcproject.database.mybatis.entity.Pet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//CRUD 정의
@Mapper  // mybatis는 인터페이스를 직접 구현하지 않고 xml을 매핑해서 자동으로 만들어줌
public interface PetRepository {

    //create
    boolean save(Pet pet);

    // read - single
    Pet findById(Long id);
    // read - multi
    List<Pet> findAll();

    // update
    boolean updatePet(Pet pet);

    //delete
    // enum 타입은 파라미터로 지정해서 사용하고
    // mybatis에서 자동으로  string으로 변경해줌
    boolean deleteById(Long id, @Param("status")PostStatus status);

    // read - count
    int petCount();
}
