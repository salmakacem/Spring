package com.DPC.spring.services;

import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.entities.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDetailsService {
    UserDetailsDto saveNewUserDetailsDto(UserDetailsDto userDetailsDto);
    List<UserDetailsDto> getAllUserDetailsDto();
    UserDetailsDto findUserDtoByID(long id);
    String UpdateByIdDto(UserDetailsDto userDetailsDtoDto , long id);
    UserDetails saveNewDetails(UserDetails userDetails);
    List<UserDetails> getAllUserDetails();
    UserDetails findUserDetailsByID(long id);
    String updateUserDetailsByID(long id, UserDetails userDetails);
    String deleteUserDetailsById(long id);
    String affectDetailsToAdres(long idAdress, long idDetails);


}
