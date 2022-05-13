package com.DPC.spring.controllers;

import com.DPC.spring.entities.ImageModel;
import com.DPC.spring.entities.User;
import com.DPC.spring.repositories.UserRepository;
import com.DPC.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

@RestController
@RequestMapping(value = "/images")
@CrossOrigin("*")
@Controller
public class ImageController {


    @Autowired
    UserRepository userRepository;
//    @Autowired
//    ImageRepository imageRepository;
//    @Autowired
//    FileStorageService storageService;
    @Autowired
    UserService userService;
@PostMapping("/upload_photo_c")
public User uplaodImage(@RequestParam("id") long id,@RequestPart("file") MultipartFile file) throws IOException {
    System.out.println("Original Image Byte Size - " + file.getBytes().length);
    // ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
    //      compressBytes(file.getBytes()));
    //  imageRepository.save(img);
    User user= this.userService.findUserByID(id);
    user.setPic(file.getOriginalFilename());
    user.setPictype(file.getContentType());
    user.setPicByte(compress(file.getBytes()));
    userRepository.save(user);
    return user;
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
        User inter= this.userService.findUserByID(id);
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
//    @PostMapping("/upload")
//    public String  uplaodImage(@RequestPart("imageFile") MultipartFile file,String email) throws IOException {
//        User u = this.userRepository.findByEmail(email);
//        System.out.println("Original Image Byte Size - " + file.getBytes().length);
//
//        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
//
//                compressBytes(file.getBytes()));
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
//
//    }
//    @GetMapping(path = { "/get/{imageName}" })
//    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
//
//        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
//
//        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
//
//                decompressBytes(retrievedImage.get().getPicByte()));
//
//        return img;
//
//    }
//
//        // compress the image bytes before storing it in the database
//
//    public static byte[] compressBytes(byte[] data) {
//
//        Deflater deflater = new Deflater();
//
//        deflater.setInput(data);
//
//        deflater.finish();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//
//        byte[] buffer = new byte[1024];
//        while (!deflater.finished()) {
//
//            int count = deflater.deflate(buffer);
//
//            outputStream.write(buffer, 0, count);
//
//        }
//        try {
//
//            outputStream.close();
//
//        } catch (IOException e) {
//
//        }
//
//        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//
//        return outputStream.toByteArray();
//
//    }
//
//        // uncompress the image bytes before returning it to the angular application
//
//    public static byte[] decompressBytes(byte[] data) {
//
//        Inflater inflater = new Inflater();
//
//        inflater.setInput(data);
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//
//        byte[] buffer = new byte[1024];
//
//        try {
//
//            while (!inflater.finished()) {
//
//                int count = inflater.inflate(buffer);
//
//                outputStream.write(buffer, 0, count);
//
//            }
//
//            outputStream.close();
//
//        } catch (IOException ioe) {
//
//        } catch (DataFormatException e) {
//
//        }
//
//        return outputStream.toByteArray();
//
//    }

//    @PostMapping("/upload")
//    public String uploadFile(@RequestPart("file") MultipartFile file,String email) {
//        User u = this.userRepository.findByEmail(email);
//        ImageModel img = new ImageModel(null,file.getOriginalFilename(),file.getContentType(), null,u);
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
//    @GetMapping(path="/img/{id}")
//    public byte[] getPhoto(@PathVariable Long id) throws Exception{
//        User user = userService.findUserByID(id);
//        return Files.readAllBytes(Paths.get("C:/Users/user/OneDrive/Bureau/angular/Front-End/src/assets/img/"+user.getPhoto()));
//
//    }
//
//    @GetMapping("/files/{id}")
//    public ResponseEntity<ImageModel> getFile(@PathVariable String id) {
//        ImageModel retrievedImage = storageService.getFile(id);
//
//
//        ImageModel img = new ImageModel(retrievedImage.getName(), retrievedImage.getType(),
//                decompressBytes( retrievedImage.getData()));
//
//
//        return ResponseEntity.ok()
//
//                .body(img);
//    }
//
//    // uncompress the image bytes before returning it to the angular application
//    public static byte[] decompressBytes(byte[] data) {
//        Inflater inflater = new Inflater();
//        inflater.setInput(data);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        try {
//            while (!inflater.finished()) {
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