package com.DPC.spring.controllers;

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
@RequestMapping("archive")
public class ArchiveController {
    @Autowired
    ArchiveService archiveService;

    @PostMapping
    public ResponseEntity<?> saveAdressDto(@RequestBody ArchiveDto archiveDto){
        ArchiveDto savedArchive =  this.archiveService.saveNewArchiveDto(archiveDto);
        return new ResponseEntity<>(savedArchive, HttpStatus.CREATED);

    }
    @GetMapping("/GetAll")
    public ResponseEntity<List<ArchiveDto>> getAllAdress()
    {
        List<ArchiveDto> listArchive = this.archiveService.getAllArchiveDto();
        return new ResponseEntity<>(listArchive, HttpStatus.OK);
    }
    @PutMapping("Dto/{id}")
    public ResponseEntity<MessageResponse> updateUserDto(@RequestBody ArchiveDto archiveDto , @PathVariable("id") long id){
        String message = this.archiveService.UpdateByIdDto(archiveDto,id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);

    }

}
