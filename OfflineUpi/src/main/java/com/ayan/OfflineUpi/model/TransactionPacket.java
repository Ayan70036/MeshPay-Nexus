package com.ayan.OfflineUpi.model;

import lombok.Data;

// Model for transaction packets in the mesh network
@Data
public class TransactionPacket {

    // Unique ID for the packet
    private String packetId;

    // AES-encrypted transaction data
    private String ciphertext;

    // RSA-encrypted AES key
    private String encryptedAESKey;

    // When the packet was created
    private long timestamp;

    // Time to live for propagation
    private int ttl;

    // Unique nonce for security
    private String nonce;

    // Creates a copy of the packet
    public TransactionPacket copy() {

        TransactionPacket copy =
                new TransactionPacket();

        copy.setPacketId(
                this.packetId
        );

        copy.setCiphertext(
                this.ciphertext
        );

        copy.setEncryptedAESKey(
                this.encryptedAESKey
        );

        copy.setTimestamp(
                this.timestamp
        );

        copy.setTtl(
                this.ttl
        );

        copy.setNonce(
                this.nonce
        );

        return copy;
    }
}