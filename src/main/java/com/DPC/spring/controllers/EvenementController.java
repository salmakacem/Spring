package com.DPC.spring.controllers;


import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.EvenementDto;
import com.DPC.spring.entities.Evenement;

import com.DPC.spring.entities.UserDetails;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event")
public class EvenementController {

    @Autowired
    EvenementService evenementService;





    @PostMapping("/")
    public ResponseEntity<?> saveEventDto(@RequestBody EvenementDto evenementDto){
       EvenementDto savedEvent =  this.evenementService.saveNewEventDto(evenementDto);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<EvenementDto>> getAllEvenement()
    {
            List<EvenementDto> listEvent = this.evenementService.getAllEventDto();
            return new ResponseEntity<>(listEvent, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEvenementByID(@PathVariable("id") long id)
    {
        EvenementDto evenementDto = this.evenementService.findEventByID(id);
        return new ResponseEntity<>(evenementDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEventDto(@RequestBody EvenementDto evenementDto,@PathVariable("id") long id){
        String EventData = this.evenementService.UpdateById(evenementDto ,id);
        return new ResponseEntity<>(EventData, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteEvenementById(@PathVariable("id") long id)
    {
        String message = this.evenementService.deleteEventById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }


    @PutMapping("/affect-adress/{idAdress}/{idEvent}")
    public ResponseEntity<MessageResponse> affectEvenementToAdres(long idAdress, long idEvent) {
        String message = this.evenementService.affectEventToAdress(idAdress, idEvent);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
