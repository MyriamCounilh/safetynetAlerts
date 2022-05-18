package com.myriamcounilh.safetynetalerts.service.alerts;

import java.util.Set;

public interface IAlertsService {

    Set<String> getEmail(String city);

    Set<String> getPhone(Integer station);
}
