package com.ayan.OfflineUpi.model;

import lombok.Data;

// Model for transaction requests
@Data
public class TransactionRequest {
    private String sender;
    private String receiver;
    private double amount;
    // User's PIN for the transaction
    private String pin;
}
