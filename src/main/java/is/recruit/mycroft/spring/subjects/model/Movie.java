package is.recruit.mycroft.spring.subjects.model;

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

public class Movie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_id")
    private Long id;

    @Column(nullable = false)
    private String movieName;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Theater> theaters = new ArrayList<>();

    public Movie(Long id, String movieName) {
        this.id = id;
        this.movieName = movieName;
    }

    public void update(List <Theater> theaters){
        this.theaters = theaters;
    }
}
