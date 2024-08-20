package com.example.practice.member;

import com.example.practice.member.dto.JoinMemberRequest;
import com.example.practice.member.dto.LoginMemberRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void joinMember(JoinMemberRequest request) throws IllegalAccessException {
        String joinname = request.name();
        String joinemail = request.email();
        String joinpassword = request.password();

        checkDuplicateName(joinname);
        checkDuplicateEmail(joinemail);

        Member member = new Member(joinname, joinemail, joinpassword);

        //가입한 멤버 정보 저장
        memberRepository.save(member);
    }

    public void loginMember (LoginMemberRequest request) {
        String loginemail = request.email();
        String loginepassword = request.password();

        //해당 이메일에 해당하는 Member를 데이터베이스에서 찾는다. 찾으면 Optional<Member>로 반환
        //Optional<Member>에서 Member 객체를 꺼내서 member 변수에 저장
        // 이 member 변수는 실제로 로그인된 사용자 정보나 다른 로직에서 사용할 수 있는 실제 Member 객체
        Member member = memberRepository.findByEmail(loginemail)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        if(!member.getEmail().equals(loginemail) || !member.getPassword().equals(loginepassword)) {
            log.error("회원 정보가 없습니다.");
            throw new EntityNotFoundException("Member not found");
        } else {
            log.info("로그인 되었습니다.");
        }
    }

    //매개변수 이메일 어떻게 가져오느냐? -> 가져오는 게 아니라 검색해서 비교하고 예외 던짐.
    public void checkDuplicateEmail(String email) throws IllegalAccessException {
        //해당 이메일을 가진 객체가 있으면 Optional<Member>로 반환
        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isPresent()) {
            //log.error("중복된 이메일 입니다.");
            //예외 안 던지면 로그는 떠도 db에 저장이 되버림.
            throw new IllegalAccessException("중복된 이메일입니다.");
        }
    }

    public void checkDuplicateName(String name) throws IllegalAccessException {
        Optional<Member> member = memberRepository.findByName(name);

        if(member.isPresent()) {
            throw new IllegalAccessException("중복된 이름입니다.");
        }
    }



}
