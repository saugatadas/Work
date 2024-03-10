package BookMyShow.model;


import BookMyShow.repository.TheatreRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {
    private String name;
    private String address;
    @OneToMany
    private List<Auditorium> audi;

    public Theatre() {

    }
    public Theatre(String name, String address) {
        this.name =name;
        this.address = address;
    }
}
