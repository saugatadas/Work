package BookMyShow.model;

import BookMyShow.model.constant.AuditoriumFeatures;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity

public class Auditorium extends BaseModel {
    private String name;
    private int capacity;
    @OneToMany
    private List<BmsShow> shows;
    @OneToMany
    private List<Seat> seats;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AuditoriumFeatures> auditoriumFeatures;
}
