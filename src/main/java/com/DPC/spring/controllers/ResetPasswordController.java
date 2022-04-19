package com.DPC.spring.controllers;

import com.DPC.spring.entities.User;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.UserRepository;
import com.DPC.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping(value = "/pass")
@CrossOrigin("*")
@Controller
public class ResetPasswordController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @PostMapping("/change_password/{token}")
    public Boolean processChangePassword(@PathVariable("token") String token, @RequestBody String password) throws
            ResourceNotFoundException {
        Optional<User> user = userRepository.findByResetPasswordToken(token);

        if ((user.isPresent())&&(password!=null)){
            userService.updatePassword(user.get(),password);
            return true;
        }else {
            return false;
        }

    }
}
