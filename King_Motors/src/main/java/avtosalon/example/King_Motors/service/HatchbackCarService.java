package avtosalon.example.King_Motors.service;

import avtosalon.example.King_Motors.model.CrasoverCar;
import avtosalon.example.King_Motors.model.HatchbackCar;
import avtosalon.example.King_Motors.repository.HatchbackCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HatchbackCarService {
    @Autowired
    HatchbackCarRepository hatchbackCarRepository;
    public HatchbackCar addnewProducts(HatchbackCar hatchbackCar){
        return hatchbackCarRepository.save(hatchbackCar);
    }
}
