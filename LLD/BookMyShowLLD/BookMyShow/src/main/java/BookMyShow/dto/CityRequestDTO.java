package BookMyShow.dto;

import lombok.Getter;
import lombok.Setter;

// beginning when creating city,
// only name will be given
@Getter
@Setter
public class CityRequestDTO {
    private String name;
}
