package is.recruit.mycroft.spring.subjects.service;

import is.recruit.mycroft.spring.subjects.dto.TicketRequestDto;
import is.recruit.mycroft.spring.subjects.dto.TicketResponseDto;
import is.recruit.mycroft.spring.subjects.model.*;
import is.recruit.mycroft.spring.subjects.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;

    public ResponseEntity makeTicket(UserDetails userDetails, TicketRequestDto ticketRequestDto) {
        if (ObjectUtils.isEmpty(userDetails)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Movie movie = movieRepository.findByMovieName(ticketRequestDto.getMovieName()).orElse(null);
        Theater theater = theaterRepository.findByTheaterNameAndMovie(ticketRequestDto.getTheaterName(), movie);
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        Seat seat = seatRepository.findByColAndRowAndTheater(ticketRequestDto.getCol(), ticketRequestDto.getRow(),theater);
        List<Seat> seats = seatRepository.findAll();

        int price = seat.getPrice();

        System.out.println(price);
        if (price != ticketRequestDto.getPrice()){
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (!seat.isAvailable()) return new ResponseEntity(HttpStatus.CONFLICT);

        Ticket ticket = Ticket.builder()
                .movieName(ticketRequestDto.getMovieName())
                .theaterName(ticketRequestDto.getTheaterName())
                .price(ticketRequestDto.getPrice())
                .col(ticketRequestDto.getCol())
                .row(ticketRequestDto.getRow())
                .build();
        ticketRepository.save(ticket);
        ticket.addUser(user);

        seat.setAvailable(false);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity viewTicket(UserDetails userDetails) {
        if (ObjectUtils.isEmpty(userDetails)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        Ticket ticket = ticketRepository.findByUser(user).orElse(null);
        TicketResponseDto ticketResponseDto = new TicketResponseDto(
                user.getUsername(),
                ticket.getMovieName(),
                ticket.getTheaterName(),
                ticket.getPrice(),
                ticket.getCol(),
                ticket.getRow()
        );
        return ResponseEntity.ok(ticketResponseDto);
    }
}