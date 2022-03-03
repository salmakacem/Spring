package com.DPC.spring.controllers;

import com.DPC.spring.DTO.ArchiveDto;
import com.DPC.spring.DTO.EvenementDto;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.services.ArchiveService;
import com.DPC.spring.services.EvenementService;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.ArchiveDto;
import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.services.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping
public class ArchiveController {

    @Autowired
    ArchiveService archiveService;

    @PostMapping("/")
    public ResponseEntity<?> saveArchiveDto(@RequestBody ArchiveDto archiveDto) {
        ArchiveDto savedArchive = this.archiveService.saveNewArchiveDto(archiveDto);
        return new ResponseEntity<>(savedArchive, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<ArchiveDto>> getAllArchive() {
        List<ArchiveDto> listArchive = this.archiveService.getAllArchiveDto();
        return new ResponseEntity<>(listArchive, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findARCHIVEByID(@PathVariable("id") long id) {
        ArchiveDto archiveDto = this.archiveService.findArchiveByID(id);
        return new ResponseEntity<>(archiveDto, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> archiver(long idEvent){
        String EventData = this.archiveService.affectEventToArchive(idEvent);
        return new ResponseEntity<>(new MessageResponse(EventData), HttpStatus.OK);

    }
}



