package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.ConvertibleCar;
import avtosalon.example.King_Motors.model.ImageModel;
import avtosalon.example.King_Motors.repository.ConvertibleCarRepository;
import avtosalon.example.King_Motors.service.ConvertibleCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ConvertibleCarController {


    @Autowired
    ConvertibleCarRepository convertibleCarRepository;
    @Autowired
    ConvertibleCarService convertibleCarService;

    @GetMapping("/convertiblecar")
    public List<ConvertibleCar> getAllConvertibleCar(){
        return convertibleCarRepository.findAll();
    }
    @GetMapping("/convertiblecar/{id}")
    public Optional<ConvertibleCar> getConvertibleCar(@PathVariable Long id) {
        return convertibleCarRepository.findById(id);
    }

    @PostMapping(value= {"/addnewProduct"} ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ConvertibleCar addnewCar(@RequestPart("product") ConvertibleCar convertibleCar,
                                    @RequestPart("imagefile")MultipartFile[] file){
//        return convertibleCarService.addnewProduct(convertibleCar);

        try {
            Set<ImageModel> images= uploadImage(file);
            convertibleCar.setProductImages(images);
          return  convertibleCarService.addnewProduct(convertibleCar);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles)throws IOException {
        Set<ImageModel> imageModels=new HashSet<>();
        for(MultipartFile file:multipartFiles){
            ImageModel imageModel =new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
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
