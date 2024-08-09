package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.ConvertibleCar;
import avtosalon.example.King_Motors.model.CrasoverCar;
import avtosalon.example.King_Motors.repository.CrasoverCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CrasoverCarController {
    @Autowired
    CrasoverCarRepository crasoverCarRepository;
    @GetMapping("/crasovercar")
    public List<CrasoverCar> getAllCrasoverCar(){
        return crasoverCarRepository.findAll();
    }
    @GetMapping("/crasovercar/{id}")
    public Optional<CrasoverCar> getCrasover(@PathVariable Long id) {
        return crasoverCarRepository.findById(id);
    }

    @PostMapping("/crasovercar")
    public CrasoverCar createCrasovercar(@RequestBody CrasoverCar crasoverCar){
        return crasoverCarRepository.save(crasoverCar);
    }
    @PutMapping("crasovercar/{id}")
    public ResponseEntity<CrasoverCar> getAllCrasovercar(@PathVariable Long id, @RequestBody CrasoverCar crasovercardetails){
        CrasoverCar crasoverCar =crasoverCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        crasoverCar.setCarbox(crasovercardetails.getCarbox());
        crasoverCar.setCost(crasovercardetails.getCost());
        crasoverCar.setFuel(crasovercardetails.getFuel());
        crasoverCar.setCaryear(crasovercardetails.getCaryear());
        crasoverCar.setInformationcar(crasovercardetails.getInformationcar());
        crasoverCar.setModelname(crasovercardetails.getModelname());
        CrasoverCar update =crasoverCarRepository.save(crasoverCar);
        return ResponseEntity.ok(crasoverCar);
    }
    @DeleteMapping("/crasovercar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteCrasoverCar(@PathVariable Long id){
        CrasoverCar crasoverCar=crasoverCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        crasoverCarRepository.delete(crasoverCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
