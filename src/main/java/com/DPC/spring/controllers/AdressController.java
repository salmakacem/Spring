package com.DPC.spring.controllers;


import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.EvenementDto;
import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adress")
@CrossOrigin("*" )
public class AdressController {
    @Autowired
    AdressService adressService;




    @GetMapping("/find/{iduser}")
    public ResponseEntity<?> findAdresseByUser(@PathVariable("iduser") long iduser){
        AdressDto adresseData = this.adressService.findAdresseByUser(iduser);
        return new ResponseEntity<>(adresseData, HttpStatus.OK);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<AdressDto>> getAllAdress()
    {
        List<AdressDto> listAdress = this.adressService.getAllAdressDto();
        return new ResponseEntity<>(listAdress, HttpStatus.OK);
    }



    @GetMapping("/trouve/{id}")
    public ResponseEntity<?> findAdressByID(@PathVariable("id") long id)
    {
        AdressDto adressDto = this.adressService.findAdressByID(id);
        return new ResponseEntity<>(adressDto, HttpStatus.OK);
    }
    @GetMapping("/getadress/{id}")
    public ResponseEntity<AdressDto> getAdressByIdUser(@PathVariable("id") long id)
    {
        AdressDto adress = this.adressService.getAdressByIdUser(id);
        return new ResponseEntity<>(adress, HttpStatus.OK);
    }



    @PutMapping("/updat/{id}")
    public ResponseEntity<MessageResponse> updateAdressDto(@RequestBody AdressDto adressDto,@PathVariable("id") long id){
        String message = this.adressService.UpdateById(adressDto,id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);

    }
    @PostMapping("/save")
    public ResponseEntity<?> saveAdressDto(@RequestBody AdressDto adressDto){
        AdressDto savedadress =  this.adressService.saveNewAdressDto(adressDto);

        return new ResponseEntity<>(savedadress, HttpStatus.CREATED);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteAdressById(@PathVariable("id") long id)
    {
        System.out.println(id);
        String message = this.adressService.deleteAdressById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }






}
