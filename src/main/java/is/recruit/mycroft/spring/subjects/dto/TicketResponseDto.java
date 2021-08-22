package is.recruit.mycroft.spring.subjects.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class TicketResponseDto {
    private String userName;
    private String movieName;
    private String theaterName;
    private int price;
    private int col;
    private int row;

    public TicketResponseDto(String userName, String movieName, String theaterName, int price, int col, int row){
        this.userName = userName;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.price = price;
        this.col = col;
        this.row = row;
    }
}
