package com.DPC.spring.controllers;

import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.entities.User;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> findUserDto(@PathVariable("id") long id){
        UserDto userData = this.userService.findUserDtoByID(id);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveUserDDto(@RequestBody UserDto userDto){
        UserDto savedUser =  this.userService.saveNewUserDto(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<UserDto>> getAll()
    {
        List<UserDto> listUsers = this.userService.getAllUserDto();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<User> saveNewUser(@RequestBody User user)
    {
        User savedUser =  this.userService.saveNewUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> listUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserByID(@PathVariable("id") long id)
    {
        User user = this.userService.findUserByID(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUserByID(@PathVariable("id") long id, @RequestBody User user)
    {
        String message = this.userService.updateUserByID(id, user);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable("id") long id)
    {
        String message = this.userService.deleteUserById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    // Affecter Role to user
    @PutMapping("/affect-role/{idUser}/{idRole}")
    public ResponseEntity<MessageResponse> affectUserToRole(long idUser, long idRole) {
        String message = this.userService.affectUserToRole(idUser, idRole);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    // Affecter Details to user
    @PutMapping("/affect-details/{idUser}/{idDetails}")
    public ResponseEntity<MessageResponse> affectUserToUserDetails(long idUser, long idDetails) {
        String message = this.userService.affectUserToUserDetails(idUser, idDetails);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

}
