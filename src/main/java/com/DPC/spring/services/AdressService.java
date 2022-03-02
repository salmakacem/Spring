package com.DPC.spring.services;

import com.DPC.spring.DTO.AdressDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AdressService {
    AdressDto saveNewAdressDto(AdressDto adressDto);
    List<AdressDto> getAllAdressDto();


    String deleteAdressById(long id);
    String UpdateById(AdressDto adressDto, long id);

    AdressDto findAdressByID(long id);
}
