package com.DPC.spring.controllers;

import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.entities.Role;
import com.DPC.spring.entities.User;
import com.DPC.spring.entities.UserDetails;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.services.UserDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-details")
@CrossOrigin("*")
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;


    @GetMapping("/fin/{iduser}")
    public ResponseEntity<?> findUserDetailsByUser(@PathVariable("iduser") long iduser){
        UserDetailsDto userDetailsData = this.userDetailsService.findUserDetailsByUser(iduser);
        return new ResponseEntity<>(userDetailsData, HttpStatus.OK);
    }

    @PostMapping("/ajoutd")
    public ResponseEntity<?> saveUserDDto(@RequestBody UserDetailsDto userDetailsDto){
        UserDetailsDto savedUser =  this.userDetailsService.saveNewUserDetailsDto(userDetailsDto);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }
    @PutMapping("/Dto/{id}")
    public ResponseEntity<MessageResponse> updateUserDto(@RequestBody UserDetailsDto userDetailsDto , @PathVariable("id") long id){
        String message = this.userDetailsService.UpdateByIdDto(userDetailsDto,id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);

    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<UserDetailsDto>> getAll()
    {
        List<UserDetailsDto> listUsersDetails = this.userDetailsService.getAllUserDetailsDto();
        return new ResponseEntity<>(listUsersDetails, HttpStatus.OK);
    }


    @PostMapping("/ajoutdetails")
    public ResponseEntity<UserDetails> saveNewDetails(@RequestBody UserDetails userDetails)
    {
        UserDetails savedUserDetails =  this.userDetailsService.saveNewDetails(userDetails);
        return new ResponseEntity<>(savedUserDetails, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDetails>> getAllUserDetails()
    {
        List<UserDetails> listUsersDetails = this.userDetailsService.getAllUserDetails();
        return new ResponseEntity<>(listUsersDetails, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findUserDetailsByID(@PathVariable("id") long id)
    {
        UserDetails userDetails = this.userDetailsService.findUserDetailsByID(id);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping("/detailByIdUser/{id}")
    public ResponseEntity<UserDetailsDto> getDetailsByIdUser(@PathVariable("id") long id)
    {
        UserDetailsDto userDetails = this.userDetailsService.getDetailsByIdUser(id);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUserDetailsByID(@PathVariable("id") long id, @RequestBody UserDetails userDetails)
    {
        String message = this.userDetailsService.updateUserDetailsByID(id, userDetails);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUserDetailsById(@PathVariable("id") long id)
    {
        String message = this.userDetailsService.deleteUserDetailsById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @PutMapping("/affect-adress/{idAdres}/{idDetails}")
    public ResponseEntity<MessageResponse> affectDetailsToAdres(long idAdress, long idDetails) {
        String message = this.userDetailsService.affectDetailsToAdres(idAdress, idDetails);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }


}
