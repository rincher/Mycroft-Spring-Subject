package is.recruit.mycroft.spring.subjects.repository;

import is.recruit.mycroft.spring.subjects.model.Movie;
import is.recruit.mycroft.spring.subjects.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    Theater findByTheaterNameAndMovie(String name, Movie movie);
    Theater findByTheaterName(String name);
}
