package com.ayan.OfflineUpi.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

// AES utility for encryption and decryption
public class AESUtil {

    // AES algorithm name
    private static final String AES = "AES";

    // Creates a random AES key
    public static SecretKey generateKey() throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);

        // Set up for AES-128
        keyGenerator.init(128);

        return keyGenerator.generateKey();
    }

    // Encrypts text with AES key
    public static String encrypt(String plaintext, SecretKey secretKey) throws Exception {

        Cipher cipher = Cipher.getInstance(AES);

        // Initialize cipher for encryption
        cipher.init(
                Cipher.ENCRYPT_MODE,
                secretKey
        );

        // Encrypt and get bytes
        byte[] encryptedBytes =
                cipher.doFinal(
                        plaintext.getBytes()
                );

        // Encode to Base64 string
        return Base64
                .getEncoder()
                .encodeToString(encryptedBytes);
    }

    // Decrypts text with AES key
    public static String decrypt(String ciphertext, SecretKey secretKey) throws Exception {

        Cipher cipher = Cipher.getInstance(AES);

        // Initialize cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey
        );

        // Decode Base64 to bytes
        byte[] decodedBytes =
                Base64
                        .getDecoder()
                        .decode(ciphertext);

        // Decrypt the bytes
        byte[] decryptedBytes =
                cipher.doFinal(decodedBytes);

        return new String(decryptedBytes);
    }
}