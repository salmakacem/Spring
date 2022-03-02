package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.EvenementDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.Adress;
import com.DPC.spring.entities.Evenement;
import com.DPC.spring.entities.UserDetails;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.AdressRepository;
import com.DPC.spring.repositories.EvenementRepository;
import com.DPC.spring.services.EvenementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    EvenementRepository evenementRepository;

    @Autowired
    AdressRepository adressRepository;

    final MappersDto mappersDto;

    public EvenementServiceImpl(MappersDto mappersDto) {
        this.mappersDto = mappersDto;
    }

    @Override
    public EvenementDto saveNewEventDto(EvenementDto evenementDto) {
            Evenement evenement = mappersDto.EvenementDtoToEvenement(evenementDto);
            Evenement saveEvent=evenementRepository.save(evenement);
            EvenementDto evenementDto1=mappersDto.EvenementToEvenementDto(saveEvent);
            return evenementDto1;
    }

    @Override
    public List<EvenementDto> getAllEventDto() {
        List<Evenement> listEventfiltre  =this.evenementRepository.findAll();
        return listEventfiltre
                .stream()
                .map(mappersDto::EvenementToEvenementDto)
                .collect(Collectors.toList());
    }

    @Override
    public EvenementDto findEventByID(long id) {
        Evenement eventData = this.evenementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: evenement is not found."));
        return mappersDto.EvenementToEvenementDto(eventData);
    }

    @Override
    public EvenementDto UpdateById(EvenementDto evenementDto , long id) {
        Evenement evenement = mappersDto.EvenementDtoToEvenement(evenementDto);
        Evenement saveEvent= evenementRepository.findById(id).get();
        EvenementDto eventDto1=mappersDto.EvenementToEvenementDto(saveEvent);
        return eventDto1;

    }

    public String affectEventToAdress(long idAdress, long idEvent)
    {


        Optional<Evenement> eventData = this.evenementRepository.findById(idEvent);
        if (eventData.isPresent()) {
            Evenement existingEvent = eventData.orElseThrow(() -> new ResourceNotFoundException("Details not found"));
            Optional<Adress> adressData = this.adressRepository.findById(idAdress);
            if (adressData.isPresent()) {
                Adress existingAdress = adressData.orElseThrow(() -> new ResourceNotFoundException("Adress not found"));
                //  existingAdress.setUserDetails(existingUserDetails);
                existingEvent.setAdress(existingAdress);
                this.evenementRepository.save(existingEvent);
                this.adressRepository.save(existingAdress);
            }
        }
        return "Details affected to details successfully!";
    }
}