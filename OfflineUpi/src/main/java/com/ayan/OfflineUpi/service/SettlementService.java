package com.ayan.OfflineUpi.service;

import com.ayan.OfflineUpi.model.Account;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto
        .bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

// Service for managing accounts and settling transactions
@Service
public class SettlementService {

    // Password encoder for hashing pins
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Map of user accounts
    private final Map<String, Account> accounts =
            new HashMap<>();

    // Visible pins for demo purposes
    private final Map<String, String> visiblePins =
            new HashMap<>();

    // Default constructor
    public SettlementService() {

    }

    // Initializes demo accounts
    @PostConstruct
    public void initAccounts() {

        // Set up Alice's account
        Account alice = new Account();

        alice.setUsername("alice");

        alice.setBalance(5000);

        alice.setPinHash(
                passwordEncoder.encode("1234")
        );

        accounts.put(
                "alice",
                alice
        );

        visiblePins.put(
                "alice",
                "1234"
        );

        // Set up Bob's account
        Account bob = new Account();

        bob.setUsername("bob");

        bob.setBalance(3000);

        bob.setPinHash(
                passwordEncoder.encode("5678")
        );

        accounts.put(
                "bob",
                bob
        );

        visiblePins.put(
                "bob",
                "5678"
        );

        // Set up Charlie's account
        Account charlie = new Account();

        charlie.setUsername("charlie");

        charlie.setBalance(7000);

        charlie.setPinHash(
                passwordEncoder.encode("1111")
        );

        accounts.put(
                "charlie",
                charlie
        );

        visiblePins.put(
                "charlie",
                "1111"
        );

        // Set up David's account
        Account david = new Account();

        david.setUsername("david");

        david.setBalance(9000);

        david.setPinHash(
                passwordEncoder.encode("2222")
        );

        accounts.put(
                "david",
                david
        );

        visiblePins.put(
                "david",
                "2222"
        );

        // Set up Eva's account
        Account eva = new Account();

        eva.setUsername("eva");

        eva.setBalance(6500);

        eva.setPinHash(
                passwordEncoder.encode("3333")
        );

        accounts.put(
                "eva",
                eva
        );

        visiblePins.put(
                "eva",
                "3333"
        );
    }

    // Verifies the user's PIN
    public boolean verifyPin(
            String username,
            String enteredPin
    ) {

        Account account =
                accounts.get(username);

        if(account == null) {

            return false;
        }

        return passwordEncoder.matches(
                enteredPin,
                account.getPinHash()
        );
    }

    // Settles the transaction
    public boolean settleTransaction(
            String sender,
            String receiver,
            double amount
    ) {

        Account senderAccount =
                accounts.get(sender);

        Account receiverAccount =
                accounts.get(receiver);

        // Check if accounts exist
        if(senderAccount == null
                || receiverAccount == null) {

            return false;
        }

        // Check sufficient balance
        if(senderAccount.getBalance() < amount) {

            return false;
        }

        // Deduct from sender
        senderAccount.setBalance(
                senderAccount.getBalance()
                        - amount
        );

        // Add to receiver
        receiverAccount.setBalance(
                receiverAccount.getBalance()
                        + amount
        );

        return true;
    }

    // Returns account data for UI
    public Map<String, Object> getAccounts() {

        Map<String, Object> response =
                new HashMap<>();

        for(String username :
                accounts.keySet()) {

            Account account =
                    accounts.get(username);

            Map<String, Object> details =
                    new HashMap<>();

            details.put(
                    "balance",
                    account.getBalance()
            );

            details.put(
                    "pin",
                    visiblePins.get(username)
            );

            response.put(
                    username,
                    details
            );
        }

        return response;
    }
}