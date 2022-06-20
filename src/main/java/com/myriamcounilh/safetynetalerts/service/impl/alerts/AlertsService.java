package com.myriamcounilh.safetynetalerts.service.impl.alerts;

import com.myriamcounilh.safetynetalerts.dto.ChildAlertDTO;
import com.myriamcounilh.safetynetalerts.dto.ChildInfoDTO;
import com.myriamcounilh.safetynetalerts.dto.PersonInfoLiteDTO;
import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.service.IFirestationService;
import com.myriamcounilh.safetynetalerts.service.IMedicalRecordService;
import com.myriamcounilh.safetynetalerts.service.IPersonService;
import com.myriamcounilh.safetynetalerts.service.alerts.IAlertsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlertsService implements IAlertsService {

    private static final Logger logger = LogManager.getLogger(AlertsService.class);

    private final IPersonService personService;
    private final IFirestationService firestationService;
    private final IMedicalRecordService medicalRecordService;

    public AlertsService(@Autowired IPersonService personService, @Autowired IFirestationService firestationService, @Autowired IMedicalRecordService medicalRecordService) {
        this.personService = personService;
        this.firestationService = firestationService;
        this.medicalRecordService = medicalRecordService;
    }

    @Override
    public Set<String> getEmail(String city) {
        Set<String> emailOptional = personService.getPerson().stream()
                .filter(person -> person.getCity().equals(city))
                .map(p -> p.getEmail())
                .collect(Collectors.toSet());
        logger.debug("Return getEmail with listCity");

        return emailOptional;
    }

    @Override
    public Set<String> getPhone(Integer station) {
        Set<String> listPhone = new HashSet<>();
        listPhone.forEach(address -> listPhone.addAll(getPhone(address)));
        return listPhone;
    }

    private Set<String> getPhone(String address) {
        Set<String> phoneOptional = personService.getPerson().stream()
                .filter(person -> person.getAddress().equals(address))
                .map(p -> p.getPhone())
                .collect(Collectors.toSet());
        logger.debug("Return getPhone with listPhone");
       return phoneOptional;
    }

    @Override
    public ChildAlertDTO getChildAlert(String address) {
        List<Person> listPersonsByAddress = personService.getPersonByAdress(address);
        List<ChildInfoDTO> listChildren = new ArrayList<>();
        listPersonsByAddress.forEach(person -> {
            MedicalRecord medicalRecord = medicalRecordService.getMedicalByFirstnameAndLastName(person.getFirstName(), person.getLastName());
            int age = calculateAge(medicalRecord.getBirthdate());
            if (age <= 18) {
                ChildInfoDTO childInfoDTO = new ChildInfoDTO();
                childInfoDTO.setFirstname(person.getFirstName());
                childInfoDTO.setLastname(person.getLastName());
                childInfoDTO.setAge(age);
                List<PersonInfoLiteDTO> listeFamilyMember = personService.getFamilyMember(person.getAddress(), person.getLastName())
                        .stream()
                        .map(familyMember -> {
                            PersonInfoLiteDTO personInfoLiteDTO = new PersonInfoLiteDTO();
                            personInfoLiteDTO.setFirstname(familyMember.getFirstName());
                            personInfoLiteDTO.setLastName(familyMember.getLastName());
                            return personInfoLiteDTO;
                        }).collect(Collectors.toList());
                //Enlever l'enfant de la liste de la famille
//
//                List<ChildInfoDTO> listChildrens = listChildren.stream().filter(personInfoLiteDTO -> childInfoDTO.getMemberOfFamily()
//                        .equals(listChildren)).collect(Collectors.toList());

                childInfoDTO.setMemberOfFamily(listeFamilyMember);
                listChildren.add(childInfoDTO);

            }
        });

        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setListChildren(listChildren);

        return childAlertDTO;
    }

    private int calculateAge(String birthdate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Period period = LocalDate.parse(birthdate, format).until(LocalDate.now());

        return period.getYears();
    }




}
