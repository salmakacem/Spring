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
        Evenement saveEvent = evenementRepository.save(evenement);
        EvenementDto evenementDto1 = mappersDto.EvenementToEvenementDto(saveEvent);
        return evenementDto1;
    }

    @Override
    public List<EvenementDto> getAllEventDto() {
        List<Evenement> listEventfiltre = this.evenementRepository.findAll();
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
    public String UpdateById(EvenementDto evenementDto, long id) {
        Optional<Evenement> eventData = this.evenementRepository.findById(id);
        if (eventData.isPresent()) {
            Evenement existingEvenement = eventData.orElseThrow(() -> new ResourceNotFoundException("Evenement not found"));
            existingEvenement.setBudget(evenementDto.getBudget());
            existingEvenement.setEvent_name(evenementDto.getEvent_name());
            existingEvenement.setDescriptionEvent(evenementDto.getDescriptionEvent());
            existingEvenement.setStatut(evenementDto.getStatut());
            existingEvenement.setDate(evenementDto.getDate());

            this.evenementRepository.save(existingEvenement);


            return "evenement updated successfully!";
        }
        else {
            throw new ResourceNotFoundException("evenement not found");
        }

    }
    public String deleteEventById(long id)
    {
        Optional<Evenement> eventData = this.evenementRepository.findById(id);
        if (eventData.isPresent()) {
            this.evenementRepository.deleteById(eventData.get().getId());
            return "Event deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Event not found");
        }
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


