package is.recruit.mycroft.spring.subjects.model;

import is.recruit.mycroft.spring.subjects.model.Movie;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Theater {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "theater_id")
    private Long id;

    @Column(nullable = false)
    private String theaterName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Movie movie;

    @OneToMany(mappedBy = "theater", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List <Seat> seats = new ArrayList<>();

    public Theater(Long id, String theaterName, Movie movie){
        this.id = id;
        this.theaterName = theaterName;
        this.movie = movie;
    }
    public void addMovie(Movie movie){
        this.movie = movie;
        movie.getTheaters().add(this);
    }
}
