package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.User;
import com.DPC.spring.entities.UserDetails;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.UserRepository;
import com.DPC.spring.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

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
    public UserDto findUserDtoByID(long id) {

        User userData = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: User is not found."));
        return mappersDto.UserToUserDto(userData);
    }

    @Override
    public User saveNewUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User findUserByID(long id) {
        return null;
    }

    @Override
    public String updateUserByID(long id, User user) {
        return null;
    }

    @Override
    public String deleteUserById(long id) {
        return null;
    }

    @Override
    public String affectUserToRole(long idUser, long idRole) {
        return null;
    }

    @Override
    public String affectUserToUserDetails(long idUser, long idDetails) {
        return null;
    }
    @Override

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
