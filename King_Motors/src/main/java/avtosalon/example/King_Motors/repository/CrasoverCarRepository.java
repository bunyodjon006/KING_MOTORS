package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.CrasoverCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrasoverCarRepository extends JpaRepository<CrasoverCar,Long> {
}
