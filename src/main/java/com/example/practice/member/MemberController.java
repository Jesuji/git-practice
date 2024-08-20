package com.example.practice.member;

import com.example.practice.member.dto.JoinMemberRequest;
import com.example.practice.member.dto.LoginMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> joinMember(@RequestBody JoinMemberRequest request) throws IllegalAccessException {
        memberService.joinMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입이 되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestBody LoginMemberRequest request) {
        memberService.loginMember(request);
        return ResponseEntity.ok("로그인 성공");
    }



}
