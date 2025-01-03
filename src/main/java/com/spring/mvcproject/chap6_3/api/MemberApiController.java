package com.spring.mvcproject.chap6_3.api;

import com.spring.mvcproject.chap6_3.dto.request.MemberCreateRequest;
import com.spring.mvcproject.chap6_3.entity.Member;
import com.spring.mvcproject.chap6_3.exception.ErrorCode;
import com.spring.mvcproject.chap6_3.exception.dto.MemberException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v6/members")
@Slf4j
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

        log.info("회원 단일 조회 요청이 들어옴");
        log.debug("parameter account : {}",account);

        Member member = memberStore.get(account);

        if (account.length() > 10) {
            throw new MemberException(ErrorCode.TEST_ERROR,"10자 이하");
        }
        if (account.equals("admin")) {
            throw new MemberException(ErrorCode.TEST_ERROR,"admin으로 작성");
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

    @PostMapping
    public ResponseEntity<?> create (
            @RequestBody @Valid MemberCreateRequest dto
            ){
        Member member = Member.builder()
                .nickname(dto.getNickname())
                .account(dto.getAccount())
                .password(dto.getPassword())
                .build();
        memberStore.put(member.getAccount(),member);
        return ResponseEntity.ok().body(member);
    }
}
