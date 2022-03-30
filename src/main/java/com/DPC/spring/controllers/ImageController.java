package com.DPC.spring.controllers;


import com.DPC.spring.entities.ImageModel;
import com.DPC.spring.entities.User;
import com.DPC.spring.repositories.ImageRepository;
import com.DPC.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@RestController
@RequestMapping(value = "api/images")
public class ImageController {


    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;


    /*fonction upload image */
    @PostMapping("/upload")
    public String uplaodImage(@RequestPart("imageFile") MultipartFile file, String email) throws IOException {
        User u = this.userRepository.findByEmail(email);
        ImageModel img = new ImageModel(null,file.getOriginalFilename(),file.getContentType(),
                compressBytes(file.getBytes()), null, u);
        ImageModel imgexist = imageRepository.findByUser(u);
        if (imgexist != null) {
            imageRepository.delete(imgexist);
            img.setUser(u);
            imageRepository.save(img);
        } else {

            img.setUser(u);
            imageRepository.save(img);
        }
        return "success";
    }
    /*fonction get image */
//    @GetMapping(path = { "/get/{imageName}" })
//    public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
//
//        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
//        ImageModel img = new ImageModel(null,retrievedImage.get().getName(), retrievedImage.get().getType(),
//          decompressBytes(retrievedImage.get().getPicByte()), null,null);
//        byte[] by = img.getPicByte();
//        return this.decompressBytes(by);
//    }

    @RequestMapping(value = "/imagebyUsername", method = RequestMethod.GET)
    public byte[] findNameByUtilisateur(String email) throws IOException {

        User user = this.userRepository.findByEmail(email);
        ImageModel retrievedImage = imageRepository.findByUser(user);
        System.out.println(retrievedImage.getName());
        byte[] by = retrievedImage.getPicByte();

        return this.decompressBytes(by);

    }
//@GetMapping(
//        value = "getImage/{imageName:.+}",
//        produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE}
//)
//public  byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException {

//    return this.imageService.getImageWithMediaType(fileName);
//}



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

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                System.out.println(data.length);
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}