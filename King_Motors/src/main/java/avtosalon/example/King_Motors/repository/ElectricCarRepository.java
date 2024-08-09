package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.ElectricCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricCarRepository extends JpaRepository<ElectricCar,Long> {
}
