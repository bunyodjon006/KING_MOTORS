package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.ElectricCar;
import avtosalon.example.King_Motors.model.SedanCar;
import avtosalon.example.King_Motors.repository.ElectricCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ElectricCarController {
    @Autowired
    ElectricCarRepository electricCarRepository;
    @GetMapping("/electriccar")
    public List<ElectricCar> getAllElectric(){
        return electricCarRepository.findAll();
    }
    //    ID GET
    @GetMapping("electriccar/{id}")
    public ResponseEntity<ElectricCar> getAllElectriccar(@PathVariable Long id){
        ElectricCar electricCar =electricCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("Not found id"+id));
        return ResponseEntity.ok(electricCar);
    }
    @PostMapping("/electriccar")
    public ElectricCar createElectriccar(@RequestBody ElectricCar electricCar){
        return electricCarRepository.save(electricCar);
    }
    @PutMapping("electriccar/{id}")
    public ResponseEntity<ElectricCar> getALLElektriccar(@PathVariable Long id, @RequestBody ElectricCar electriccardetails){
        ElectricCar electricCar =electricCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        electricCar.setCarbox(electriccardetails.getCarbox());
        electricCar.setCost(electriccardetails.getCost());
        electricCar.setFuel(electriccardetails.getFuel());
        electricCar.setCaryear(electriccardetails.getCaryear());
        electricCar.setInformationcar(electriccardetails.getInformationcar());
        electricCar.setModelname(electriccardetails.getModelname());
        ElectricCar update =electricCarRepository.save(electricCar);
        return ResponseEntity.ok(electricCar);
    }
    @DeleteMapping("/electriccar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteElectriccar(@PathVariable Long id){
        ElectricCar electricCar=electricCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        electricCarRepository.delete(electricCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
