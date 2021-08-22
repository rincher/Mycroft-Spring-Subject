package is.recruit.mycroft.spring.subjects.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data

public class MovieResponseDto {
    private Long id;
    private String name;
    private List<TheaterResponseDto> theaterResponseDto;

    public MovieResponseDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public MovieResponseDto(Long id, String name, List<TheaterResponseDto> theaterResponseDtos){
        this.id = id;
        this.name = name;
        this.theaterResponseDto = theaterResponseDtos;
    }
}
