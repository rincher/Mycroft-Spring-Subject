package is.recruit.mycroft.spring.subjects.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data

public class TheaterResponseDto {
    private String theaterName;

    private List<SeatResponseDto> seatResponseDtos = new ArrayList<>();

    public TheaterResponseDto (String theaterName){
        this.theaterName = theaterName;
    }

    public TheaterResponseDto (String theaterName, List<SeatResponseDto> seatResponseDtos){
        this.theaterName = theaterName;
        this.seatResponseDtos = seatResponseDtos;
    }
}
