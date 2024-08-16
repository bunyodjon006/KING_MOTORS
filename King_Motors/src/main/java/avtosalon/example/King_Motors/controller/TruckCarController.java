package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.FileUploadUtilService;
import avtosalon.example.King_Motors.model.*;
import avtosalon.example.King_Motors.repository.TruckCarRepository;
import avtosalon.example.King_Motors.service.TruckCarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/v1")
public class TruckCarController {
    @Autowired
    FileUploadUtilService fileUploadUtilService;
    @Autowired
    TruckCarService truckCarService;
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
    @PostMapping("/addnewtruckarProducts")
    public TruckCar addnewCar(@RequestParam("product") String truckcarJson,
                            @RequestParam("imagefile") MultipartFile[] multipartFiles){


        try {

            TruckCar truckCar = new ObjectMapper().readValue(truckcarJson,TruckCar.class);

            Set<ImageModelTruckCar> images = uploadImage(multipartFiles,truckCar);
            truckCar.setProductImages(images);

            return truckCarService.addnewProducts(truckCar);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }

    public Set<ImageModelTruckCar> uploadImage(MultipartFile[] multipartFiles, TruckCar truckCar)throws IOException {

        Set<ImageModelTruckCar> imageModelTruckCars = new HashSet<>();
        int count = 1;

        for (MultipartFile file:multipartFiles) {

            ImageModelTruckCar imageModelTruckCar = new ImageModelTruckCar();
            imageModelTruckCar.setImageLink(fileUploadUtilService.
                    handleMediaUpload(truckCar.getModelname().trim().replace(" ", "_") + "_" + count + "_image", file));
            count++;

            imageModelTruckCars.add(imageModelTruckCar);
        }

        return imageModelTruckCars;
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
