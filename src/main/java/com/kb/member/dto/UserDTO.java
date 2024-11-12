package com.kb.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String userId;      // 사용자 ID
    private String userPw;      // 비밀번호
    private String userName;    // 사용자 이름
    private String email;       // 이메일

    public User toUser() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .email(email)
                .build();
    }
}
