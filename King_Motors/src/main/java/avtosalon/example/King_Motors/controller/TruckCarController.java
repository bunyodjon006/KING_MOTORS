package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.ConvertibleCar;
import avtosalon.example.King_Motors.model.TruckCar;
import avtosalon.example.King_Motors.repository.TruckCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TruckCarController {
    @Autowired
    TruckCarRepository truckCarRepository;
    @GetMapping("/truckcar")
    public List<TruckCar> getAllTruckarCar(){
        return truckCarRepository.findAll();
    }
    @GetMapping("/truckcar/{id}")
    public Optional<TruckCar> getTruckCar(@PathVariable Long id) {
        return truckCarRepository.findById(id);
    }

    @PostMapping("/truckcar")
    public TruckCar createTruckCar(@RequestBody TruckCar truckCar){
        return truckCarRepository.save(truckCar);
    }
    @PutMapping("truckcar/{id}")
    public ResponseEntity<TruckCar> getALLTruckCar(@PathVariable Long id, @RequestBody TruckCar truckCardetails){
        TruckCar truckCar =truckCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        truckCar.setCarbox(truckCardetails.getCarbox());
        truckCar.setCost(truckCardetails.getCost());
        truckCar.setFuel(truckCardetails.getFuel());
        truckCar.setCaryear(truckCardetails.getCaryear());
        truckCar.setInformationcar(truckCardetails.getInformationcar());
        truckCar.setModelname(truckCardetails.getModelname());
        TruckCar update =truckCarRepository.save(truckCar);
        return ResponseEntity.ok(truckCar);
    }
    @DeleteMapping("/truckcar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteTruckCar(@PathVariable Long id){
        TruckCar truckCar=truckCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        truckCarRepository.delete(truckCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
