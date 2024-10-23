package my.newsfeed.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignupRequestDto {
   //@NotBlank
    private String username;
    //@NotBlank
    private String password;
    //@NotBlank
    //@Email
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}