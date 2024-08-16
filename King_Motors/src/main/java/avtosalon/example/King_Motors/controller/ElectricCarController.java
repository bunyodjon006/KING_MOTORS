package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.FileUploadUtilService;
import avtosalon.example.King_Motors.model.*;
import avtosalon.example.King_Motors.repository.ElectricCarRepository;
import avtosalon.example.King_Motors.service.ElectricCarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ElectricCarController {
    @Autowired
    FileUploadUtilService fileUploadUtilService;
    @Autowired
    ElectricCarService electricCarService;
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
    @PostMapping("/addnewelectricProducts")
    public ElectricCar addnewCar(@RequestParam("product") String electricCarJson,
                                 @RequestParam("imagefile") MultipartFile[] multipartFiles){


        try {

            ElectricCar electricCar = new ObjectMapper().readValue(electricCarJson,ElectricCar.class);
            Set<ImageModelElectrocar> images=uploadImage(multipartFiles,electricCar);

            electricCar.setProductImages(images);

            return electricCarService.addnewProducts(electricCar);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }

    public Set<ImageModelElectrocar> uploadImage(MultipartFile[] multipartFiles, ElectricCar electricCar)throws IOException {

        Set<ImageModelElectrocar> imageModelsElectrocar = new HashSet<>();
        int count = 1;

        for (MultipartFile file:multipartFiles) {

            ImageModelElectrocar imageModelElectrocar = new ImageModelElectrocar();
            imageModelElectrocar.setImageLink(fileUploadUtilService.
                    handleMediaUpload(electricCar.getModelname().trim().replace(" ", "_") + "_" + count + "_image", file));
            count++;

            imageModelsElectrocar.add(imageModelElectrocar);
        }

        return imageModelsElectrocar;
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
