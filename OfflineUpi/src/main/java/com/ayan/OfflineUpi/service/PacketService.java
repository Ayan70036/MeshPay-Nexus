package com.ayan.OfflineUpi.service;

import com.ayan.OfflineUpi.crypto.AESUtil;
import com.ayan.OfflineUpi.crypto.HashUtil;
import com.ayan.OfflineUpi.crypto.RSAUtil;

import com.ayan.OfflineUpi.model.TransactionPacket;
import com.ayan.OfflineUpi.model.TransactionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// Service for creating and processing transaction packets
@Service
public class PacketService {

    @Autowired
    private SettlementService settlementService;

    // Set of hashes of processed packets
    private final Set<String> processedHashes =
            new HashSet<>();

    // RSA key pair for the backend
    private final KeyPair keyPair;

    // Generates RSA keys on startup
    public PacketService() {

        try {

            keyPair =
                    RSAUtil.generateKeyPair();

        }
        catch (Exception e) {

            throw new RuntimeException(
                    "RSA Key Generation Failed"
            );
        }
    }

    // Creates an encrypted packet from a transaction request
    public TransactionPacket createPacket(
            TransactionRequest request
    ) {

        TransactionPacket packet =
                new TransactionPacket();

        // Generate unique packet ID
        packet.setPacketId(
                UUID.randomUUID().toString()
        );

        try {

            // Build plaintext from request
            String plaintext =
                    request.getSender()
                            + "|"
                            + request.getReceiver()
                            + "|"
                            + request.getAmount()
                            + "|"
                            + request.getPin();

            // Generate AES key
            var secretKey =
                    AESUtil.generateKey();

            // Encrypt data with AES
            String encryptedData =
                    AESUtil.encrypt(
                            plaintext,
                            secretKey
                    );

            // Encrypt AES key with RSA
            String encryptedAESKey =
                    RSAUtil.encryptAESKey(
                            secretKey,
                            keyPair.getPublic()
                    );

            // Set encrypted values
            packet.setCiphertext(
                    encryptedData
            );

            packet.setEncryptedAESKey(
                    encryptedAESKey
            );

        }
        catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Hybrid Encryption Failed"
            );
        }

        // Set packet metadata
        packet.setTimestamp(
                System.currentTimeMillis()
        );

        packet.setTtl(50);

        packet.setNonce(
                UUID.randomUUID().toString()
        );

        return packet;
    }

    // Decrypts the packet to get transaction data
    public String decryptPacket(
            TransactionPacket packet
    ) {

        try {

            // Check for required fields
            if(packet.getCiphertext() == null ||
                    packet.getEncryptedAESKey() == null) {

                throw new RuntimeException(
                        "Encrypted Packet Data Missing"
                );
            }

            // Decrypt AES key
            var secretKey =
                    RSAUtil.decryptAESKey(
                            packet.getEncryptedAESKey(),
                            keyPair.getPrivate()
                    );

            // Decrypt transaction data
            return AESUtil.decrypt(
                    packet.getCiphertext(),
                    secretKey
            );

        }
        catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Packet Decryption Failed : "
                            + e.getMessage()
            );
        }
    }

    // Processes and settles the transaction from the packet
    public boolean processPacket(
            TransactionPacket packet
    ) {

        try {

            // Validate packet
            if(packet == null) {

                throw new RuntimeException(
                        "Packet Is Null"
                );
            }

            if(packet.getCiphertext() == null) {

                throw new RuntimeException(
                        "Ciphertext Missing"
                );
            }

            // Create packet hash
            String packetHash =
                    HashUtil.sha256(
                            packet.getCiphertext()
                    );

            // Reject duplicate packets
            if(processedHashes.contains(packetHash)) {

                System.out.println(
                        "Duplicate Packet"
                );

                return false;
            }

            // Decrypt transaction
            String plaintext =
                    decryptPacket(packet);

            System.out.println(
                    "Decrypted Data : "
                            + plaintext
            );

            // Split transaction data
            String[] parts =
                    plaintext.split("\\|");

            if(parts.length != 4) {

                throw new RuntimeException(
                        "Invalid Transaction Format"
                );
            }

            String sender =
                    parts[0];

            String receiver =
                    parts[1];

            double amount =
                    Double.parseDouble(parts[2]);

            String enteredPin =
                    parts[3];

            // Verify PIN
            boolean validPin =
                    settlementService.verifyPin(
                            sender,
                            enteredPin
                    );

            if(!validPin) {

                System.out.println(
                        "Invalid PIN"
                );

                return false;
            }

            // Settle transaction
            boolean settled =
                    settlementService
                            .settleTransaction(
                                    sender,
                                    receiver,
                                    amount
                            );

            if(!settled) {

                System.out.println(
                        "Settlement Failed"
                );

                return false;
            }

            // Store packet hash
            processedHashes.add(packetHash);

            System.out.println(
                    "Settlement Completed"
            );

            return true;

        }
        catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }
}