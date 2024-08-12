package avtosalon.example.King_Motors.service;

import avtosalon.example.King_Motors.model.ConvertibleCar;
import avtosalon.example.King_Motors.repository.ConvertibleCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertibleCarService {
    @Autowired
    ConvertibleCarRepository convertibleCarRepository;
    public ConvertibleCar addnewProduct(ConvertibleCar convertibleCar){
       return  convertibleCarRepository.save(convertibleCar);
    }
}
