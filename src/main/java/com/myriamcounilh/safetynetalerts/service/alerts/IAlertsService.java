package com.myriamcounilh.safetynetalerts.service.alerts;

import java.util.List;

public interface IAlertsService {
    List<String> getEmail(String city);

    List<String> getPhoneAlert(Integer station);
}
