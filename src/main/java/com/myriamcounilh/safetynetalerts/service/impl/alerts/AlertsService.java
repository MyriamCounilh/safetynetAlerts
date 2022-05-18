package com.myriamcounilh.safetynetalerts.service.impl.alerts;

import com.myriamcounilh.safetynetalerts.service.IFirestationService;
import com.myriamcounilh.safetynetalerts.service.IPersonService;
import com.myriamcounilh.safetynetalerts.service.alerts.IAlertsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
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
        firestationService.getFirestations().get(station).forEach(address -> listPhone.addAll(getPhone(address)));
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

}
