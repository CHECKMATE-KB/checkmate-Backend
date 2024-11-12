package com.kb.member.controller;

import com.kb.common.util.UploadFiles;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.User;
import com.kb.member.dto.UserDTO;
import com.kb.member.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

    @PostMapping("/join")
    public ResponseEntity<User> join(@RequestBody UserDTO userDTO) throws IllegalAccessException {
        User user = userDTO.toUser();
        User registeredUser = service.join(user);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/checkid/{id}")
    public ResponseEntity<Boolean> checkDuplicate(@PathVariable String id) {
        return ResponseEntity.ok().body(service.checkDuplicate(id));
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
