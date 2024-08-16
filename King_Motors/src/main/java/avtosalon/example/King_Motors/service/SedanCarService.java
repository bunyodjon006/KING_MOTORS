package avtosalon.example.King_Motors.service;

import avtosalon.example.King_Motors.model.HatchbackCar;
import avtosalon.example.King_Motors.model.SedanCar;
import avtosalon.example.King_Motors.repository.SedanCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedanCarService {
    @Autowired
    SedanCarRepository sedanCarRepository;
    public SedanCar addnewProducts(SedanCar sedanCar){
        return sedanCarRepository.save(sedanCar);
    }
}
