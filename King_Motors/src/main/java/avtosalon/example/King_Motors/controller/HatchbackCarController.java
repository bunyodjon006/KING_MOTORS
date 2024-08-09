package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.ElectricCar;
import avtosalon.example.King_Motors.model.HatchbackCar;
import avtosalon.example.King_Motors.repository.HatchbackCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExecutionChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class HatchbackCarController {
    @Autowired
    HatchbackCarRepository hatchbackCarRepository;
    @GetMapping("/hatchbackcar")
    public List<HatchbackCar> getAllHatchbackCar(){
        return hatchbackCarRepository.findAll();
    }
    //    ID GET
    @GetMapping("hatchbackcar/{id}")
    public ResponseEntity<HatchbackCar> getAllHatchbackcar(@PathVariable Long id){
        HatchbackCar hatchbackCar =hatchbackCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("Not found id"+id));
        return ResponseEntity.ok(hatchbackCar);
    }
    @PostMapping("/hatchbackcar")
    public HatchbackCar createHachbackcar(@RequestBody HatchbackCar hatchbackCar){
        return hatchbackCarRepository.save(hatchbackCar);
    }
    @PutMapping("hatchbackcar/{id}")
    public ResponseEntity<HatchbackCar> getALLHachbackcar(@PathVariable Long id, @RequestBody HatchbackCar hatchbackCardetails){
        HatchbackCar hatchbackCar =hatchbackCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        hatchbackCar.setCarbox(hatchbackCardetails.getCarbox());
        hatchbackCar.setCost(hatchbackCardetails.getCost());
        hatchbackCar.setFuel(hatchbackCardetails.getFuel());
        hatchbackCar.setCaryear(hatchbackCardetails.getCaryear());
        hatchbackCar.setInformationcar(hatchbackCardetails.getInformationcar());
        hatchbackCar.setModelname(hatchbackCardetails.getModelname());
        HatchbackCar update =hatchbackCarRepository.save(hatchbackCar);
        return ResponseEntity.ok(hatchbackCar);
    }
    @DeleteMapping("/hatchbackcar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteHachbackcar(@PathVariable Long id){
        HatchbackCar hatchbackCar=hatchbackCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        hatchbackCarRepository.delete(hatchbackCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
