package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.FileUploadUtilService;
import avtosalon.example.King_Motors.model.ConvertibleCar;
import avtosalon.example.King_Motors.model.CrasoverCar;
import avtosalon.example.King_Motors.model.ImageModel;
import avtosalon.example.King_Motors.model.ImageModelCrasover;
import avtosalon.example.King_Motors.repository.CrasoverCarRepository;
import avtosalon.example.King_Motors.service.CrasoverCarService;
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
public class CrasoverCarController {
    @Autowired
    FileUploadUtilService fileUploadUtilService;
    @Autowired
    CrasoverCarService crasoverCarService;
    @Autowired
    CrasoverCarRepository crasoverCarRepository;
    @GetMapping("/crasovercar")
    public List<CrasoverCar> getAllCrasoverCar(){
        return crasoverCarRepository.findAll();
    }
    @GetMapping("/crasovercar/{id}")
    public Optional<CrasoverCar> getCrasover(@PathVariable Long id) {
        return crasoverCarRepository.findById(id);
    }

    @PostMapping("/addnewcrasoverProducts")
    public CrasoverCar addnewCar(@RequestParam("product") String crossoverCarJson,
                                    @RequestParam("imagefile") MultipartFile[] multipartFiles){


        try {

            CrasoverCar crasoverCar = new ObjectMapper().readValue(crossoverCarJson,CrasoverCar.class);
                    Set<ImageModelCrasover> images=uploadImage(multipartFiles,crasoverCar);

                  crasoverCar.setProductImages(images);

            return crasoverCarService.addnewProducts(crasoverCar);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }

    public Set<ImageModelCrasover> uploadImage(MultipartFile[] multipartFiles, CrasoverCar crasoverCar)throws IOException {

        Set<ImageModelCrasover> imageModelsCrasover = new HashSet<>();
        int count = 1;

        for (MultipartFile file:multipartFiles) {

          ImageModelCrasover imageModelCrasover = new ImageModelCrasover();
            imageModelCrasover.setImageLink(fileUploadUtilService.
                    handleMediaUpload(crasoverCar.getModelname().trim().replace(" ", "_") + "_" + count + "_image", file));
            count++;

            imageModelsCrasover.add(imageModelCrasover);
        }

        return imageModelsCrasover;
    }

    @PostMapping("/crasovercar")
    public CrasoverCar createCrasovercar(@RequestBody CrasoverCar crasoverCar){
        return crasoverCarRepository.save(crasoverCar);
    }
    @PutMapping("crasovercar/{id}")
    public ResponseEntity<CrasoverCar> getAllCrasovercar(@PathVariable Long id, @RequestBody CrasoverCar crasovercardetails){
        CrasoverCar crasoverCar =crasoverCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        crasoverCar.setCarbox(crasovercardetails.getCarbox());
        crasoverCar.setCost(crasovercardetails.getCost());
        crasoverCar.setFuel(crasovercardetails.getFuel());
        crasoverCar.setCaryear(crasovercardetails.getCaryear());
        crasoverCar.setInformationcar(crasovercardetails.getInformationcar());
        crasoverCar.setModelname(crasovercardetails.getModelname());
        CrasoverCar update =crasoverCarRepository.save(crasoverCar);
        return ResponseEntity.ok(crasoverCar);
    }
    @DeleteMapping("/crasovercar/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteCrasoverCar(@PathVariable Long id){
        CrasoverCar crasoverCar=crasoverCarRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        crasoverCarRepository.delete(crasoverCar);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
