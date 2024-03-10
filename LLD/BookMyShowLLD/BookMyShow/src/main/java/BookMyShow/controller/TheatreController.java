package BookMyShow.controller;

import BookMyShow.dto.TheaterRequestDTO;
import BookMyShow.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @PostMapping("/theatre")
    public ResponseEntity createTheatre(@RequestBody TheaterRequestDTO theaterRequestDTO) {
        return ResponseEntity.ok(theatreService.saveTheatre(theaterRequestDTO.getName(),
                theaterRequestDTO.getAddress(),
                theaterRequestDTO.getCityId()));
    }
}
