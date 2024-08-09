package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.VanCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VanCarRepository extends JpaRepository<VanCar,Long> {
}
