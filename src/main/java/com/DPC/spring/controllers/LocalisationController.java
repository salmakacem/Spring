package com.DPC.spring.controllers;


import com.DPC.spring.entities.Localisation;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.services.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("local")
public class LocalisationController {

    @Autowired
    LocalisationService localisationService;

    @PostMapping("/")
    public ResponseEntity<Localisation> saveNewLocal(@RequestBody Localisation localisation)
    {
        Localisation savedLocal =  this.localisationService.saveNewLocal(localisation);
        return new ResponseEntity<>(savedLocal, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Localisation>> getAllLocalisations()
    {
        List<Localisation> listLocal = this.localisationService.getAllLocalisations();
        return new ResponseEntity<>(listLocal, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findLocalisationsByID(@PathVariable("id") long id)
    {
        Localisation local = this.localisationService.findLocalisationsByID(id);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateLocalisationsByID(@PathVariable("id") long id, @RequestBody Localisation localisation)
    {
        String message = this.localisationService.updateLocalisationsByID(id, localisation);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteLocalisationById(@PathVariable("id") long id)
    {
        String message = this.localisationService.deleteLocalisationById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }


    @PutMapping("/affect-adress/{idAdress}/{idLocal}")
    public ResponseEntity<MessageResponse> affectLocalisationToAdres(long idAdress, long idLocal) {
        String message = this.localisationService.affectLocalisationToAdres(idAdress, idLocal);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
