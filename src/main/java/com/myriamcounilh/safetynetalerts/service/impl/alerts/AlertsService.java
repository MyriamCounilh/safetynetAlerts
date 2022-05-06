package com.myriamcounilh.safetynetalerts.service.impl.alerts;

import com.myriamcounilh.safetynetalerts.service.IFirestationService;
import com.myriamcounilh.safetynetalerts.service.IPersonService;
import com.myriamcounilh.safetynetalerts.service.alerts.IAlertsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlertsService implements IAlertsService {

    private static final Logger logger = LogManager.getLogger(AlertsService.class);

    private final IPersonService personService;
    private final IFirestationService firestationService;

    public AlertsService(@Autowired IPersonService personService, @Autowired IFirestationService firestationService) {
        this.personService = personService;
        this.firestationService = firestationService;
    }

    @Override
    public List<String> getEmail(String city) {
        List<String> emailOptional = personService.getPerson().stream()
                .filter(person -> person.getCity().equals(city))
                .map(p -> p.getEmail())
                .collect(Collectors.toList());
        logger.debug("Return getEmail with listCity");

        return emailOptional;
    }

    public List<String> getPhone(String address) {
        List<String> phoneOptional = personService.getPerson().stream()
                .filter(person -> person.getAddress().equals(address))
                .map(p -> p.getPhone())
                .collect(Collectors.toList());
        logger.debug("Return getPhone with listPhone");

        return phoneOptional;
    }

    @Override
    public List<String> getPhoneAlert(Integer station) {
        List<String> listPhone = new ArrayList<>();

        firestationService.getFirestation().get(station).forEach(address -> listPhone.addAll(getPhone(address)));

        return listPhone;
    }





}
