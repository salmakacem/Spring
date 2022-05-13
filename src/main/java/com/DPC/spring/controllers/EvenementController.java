package com.DPC.spring.controllers;


import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.EvenementDto;
import com.DPC.spring.entities.Evenement;

import com.DPC.spring.entities.ImageModel;
import com.DPC.spring.entities.User;
import com.DPC.spring.entities.UserDetails;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.repositories.EvenementRepository;
import com.DPC.spring.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

@RestController
@RequestMapping("event")
public class EvenementController {

    @Autowired
    EvenementService evenementService;

    @Autowired
    EvenementRepository evenementRepository;


    @PostMapping("/save")
    public ResponseEntity<?> saveEventDto(@RequestBody EvenementDto evenementDto) {
        EvenementDto savedEvent = this.evenementService.saveNewEventDto(evenementDto);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);

    }

    @GetMapping("/get")
    public ResponseEntity<List<EvenementDto>> getAllEvenement() {
        List<EvenementDto> listEvent = this.evenementService.getAllEventDto();
        return new ResponseEntity<>(listEvent, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEvenementByID(@PathVariable("id") long id) {
        EvenementDto evenementDto = this.evenementService.findEventByID(id);
        return new ResponseEntity<>(evenementDto, HttpStatus.OK);
    }

    @PutMapping("/updat/{id}")
    public ResponseEntity<?> updateEventDto(@RequestBody EvenementDto evenementDto, @PathVariable("id") long id) {
        String EventData = this.evenementService.UpdateById(evenementDto, id);
        return new ResponseEntity<>(EventData, HttpStatus.OK);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteEvenementById(@PathVariable("id") long id) {
        String message = this.evenementService.deleteEventById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }


//    @PutMapping("/affect-adress/{idAdress}/{idEvent}")
//    public ResponseEntity<MessageResponse> affectEvenementToAdres(long idAdress, long idEvent) {
//        String message = this.evenementService.affectEventToAdress(idAdress, idEvent);
//        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
//    }

    @PostMapping("/upload_photo_c")
    public Evenement uplaodImage(@RequestParam("id") long id, @RequestPart("file") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        // ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
        //      compressBytes(file.getBytes()));
        //  imageRepository.save(img);
        Evenement cli= this.evenementService.findByID(id);
        cli.setPic(file.getOriginalFilename());
        cli.setPictype(file.getContentType());
        cli.setPicByte(compress(file.getBytes()));
        evenementRepository.save(cli);
        return cli;
    }

    public static byte[] compress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream defl = new DeflaterOutputStream(out);
            defl.write(in);
            defl.flush();
            defl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(150);
            return null;
        }
    }


    public static byte[] decompress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out);
            infl.write(in);
            infl.flush();
            infl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(150);
            return null;
        }
    }
    @GetMapping(path = { "/get_photo_c/{id}" })
    public ImageModel getImage(@PathVariable("id") Long id) throws IOException {
        Evenement inter= this.evenementService.findByID(id);
        if(inter.getPic()!=null) {
            ImageModel img = new ImageModel(inter.getPic(), inter.getPictype(),
                    decompress(inter.getPicByte()));
            return img;
        }
        else{
            return new ImageModel(null, null,
                    null);
        }
    }

}
