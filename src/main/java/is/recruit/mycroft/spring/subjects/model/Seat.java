package is.recruit.mycroft.spring.subjects.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Seat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "seat_id")
    private Long id;

    @Column(nullable = false)
    private String seatName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int row;

    @Column(nullable = false)
    private int col;

    @Column(nullable = false)
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "theather_id")
    private Theater theater;

    public void addTheater(Theater theater){
        this.theater = theater;
        theater.getSeats().add(this);
    }
}
