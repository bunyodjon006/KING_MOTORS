package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.ConvertibleCar;
import avtosalon.example.King_Motors.model.SuvCar;
import avtosalon.example.King_Motors.repository.SuvCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SuvCarController {
    @Autowired
    SuvCarRepository suvCarRepository;
    @GetMapping("/suvcar")
    public List<SuvCar> getAllSuvCar(){
        return suvCarRepository.findAll();
    }
    @GetMapping("/suvcar/{id}")
    public Optional<SuvCar> getSuvcar(@PathVariable Long id) {
        return suvCarRepository.findById(id);
    }

    @PostMapping("/suvcar")
    public SuvCar createSuvcar(@RequestBody SuvCar suvCar){
        return suvCarRepository.save(suvCar);
    }
    @PutMapping("suvcar/{id}")
    public ResponseEntity<SuvCar> getALLSuvcar(@PathVariable Long id, @RequestBody SuvCar suvCardetails){
        SuvCar suvCar =suvCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        suvCar.setCarbox(suvCardetails.getCarbox());
        suvCar.setCost(suvCardetails.getCost());
        suvCar.setFuel(suvCardetails.getFuel());
        suvCar.setCaryear(suvCardetails.getCaryear());
        suvCar.setInformationcar(suvCardetails.getInformationcar());
        suvCar.setModelname(suvCardetails.getModelname());
        SuvCar update =suvCarRepository.save(suvCar);
        return ResponseEntity.ok(suvCar);
    }
    @DeleteMapping("/suvcar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteSuvcar(@PathVariable Long id){
        SuvCar suvCar=suvCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        suvCarRepository.delete(suvCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
