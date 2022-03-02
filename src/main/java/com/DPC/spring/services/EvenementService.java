package com.DPC.spring.services;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.EvenementDto;
import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.entities.Evenement;

import java.util.List;

public interface EvenementService {
    EvenementDto saveNewEventDto(EvenementDto evenementDto);
    List<EvenementDto> getAllEventDto();
    EvenementDto findEventByID(long id);
    EvenementDto UpdateById(EvenementDto evenementDto , long id);

    String affectEventToAdress(long idAdress, long idEvent);

}
