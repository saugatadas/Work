package BookMyShow.repository;

import BookMyShow.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Seat findSeatBySeatNumber(String seatNumber); // custom query method
}
