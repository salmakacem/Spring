package com.DPC.spring.services;

import com.DPC.spring.DTO.AdressDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AdressService {
    AdressDto saveNewAdressDto(AdressDto adressDto);
    List<AdressDto> getAllAdressDto();
    AdressDto UpdateById(AdressDto adressDto ,long id);
}
