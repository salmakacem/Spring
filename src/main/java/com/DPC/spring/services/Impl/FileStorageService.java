//package com.DPC.spring.services.Impl;
//
//import com.DPC.spring.entities.ImageModel;
//import com.DPC.spring.repositories.ImageRepository;
//import com.DPC.spring.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.stream.Stream;
//import java.util.zip.Deflater;
//@Service
//public class FileStorageService {
//    @Autowired
//    private ImageRepository imageRepository;
//
//
//    public ImageModel store(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        ImageModel imageModel = new ImageModel(fileName, file.getContentType(), compressBytes(file.getBytes()));
//
//        return imageRepository.save(imageModel);
//    }
//
//    public ImageModel getFile(String id) {
//        return imageRepository.findById(id).get();
//
//    }
//
//    // compress the image bytes before storing it in the database
//    public static byte[] compressBytes(byte[] data) {
//        Deflater deflater = new Deflater();
//        deflater.setInput(data);
//        deflater.finish();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        while (!deflater.finished()) {
//            int count = deflater.deflate(buffer);
//            outputStream.write(buffer, 0, count);
//        }
//        try {
//            outputStream.close();
//        } catch (IOException e) {
//        }
//        return outputStream.toByteArray();
//    }
//
//}
