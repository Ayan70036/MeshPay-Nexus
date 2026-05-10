package com.ayan.OfflineUpi.model;

import lombok.Data;

// Represents a user account
@Data
public class Account {

    // Username of the account holder
    private String username;

    // Current balance in the account
    private double balance;
    // Hashed PIN for security
    private String pinHash;
}