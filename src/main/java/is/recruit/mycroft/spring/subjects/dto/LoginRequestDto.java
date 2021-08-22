package is.recruit.mycroft.spring.subjects.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
