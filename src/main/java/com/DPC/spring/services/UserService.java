package com.DPC.spring.services;

import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

public interface UserService {
    UserDto saveNewUserDto(UserDto userDto);
    List<UserDto> getAllUserDto();
    UserDto findUserDtoByID(long id);
    String UpdateByIdDto(UserDto userDto , long id);
    User saveNewUser(User user);
    List<User> getAllUsers();
    User findUserByID( long id);
   // String updateUserByID(@PathVariable("id") long id, @RequestBody User user);
    String deleteUserById( long id);
    String affectUserToRole(long idUser, long idRole);
    String affectUserToDetails(long idUser, long idDetails);
    User findByEmail(String email);


}
