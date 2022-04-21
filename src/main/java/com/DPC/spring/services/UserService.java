package com.DPC.spring.services;

import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto saveNewUserDto(UserDto userDto);
    List<UserDto> getAllUserDto();
    UserDto findUserDtoByID(long id);
    String UpdateByIdDto(UserDto userDto , long id);

    List<User> getAllUsers();
    User findUserByID( long id);
   // String updateUserByID(@PathVariable("id") long id, @RequestBody User user);
    String deleteUserById( long id);
    String affectUserToRole(long idUser, long idRole);
    String affectUserToDetails(long idUser, long idDetails);

    User findByEmail(String email);


    List<UserDto> getAllUsersDto();
    UserDto findUserByEmail(String email);
    String changePassword(String email ,String currentClearTextPassword, String newPassword);
    void updatePassword (User user, String newPassword);



}
