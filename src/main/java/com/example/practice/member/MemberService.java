package com.example.practice.member;

import com.example.practice.member.dto.JoinMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void joinMember(JoinMemberRequest request) {
        String joinname = request.name();
        String joinemail = request.email();
        String joinpassword = request.password();

        Member member = new Member(joinname, joinemail, joinpassword);

        //가입한 멤버 정보 저장
        memberRepository.save(member);
    }



}
