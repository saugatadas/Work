package BookMyShow.service;

import BookMyShow.model.*;
import BookMyShow.model.constant.AuditoriumFeatures;
import BookMyShow.model.constant.SeatStatus;
import BookMyShow.model.constant.SeatType;
import BookMyShow.model.constant.ShowSeatStatus;
import BookMyShow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public boolean initialise() {
        City delhi = new City("Delhi");
        City bangalore = new City("Bangalore");

        cityRepository.save(delhi);
        cityRepository.save(bangalore);

        City savedBangalore = cityRepository.findCityByName("Bangalore");
        Theatre pvrTheater = new Theatre("PVR", "Kormangala");
        Theatre rexTheatre = new Theatre("REX", "Whitefield");

        theatreRepository.save(pvrTheater);
        theatreRepository.save(rexTheatre);

        Theatre savedPvrTheater = theatreRepository.findTheatreByName("PVR");
        Theatre savedRexTheatre  = theatreRepository.findTheatreByName("REX");

        List<Theatre> bangaloreTheatres = new ArrayList<>();
        bangaloreTheatres.add(savedPvrTheater);
        bangaloreTheatres.add(savedRexTheatre);
        savedBangalore.setTheatres(bangaloreTheatres);
        cityRepository.save(savedBangalore);

        List<Seat> savedSeatsAudi1 = new ArrayList<>();
        for(int i=1;i<=5;i++){
            Seat s = new Seat(i, i, i+ "1"+i, SeatType.GOLD, SeatStatus.AVAILABLE);
            savedSeatsAudi1.add(s);
            seatRepository.save(s);
        }
        Auditorium auditorium = new Auditorium();
        auditorium.setName("Audi01");
        auditorium.setCapacity(5);
        auditorium.setAuditoriumFeatures(List.of(AuditoriumFeatures.DOLBY, AuditoriumFeatures.IMAX));
        auditorium.setSeats(savedSeatsAudi1);
        auditoriumRepository.save(auditorium);

        List<Seat> savedSeatsAudi2 = new ArrayList<>();
        for(int i=1;i<=5;i++){
            Seat s = new Seat(i, i, i+ "2"+i, SeatType.GOLD, SeatStatus.AVAILABLE);
            savedSeatsAudi2.add(s);
            seatRepository.save(s);
        }

        Auditorium auditorium2 = new Auditorium();
        auditorium2.setName("Audi02");
        auditorium2.setCapacity(5);
        auditorium2.setAuditoriumFeatures(List.of(AuditoriumFeatures.IMAX));
        auditorium2.setSeats(savedSeatsAudi2);
        auditoriumRepository.save(auditorium2);

        Auditorium savedAudi1 = auditoriumRepository.findAuditoriumByName("Audi01");
        Auditorium savedAudi2 = auditoriumRepository.findAuditoriumByName("Audi02");
        Theatre savedTheatre = theatreRepository.findTheatreByName("PVR");
        List<Auditorium> auditoriums = new ArrayList<>();
        auditoriums.add(savedAudi1);
        auditoriums.add(savedAudi2);
        savedTheatre.setAudi(auditoriums);
        theatreRepository.save(savedTheatre);

        Movie movie1 = new Movie("Sholay", "Classic");
        movieRepository.save(movie1);
        Movie movie2 = new Movie("DDLJ", "Classic");
        movieRepository.save(movie2);

        BmsShow show = new BmsShow();
        show.setStartTime(LocalDateTime.now());
        show.setEndTime(LocalDateTime.now().plusMinutes(180));
        show.setMovie(movieRepository.findMovieByName("Sholay"));
        show.setAuditorium(auditoriumRepository.findAuditoriumByName("Audi01"));
        showRepository.save(show);

        BmsShow show2 = new BmsShow();
        show2.setStartTime(LocalDateTime.now());
        show2.setEndTime(LocalDateTime.now().plusMinutes(180));
        show2.setMovie(movieRepository.findMovieByName("DDLJ"));
        show2.setAuditorium(auditoriumRepository.findAuditoriumByName("Audi02"));
        showRepository.save(show2);

        for(int i=1;i<=5;i++){
            ShowSeat s = new ShowSeat(1251, showRepository.findById(1).get(), seatRepository.findSeatBySeatNumber(i+"1"+i), ShowSeatStatus.AVAILABLE);
            showSeatRepository.save(s);
        }

        for(int i=1;i<=5;i++){
            ShowSeat s = new ShowSeat(1251, showRepository.findById(2).get(), seatRepository.findSeatBySeatNumber(i+"2"+i), ShowSeatStatus.AVAILABLE);
            showSeatRepository.save(s);
        }

        return true;
    }
}