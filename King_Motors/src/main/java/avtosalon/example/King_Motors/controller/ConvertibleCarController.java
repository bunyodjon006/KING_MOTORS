package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.ConvertibleCar;
import avtosalon.example.King_Motors.model.SedanCar;
import avtosalon.example.King_Motors.repository.ConvertibleCarRepository;
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
public class ConvertibleCarController {

    @Autowired
    ConvertibleCarRepository convertibleCarRepository;
    @GetMapping("/convertiblecar")
    public List<ConvertibleCar> getAllConvertibleCar(){
        return convertibleCarRepository.findAll();
    }
    @GetMapping("/convertiblecar/{id}")
    public Optional<ConvertibleCar> getConvertibleCar(@PathVariable Long id) {
        return convertibleCarRepository.findById(id);
    }

    @PostMapping("/convertiblecar")
    public ConvertibleCar createConvertablecar(@RequestBody ConvertibleCar convertibleCar){
        return convertibleCarRepository.save(convertibleCar);
    }
    @PutMapping("convertiblecar/{id}")
    public ResponseEntity<ConvertibleCar> getALLConvertibleCar(@PathVariable Long id, @RequestBody ConvertibleCar convertiblecardetails){
        ConvertibleCar convertibleCar =convertibleCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        convertibleCar.setCarbox(convertiblecardetails.getCarbox());
        convertibleCar.setCost(convertiblecardetails.getCost());
        convertibleCar.setFuel(convertiblecardetails.getFuel());
        convertibleCar.setCaryear(convertiblecardetails.getCaryear());
        convertibleCar.setInformationcar(convertiblecardetails.getInformationcar());
        convertibleCar.setModelname(convertiblecardetails.getModelname());
        ConvertibleCar update =convertibleCarRepository.save(convertibleCar);
        return ResponseEntity.ok(convertibleCar);
    }
    @DeleteMapping("/convertiblecar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteConvertibleCar(@PathVariable Long id){
        ConvertibleCar convertibleCar=convertibleCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        convertibleCarRepository.delete(convertibleCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
