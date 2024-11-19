package com.kb.member.service;

import com.kb.member.dto.Auth;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.User;
import com.kb.member.exception.PasswordMissmatchException;
import com.kb.member.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class UserService {

    @Value("#{'${os_type}' == 'win' ? '${file_save_location_win}':'${file_save_location_other}'}")
    public String LOCATION;

    final PasswordEncoder passwordEncoder;
    final UserMapper mapper;

    public User login(User user) {
        User savedUser = mapper.selectById(user.getUserId());
        if (passwordEncoder.matches(user.getUserPw(), savedUser.getUserPw())) {
//            savedUser.setUserPw("");
//            savedUser.setUserNo(0);
            return savedUser;
        } else {
            return null;
        }
    }

    public boolean checkDuplicate(String userId) {
        User user = mapper.selectById(userId);
        return user != null;
    }

    public User getUser(String userId) {
        return Optional.ofNullable(mapper.selectById(userId))
                .orElseThrow(NoSuchElementException::new);
    }

    private void saveAvatar(MultipartFile avatar, String userId) {
        if (avatar != null && !avatar.isEmpty()) {
            File dir = new File(LOCATION + "/avatar");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File dest = new File(LOCATION + "/avatar", userId + ".png");
            if (dest.exists()) {
                dest.delete();
            }
            try {
                avatar.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public User join(User user) throws IllegalAccessException {
        if (user.checkRequiredValue()) {
            throw new IllegalAccessException("Required fields are missing");
        }
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        int result = mapper.insertUser(user);
        if (result != 1) {
            throw new IllegalAccessException("Failed to insert user");
        }

        // Auth 테이블이 없으므로 권한 삽입 로직을 제거합니다.

        return mapper.selectById(user.getUserId());
    }


    public User update(User updateUser, MultipartFile avatar) throws IllegalAccessException {
        User oldUser = mapper.selectById(updateUser.getUserId());
        if (!passwordEncoder.matches(updateUser.getUserPw(), oldUser.getUserPw())) {
            throw new PasswordMissmatchException();
        }
        updateUser.setUserNo(oldUser.getUserNo());
        mapper.updateUser(updateUser);
        if (avatar != null && !avatar.isEmpty()) {
            saveAvatar(avatar, oldUser.getUserId());
        }
        return mapper.selectById(updateUser.getUserId());
    }

    public User delete(String userId) {
        User user = mapper.selectById(userId);
        mapper.deleteUser(user.getUserNo());
        return user;
    }

    public void changePassword(ChangePasswordDTO changePassword) {
        User user = mapper.selectById(changePassword.getUserId());
        if (!passwordEncoder.matches(changePassword.getOldPassword(), user.getUserPw())) {
            throw new PasswordMissmatchException();
        }
        changePassword.setNewPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        mapper.updatePassword(changePassword);
    }

    public boolean checkDuplicateUserId(String userId) {
        return mapper.existsByUserId(userId);
    }

    public boolean checkDuplicateEmail(String email) {
        return mapper.existsByEmail(email);
    }

    public boolean checkDuplicateNickname(String nickname) {
        return mapper.existsByNickname(nickname); // 닉네임이 존재하면 true 반환
    }

    public void resetUserPoint(Long userNo) {
        User user = mapper.selectByUserNo(userNo); // userNo로 사용자 조회
        if (user == null) {
            throw new NoSuchElementException("User not found with userNo: " + userNo);
        }
        mapper.resetUserPoint(userNo); // 포인트 0으로 업데이트
    }



    public void updateNickname(Long userNo, String newNickname) {
        try {
            mapper.updateNickname(userNo, newNickname);
        } catch (Exception e) {
            throw new RuntimeException("닉네임 업데이트 중 오류 발생", e);
        }
    }

    public void updateEmail(Long userNo, String newEmail) {
        try {
            mapper.updateEmail(userNo, newEmail);
        } catch (Exception e) {
            throw new RuntimeException("이메일 업데이트 중 오류 발생", e);
        }
    }


    public User getUserInfoByUserNo(Long userNo) {
        return mapper.findUserByUserNo(userNo);
    }
}
