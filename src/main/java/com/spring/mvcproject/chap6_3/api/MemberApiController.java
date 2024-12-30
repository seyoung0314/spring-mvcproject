package com.spring.mvcproject.chap6_3.api;

import com.spring.mvcproject.chap6_3.entity.Member;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v6/members")
public class MemberApiController {
    private Map<String, Member> memberStore = new HashMap<>();

    public MemberApiController() {
        Member mem1 = Member.builder()
                .account("pika11")
                .password("1234")
                .nickname("피카츄")
                .build();

        Member mem2 = Member.builder()
                .account("hello")
                .password("1234")
                .nickname("안녕")
                .build();

        memberStore.put(mem1.getAccount(), mem1);
        memberStore.put(mem2.getAccount(), mem2);
    }

    // 회원 단일 조회
    @GetMapping("/{account}")
    public ResponseEntity<?> findOne(
            @PathVariable String account
    ) {
        Member member = memberStore.get(account);

        if (account.length() > 10) {
            throw new IllegalStateException("10ㅡㅡ");
        }
        if (account.equals("admin")) {
            throw new HttpServerErrorException(HttpStatusCode.valueOf(500));
        }
        if (member == null) {
            return ResponseEntity
                    .badRequest()
                    .body("ㅜㅜ");
        }

        return ResponseEntity
                .ok()
                .body(member);
    }
}
