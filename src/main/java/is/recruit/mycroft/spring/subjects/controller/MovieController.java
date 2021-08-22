package is.recruit.mycroft.spring.subjects.controller;

import is.recruit.mycroft.spring.subjects.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class MovieController {

    private final MovieService movieService;

    @GetMapping("/api/movies")
    public ResponseEntity getMovieList(@AuthenticationPrincipal UserDetails userDetails){
        return movieService.getMovieList();
    }

    @GetMapping("/api/movie/{movieName}")
    public ResponseEntity getMovie(@PathVariable String movieName, @AuthenticationPrincipal UserDetails userDetails){
        return movieService.getMovie(movieName, userDetails);
    }
}
