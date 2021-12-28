package fr.oukilson.backend.controller;

import fr.oukilson.backend.dto.UserCreationDTO;
import fr.oukilson.backend.dto.ResponseDTO;
import fr.oukilson.backend.dto.UserDTO;
import fr.oukilson.backend.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    /*
    declares the beaned service as an attribute then inject it inside the constructor
     */
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/usersus")
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return ResponseEntity.ok(this.userService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.findById(id));
    }
    /**
     * method to save a user in the database
     * @param userCreationDTO the user object to be saved
     * @return a response entity validating the created user
     */
    @PostMapping()
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserCreationDTO userCreationDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(userCreationDTO));
    }

    @PutMapping("/user/add/{id1}/{id2}")
    public ResponseEntity<ResponseDTO> addUserToFriendList(@PathVariable Long id1,
                                                           @PathVariable Long id2) {
        return ResponseEntity.ok(this.userService.addUserToFriendList(id1, id2));
    }
}
