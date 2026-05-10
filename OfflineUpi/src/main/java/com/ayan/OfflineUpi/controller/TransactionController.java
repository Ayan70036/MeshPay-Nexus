package com.ayan.OfflineUpi.controller;

import com.ayan.OfflineUpi.model.PhoneNode;
import com.ayan.OfflineUpi.model.TransactionPacket;
import com.ayan.OfflineUpi.model.TransactionRequest;

import com.ayan.OfflineUpi.service.MeshNetworkService;
import com.ayan.OfflineUpi.service.NetworkStateService;
import com.ayan.OfflineUpi.service.PacketService;
import com.ayan.OfflineUpi.service.SettlementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Controller for transaction operations
@RestController
@RequestMapping("/transaction")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private MeshNetworkService meshNetworkService;

    @Autowired
    private PacketService packetService;

    @Autowired
    private SettlementService settlementService;

    @Autowired
    private NetworkStateService networkStateService;

    // Creates an encrypted transaction packet
    @PostMapping("/create")
    public Map<String, Object> createTransaction(
            @RequestBody TransactionRequest request
    ) {

        Map<String, Object> response =
                new HashMap<>();

        try {

            TransactionPacket packet =
                    packetService.createPacket(request);

            response.put(
                    "status",
                    "SUCCESS"
            );

            response.put(
                    "message",
                    "Encrypted Transaction Packet Created"
            );

            response.put(
                    "packet",
                    packet
            );

            return response;

        }
        catch (Exception e) {

            response.put(
                    "status",
                    "FAILED"
            );

            response.put(
                    "message",
                    e.getMessage()
            );

            return response;
        }
    }

    // Processes a packet directly
    @PostMapping("/process")
    public Map<String, Object> processPacket(
            @RequestBody TransactionPacket packet
    ) {

        Map<String, Object> response =
                new HashMap<>();

        try {

            boolean processed =
                    packetService.processPacket(packet);

            if(processed) {

                response.put(
                        "status",
                        "SUCCESS"
                );

                response.put(
                        "message",
                        "Packet Processed Successfully"
                );
            }
            else {

                response.put(
                        "status",
                        "FAILED"
                );

                response.put(
                        "message",
                        "Packet Rejected"
                );
            }

            return response;

        }
        catch (Exception e) {

            response.put(
                    "status",
                    "FAILED"
            );

            response.put(
                    "message",
                    e.getMessage()
            );

            return response;
        }
    }

    // Tests offline mesh propagation
    @PostMapping("/mesh-test")
    public Map<String, Object> meshTest(
            @RequestBody TransactionPacket packet
    ) {

        Map<String, Object> response =
                new HashMap<>();

        try {

            // Store propagation logs
            List<String> logs =
                    new ArrayList<>();

            // Create phone nodes
            PhoneNode phoneA = new PhoneNode();
            PhoneNode phoneB = new PhoneNode();
            PhoneNode phoneC = new PhoneNode();
            PhoneNode phoneD = new PhoneNode();
            PhoneNode phoneE = new PhoneNode();
            PhoneNode phoneF = new PhoneNode();
            PhoneNode phoneG = new PhoneNode();
            PhoneNode phoneH = new PhoneNode();

            // Assign IDs
            phoneA.setPhoneId("PHONE_A");
            phoneB.setPhoneId("PHONE_B");
            phoneC.setPhoneId("PHONE_C");
            phoneD.setPhoneId("PHONE_D");
            phoneE.setPhoneId("PHONE_E");
            phoneF.setPhoneId("PHONE_F");
            phoneG.setPhoneId("PHONE_G");
            phoneH.setPhoneId("PHONE_H");

            // Set internet states
            phoneA.setInternetEnabled(
                    networkStateService
                            .getInternetState("PHONE_A")
            );

            phoneB.setInternetEnabled(
                    networkStateService
                            .getInternetState("PHONE_B")
            );

            phoneC.setInternetEnabled(
                    networkStateService
                            .getInternetState("PHONE_C")
            );

            phoneD.setInternetEnabled(
                    networkStateService
                            .getInternetState("PHONE_D")
            );

            phoneE.setInternetEnabled(
                    networkStateService
                            .getInternetState("PHONE_E")
            );

            phoneF.setInternetEnabled(
                    networkStateService
                            .getInternetState("PHONE_F")
            );

            phoneG.setInternetEnabled(
                    networkStateService
                            .getInternetState("PHONE_G")
            );

            phoneH.setInternetEnabled(
                    networkStateService
                            .getInternetState("PHONE_H")
            );

            // Define mesh topology
            phoneA.getNearbyPhones().add(phoneB);
            phoneA.getNearbyPhones().add(phoneC);

            phoneB.getNearbyPhones().add(phoneD);
            phoneB.getNearbyPhones().add(phoneE);

            phoneC.getNearbyPhones().add(phoneF);

            phoneD.getNearbyPhones().add(phoneG);

            phoneE.getNearbyPhones().add(phoneG);

            phoneF.getNearbyPhones().add(phoneH);

            phoneG.getNearbyPhones().add(phoneH);

            // Start propagation
            meshNetworkService.propagatePacket(
                    phoneA,
                    packet,
                    logs
            );

            response.put(
                    "status",
                    "SUCCESS"
            );

            response.put(
                    "message",
                    "Offline Mesh Propagation Completed"
            );

            // Return logs
            response.put(
                    "logs",
                    logs
            );

            return response;

        }
        catch (Exception e) {

            response.put(
                    "status",
                    "FAILED"
            );

            response.put(
                    "message",
                    e.getMessage()
            );

            return response;
        }
    }


    // Toggles internet for a phone
    @PostMapping("/toggle-internet/{phoneId}")
    public Map<String, Object> toggleInternet(
            @PathVariable String phoneId,
            @RequestParam boolean enabled
    ) {

        networkStateService.setInternetState(
                phoneId,
                enabled
        );

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "phone",
                phoneId
        );

        response.put(
                "internet",
                enabled
        );

        response.put(
                "status",
                "UPDATED"
        );

        return response;
    }

    // Gets all internet states
    @GetMapping("/internet-states")
    public Object getInternetStates() {

        return networkStateService.getAllStates();
    }

    @GetMapping("/balances")
    public Object getBalances() {

        return settlementService.getAccounts();
    }
}