package com.ayan.OfflineUpi.service;

import com.ayan.OfflineUpi.model.PhoneNode;
import com.ayan.OfflineUpi.model.TransactionPacket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service for propagating packets in the mesh network
@Service
public class MeshNetworkService {

    @Autowired
    private PacketService packetService;

    // Propagates a packet through the mesh
    public void propagatePacket(
            PhoneNode phone,
            TransactionPacket packet,
            List<String> logs
    ) {

        // Stop if TTL expired
        if(packet.getTtl() <= 0) {

            String log =
                    "Packet Expired";

            System.out.println(log);

            logs.add(log);

            return;
        }

        // Prevent loops
        if(phone.getSeenPackets()
                .contains(packet.getPacketId())) {

            return;
        }

        // Mark packet as seen
        phone.getSeenPackets()
                .add(packet.getPacketId());

        // Log receipt
        String receivedLog =
                phone.getPhoneId()
                        + " received packet";

        System.out.println(receivedLog);

        logs.add(receivedLog);

        // Store packet in phone
        phone.getStoredPackets()
                .add(packet);

        // If internet available
        if(phone.isInternetEnabled()) {

            String uploadLog =
                    phone.getPhoneId()
                            + " uploaded packet to backend";

            System.out.println(uploadLog);

            logs.add(uploadLog);

            boolean processed =
                    packetService.processPacket(packet);

            if(processed) {

                String successLog =
                        "Packet Processed Successfully";

                System.out.println(successLog);

                logs.add(successLog);
            }
            else {

                String failedLog =
                        "Packet Rejected";

                System.out.println(failedLog);

                logs.add(failedLog);
            }

            return;
        }

        // Forward to nearby phones
        for(PhoneNode nearbyPhone :
                phone.getNearbyPhones()) {

            // Create independent copy
            TransactionPacket packetCopy =
                    packet.copy();

            // Reduce TTL for copy
            packetCopy.setTtl(
                    packet.getTtl() - 1
            );

            // Forward the copy
            propagatePacket(
                    nearbyPhone,
                    packetCopy,
                    logs
            );
        }
    }
}