package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.TruckCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckCarRepository  extends JpaRepository<TruckCar,Long> {
}
