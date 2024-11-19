package com.kb.member.controller;

import com.kb.common.util.UploadFiles;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.User;
import com.kb.member.dto.UserDTO;
import com.kb.member.service.UserService;
import com.kb.security.util.JwtProcessor;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Api(value = "UserController", tags = "유저 정보")
@PropertySource({"classpath:/application.properties"})
public class UserController {

    @Value("#{'${os_type}' == 'win' ? '${file_save_location_win}':'${file_save_location_other}'}")
    public String LOCATION;

    private final UserService service;
    private final JwtProcessor jwtProcessor;

    @PostMapping("/join")
    public ResponseEntity<User> join(@RequestBody UserDTO userDTO) throws IllegalAccessException {
        User user = userDTO.toUser();
        User registeredUser = service.join(user);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/point/{userNo}")
    public ResponseEntity<?> resetUserPoint(@PathVariable Long userNo) {
        try {
            service.resetUserPoint(userNo); // 서비스 메서드 호출
            return ResponseEntity.ok("User points have been reset to 0.");
        } catch (Exception e) {
            log.error("Failed to reset user points for userNo: {}", userNo, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to reset user points.");
        }
    }


    @PutMapping("/{id}/nickname")
    public ResponseEntity<?> updateNickname(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            String newNickname = request.get("nickname");
            service.updateNickname(id, newNickname);
            return ResponseEntity.ok(1);
        } catch (Exception e) {
            log.error("Failed to update nickname for userId: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("닉네임 업데이트에 실패했습니다.");
        }
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            String newEmail = request.get("email");
            service.updateEmail(id, newEmail);
            return ResponseEntity.ok("이메일이 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            log.error("Failed to update email for userId: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("이메일 업데이트에 실패했습니다.");
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        User user = service.login(userDTO.toUser());
        if (user != null) {
            String token = jwtProcessor.generateToken(user.getUserId());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userNo", user.getUserNo()); // userNo를 응답에 포함
            response.put("nickName", user.getNickname());

            return ResponseEntity.ok(response); // JWT와 userNo 응답
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    @GetMapping("/info/{userNo}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userNo) {
        try {
            User user = service.getUserInfoByUserNo(userNo);
            return ResponseEntity.ok(user); // 사용자의 모든 정보를 반환
        } catch (Exception e) {
            log.error("Failed to retrieve user information for userNo: {}", userNo, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve user information.");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return ResponseEntity.ok("로그아웃 성공");
    }


    @GetMapping("/checkid/{userId}")
    public ResponseEntity<Boolean> checkDuplicateUserId(@PathVariable String userId) {
        boolean isDuplicate = service.checkDuplicateUserId(userId);
        return ResponseEntity.ok(isDuplicate);
    }

    // 이메일 중복 체크 엔드포인트
    @GetMapping("/checkemail/{email}")
    public ResponseEntity<Boolean> checkDuplicateEmail(@PathVariable String email) {
        boolean isDuplicate = service.checkDuplicateEmail(email);
        return ResponseEntity.ok(isDuplicate);
    }

    @GetMapping("/checknickname/{nickname}")
    public ResponseEntity<Boolean> checkDuplicateNickname(@PathVariable String nickname) {
        boolean isDuplicate = service.checkDuplicateNickname(nickname);
        return ResponseEntity.ok(isDuplicate);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping("/{id}/avatar")
    public void getAvatar(@PathVariable String id, HttpServletResponse response) {
        String avatarPath = LOCATION + "/avatar/" + id + ".png";
        File file = new File(avatarPath);
        if (!file.exists()) {
            file = new File(LOCATION + "/avatar/unknown.png");
        }
        UploadFiles.downloadImage(response, file);
    }


    @PutMapping("/{id}/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePassword) {
        service.changePassword(changePassword);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
