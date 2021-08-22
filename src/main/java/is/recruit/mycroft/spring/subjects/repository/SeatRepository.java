package is.recruit.mycroft.spring.subjects.repository;

import is.recruit.mycroft.spring.subjects.model.Seat;
import is.recruit.mycroft.spring.subjects.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Seat findByColAndRowAndTheater(int col, int row, Theater theater);
}
