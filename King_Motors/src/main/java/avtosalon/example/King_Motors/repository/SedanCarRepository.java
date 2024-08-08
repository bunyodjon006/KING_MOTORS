package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.SedanCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedanCarRepository extends JpaRepository<SedanCar, Long> {


}
