package avtosalon.example.King_Motors.service;

import avtosalon.example.King_Motors.model.ElectricCar;
import avtosalon.example.King_Motors.repository.ElectricCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectricCarService {
    @Autowired
    ElectricCarRepository electricCarRepository;
    public ElectricCar addnewProducts(ElectricCar electricCar){
        return electricCarRepository.save(electricCar);
    }
}
