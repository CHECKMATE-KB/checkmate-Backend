package com.kb.member.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {
    private long userNo;        // PK
    private String userId;      // 사용자 ID
    private String userName;    // 사용자 이름
    private String userPw;      // 비밀번호
    private String email;       // 이메일
    private Date birth;         // 생년월일
    private String userImg;     // 프로필 이미지
    private String nickname;    // 닉네임
    private long accountNo;     // 계좌번호
    private int point;

    private String status;      // 활성화 여부 (Y/N)
    private Date createDate;    // 생성일
    private Date modifyDate;    // 수정일

    private String token;       // JWT 토큰 (DB 저장하지 않음)

    private List<SimpleGrantedAuthority> authorities; // 권한 목록

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "Y".equalsIgnoreCase(status);
    }

    public boolean checkRequiredValue() {
        try {
            return (userId.isEmpty() || userPw.isEmpty() || userName.isEmpty() || email.isEmpty());
        } catch (Exception e) {
            return false;
        }
    }
}
