package BookMyShow.model;

import BookMyShow.model.constant.SeatStatus;
import BookMyShow.model.constant.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int bmsRow;
    private int bmsCol;
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    public Seat() {
    }

    public Seat(int row, int col, String seatNumber, SeatType seatType, SeatStatus status) {
        this.bmsRow = row;
        this.bmsCol = col;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = status;
    }
}
