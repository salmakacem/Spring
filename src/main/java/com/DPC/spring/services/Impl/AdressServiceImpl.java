package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.Adress;
import com.DPC.spring.entities.UserDetails;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.AdressRepository;
import com.DPC.spring.repositories.UserDetailsRepository;
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

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public AdressServiceImpl(MappersDto mappersDto) {
        this.mappersDto = mappersDto;
    }



    @Override
    public AdressDto saveNewAdressDto(AdressDto adressDto) {

        Adress adresse = mappersDto.AdressDtoToAdress(adressDto);
        Adress saveadress = adressRepository.save(adresse);
        AdressDto adress1 = mappersDto.AdressToAdressDto(saveadress);

        return adress1;
    }

    @Override
    public AdressDto findAdressByID(long id) {
        Adress adressData = this.adressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: evenement is not found."));
        return mappersDto.AdressToAdressDto(adressData);
    }
    @Override
    public AdressDto getAdressByIdUser(long id) {

        Adress adress = this.adressRepository.findByUserDetails(id).orElseThrow(() -> new ResourceNotFoundException("Error: UserDetails is not found."));
        return mappersDto.AdressToAdressDto(adress);
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
        Optional<UserDetails> userData = this.userDetailsRepository.findUserDetailsByAdress(adressData.get());
        if (userData.isPresent()) {
            UserDetails existingUser = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
            existingUser.setAdress(null);
            this.userDetailsRepository.saveAndFlush(existingUser);
        }

        if (adressData.isPresent()) {

            this.adressRepository.deleteById(id);
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
           existingAdress.setGouvernorat(adressDto.getGouvernorat());
           existingAdress.setZip(adressDto.getZip());
           existingAdress.setWork_adress(adressDto.getWork_adress());
           existingAdress.setHome_adress(adressDto.getHome_adress());
           existingAdress.setCity_name(adressDto.getCity_name());





            this.adressRepository.save(existingAdress);


            return "adress updated successfully!";
        }
        else {
            throw new ResourceNotFoundException("adress not found");
        }

    }

    public AdressDto findAdresseByUser(long idUserd)
    {
        Optional<UserDetails> userData = this.userDetailsRepository.findById(idUserd);
        UserDetails existingUser = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
//      System.out.println(existingUser.getEmail());
        Optional<Adress> adressData = this.adressRepository.findByUserDetails(existingUser.getId());
        Adress adress = adressData.orElseThrow(() -> new ResourceNotFoundException("User details not found"));
        return mappersDto.AdressToAdressDto(adress);
    }
}
