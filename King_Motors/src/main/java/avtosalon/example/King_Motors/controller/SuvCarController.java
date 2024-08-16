package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.FileUploadUtilService;
import avtosalon.example.King_Motors.model.*;
import avtosalon.example.King_Motors.repository.SuvCarRepository;
import avtosalon.example.King_Motors.service.SuvCarService;
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
public class SuvCarController {
    @Autowired
    FileUploadUtilService fileUploadUtilService;
    @Autowired
    SuvCarService suvCarService;
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
    @PostMapping("/addnewsuvcarProducts")
    public SuvCar addnewCar(@RequestParam("product") String suvCarJson,
                              @RequestParam("imagefile") MultipartFile[] multipartFiles){


        try {

            SuvCar suvCar = new ObjectMapper().readValue(suvCarJson,SuvCar.class);

            Set<ImageModelSuvCar> images = uploadImage(multipartFiles,suvCar);
            suvCar.setProductImages(images);

            return suvCarService.addnewProducts(suvCar);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }

    public Set<ImageModelSuvCar> uploadImage(MultipartFile[] multipartFiles, SuvCar suvCar)throws IOException {

        Set<ImageModelSuvCar> imageModelSuvCars = new HashSet<>();
        int count = 1;

        for (MultipartFile file:multipartFiles) {

            ImageModelSuvCar imageModelSuvCar = new ImageModelSuvCar();
            imageModelSuvCar.setImageLink(fileUploadUtilService.
                    handleMediaUpload(suvCar.getModelname().trim().replace(" ", "_") + "_" + count + "_image", file));
            count++;

            imageModelSuvCars.add(imageModelSuvCar);
        }

        return imageModelSuvCars;
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
