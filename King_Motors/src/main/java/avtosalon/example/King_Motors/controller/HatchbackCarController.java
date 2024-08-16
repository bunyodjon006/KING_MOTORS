package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.FileUploadUtilService;
import avtosalon.example.King_Motors.model.*;
import avtosalon.example.King_Motors.repository.HatchbackCarRepository;
import avtosalon.example.King_Motors.service.HatchbackCarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExecutionChain;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class HatchbackCarController {
    @Autowired
    FileUploadUtilService fileUploadUtilService;
    @Autowired
    HatchbackCarService hatchbackCarService;
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
    @PostMapping("/addnewhatchbacProducts")
    public HatchbackCar addnewCar(@RequestParam("product") String hatchbackCarJson,
                                  @RequestParam("imagefile") MultipartFile[] multipartFiles){

        try {
            HatchbackCar hatchbackCar = new ObjectMapper().readValue(hatchbackCarJson, HatchbackCar.class);
            Set<ImageModelHatchbackCar> images = uploadImage(multipartFiles, hatchbackCar);

            hatchbackCar.setProductImages(images);

            return hatchbackCarService.addnewProducts(hatchbackCar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageModelHatchbackCar> uploadImage(MultipartFile[] multipartFiles, HatchbackCar hatchbackCar) throws IOException {
        Set<ImageModelHatchbackCar> imageModelHatchbackCars = new HashSet<>();
        int count = 1;
        for (MultipartFile file : multipartFiles) {
            ImageModelHatchbackCar imageModelHatchbackCar = new ImageModelHatchbackCar();
            imageModelHatchbackCar.setImageLink(fileUploadUtilService.handleMediaUpload(hatchbackCar.getModelname().trim().replace(" ", "_") + "_" + count + "_image", file));

            count++;
            imageModelHatchbackCars.add(imageModelHatchbackCar);
        }

        return imageModelHatchbackCars;
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
