package com.DPC.spring.controllers;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.entities.ImageModel;
import com.DPC.spring.entities.ResetPassword;
import com.DPC.spring.entities.User;
import com.DPC.spring.payload.responses.MessageResponse;
import com.DPC.spring.repositories.UserRepository;
import com.DPC.spring.services.MailService;
import com.DPC.spring.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("user/{id}")
    public ResponseEntity<?> findUserDto(@PathVariable("id") long id){
        UserDto userData = this.userService.findUserDtoByID(id);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }


    @PostMapping("/ajout")
    public ResponseEntity<?> saveUserDDto(@RequestBody UserDto userDto){

        UserDto savedUser =  this.userService.saveNewUserDto(userDto);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);


    }



    @PutMapping("/update/{id}")

    public ResponseEntity<MessageResponse> updateUserDto(@RequestBody UserDto userDto , @PathVariable("id") long id){
        String message = this.userService.UpdateByIdDto(userDto,id);
       return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);

    }



    @GetMapping("/GetAllU")
    public ResponseEntity<List<UserDto>> getAllUser()
    {
        List<UserDto> listuSERS = this.userService.getAllUsersDto();
        return new ResponseEntity<>(listuSERS, HttpStatus.OK);
    }


    @GetMapping("/find/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable("email") String email)
    {
        UserDto userDto = this.userService.findUserByEmail(email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }




    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> listUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserByID(@PathVariable("id") long id)
    {
        User user = this.userService.findUserByID(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<MessageResponse> updateUserByID(@PathVariable("id") long id, @RequestBody User user)
//    {
//        String message = this.userService.updateUserByID(id, user);
//        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
//    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable("id") long id)
    {
        String message = this.userService.deleteUserById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    // Affecter Role to user
    @PutMapping("/affect-role/{idUser}/{idRole}")
    public ResponseEntity<MessageResponse> affectUserToRole(long idUser, long idRole) {
        String message = this.userService.affectUserToRole(idUser, idRole);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    // Affecter Details to user
    @PutMapping("/affect-details/{idUser}/{idDetails}")
    public ResponseEntity<MessageResponse> affectUserToUserDetails(long idUser, long idDetails) {
        String message = this.userService.affectUserToDetails(idUser, idDetails);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @PostMapping("/upload_photo_c")
    public User uplaodImage(@RequestParam("id") long id,@RequestPart("file") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        // ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
        //      compressBytes(file.getBytes()));
        //  imageRepository.save(img);
        User cli= this.userService.findUserByID(id);
        cli.setPic(file.getOriginalFilename());
        cli.setPictype(file.getContentType());
        cli.setPicByte(compress(file.getBytes()));
        userRepository.save(cli);
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

//    @RequestMapping(value="/clients/resetPassword",method=RequestMethod.POST)
//    public boolean resetPassword(@RequestBody ResetPassword passwordReset ,@RequestParam String email){
//        Optional<User> existeClient=userRepository.findOneByEmailIgnoreCase(email);
//
//        if (!existeClient.get().getpassword().equals(passwordReset.getPasswordA())){
//            return false ;
//        }
//        else {
//            existeClient.get().setpassword(passwordReset.getPasswordN());
//            userService.AjoutClient(existeClient.get());
//            return true;
//        }
//    }

    @PostMapping(path = "/clients/resetPassword")
    public String changePassword(@RequestParam String email ,@RequestParam String currentPassword,@RequestParam String newPassword) {
        return   this.userService.changePassword(email,currentPassword,newPassword);
    }

}
