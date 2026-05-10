package com.ayan.OfflineUpi.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.util.Base64;

// RSA utility for key generation and AES key encryption/decryption
public class RSAUtil {

    // Generates a new RSA key pair
    public static KeyPair generateKeyPair() throws Exception {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        // Initialize with 2048 bits for security
        generator.initialize(2048);

        // Generate the key pair
        return generator.generateKeyPair();
    }

    // Encrypts AES key with RSA public key
    public static String encryptAESKey(
            SecretKey secretKey,
            PublicKey publicKey
    ) throws Exception {

        // Set up RSA cipher for encryption
        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Extract AES key bytes
        byte[] aesKeyBytes = secretKey.getEncoded();

        // Encrypt the bytes
        byte[] encryptedBytes = cipher.doFinal(aesKeyBytes);

        // Encode to Base64 string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypts AES key with RSA private key
    public static SecretKey decryptAESKey(
            String encryptedAESKey,
            PrivateKey privateKey
    ) throws Exception {

        // Set up RSA cipher for decryption
        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // Decode Base64 to bytes
        byte[] encryptedBytes =
                Base64.getDecoder().decode(encryptedAESKey);

        // Decrypt the bytes
        byte[] decryptedBytes =
                cipher.doFinal(encryptedBytes);

        // Rebuild AES key from bytes
        return new SecretKeySpec(
                decryptedBytes,
                0,
                decryptedBytes.length,
                "AES"
        );
    }
}