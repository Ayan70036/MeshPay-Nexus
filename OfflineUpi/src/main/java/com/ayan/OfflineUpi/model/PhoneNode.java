package com.ayan.OfflineUpi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Represents a phone node in the mesh network
@Data
public class PhoneNode {

    // Unique identifier for the phone
    private String phoneId;

    // Whether the phone has internet access
    private boolean internetEnabled;

    // List of nearby phones in the mesh
    private List<PhoneNode> nearbyPhones =
            new ArrayList<>();

    // Packets stored on this phone
    private List<TransactionPacket> storedPackets =
            new ArrayList<>();

    // Set of packet IDs already seen to prevent loops
    private Set<String> seenPackets =
            new HashSet<>();
}