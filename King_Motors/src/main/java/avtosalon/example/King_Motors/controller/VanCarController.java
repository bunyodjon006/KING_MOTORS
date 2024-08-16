package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.FileUploadUtilService;
import avtosalon.example.King_Motors.model.*;
import avtosalon.example.King_Motors.repository.VanCarRepository;
import avtosalon.example.King_Motors.service.VanCarService;
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
public class VanCarController {
    @Autowired
    FileUploadUtilService fileUploadUtilService;
    @Autowired
    VanCarService vanCarService;
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
    @PostMapping("/addnewvancarProducts")
    public VanCar addnewCar(@RequestParam("product") String vancarJson,
                            @RequestParam("imagefile") MultipartFile[] multipartFiles){


        try {

            VanCar vanCar = new ObjectMapper().readValue(vancarJson,VanCar.class);

            Set<ImageModelVanCar> images = uploadImage(multipartFiles,vanCar);
            vanCar.setProductImages(images);

            return vanCarService.addnewProducts(vanCar);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }

    public Set<ImageModelVanCar> uploadImage(MultipartFile[] multipartFiles, VanCar vanCar)throws IOException {

        Set<ImageModelVanCar> imageModelVanCars = new HashSet<>();
        int count = 1;

        for (MultipartFile file:multipartFiles) {

            ImageModelVanCar imageModelVanCar = new ImageModelVanCar();
            imageModelVanCar.setImageLink(fileUploadUtilService.
                    handleMediaUpload(vanCar.getModelname().trim().replace(" ", "_") + "_" + count + "_image", file));
            count++;

            imageModelVanCars.add(imageModelVanCar);
        }

        return imageModelVanCars;
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
