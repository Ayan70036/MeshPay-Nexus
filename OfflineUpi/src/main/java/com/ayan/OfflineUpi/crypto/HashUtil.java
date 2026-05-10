package com.ayan.OfflineUpi.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

// Utility for hashing data with SHA-256
public class HashUtil {

    // Computes SHA-256 hash of the input string
    public static String sha256(String data) throws Exception {

        // Set up SHA-256 digest
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Hash the data bytes
        byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

        // Build hex string from bytes
        StringBuilder builder = new StringBuilder();

        // Convert each byte to hex
        for(byte b : hashBytes){

            builder.append(String.format("%02x", b));
        }

        // Return the final hash
        return builder.toString();
    }
}