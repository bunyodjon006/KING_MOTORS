package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.HatchbackCar;
import avtosalon.example.King_Motors.model.HybridCar;
import avtosalon.example.King_Motors.repository.HybridCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class HybridCarController {
    @Autowired
    HybridCarRepository hybridCarRepository;
    @GetMapping("/hybridcar")
    public List<HybridCar> getAllHybridCar(){
        return hybridCarRepository.findAll();
    }
    //    ID GET
    @GetMapping("hybridcar/{id}")
    public ResponseEntity<HybridCar> getAllHatchbackcar(@PathVariable Long id){
        HybridCar hybridCar =hybridCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("Not found id"+id));
        return ResponseEntity.ok(hybridCar);
    }
    @PostMapping("/hybridcar")
    public HybridCar createHybridcar(@RequestBody HybridCar hybridCar){
        return hybridCarRepository.save(hybridCar);
    }
    @PutMapping("hybridcar/{id}")
    public ResponseEntity<HybridCar> getALLHybridCar(@PathVariable Long id, @RequestBody HybridCar hybridCardetails){
        HybridCar hybridCar =hybridCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        hybridCar.setCarbox(hybridCardetails.getCarbox());
        hybridCar.setCost(hybridCardetails.getCost());
        hybridCar.setFuel(hybridCardetails.getFuel());
        hybridCar.setCaryear(hybridCardetails.getCaryear());
        hybridCar.setInformationcar(hybridCardetails.getInformationcar());
        hybridCar.setModelname(hybridCardetails.getModelname());
        HybridCar update =hybridCarRepository.save(hybridCar);
        return ResponseEntity.ok(hybridCar);
    }
    @DeleteMapping("/hybridcar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteHybridCar(@PathVariable Long id){
        HybridCar hybridCar=hybridCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        hybridCarRepository.delete(hybridCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
