package modal.Cinema;

import com.example.backendcinema.entity.Cinema.LocationEnum;
import lombok.Data;

@Data
public class CinemaLocationReq {
    private LocationEnum locationEnum;
}
