package com.ayan.OfflineUpi.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

// Service for managing internet states of phones
@Service
public class NetworkStateService {

    // Map of internet states for phones
    private final Map<String, Boolean> internetStates =
            new HashMap<>();

    // Sets default states for phones
    public NetworkStateService() {

        internetStates.put("PHONE_A", false);
        internetStates.put("PHONE_B", false);
        internetStates.put("PHONE_C", false);
        internetStates.put("PHONE_D", false);
        internetStates.put("PHONE_E", false);
        internetStates.put("PHONE_F", false);
        internetStates.put("PHONE_G", false);
        internetStates.put("PHONE_H", true);
    }

    // Gets the internet state of a phone
    public boolean getInternetState(
            String phoneId
    ) {

        return internetStates.getOrDefault(
                phoneId,
                false
        );
    }

    // Updates the internet state
    public void setInternetState(
            String phoneId,
            boolean enabled
    ) {

        internetStates.put(
                phoneId,
                enabled
        );
    }

    // Returns all states
    public Map<String, Boolean> getAllStates() {

        return internetStates;
    }
}