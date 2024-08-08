package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.SedanCar;
import avtosalon.example.King_Motors.repository.SedanCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SedanCarController {
    @Autowired
    SedanCarRepository sedanCarRepository;
    @GetMapping("/sedancar")
    public List<SedanCar> getAllSedancar(){
        return sedanCarRepository.findAll();
    }
//    ID GET
    @GetMapping("sedancar/{id}")
    public ResponseEntity<SedanCar> getAllSedancar(@PathVariable Long id){
        SedanCar sedanCar =sedanCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("Not found id"+id));
          return ResponseEntity.ok(sedanCar);
    }
    @PostMapping("/sedancar")
    public SedanCar createSedancar(@RequestBody SedanCar sedanCar){
        return sedanCarRepository.save(sedanCar);
    }
    @PutMapping("sedancar/{id}")
    public ResponseEntity<SedanCar> getALLSedanCar(@PathVariable Long id, @RequestBody SedanCar sedanCardetails){
        SedanCar sedanCar =sedanCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
           sedanCar.setCarbox(sedanCardetails.getCarbox());
           sedanCar.setCost(sedanCardetails.getCost());
           sedanCar.setFuel(sedanCardetails.getFuel());
           sedanCar.setCaryear(sedanCardetails.getCaryear());
           sedanCar.setInformationcar(sedanCardetails.getInformationcar());
           sedanCar.setModelname(sedanCardetails.getModelname());
           SedanCar update =sedanCarRepository.save(sedanCar);
           return ResponseEntity.ok(sedanCar);
    }
           @DeleteMapping("/sedancar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteSedanCar(@PathVariable Long id){
        SedanCar sedanCar=sedanCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        sedanCarRepository.delete(sedanCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
           }
}
