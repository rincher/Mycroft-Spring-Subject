package is.recruit.mycroft.spring.subjects.service;

import is.recruit.mycroft.spring.subjects.model.Seat;
import is.recruit.mycroft.spring.subjects.model.Theater;
import is.recruit.mycroft.spring.subjects.repository.SeatRepository;
import is.recruit.mycroft.spring.subjects.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class SeatService {

    private final TheaterRepository theaterRepository;
    private final SeatRepository seatRepository;

    @PostConstruct
    public void init() {
        Theater theaterA = theaterRepository.findByTheaterName("A");
        List<Seat> seats = seatRepository.findAll();
        if (ObjectUtils.isEmpty(seats)) {
            Seat seat = null;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i < 3) {
                        seat = Seat.builder().seatName("front")
                                .theater(theaterA)
                                .row(i)
                                .col(j)
                                .price(10000)
                                .isAvailable(true)
                                .build();
                        seatRepository.save(seat);
                        seat.addTheater(theaterA);
                    }
                    if (i >= 3 && i < 7) {
                        seat = Seat.builder().seatName("middle")
                                .theater(theaterA)
                                .row(i)
                                .col(j)
                                .isAvailable(true)
                                .price(12000)
                                .build();
                        seatRepository.save(seat);
                        seat.addTheater(theaterA);
                    }
                    if (i >= 7) {
                        seat = Seat.builder().seatName("back")
                                .theater(theaterA)
                                .row(i)
                                .col(j)
                                .isAvailable(true)
                                .price(15000)
                                .build();
                        seatRepository.save(seat);
                        seat.addTheater(theaterA);
                    }

                }
            }
            Theater theaterB = theaterRepository.findByTheaterName("B");
            for (int k = 0; k < 8; k++) {
                for (int l = 0; l < 6; l++) {
                    if (k < 2) {
                        seat = Seat.builder().seatName("front")
                                .theater(theaterB)
                                .row(k)
                                .col(l)
                                .isAvailable(true)
                                .price(10000)
                                .build();
                        seatRepository.save(seat);
                        seat.addTheater(theaterB);
                    }
                    if (k >= 2 && k < 6) {
                        seat = Seat.builder().seatName("middle")
                                .theater(theaterB)
                                .row(k)
                                .col(l)
                                .isAvailable(true)
                                .price(12000)
                                .build();
                        seatRepository.save(seat);
                        seat.addTheater(theaterB);
                    }
                    if (k >= 6) {
                        seat = Seat.builder().seatName("back")
                                .theater(theaterB)
                                .row(k)
                                .col(l)
                                .isAvailable(true)
                                .price(15000)
                                .build();
                        seatRepository.save(seat);
                        seat.addTheater(theaterB);
                    }
                }
            }
        }
    }
}
