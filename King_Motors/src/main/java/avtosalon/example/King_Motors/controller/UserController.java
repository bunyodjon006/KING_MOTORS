package avtosalon.example.King_Motors.controller;

import avtosalon.example.King_Motors.ExceptionNot;
import avtosalon.example.King_Motors.model.TruckCar;
import avtosalon.example.King_Motors.model.User;
import avtosalon.example.King_Motors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable Long id){
        return userRepository.findById(id);
    }
    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<User> getAllUser(@PathVariable Long id, @RequestBody User userdetails){
        User user =userRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
          user.setFirstname(userdetails.getFirstname());
          user.setAddrees(userdetails.getAddrees());
          user.setEmail(userdetails.getEmail());
          user.setName(userdetails.getName());
          user.setKey(userdetails.getKey());
          User update =userRepository.save(user);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()-> new ExceptionNot("not found id"+id));
        userRepository.delete(user);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
