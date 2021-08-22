package is.recruit.mycroft.spring.subjects.controller;

import is.recruit.mycroft.spring.subjects.dto.LoginRequestDto;
import is.recruit.mycroft.spring.subjects.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class LoginController {
    private final UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity doLogin(@RequestBody LoginRequestDto loginRequestDto){
        return userService.doLogin(loginRequestDto);
    }
}
