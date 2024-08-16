package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.FileUploadUtilService;
import avtosalon.example.King_Motors.model.HybridCar;
import avtosalon.example.King_Motors.model.ImageModelHybridCar;
import avtosalon.example.King_Motors.model.ImageModelSedanCar;
import avtosalon.example.King_Motors.model.SedanCar;
import avtosalon.example.King_Motors.repository.SedanCarRepository;
import avtosalon.example.King_Motors.service.SedanCarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SedanCarController {
    @Autowired
    FileUploadUtilService fileUploadUtilService;
    @Autowired
    SedanCarService sedanCarService;
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

    @PostMapping("/addnewsedancarProducts")
    public SedanCar addnewCar(@RequestParam("product") String sedanCarJson,
                               @RequestParam("imagefile") MultipartFile[] multipartFiles){


        try {

            SedanCar sedanCar = new ObjectMapper().readValue(sedanCarJson,SedanCar.class);

            Set<ImageModelSedanCar> images = uploadImage(multipartFiles,sedanCar);
            sedanCar.setProductImages(images);

            return sedanCarService.addnewProducts(sedanCar);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }

    public Set<ImageModelSedanCar> uploadImage(MultipartFile[] multipartFiles, SedanCar sedanCar)throws IOException {

        Set<ImageModelSedanCar> imageModelSedanCars = new HashSet<>();
        int count = 1;

        for (MultipartFile file:multipartFiles) {

            ImageModelSedanCar imageModelSedanCar = new ImageModelSedanCar();
            imageModelSedanCar.setImageLink(fileUploadUtilService.
                    handleMediaUpload(sedanCar.getModelname().trim().replace(" ", "_") + "_" + count + "_image", file));
            count++;

            imageModelSedanCars.add(imageModelSedanCar);
        }

        return imageModelSedanCars;
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
