package my.newsfeed.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.newsfeed.model.dto.UserRequestDto;
import my.newsfeed.model.dto.UserResponseDto;
import my.newsfeed.repository.UserRepository;
import my.newsfeed.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserRepository userRepository;
    private final UserService userService;

    // 1. 프로필 이미지 업로드 API
    @PostMapping("{id}/uploadProfileImage")
    public String uploadProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {

        // 파일 저장 경로 설정
        String filePath = "path/profileImage/" + file.getOriginalFilename();
        file.transferTo(new File(filePath)); //업로드된 파일을 지정한 경로로 저장

        // Service에 유저 프로필 이미지 URL 업데이트 요청
        userService.updateProfileImage(id, filePath);

        return "프로필 이미지가 업로드 되었습니다.";
    }

    // 2. 배경 이미지 업로드 API
    @PostMapping("{id}/uploadBackgroundImage")
    public String uploadBackgroundImage(@PathVariable long id, @RequestParam("file") MultipartFile file) throws IOException {

        // 파일 저장 경로 설정
        String filePath = "path/backgrounImage/" + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        //Service에 유저 배경 이미지 URL 업데이트 요청
        userService.updateBackgroundImage(id, filePath);

        return "배경 이미지가 업로드 되었습니다.";
    }

    // 3. 프로필 공개/ 비공개 설정
    @PutMapping("/{id}/setProfileVisibility")
    public String setProfileVisibility(@PathVariable Long id, @RequestParam UserRequestDto requestDto) {
        //서비스 계층을 통해 프로필 공개 여부 업데이트
        userService.updateProfileVisibility(id, requestDto);
        return "프로필 공개 설정이 변경되었습니다.";
    }

    // 4. 프로필 조회 시 ResponseDTO 반환
    @GetMapping("/{id}")
    public UserResponseDto viewProfile(@PathVariable Long id) {

        //Service를 통해 프로필 조회 & 반환
        return userService.getUserProfile(id);
    }

    // 5. 프로필 상태 확인(공개 여부)
    @GetMapping("/{id}isProfilePrivate")
    public String isProfilePrivate(@PathVariable Long id) {
        UserResponseDto userDto = userService.getUserProfile(id);

        //공개 여부에 따라 메시지 반환
        if (userDto.isPrivate()) {
            return "비공개 프로필입니다.";
        } else {
            return "공개 프로필입니다.";
        }
    }

}