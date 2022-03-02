package com.DPC.spring.controllers;


import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.EvenementDto;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adress")
public class AdressController {
    @Autowired
    AdressService adressService;

    @GetMapping("/GetAll")
    public ResponseEntity<List<AdressDto>> getAllAdress()
    {
        List<AdressDto> listAdress = this.adressService.getAllAdressDto();
        return new ResponseEntity<>(listAdress, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAdressByID(@PathVariable("id") long id)
    {
        AdressDto adressDto = this.adressService.findAdressByID(id);
        return new ResponseEntity<>(adressDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> updateAdressDto(@RequestBody AdressDto adressDto,@PathVariable("id") long id){
        String AdressData = this.adressService.UpdateById(adressDto,id);
        return new ResponseEntity<>(AdressData, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<?> saveAdressDto(@RequestBody AdressDto adressDto){
        AdressDto savedAdress =  this.adressService.saveNewAdressDto(adressDto);
        return new ResponseEntity<>(savedAdress, HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteAdressById(@PathVariable("id") long id)
    {
        String message = this.adressService.deleteAdressById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }






}
