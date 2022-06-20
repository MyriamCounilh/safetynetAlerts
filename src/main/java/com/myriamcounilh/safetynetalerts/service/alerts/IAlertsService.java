package com.myriamcounilh.safetynetalerts.service.alerts;

import com.myriamcounilh.safetynetalerts.dto.ChildAlertDTO;

import java.util.Set;

public interface IAlertsService {

    Set<String> getEmail(String city);

    Set<String> getPhone(Integer station);

    ChildAlertDTO getChildAlert(String address);
}
