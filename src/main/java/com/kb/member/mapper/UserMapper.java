package com.kb.member.mapper;

import com.kb.member.dto.Auth;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.User;

public interface UserMapper {
    User selectById(String userId);

    int insertUser(User user);

    void updateUser(User updateUser);

    void deleteUser(long userNo);

    void updatePassword(ChangePasswordDTO changePassword);
}
