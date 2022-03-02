package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.Adress;
import com.DPC.spring.repositories.AdressRepository;
import com.DPC.spring.services.AdressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class AdressServiceImpl implements AdressService {

    final MappersDto mappersDto;

    @Autowired
    AdressRepository adressRepository;

    public AdressServiceImpl(MappersDto mappersDto) {
        this.mappersDto = mappersDto;
    }



    @Override
    public AdressDto saveNewAdressDto(AdressDto adressDto) {
        Adress adress = mappersDto.AdressDtoToAdress(adressDto);
        Adress saveAdress=adressRepository.save(adress);
        AdressDto adressDto1=mappersDto.AdressToAdressDto(saveAdress);
        return adressDto1;
    }

    @Override
    public List<AdressDto> getAllAdressDto() {
        List<Adress> listAdressfiltre  =this.adressRepository.findAll();
        return listAdressfiltre
                .stream()
                .map(mappersDto::AdressToAdressDto)
                .collect(Collectors.toList());

    }

    @Override
    public AdressDto UpdateById(AdressDto adressDto , long id) {
       Adress adress = mappersDto.AdressDtoToAdress(adressDto);
        Adress saveAdress= adressRepository.findById(id).get();
        AdressDto adressDto1=mappersDto.AdressToAdressDto(saveAdress);
        return adressDto1;

    }
}
