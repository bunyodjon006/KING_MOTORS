package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.TruckCar;
import avtosalon.example.King_Motors.model.VanCar;
import avtosalon.example.King_Motors.repository.VanCarRepository;
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
public class VanCarController {
    @Autowired
    VanCarRepository vanCarRepository;
    @GetMapping("/vancar")
    public List<VanCar> getAllVancar(){
        return vanCarRepository.findAll();
    }
    @GetMapping("/vancar/{id}")
    public Optional<VanCar> getVancar(@PathVariable Long id) {
        return vanCarRepository.findById(id);
    }

    @PostMapping("/vancar")
    public VanCar createVancar(@RequestBody VanCar vanCar){
        return vanCarRepository.save(vanCar);
    }
    @PutMapping("vancar/{id}")
    public ResponseEntity<VanCar> getALLVancar(@PathVariable Long id, @RequestBody VanCar vanCardetails){
        VanCar vanCar =vanCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        vanCar.setCarbox(vanCardetails.getCarbox());
        vanCar.setCost(vanCardetails.getCost());
        vanCar.setFuel(vanCardetails.getFuel());
        vanCar.setCaryear(vanCardetails.getCaryear());
        vanCar.setInformationcar(vanCardetails.getInformationcar());
        vanCar.setModelname(vanCardetails.getModelname());
        VanCar update =vanCarRepository.save(vanCar);
        return ResponseEntity.ok(vanCar);
    }
    @DeleteMapping("/vancar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteVancar(@PathVariable Long id){
        VanCar vanCar=vanCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        vanCarRepository.delete(vanCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
