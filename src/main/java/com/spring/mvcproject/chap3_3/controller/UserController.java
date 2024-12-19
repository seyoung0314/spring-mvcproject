package com.spring.mvcproject.chap3_3.controller;

import com.spring.mvcproject.chap3_3.entity.User;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v3-3/users")
public class UserController {
    private Map<Long, User> userStore = new HashMap<>();
    private long nextId = 1;

    //사용자 생성
    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody User user
    ) {
        //검증
        if (user.getAge() < 0) {
            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
                    .badRequest()    // 몇몇개는 이런 모양으로 되있음
                    .header("cause","ddd")
                    .body("나이는 양수여야 합니다 - " + user.getAge());

        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("dd","aa");
        headers.add("ddaa","aa22");

        if (user.getName().isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .headers(headers)
                    .body("이름는 필수여야 합니다 - ");
        }

        user.setId(nextId++);

        System.out.println("user = " + user);

        userStore.put(user.getId(), user);
        return  ResponseEntity
//                .status(HttpStatus.OK)
                .ok()
                .body("유저정보가 생성되었습니다 - " + user);
    }

}
