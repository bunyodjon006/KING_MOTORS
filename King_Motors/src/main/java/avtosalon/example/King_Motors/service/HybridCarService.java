package avtosalon.example.King_Motors.service;

import avtosalon.example.King_Motors.model.HybridCar;
import avtosalon.example.King_Motors.repository.HybridCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HybridCarService {
    @Autowired
    HybridCarRepository hybridCarRepository;
    public HybridCar addnewProducts(HybridCar hybridCar){
        return hybridCarRepository.save(hybridCar);
    }
}
