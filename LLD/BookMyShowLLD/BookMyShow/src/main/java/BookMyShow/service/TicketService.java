package BookMyShow.service;

import BookMyShow.exception.SeatNotAvailableException;
import BookMyShow.model.ShowSeat;
import BookMyShow.model.Ticket;
import BookMyShow.model.User;
import BookMyShow.model.constant.ShowSeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private ShowSeatService showSeatService;

    @Autowired
    private UserService userService;

    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws Exception{
        checkAndLockSeats(showSeatIds);
        startPayment(showSeatIds);
        // user service to save ticket history
        Ticket ticket = new Ticket();
        userService.addTicket(ticket, userId);
        return ticket;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void checkAndLockSeats(List<Integer> showSeatIds) throws Exception {
        // checking if the selected seats are available
        for(int showSeatId : showSeatIds){
            ShowSeat seat = showSeatService.getShowSeat(showSeatId);
            if(!seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new SeatNotAvailableException("Seat is not available anymore");
            }
        }
        // updating the seats to a locked state
        for(int showSeatId : showSeatIds){
            ShowSeat seat = showSeatService.getShowSeat(showSeatId);
            seat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatService.saveShowSeat(seat);
        }
    }

    public boolean startPayment(List<Integer> showSeatIds) {
        return true;
    }

    public String greet() {
        return "Hello from Service";
    }
}
