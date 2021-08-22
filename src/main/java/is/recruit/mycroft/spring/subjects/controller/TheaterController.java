package is.recruit.mycroft.spring.subjects.controller;

import is.recruit.mycroft.spring.subjects.service.TheaterService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping("/api/theater")
    public ResponseEntity getTheater(@AuthenticationPrincipal UserDetails userDetails){
        return theaterService.getTheater(userDetails);
    }
}
