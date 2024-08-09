package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.HatchbackCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HatchbackCarRepository extends JpaRepository<HatchbackCar,Long> {
}
