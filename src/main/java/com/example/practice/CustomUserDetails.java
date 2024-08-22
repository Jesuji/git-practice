package com.example.practice;

import com.example.practice.member.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDatails implements UserDetails {

    private final Member member;

    public CustomUserDatails(Member member) {
        this.member = member;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }


    @Override
    public String getPassword() {
        return member.getPassword(); // 사용자 비밀번호 반환
    }

    @Override
    public String getUsername() {
        return member.getName(); // 사용자 이름 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부 반환
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 여부 반환
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격증명(비밀번호) 만료 여부 반환
    }

    /*@Override
    public boolean isEnabled() {
        return member.isEnabled(); // 계정 활성화 여부 반환
    }*/

    public Long getId() {
        return member.getUserId();
    }


}
