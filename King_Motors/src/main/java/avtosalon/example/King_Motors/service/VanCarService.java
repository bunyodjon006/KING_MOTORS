package avtosalon.example.King_Motors.service;

import avtosalon.example.King_Motors.model.TruckCar;
import avtosalon.example.King_Motors.model.VanCar;
import avtosalon.example.King_Motors.repository.VanCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VanCarService {
    @Autowired
    VanCarRepository vanCarRepository;
    public VanCar addnewProducts(VanCar vanCar){
        return vanCarRepository.save(vanCar);
    }
}
