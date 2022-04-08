package com.DPC.spring.controllers;


import com.DPC.spring.entities.ImageModel;
import com.DPC.spring.entities.User;
import com.DPC.spring.message.ResponseFile;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.repositories.ImageRepository;
import com.DPC.spring.repositories.UserRepository;
import com.DPC.spring.services.Impl.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static com.DPC.spring.services.Impl.FileStorageService.compressBytes;


@RestController
@RequestMapping(value = "/images")
@CrossOrigin("*")
@Controller
public class ImageController {


    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    FileStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadFile(@RequestPart("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    decompressBytes(dbFile.getData()),
                    dbFile.getType(),
                    decompressBytes(dbFile.getData()).length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<ImageModel> getFile(@PathVariable String id) {
        ImageModel retrievedImage = storageService.getFile(id);


        ImageModel img = new ImageModel(retrievedImage.getName(), retrievedImage.getType(),
                decompressBytes( retrievedImage.getData()));


        return ResponseEntity.ok()

                .body(img);
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }


    /*fonction upload image */
//    @PostMapping("/upload")
//    public String uplaodImage(@RequestPart("imageFile") MultipartFile file, @RequestParam String email) throws IOException {
//        User u = this.userRepository.findByEmail(email);
//        ImageModel img = new ImageModel(null,file.getOriginalFilename(),file.getContentType(),null);
//
//        ImageModel imgexist = imageRepository.findByUser(u);
//        if (imgexist != null) {
//            imageRepository.delete(imgexist);
//            img.setUser(u);
//            imageRepository.save(img);
//        } else {
//
//            img.setUser(u);
//            imageRepository.save(img);
//        }
//        return "success";
//    }
//    /*fonction get image */
////    @GetMapping(path = { "/get/{imageName}" })
////    public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
////
////        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
////        ImageModel img = new ImageModel(null,retrievedImage.get().getName(), retrievedImage.get().getType(),
////          decompressBytes(retrievedImage.get().getPicByte()), null,null);
////        byte[] by = img.getPicByte();
////        return this.decompressBytes(by);
////    }
//
//    @RequestMapping(value = "/imagebyUsername", method = RequestMethod.GET)
//    public byte[] findNameByUtilisateur(String email) throws IOException {
//
//        User user = this.userRepository.findByEmail(email);
//        ImageModel retrievedImage = imageRepository.findByUser(user);
//        System.out.println(retrievedImage.getName());
//        byte[] by = retrievedImage.getPicByte();
//
//        return this.decompressBytes(by);
//
//    }
////@GetMapping(
////        value = "getImage/{imageName:.+}",
////        produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE}
////)
////public  byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException {
//
////    return this.imageService.getImageWithMediaType(fileName);
////}
//
//
//
    // compress the image bytes before soring it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }
//
//    // uncompress the image bytes before returning it to the angular application
//    public static byte[] decompressBytes(byte[] data) {
//        Inflater inflater = new Inflater();
//        inflater.setInput(data);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//
//        byte[] buffer = new byte[1024];
//        try {
//            while (!inflater.finished()) {
//                System.out.println(data.length);
//                int count = inflater.inflate(buffer);
//                outputStream.write(buffer, 0, count);
//            }
//            outputStream.close();
//        } catch (IOException ioe) {
//        } catch (DataFormatException e) {
//        }
//        return outputStream.toByteArray();
//    }
}