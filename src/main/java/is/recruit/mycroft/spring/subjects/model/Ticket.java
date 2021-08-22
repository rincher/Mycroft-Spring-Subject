package is.recruit.mycroft.spring.subjects.model;

import is.recruit.mycroft.spring.subjects.config.Timestamped;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Ticket extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ticket_no")
    private Long id;

    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private String theaterName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int col;

    @Column(nullable = false)
    private int row;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn
    private User user;

    public void addUser(User user) {
        this.user = user;
        user.getTickets().add(this);
    }
}
