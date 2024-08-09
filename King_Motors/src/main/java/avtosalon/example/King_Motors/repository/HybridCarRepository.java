package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.HybridCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HybridCarRepository extends JpaRepository<HybridCar,Long> {
}
