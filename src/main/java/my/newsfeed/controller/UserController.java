package my.newsfeed.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.newsfeed.model.dto.LoginRequestDto;
import my.newsfeed.model.dto.SignupRequestDto;
import my.newsfeed.security.UserDetailsImpl;
import my.newsfeed.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private String validateRequest(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "실패: " + bindingResult.getAllErrors()
                    .stream()
                    .map(error -> ((FieldError) error).getDefaultMessage())
                    .collect(Collectors.joining(", "));
        }
        return null;
    }

    @GetMapping("/user/login-page")
    public ResponseEntity<?> loginPage() {
        return ResponseEntity.ok("메인페이지");
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        String validationResult = validateRequest(bindingResult);
        if (validationResult != null) {
            return ResponseEntity.badRequest().body(validationResult);
        }

        try {
            userService.signup(signupRequestDto);
            return ResponseEntity.ok("회원가입 성공");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto RequestDto, HttpServletResponse res, BindingResult bindingResult) {
        String validationResult = validateRequest(bindingResult);
        if (validationResult != null) {
            return ResponseEntity.badRequest().body(validationResult);
        }

        try {
            userService.login(RequestDto,res);
            return ResponseEntity.ok("로그인 성공");
        } catch (Exception e) {
            return ResponseEntity.ok("실패:" + Arrays.toString(e.getStackTrace()));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestParam String password) {

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
        }

        try {
            userService.deleteUser(userDetails.getUsername());
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}