package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.EvenementDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.Adress;
import com.DPC.spring.entities.Evenement;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.AdressRepository;
import com.DPC.spring.services.AdressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public AdressDto findAdressByID(long id) {
        Adress adressData = this.adressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: evenement is not found."));
        return mappersDto.AdressToAdressDto(adressData);
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
    public String deleteAdressById(long id)
    {
        Optional<Adress> adressData = this.adressRepository.findById(id);
        if (adressData.isPresent()) {
            this.adressRepository.deleteById(adressData.get().getId());
            return "Adress deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Adress not found");
        }
    }

    @Override
    public String UpdateById(AdressDto adressDto, long id) {
        Optional<Adress> adressData = this.adressRepository.findById(id);
        if (adressData.isPresent()) {
           Adress existingAdress = adressData.orElseThrow(() -> new ResourceNotFoundException("Adress not found"));
           existingAdress.setRegion(adressDto.getRegion());
           existingAdress.setZIP(adressDto.getZIP());
           existingAdress.setWork_adress(adressDto.getWork_adress());
           existingAdress.setHome_adress(adressDto.getHome_adress());
           existingAdress.setCity_name(adressDto.getCity_name());
           existingAdress.setCountry(adressDto.getCountry());


            this.adressRepository.save(existingAdress);


            return "adress updated successfully!";
        }
        else {
            throw new ResourceNotFoundException("adress not found");
        }

    }
}
