package my.newsfeed.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}