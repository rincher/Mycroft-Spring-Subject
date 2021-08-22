package is.recruit.mycroft.spring.subjects;

import lombok.Getter;

@Getter
public class AuthenticationRequestDto {
    private String username;
    private String password;

    public AuthenticationRequestDto(String username, String password){
        this.username = username;
        this.password = password;
    }
}
