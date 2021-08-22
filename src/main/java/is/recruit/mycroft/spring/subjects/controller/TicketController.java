package is.recruit.mycroft.spring.subjects.controller;

import is.recruit.mycroft.spring.subjects.dto.TicketRequestDto;
import is.recruit.mycroft.spring.subjects.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/api/ticket")
    public ResponseEntity makeTicket(@AuthenticationPrincipal UserDetails userDetails, @RequestBody TicketRequestDto ticketRequestDto){
        return ticketService.makeTicket(userDetails, ticketRequestDto);
    }

    @GetMapping("/api/ticket")
    public ResponseEntity viewTicket(@AuthenticationPrincipal UserDetails userDetails){
        return ticketService.viewTicket(userDetails);
    }
}
