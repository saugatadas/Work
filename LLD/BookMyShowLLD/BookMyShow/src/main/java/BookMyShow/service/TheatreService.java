package BookMyShow.service;


import BookMyShow.model.City;
import BookMyShow.model.Theatre;
import BookMyShow.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.TableHeaderUI;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private CityService cityService;

    public Theatre saveTheatre(String name, String address, int cityId) {
        Theatre theater = new Theatre();
        theater.setName(name);
        theater.setAddress(address);
        Theatre savedTheater = theatreRepository.save(theater);
        City city = cityService.getCityById(cityId);
        List<Theatre> theaters = city.getTheatres();
        theaters.add(savedTheater);
        city.setTheatres(theaters);
        cityService.saveCity(city);
        return savedTheater;
    }

}
