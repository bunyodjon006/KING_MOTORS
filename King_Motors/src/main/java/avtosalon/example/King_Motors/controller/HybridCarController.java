package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.FileUploadUtilService;
import avtosalon.example.King_Motors.model.*;
import avtosalon.example.King_Motors.repository.HybridCarRepository;
import avtosalon.example.King_Motors.service.HybridCarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class HybridCarController {
    @Autowired
    FileUploadUtilService fileUploadUtilService;
    @Autowired
    HybridCarService hybridCarService;
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
    @PostMapping("/addnewhatchbackProducts")
    public HybridCar addnewCar(@RequestParam("product") String hybridCarJson,
                                 @RequestParam("imagefile") MultipartFile[] multipartFiles){


        try {

            HybridCar hybridCar = new ObjectMapper().readValue(hybridCarJson,HybridCar.class);
//            Set<ImageModelHybridCar> images=uploadImage(multipartFiles,hybridCar);
              Set<ImageModelHybridCar> images = uploadImage(multipartFiles,hybridCar);
            hybridCar.setProductImages(images);

            return hybridCarService.addnewProducts(hybridCar);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }

    public Set<ImageModelHybridCar> uploadImage(MultipartFile[] multipartFiles, HybridCar hybridCar)throws IOException {

        Set<ImageModelHybridCar> imageModelsHybridCar = new HashSet<>();
        int count = 1;

        for (MultipartFile file:multipartFiles) {

            ImageModelHybridCar imageModelHybridCar = new ImageModelHybridCar();
            imageModelHybridCar.setImageLink(fileUploadUtilService.
                    handleMediaUpload(hybridCar.getModelname().trim().replace(" ", "_") + "_" + count + "_image", file));
            count++;

            imageModelsHybridCar.add(imageModelHybridCar);
        }

        return imageModelsHybridCar;
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
