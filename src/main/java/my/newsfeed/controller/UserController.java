package my.newsfeed.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import my.newsfeed.model.dto.LoginRequestDto;
import my.newsfeed.model.dto.SignupRequestDto;
import my.newsfeed.security.UserDetailsImpl;
import my.newsfeed.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @GetMapping("/user/login-page")
    public ResponseEntity<?> loginPage() {
        return ResponseEntity.ok("메인페이지");
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(SignupRequestDto signupRequestDto) {
        try {
            userService.signup(signupRequestDto);
        }catch (Exception e) {
            //return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(LoginRequestDto RequestDto, HttpServletResponse res) {
        try {
            userService.login(RequestDto,res);
        } catch (Exception e) {
            return ResponseEntity.ok("실패:" + Arrays.toString(e.getStackTrace()));
        }
        return ResponseEntity.ok("로그인 성공");
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.deleteUser(userDetails.getUsername(), userDetails.getPassword());
        return ResponseEntity.ok("탈퇴되었습니다.");
    }
}