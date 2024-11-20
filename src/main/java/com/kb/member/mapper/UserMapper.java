package com.kb.member.mapper;

import com.kb.member.dto.Auth;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectById(String userId);
    int selectByUserID(String userId);

    int insertUser(User user);

    void updateUser(User updateUser);

    void deleteUser(long userNo);

    void updatePassword(ChangePasswordDTO changePassword);

    boolean existsByUserId(String userId); // 아이디 중복 여부 확인
    boolean existsByEmail(String email); // 이메일 중복 여부 확인

    boolean existsByNickname(String nickname);


    User selectByUserNo(Long userNo);

    void resetUserPoint(Long userNo);


    void updateNickname(@Param("userNo") Long userNo,  @Param("newNickname") String newNickname);

    void updateEmail(@Param("userNo")Long userNo,  @Param("newEmail")String newEmail);

    User findUserByUserNo(Long userNo);

    Integer getUserPoint(@Param("userNo") Long userNo);

    void updateUserPoint(@Param("userNo") Long userNo, @Param("newPoint") int newPoint);
}
