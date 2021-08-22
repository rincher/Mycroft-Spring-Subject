package is.recruit.mycroft.spring.subjects.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TicketRequestDto {

    private String movieName;
    private String theaterName;
    private int price;
    private int col;
    private int row;
}
