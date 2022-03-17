package com.DPC.spring.services.Impl;


import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.*;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.AdressRepository;
import com.DPC.spring.repositories.RoleRepository;
import com.DPC.spring.repositories.UserDetailsRepository;
import com.DPC.spring.repositories.UserRepository;
import com.DPC.spring.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.loader.plan.spi.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    AdressRepository adressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    final MappersDto mappersDto;

    public UserServiceImpl(MappersDto mappersDto) {
        this.mappersDto = mappersDto;
    }

    @Override
    public UserDto saveNewUserDto(UserDto userDto) {

        User user = mappersDto.UserDtoToUser(userDto);
        User saveUser = userRepository.save(user);
        UserDto userDto1 = mappersDto.UserToUserDto(saveUser);
                return userDto1;
    }

    @Override
    public List<UserDto> getAllUserDto() {

        List<User> listUser  =this.userRepository.findAll();
        return listUser
                .stream()
                .map(mappersDto::UserToUserDto)
                .collect(Collectors.toList());

    }
    @Override
    public String UpdateByIdDto(UserDto userDto , long id) {
            Optional<User> userData = this.userRepository.findById(id);
            if (userData.isPresent()) {
                User existingUser = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
                existingUser.setFirstName(userDto.getFirstName());
                existingUser.setLastName(userDto.getLastName());
                existingUser.setEmail(userDto.getEmail());
                existingUser.setPassword(userDto.getPassword());

               // Change password if exist
           if(!userDto.getPassword().isEmpty())
            {
                existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
           // save existingUser in the database
            this.userRepository.save(existingUser);
            // return statement
            return "User updated successfully!";
       } else {
            throw new ResourceNotFoundException("User not found");
        }


        }


    @Override
    public UserDto findUserDtoByID(long id) {

        User userData = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: User is not found."));
        return mappersDto.UserToUserDto(userData);
    }

    @Override
    public User saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User findUserByID(long id) {
        Optional<User> userData = this.userRepository.findById(id);
        // Return statement if user exist or throw exception
        return userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }

//    @Override
//    public String updateUserByID(long id, User user)
//    {
//        Optional<User> userData = this.userRepository.findById(id);
//        if (userData.isPresent()) {
//            User existingUser = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
//            existingUser.setFirstName(user.getFirstName());
//            existingUser.setLastName(user.getLastName());
//            existingUser.setEmail(user.getEmail());
//            // Change password if exist
//            if(!user.getPassword().isEmpty())
//            {
//                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
//            }
//            // save existingUser in the database
//            this.userRepository.save(existingUser);
//            // return statement
//            return "User updated successfully!";
//        } else {
//            throw new ResourceNotFoundException("User not found");
//        }
//    }

    @Override
    public String deleteUserById(long id)
    {
        Optional<User> userData = this.userRepository.findById(id);
        if (userData.isPresent()) {
            this.userRepository.deleteById(id);
            return "User deleted successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public String affectUserToRole(long idUser, long idRole) {
        Optional<User> userData = this.userRepository.findById(idUser);
        if (userData.isPresent()) {
            User existingUser = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
            Optional<Role> roleData = this.roleRepository.findById(idRole);
            if (roleData.isPresent()) {
                Role existingRole = roleData.orElseThrow(() -> new ResourceNotFoundException("Role not found"));
                Set<Role> roles = existingUser.getRoles();
                roles.add(existingRole);
                existingUser.setRoles(roles);
                this.userRepository.save(existingUser);
            }
        }
        return "User affected to role successfully!";
    }

    @Override
    public  String affectUserToDetails(long idUser, long idDetails) {
        Optional<User> userData = this.userRepository.findById(idUser);
        if (userData.isPresent()) {
            User existingUser = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
            Optional<UserDetails> detailsData = this.userDetailsRepository.findById(idDetails);
            if (detailsData.isPresent()) {
                UserDetails existingDetails = detailsData.orElseThrow(() -> new ResourceNotFoundException("Details not found"));
                existingDetails.setUser(existingUser);
                existingUser.setDetails(existingDetails);
                this.userRepository.save(existingUser);
                this.userDetailsRepository.save(existingDetails);
            }
        }
        return "User affected to details successfully!";
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        User user = null;
        try {
            user = userRepository.findByEmail(email);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }
}
