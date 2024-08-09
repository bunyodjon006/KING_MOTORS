package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.ConvertibleCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertibleCarRepository extends JpaRepository<ConvertibleCar,Long> {
}
