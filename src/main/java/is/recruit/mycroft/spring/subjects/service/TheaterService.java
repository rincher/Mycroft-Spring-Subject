package is.recruit.mycroft.spring.subjects.service;

import is.recruit.mycroft.spring.subjects.dto.SeatResponseDto;
import is.recruit.mycroft.spring.subjects.dto.TheaterResponseDto;
import is.recruit.mycroft.spring.subjects.model.Movie;
import is.recruit.mycroft.spring.subjects.model.Theater;
import is.recruit.mycroft.spring.subjects.repository.MovieRepository;
import is.recruit.mycroft.spring.subjects.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;

    @PostConstruct
    private void init() {
        List<Movie> movieList = movieRepository.findAll();

        for (Movie movie : movieList) {

            List<Theater> theaters = Arrays.asList(
                    new Theater(1L, "A", movie),
                    new Theater(2L, "B", movie)
            );
            List<Theater> newTheaters = theaterRepository.saveAll(theaters);
            movie.update(theaters);
        }
    }

    public ResponseEntity getTheater(UserDetails userDetails){
        List<Theater> theaters = theaterRepository.findAll();
        List<TheaterResponseDto> toList = theaters.stream().map(
                theater -> new TheaterResponseDto(
                        theater.getTheaterName(),
                        theater.getSeats().stream().map(
                                seat -> new SeatResponseDto(
                                        seat.getSeatName(),
                                        seat.getPrice(),
                                        seat.getCol(),
                                        seat.getRow()
                                )
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(toList);
    }


}
