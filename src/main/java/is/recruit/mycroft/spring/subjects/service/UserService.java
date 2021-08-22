package is.recruit.mycroft.spring.subjects.service;

import com.google.gson.JsonObject;
import io.netty.util.internal.ObjectUtil;
import is.recruit.mycroft.spring.subjects.dto.LoginRequestDto;
import is.recruit.mycroft.spring.subjects.config.JwtTokenProvider;
import is.recruit.mycroft.spring.subjects.model.User;
import is.recruit.mycroft.spring.subjects.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
@Transactional

public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostConstruct
    public void init() {
        List<User> userList = userRepository.findAll();

        if (ObjectUtils.isEmpty(userList)) {
            String person1 = "booker1";
            String person2 = "booker2";
            String person3 = "booker3";
            List<User> newUsers = Arrays.asList(
                    new User(1L, person1, Base64.getEncoder().encodeToString(person1.getBytes())),
                    new User(2L, person2, Base64.getEncoder().encodeToString(person1.getBytes())),
                    new User(3L, person3, Base64.getEncoder().encodeToString(person1.getBytes())));

            userRepository.saveAll(newUsers);
        }
    }

    public ResponseEntity doLogin(LoginRequestDto loginRequestDto){
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElse(null);
        assert user != null;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("token", jwtTokenProvider.createdToken(user.getUsername(),user.getRoles()));
        return ResponseEntity.ok().body(jsonObject.toString());
    }
}
