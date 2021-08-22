package is.recruit.mycroft.spring.subjects.service;

import io.netty.util.internal.ObjectUtil;
import is.recruit.mycroft.spring.subjects.dto.MovieResponseDto;
import is.recruit.mycroft.spring.subjects.dto.SeatResponseDto;
import is.recruit.mycroft.spring.subjects.dto.TheaterResponseDto;
import is.recruit.mycroft.spring.subjects.model.Movie;
import is.recruit.mycroft.spring.subjects.model.Theater;
import is.recruit.mycroft.spring.subjects.repository.MovieRepository;
import is.recruit.mycroft.spring.subjects.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class MovieService {
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;

    @PostConstruct
    private void init() {

        List<Movie> movieList = Arrays.asList(
                new Movie(1L, "titanic"),
                new Movie(2L, "saving private ryan")
        );
        movieRepository.saveAll(movieList);
    }

    public ResponseEntity getMovieList() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieResponseDto> toList = movieList.stream().map(
                movie -> new MovieResponseDto(
                        movie.getId(),
                        movie.getMovieName()
                )).collect(Collectors.toList());
        return ResponseEntity.ok(toList);
    }

    public ResponseEntity getMovie(String movieName, UserDetails userDetails) {
        if (ObjectUtils.isEmpty(userDetails)) {
            return new ResponseEntity<>("User not found", HttpStatus.FORBIDDEN);
        }

        Movie movie = movieRepository.findByMovieName(movieName).orElse(null);

        if (ObjectUtils.isEmpty(movie)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        MovieResponseDto movieResponseDto = new MovieResponseDto(
                movie.getId(), movie.getMovieName(), movie.getTheaters().stream().map(
                theater -> new TheaterResponseDto(
                        theater.getTheaterName()
        )).collect(Collectors.toList()));
        return ResponseEntity.ok(movieResponseDto);
    }
}
