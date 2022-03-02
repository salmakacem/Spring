package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.UserDetailsDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.Adress;
import com.DPC.spring.entities.ERole;
import com.DPC.spring.entities.User;
import com.DPC.spring.entities.UserDetails;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.AdressRepository;
import com.DPC.spring.repositories.UserDetailsRepository;
import com.DPC.spring.services.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    AdressRepository adressRepository;

    final MappersDto mappersDto;

    public UserDetailsServiceImpl(MappersDto mappersDto) {

        this.mappersDto = mappersDto;
    }

    @Override
    public UserDetailsDto saveNewUserDetailsDto(UserDetailsDto userDetailsDto) {

       UserDetails userDetails = mappersDto.UserDetailsDtoToUserDetails(userDetailsDto);
       UserDetails saveUserDetails = userDetailsRepository.save(userDetails);
       UserDetailsDto userDetailsDto1 = mappersDto.UserDetailsToUserDetailsDto(saveUserDetails);

        return userDetailsDto1;
    }

    @Override
    public List<UserDetailsDto> getAllUserDetailsDto() {

        List<UserDetails> listUserDetailsfiltre  =this.userDetailsRepository.findAll();
        return listUserDetailsfiltre
                .stream()
                .map(mappersDto::UserDetailsToUserDetailsDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserDetailsDto findUserDtoByID(long id) {

        UserDetails userDetailsData = this.userDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: UserDetails is not found."));
        return mappersDto.UserDetailsToUserDetailsDto(userDetailsData);
    }

    public UserDetails saveNewDetails(UserDetails userDetails)
    {
        return this.userDetailsRepository.save(userDetails);
    }

    public List<UserDetails> getAllUserDetails()
    {

        return this.userDetailsRepository.findAll();
    }
    @Override
    public UserDetails findUserDetailsByID(long id)
    {
        Optional<UserDetails> userDetailsData = this.userDetailsRepository.findById(id);
        // Return statement if user exist or null
        return userDetailsData.orElseThrow(() -> new ResourceNotFoundException("User details not found"));

    }


    public String updateUserDetailsByID(long id, UserDetails userDetails)
    {
        Optional<UserDetails> userDetailsData = this.userDetailsRepository.findById(id);
        if (userDetailsData.isPresent()) {
            UserDetails existingUserDetails = userDetailsData.orElse(null);
            existingUserDetails.setAge(userDetails.getAge());
                     // save existingUser in the database
            this.userDetailsRepository.save(existingUserDetails);
            // return statement
            return "User details updated successfully!";
        } else {
            throw new ResourceNotFoundException("User details not found");
        }
    }

    public String deleteUserDetailsById(long id)
    {
        Optional<UserDetails> userDetailsData = this.userDetailsRepository.findById(id);
        if (userDetailsData.isPresent()) {
            this.userDetailsRepository.deleteById(id);
            return "User details deleted successfully!";
        } else {
            throw new ResourceNotFoundException("User details not found");
        }
    }
    @Override
    public String affectDetailsToAdres(long idAdress, long idDetails)
    {


        Optional<UserDetails> userDetailsData = this.userDetailsRepository.findById(idDetails);
        if (userDetailsData.isPresent()) {
            UserDetails existingUserDetails = userDetailsData.orElseThrow(() -> new ResourceNotFoundException("Details not found"));
            Optional<Adress> adressData = this.adressRepository.findById(idAdress);
            if (adressData.isPresent()) {
                Adress existingAdress = adressData.orElseThrow(() -> new ResourceNotFoundException("Adress not found"));
                //  existingAdress.setUserDetails(existingUserDetails);
                existingUserDetails.setAdress(existingAdress);
                this.userDetailsRepository.save(existingUserDetails);
                this.adressRepository.save(existingAdress);
            }
        }
        return "Details affected to details successfully!";
    }

}
