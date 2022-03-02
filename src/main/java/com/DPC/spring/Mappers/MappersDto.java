package com.DPC.spring.Mappers;

import com.DPC.spring.DTO.*;
import com.DPC.spring.entities.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MappersDto {
    UserDetailsDto UserDetailsToUserDetailsDto(UserDetails userDetails);
    UserDetails UserDetailsDtoToUserDetails(UserDetailsDto userDetailsDto);
    AdressDto AdressToAdressDto(Adress adress);
    Adress AdressDtoToAdress(AdressDto adressDto);
    UserDto UserToUserDto(User user);
    User UserDtoToUser(UserDto userDto);
    EvenementDto EvenementToEvenementDto(Evenement evenement);
    Evenement EvenementDtoToEvenement(EvenementDto evenementDto);


}
