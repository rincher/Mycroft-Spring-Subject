package is.recruit.mycroft.spring.subjects.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data

public class SeatResponseDto {
    String seatName;
    int price;
    int col;
    int row;

    public SeatResponseDto(String seatName, int price, int col, int row) {
        this.seatName = seatName;
        this.price = price;
        this.col = col;
        this.row = row;
    }
}
