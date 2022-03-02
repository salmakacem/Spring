package com.DPC.spring.controllers;


import com.DPC.spring.DTO.AdressDto;
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
    public ResponseEntity<List<AdressDto>> getAll()
    {
        List<AdressDto> listAdress = this.adressService.getAllAdressDto();
        return new ResponseEntity<>(listAdress, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdressDto(@RequestBody AdressDto adressDto,@PathVariable("id") long id){
        AdressDto AdressData = this.adressService.UpdateById(adressDto,id);
        return new ResponseEntity<>(AdressData, HttpStatus.OK);

    }
    @PostMapping("/")
    public ResponseEntity<?> saveAdressDto(@RequestBody AdressDto adressDto){
        AdressDto savedAdress =  this.adressService.saveNewAdressDto(adressDto);
        return new ResponseEntity<>(savedAdress, HttpStatus.CREATED);

    }






}
