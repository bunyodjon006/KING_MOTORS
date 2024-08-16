package avtosalon.example.King_Motors.service;


import avtosalon.example.King_Motors.model.CrasoverCar;

import avtosalon.example.King_Motors.repository.CrasoverCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrasoverCarService {
    @Autowired
    CrasoverCarRepository crasoverCarRepository;
    public CrasoverCar addnewProducts(CrasoverCar crasoverCar){
        return  crasoverCarRepository.save(crasoverCar);
    }
}
