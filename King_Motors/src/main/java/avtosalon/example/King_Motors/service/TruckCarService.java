package avtosalon.example.King_Motors.service;

import avtosalon.example.King_Motors.model.SuvCar;
import avtosalon.example.King_Motors.model.TruckCar;
import avtosalon.example.King_Motors.repository.TruckCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruckCarService {
    @Autowired
    TruckCarRepository truckCarRepository;
    public TruckCar addnewProducts(TruckCar truckCar){
        return truckCarRepository.save(truckCar);
    }
}
