package avtosalon.example.King_Motors.service;

import avtosalon.example.King_Motors.model.SedanCar;
import avtosalon.example.King_Motors.model.SuvCar;
import avtosalon.example.King_Motors.repository.SuvCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuvCarService {
    @Autowired
    SuvCarRepository suvCarRepository;
    public SuvCar addnewProducts(SuvCar suvCar){
        return suvCarRepository.save(suvCar);
    }
}
